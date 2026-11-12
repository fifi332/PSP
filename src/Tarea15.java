// Crea 3 hilos que escriben 8 veces su nombre y el número de iteración, durmiendo un poco en cada vuelta. Se arrancan los tres a la vez, pero deben ejecutarse en orden inverso: primero el 3, luego el 2 y por último el 1. El main no termina hasta que acaban los tres y entonces avisa de que ha terminado.

/** Tarea 15 · Orden secuencial inverso: el 3 termina antes que el 2 y el 2 antes que el 1. */
class Trabajador extends Thread {
    private final int numero;
    private final Thread siguiente;     // el hilo al que hay que esperar (null para el 3)

    public Trabajador(int numero, Thread siguiente) {
        super("Hilo " + numero);
        this.numero = numero;
        this.siguiente = siguiente;
    }

    @Override
    public void run() {
        try {
            if (siguiente != null) {
                siguiente.join();       // espero a que acabe el siguiente
            }
            for (int i = 1; i <= 8; i++) {
                System.out.println("Soy el " + getName() + " - iteración: " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Tarea15 {
    public static void main(String[] args) throws InterruptedException {
        Trabajador h3 = new Trabajador(3, null);
        Trabajador h2 = new Trabajador(2, h3);
        Trabajador h1 = new Trabajador(1, h2);

        h1.start();                     // se arrancan los tres a la vez
        h2.start();
        h3.start();

        h1.join();                      // el 1 es el último en terminar
        System.out.println("Programa principal terminado.");
    }
}