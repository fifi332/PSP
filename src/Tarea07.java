//Crea un hilo que calcule los primeros N términos de la sucesión de Fibonacci. La sucesión empieza por 1 y 1, y cada término es la suma de los dos anteriores: 1, 1, 2, 3, 5, 8, 13, 21, 34, 55…
//
//
//• N se pasa al constructor de la clase del hilo.
//• El hilo principal debe esperar a que termine el cálculo con join() y después escribir «Cálculo terminado.».
//
//Pista: Diapositivas 11 a 20

public class Tarea07 {

    static class Fibonacci extends Thread {
        private final int n;

        public Fibonacci(int n) {
            super("Fibonacci-" + n);
            this.n = n;
        }

        @Override
        public void run() {
            long anterior = 1, actual = 1;
            for (int i = 1; i <= n; i++) {
                if (i <= 2) {
                    System.out.println(getName() + " → término " + i + ": 1");
                    continue;
                }
                long siguiente = anterior + actual;
                anterior = actual;
                actual = siguiente;
                System.out.println(getName() + " → término " + i + ": " + actual);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = (args.length > 0) ? Integer.parseInt(args[0]) : 20;

        Fibonacci fib = new Fibonacci(n);
        fib.start();
        fib.join();                               // main espera al hilo
        System.out.println("Cálculo terminado.");
    }
}