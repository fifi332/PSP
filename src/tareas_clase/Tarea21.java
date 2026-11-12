package tareas_clase;//Rehaz la tarea 20 con un pool de hilos

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// Cada consonante es una tarea (un Runnable), no un Thread
class ContarConsonante implements Runnable {
    private final String texto;
    private final char consonante;
    private final AtomicInteger total;

    public ContarConsonante(String texto, char consonante, AtomicInteger total) {
        this.texto = texto;
        this.consonante = consonante;
        this.total = total;
    }

    @Override
    public void run() {
        int propias = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == consonante) {
                propias++;
            }
        }
// Sumamos al contador compartido de forma atómica y segura
        total.addAndGet(propias);
        System.out.println("Letra '" + consonante + "': " + propias);
    }
}

public class Tarea21 {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        char[] consonantes = new char[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Consonante " + (i + 1) + ": ");
            consonantes[i] = Character.toLowerCase(sc.nextLine().trim().charAt(0));
        }

        System.out.print("Texto: ");
        String texto = sc.nextLine().toLowerCase().repeat(2000);

// 1. Contador atómico compartido
        AtomicInteger total = new AtomicInteger(0);

// 2. Pool de 5 hilos
        ExecutorService pool = Executors.newFixedThreadPool(5);

// 3. Enviamos cada tarea al pool
        for (char c : consonantes) {
            pool.execute(new ContarConsonante(texto, c, total));
        }

// 4. Cierre ordenado del pool
        pool.shutdown(); // No acepta más tareas
        pool.awaitTermination(10, TimeUnit.SECONDS); // Espera a que acaben todas las tareas

// 5. Mostramos el resultado acumulado
        System.out.println("tareas_clase.Total: " + total.get());
    }
}