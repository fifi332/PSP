//Crea una clase Caja con public static double capital = 1000.0; y dos hilos:
//
//Ingresos: 5.000 ventas de 10 €.
//Extracciones: 3.000 pagos de 10 €.
//En cada operación, en este orden: guarda el saldo en una variable local (double saldo = Caja.capital;), espera 1 ms (Thread.sleep(1)) y escribe Caja.capital = saldo + 10; (o - 10). El main lanza los dos hilos, espera con join() y muestra el saldo final.
//
//Utiliza synchronized.

/** Siempre 21000.0 */
class Caja {
    private static double capital = 1000.0;

    // static synchronized: el candado es la CLASE Caja, el mismo para los dos hilos
    public static synchronized void operar(double cantidad) throws InterruptedException {
        double saldo = capital;
        Thread.sleep(1);
        capital = saldo + cantidad;
    }

    public static synchronized double getCapital() {
        return capital;
    }
}

class Ingresos extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 5000; i++) {
                Caja.operar(10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Extracciones extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 3000; i++) {
                Caja.operar(-10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Tarea16 {
    public static void main(String[] args) throws InterruptedException {
        long inicio = System.currentTimeMillis();
        Ingresos ingresos = new Ingresos();
        Extracciones extracciones = new Extracciones();
        ingresos.start();
        extracciones.start();
        ingresos.join();
        extracciones.join();
        System.out.println("Saldo final: " + Caja.getCapital());
        System.out.println("Tiempo: " + (System.currentTimeMillis() - inicio) + " ms");
    }
}