//Escribe un programa que pida por teclado 5 consonantes y un texto. Cada consonante tendrá un hilo que cuenta cuántas veces aparece una de las consonantes y suma sus apariciones a un contador total compartido. Al final se muestra lo que ha contado cada hilo y el total.
//
//Para que se note el problema, repite el texto 2.000 veces: texto.repeat(2000).
//
//Si prefieres, no escribir el texto puede leer de un fichero (SOLO SI QUIERES)

import java.util.Scanner;

/**
 * Tarea 16 · Contar consonantes con 5 hilos
 */
class Total {
    private int valor = 0;

    public synchronized void incrementar() {      // sin synchronized se pierden incrementos
        valor++;
    }

    public synchronized int getValor() {
        return valor;
    }
}

class ContadorConsonante extends Thread {
    private final String texto;
    private final char consonante;
    private final Total total;
    private int propias = 0;

    public ContadorConsonante(String texto, char consonante, Total total) {
        super("Hilo-" + consonante);
        this.texto = texto;
        this.consonante = consonante;
        this.total = total;
    }

    @Override
    public void run() {
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == consonante) {
                propias++;                       // solo la toca este hilo
                total.incrementar();             // compartida: sincronizada
            }
        }
    }

    public int getPropias() { return propias; }
    public char getConsonante() { return consonante; }
}

public class Tarea17 {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        char[] consonantes = new char[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Consonante " + (i + 1) + ": ");
            consonantes[i] = Character.toLowerCase(sc.nextLine().trim().charAt(0));
        }
        System.out.print("Texto: ");
        String texto = sc.nextLine().toLowerCase().repeat(2000);   // para que se note la carrera

        Total total = new Total();
        ContadorConsonante[] hilos = new ContadorConsonante[5];
        for (int i = 0; i < 5; i++) {
            hilos[i] = new ContadorConsonante(texto, consonantes[i], total);
            hilos[i].start();
        }

        int suma = 0;
        for (ContadorConsonante h : hilos) {
            h.join();
            System.out.println("Letra '" + h.getConsonante() + "': " + h.getPropias());
            suma += h.getPropias();
        }
        System.out.println("Total compartido: " + total.getValor());
        System.out.println("Suma de cada hilo: " + suma + "  (deben coincidir)");
    }
}