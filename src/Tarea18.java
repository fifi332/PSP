// Coge la tarea 13 resuelta y rehazla utilizando unicamente AtomicInteger para el contador. Sin synchronized. Compara el tiempo de ejecución de una forma y otra.

import java.util.concurrent.atomic.AtomicInteger;

// ==========================================
// OPCIÓN 1: Contador original con SYNCHRONIZED
// ==========================================
class ContadorSynchronized {
    int valor = 0;

    synchronized void incrementar() {
        valor++;
    }
}

class HiloSynchronized extends Thread {
    ContadorSynchronized contador;

    HiloSynchronized(ContadorSynchronized c) {
        this.contador = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            contador.incrementar();
        }
    }
}

// ==========================================
// OPCIÓN 2: Contador con VARIABLE ATÓMICA
// ==========================================
class ContadorAtomico {
    // Variable atómica con valor inicial 0
    AtomicInteger valor = new AtomicInteger(0);

    void incrementar() {
// Operación atómica directa a nivel de hardware (sin bloqueos)
        valor.incrementAndGet();
    }
}

class HiloAtomico extends Thread {
    ContadorAtomico contador;

    HiloAtomico(ContadorAtomico c) {
        this.contador = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            contador.incrementar();
        }
    }
}

// ==========================================
// CLASE PRINCIPAL: Tarea18 (Comparativa)
// ==========================================
public class Tarea18 {

    public static void main(String[] args) throws Exception {

// -------------------------------------------------------------
// PRUEBA 1: Con synchronized
// -------------------------------------------------------------
        ContadorSynchronized cSync = new ContadorSynchronized();
        HiloSynchronized[] hilosSync = new HiloSynchronized[5];

        for (int i = 0; i < 5; i++) {
            hilosSync[i] = new HiloSynchronized(cSync);
        }

        long inicioSync = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            hilosSync[i].start();
        }
        for (int i = 0; i < 5; i++) {
            hilosSync[i].join();
        }

        long finSync = System.currentTimeMillis();
        long tiempoSync = finSync - inicioSync;

        System.out.println("--- RESULTADOS SYNCHRONIZED ---");
        System.out.println("Valor final: " + cSync.valor);
        System.out.println("Tiempo: " + tiempoSync + " ms\n");

// -------------------------------------------------------------
// PRUEBA 2: Con AtomicInteger
// -------------------------------------------------------------
        ContadorAtomico cAtom = new ContadorAtomico();
        HiloAtomico[] hilosAtom = new HiloAtomico[5];

        for (int i = 0; i < 5; i++) {
            hilosAtom[i] = new HiloAtomico(cAtom);
        }

        long inicioAtom = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            hilosAtom[i].start();
        }
        for (int i = 0; i < 5; i++) {
            hilosAtom[i].join();
        }

        long finAtom = System.currentTimeMillis();
        long tiempoAtom = finAtom - inicioAtom;

        System.out.println("--- RESULTADOS ATOMIC INTEGER ---");
        System.out.println("Valor final: " + cAtom.valor.get());
        System.out.println("Tiempo: " + tiempoAtom + " ms\n");

// -------------------------------------------------------------
// DIFERENCIA
// -------------------------------------------------------------
        System.out.println("Diferencia de tiempo: " + Math.abs(tiempoSync - tiempoAtom) + " ms");
    }
}