//Haz un programa que cree cuatro hilos y los ejecute de manera simultánea, sin esperar a que terminen. Cada hilo representa a un profesor cuyo «nivel de cabreo» aumenta en 1 por cada tarea que descubre copiada y pegada de una IA.
//
//Al crear cada hilo se le pasa el nombre del profesor y su «límite de paciencia»: el número máximo de trabajos que va a revisar. El nombre del profesor tiene que ser el nombre del hilo.
//
//Crea dos profesores heredando de Thread y otros dos implementando Runnable. Al ejecutarlo, las quejas de los cuatro se mezclarán en la consola.
//
//Ejemplo de salida:
//
//[Diego] Cabreo nivel: 1
//[Manuel] Cabreo nivel: 1
//Programa principal terminado.
//[Damian] Cabreo nivel: 1
//[Araujo] Cabreo nivel: 1
//[Diego] Cabreo nivel: 2
//[Damian] Cabreo nivel: 2
//[Manuel] Cabreo nivel: 2
//[Araujo] Cabreo nivel: 2
//[Damian] Cabreo nivel: 3... ¡He llegado a mi límite!
//[Araujo] Cabreo nivel: 3
//[Diego] Cabreo nivel: 3
//[Manuel] Cabreo nivel: 3
//[Diego] Cabreo nivel: 4... ¡He llegado a mi límite!
//[Manuel] Cabreo nivel: 4
//[Araujo] Cabreo nivel: 4
//[Araujo] Cabreo nivel: 5... ¡He llegado a mi límite!
//[Manuel] Cabreo nivel: 5... ¡He llegado a mi límite!
//
//Pista: Diapositivas 11 a 18


public class Tarea06 {

    /** Forma 1: la clase ES un hilo. */
    static class Profesor extends Thread {
        private final int limitePaciencia;

        public Profesor(String nombre, int limitePaciencia) {
            super(nombre);                       // nombre del hilo
            this.limitePaciencia = limitePaciencia;
        }

        @Override
        public void run() {
            for (int cabreo = 1; cabreo <= limitePaciencia; cabreo++) {
                String msg = "[" + getName() + "] Cabreo nivel: " + cabreo;
                if (cabreo == limitePaciencia) {
                    msg += "... ¡He llegado a mi límite!";
                }
                System.out.println(msg);
            }
        }
    }

    /** Forma 2: la clase es una TAREA que ejecuta un hilo. */
    static class ProfesorTarea implements Runnable {
        private final int limitePaciencia;

        public ProfesorTarea(int limitePaciencia) {
            this.limitePaciencia = limitePaciencia;
        }

        @Override
        public void run() {
            // En un Runnable no existe getName(): preguntamos al hilo actual
            String nombre = Thread.currentThread().getName();
            for (int cabreo = 1; cabreo <= limitePaciencia; cabreo++) {
                String msg = "[" + nombre + "] Cabreo nivel: " + cabreo;
                if (cabreo == limitePaciencia) {
                    msg += "... ¡He llegado a mi límite!";
                }
                System.out.println(msg);
            }
        }
    }

    public static void main(String[] args) {
        new Profesor("Diego", 4).start();
        new Profesor("Manuel", 5).start();
        new Thread(new ProfesorTarea(3), "Damian").start();
        new Thread(new ProfesorTarea(5), "Araujo").start();

        // main NO espera: este mensaje puede salir en cualquier posición
        System.out.println("Programa principal terminado.");
    }
}