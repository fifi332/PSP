package tareas_clase;//Haz un programa en Java que cree cuatro hilos y los ejecute. Cada hilo escribe diez veces el número de iteración y su nombre. En cada iteración, después de escribir, se bloquea durante un tiempo aleatorio entre 500 y 2000 ms y después vuelve a estar disponible para ejecutarse.
//
//• El hilo principal debe esperar a que terminen los cuatro: primero se arrancan todos y después se espera a cada uno.
//• Al final, el hilo principal muestra «Han terminado todos» y el tiempo total en segundos (usa System.currentTimeMillis()).
//
//Fíjate en:
//
//El tiempo total, ¿se parece a la suma de lo que ha dormido cada hilo o a lo que ha dormido el más lento? ¿Por qué?
//¿Qué pasa con el tiempo total si haces start() y join() dentro del mismo bucle? Compruébalo.
//Pista: Diapositivas 19 a 23
//
// Respuestas:
//
//1. Al del más lento (unos 12–15 s en las pruebas), no a la suma (unos 50 s). Los cuatro duermen a la vez.
//2. Pasa a ser la suma: cada join() bloquea a main antes de que arranque el siguiente hilo, así que se ejecutan uno detrás de otro.
//
//Código:


import java.util.Random;

public class Tarea08 {

    static class Dormilon implements Runnable {
        private final Random random = new Random();

        @Override
        public void run() {
            String nombre = Thread.currentThread().getName();
            for (int i = 1; i <= 10; i++) {
                System.out.println("Iteración " + i + " - " + nombre);
                try {
                    Thread.sleep(500 + random.nextInt(1501));   // entre 500 y 2000 ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println(nombre + " ha terminado");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long inicio = System.currentTimeMillis();

        Thread[] hilos = new Thread[4];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new Dormilon(), "Hilo-" + (i + 1));
            hilos[i].start();                     // 1º: arrancar TODOS
        }
        for (Thread h : hilos) {
            h.join();                             // 2º: esperar a TODOS
        }

        long total = System.currentTimeMillis() - inicio;
        System.out.println("Han terminado todos en " + total / 1000.0 + " s");
    }
}