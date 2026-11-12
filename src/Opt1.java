//Un buzón guarda un único mensaje. Dos hilos escritores mandan 3 mensajes cada uno y dos hilos lectores leen 3 cada uno. Un escritor solo puede escribir si el buzón está vacío y un lector solo puede leer si tiene un mensaje; si no, esperan. Muestra por pantalla quién escribe, quién lee y quién espera.

/** OPT1 · Buzón compartido [OPTATIVA]: un solo mensaje a la vez. */
class Buzon {
    private String mensaje = null;

    public synchronized void escribir(String m) throws InterruptedException {
        while (mensaje != null) {                 // lleno: espero
            System.out.println("  " + Thread.currentThread().getName() + " espera: buzón lleno");
            wait();
        }
        mensaje = m;
        System.out.println(Thread.currentThread().getName() + " escribe: " + m);
        notifyAll();
    }

    public synchronized String leer() throws InterruptedException {
        while (mensaje == null) {                 // vacío: espero
            System.out.println("  " + Thread.currentThread().getName() + " espera: buzón vacío");
            wait();
        }
        String m = mensaje;
        mensaje = null;
        System.out.println(Thread.currentThread().getName() + " lee: " + m);
        notifyAll();
        return m;
    }
}

class Escritor extends Thread {
    private final Buzon buzon;
    public Escritor(String nombre, Buzon buzon) { super(nombre); this.buzon = buzon; }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 3; i++) {
                buzon.escribir("mensaje " + i + " de " + getName());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Lector extends Thread {
    private final Buzon buzon;
    public Lector(String nombre, Buzon buzon) { super(nombre); this.buzon = buzon; }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 3; i++) {
                buzon.leer();
                Thread.sleep(250);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Opt1 {
    public static void main(String[] args) throws InterruptedException {
        Buzon buzon = new Buzon();
        Thread[] hilos = {
                new Escritor("Escritor-1", buzon), new Escritor("Escritor-2", buzon),
                new Lector("Lector-1", buzon), new Lector("Lector-2", buzon)
        };
        for (Thread h : hilos) h.start();
        for (Thread h : hilos) h.join();
        System.out.println("Todos los mensajes entregados.");
    }
}