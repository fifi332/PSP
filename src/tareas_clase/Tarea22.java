package tareas_clase;//Haz un programa que simule 3 cocineros (hilos) y 10 pedidos (tareas): nunca hay más de 3 pizzas a la vez. Utiliza pool de hilos

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Pedido implements Runnable {
    private final int numero;

    public Pedido(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        String cocinero = Thread.currentThread().getName();
        System.out.println(cocinero + " prepara el pedido " + numero);
        try {
            Thread.sleep(1000); // Simula el tiempo en preparar la pizza
        } catch (InterruptedException e) {
            System.err.println(cocinero + " fue interrumpido.");
        }
    }
}

public class Tarea22 {
    public static void main(String[] args) throws InterruptedException {
// Pool de 3 hilos (3 cocineros fijos)
        ExecutorService cocina = Executors.newFixedThreadPool(3);

// Se envían 10 pedidos (tareas)
        for (int i = 1; i <= 10; i++) {
            cocina.execute(new Pedido(i));
        }

// Cierre ordenado: no acepta más tareas nuevas
        cocina.shutdown();

// Espera a que los 3 cocineros terminen de preparar todos los pedidos pendientes
        cocina.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Todos los pedidos han sido preparados.");
    }
}