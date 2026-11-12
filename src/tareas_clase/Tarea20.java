package tareas_clase;//Rehaz el ejemplo de productor-consumidor con una cola

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Productor extends Thread {
    private final BlockingQueue<String> almacen;

    public Productor(BlockingQueue<String> almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                String p = "Producto #" + i;
// Espera automáticamente si la cola está llena
                almacen.put(p);
                System.out.println("tareas_clase.Productor puso: " + p);
// Simulamos el tiempo que tarda el productor en fabricar el siguiente producto
// se produce más rápido de lo que se consume
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.err.println("tareas_clase.Productor interrumpido.");
        }
    }
}

class Consumidor extends Thread {
    private final BlockingQueue<String> almacen;

    public Consumidor(BlockingQueue<String> almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
// Espera automáticamente si la cola está vacía
                String p = almacen.take();
                System.out.println("tareas_clase.Consumidor sacó: " + p);
// Simulamos el tiempo que tarda el consumidor en connsumir el producto
// Al ser más lento, provoca que el almacén se llene y el productor tenga que esperar
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            System.err.println("tareas_clase.Consumidor interrumpido.");
        }
    }
}

public class Tarea20 {
    public static void main(String[] args) {
// La cola con capacidad 1 actúa como almacén sincronizado
        BlockingQueue<String> almacen = new ArrayBlockingQueue<>(1);

        Productor productor = new Productor(almacen);
        Consumidor consumidor = new Consumidor(almacen);

        productor.start();
        consumidor.start();
    }
}