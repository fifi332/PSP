package tareas_clase;//Haz un programa en Java que cree cuatro hilos y los ejecute. Cada hilo debe:
//
//Escribir diez veces el número de iteración y su nombre.
//Después de escribir su nombre, bloquearse durante un tiempo aleatorio de entre 1 y 10 segundos.
//Volver a estar disponible para su ejecución después del tiempo de espera.
//Prueba también lo siguiente:
//
//Asigna diferentes prioridades a los hilos (por ejemplo, un hilo con prioridad mínima, dos con prioridad media y uno con prioridad máxima).
//Analiza si los hilos con mayor prioridad tienden a ejecutarse más frecuentemente que los de menor prioridad, aunque el tiempo de espera aleatorio puede afectar el resultado.
//Imprime en cada iteración:
//Nombre del hilo: <nombre>, Iteración: <número>, Prioridad: <prioridad>
//Compara los resultados y fíjate si la prioridad afecta la planificación de los hilos en Java.

public class Tarea11 {

    public static void main(String[] args) {

        HiloConPrioridad hiloBaja = new HiloConPrioridad("Hilo PRIORIDAD BAJA", Thread.MIN_PRIORITY);
        HiloConPrioridad hiloNormal1 = new HiloConPrioridad("Hilo NORMAL 1", Thread.NORM_PRIORITY);
        HiloConPrioridad hiloNormal2 = new HiloConPrioridad("Hilo NORMAL 2", Thread.NORM_PRIORITY);
        HiloConPrioridad hiloAlta = new HiloConPrioridad("Hilo PRIORIDAD ALTA", Thread.MAX_PRIORITY);


        hiloBaja.start();
        hiloNormal1.start();
        hiloNormal2.start();
        hiloAlta.start();
    }
}


class HiloConPrioridad extends Thread {
    private String nombre;

    public HiloConPrioridad(String nombre, int prioridad) {
        super(nombre);
        this.nombre = nombre;
        setPriority(prioridad);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {

            System.out.println(nombre + " - Iteración: " + i + ", Prioridad: " + getPriority());

            // Dormir un tiempo aleatorio entre 1 y 3 segundos para ver el efecto más rápido
            int tiempoEspera = 1000 + (int)(Math.random() * 2000); // ms
            try {
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                System.out.println(nombre + " fue interrumpido.");
                break;
            }
        }
        System.out.println(nombre + " ha terminado.");
    }
}