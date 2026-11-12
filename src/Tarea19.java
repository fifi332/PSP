//Un parking tiene 3 plazas y llegan 8 coches. Cada coche entra si hay sitio, está aparcado un tiempo aleatorio y sale. Si el parking está lleno, espera a que salga otro. Muestra cada entrada y salida con las plazas libres que quedan. Utiliza Semaphore

import java.util.concurrent.Semaphore;

class Coche extends Thread {
    private final Semaphore plazas;

    public Coche(String nombre, Semaphore plazas) {
        super(nombre);
        this.plazas = plazas;
    }

    @Override
    public void run() {
        try {
// Espera si el parking está lleno (0 plazas disponibles)
            plazas.acquire();

            try {
// Al entrar, mostramos las plazas que quedan libres tras ocupar una
                System.out.println(getName() + " entra al parking. Plazas libres: " + plazas.availablePermits());

// Tiempo aleatorio aparcado: entre 1000 ms y 3000 ms (1 a 3 segundos)
                int tiempoAparcado = (int) (Math.random() * 2000) + 1000;
                Thread.sleep(tiempoAparcado);

            } finally {
// Se ejecuta siempre al salir, liberando la plaza
                plazas.release();
                System.out.println(getName() + " sale del parking. Plazas libres: " + plazas.availablePermits());
            }

        } catch (InterruptedException e) {
            System.err.println(getName() + " fue interrumpido.");
        }
    }
}

public class Tarea19 {
    public static void main(String[] args) {
// Semáforo con 3 permisos (las 3 plazas)
// El valor 'true' garantiza que la cola de espera sea justa (FIFO)
        Semaphore plazas = new Semaphore(3, true);

// Creamos y arrancamos los 8 coches
        for (int i = 1; i <= 8; i++) {
            Coche coche = new Coche("Coche-" + i, plazas);
            coche.start();
        }
    }
}