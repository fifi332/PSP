//Un parking tiene 3 plazas y llegan 8 coches. Cada coche entra si hay sitio, está aparcado un tiempo aleatorio y sale. Si el parking está lleno, espera a que salga otro. Muestra cada entrada y salida con las plazas libres que quedan

/** OPT2 · Parking [OPTATIVA]: N plazas, los coches esperan si está lleno. */
//class Parking {
//    private int libres;
//
//    public Parking(int plazas) { this.libres = plazas; }
//
//    public synchronized void entrar() throws InterruptedException {
//        while (libres == 0) {
//            System.out.println("  " + Thread.currentThread().getName() + " espera: parking lleno");
//            wait();
//        }
//        libres--;
//        System.out.println(Thread.currentThread().getName() + " entra. Plazas libres: " + libres);
//    }
//
//    public synchronized void salir() {
//        libres++;
//        System.out.println(Thread.currentThread().getName() + " sale. Plazas libres: " + libres);
//        notifyAll();
//    }
//}
//
//class Coche extends Thread {
//    private final Parking parking;
//    public Coche(int n, Parking parking) { super("Coche-" + n); this.parking = parking; }
//
//    @Override
//    public void run() {
//        try {
//            parking.entrar();
//            Thread.sleep(500 + (long) (Math.random() * 1000));   // tiempo aparcado
//            parking.salir();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//}
//
//public class Opt2 {
//    public static void main(String[] args) throws InterruptedException {
//        Parking parking = new Parking(3);
//        Coche[] coches = new Coche[8];
//        for (int i = 0; i < coches.length; i++) {
//            coches[i] = new Coche(i + 1, parking);
//            coches[i].start();
//        }
//        for (Coche c : coches) c.join();
//        System.out.println("Todos los coches han salido.");
//    }
//}