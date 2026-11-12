package tareas_clase;//Realiza un programa en Java que ejecute tres hilos de forma concurrente:
//
//uno suma los números pares del 1 al 1923;
//otro suma los impares;
//otro suma los que terminan en 3 o en 4.
//Cada hilo guarda su resultado. Cuando los tres hayan terminado, el main muestra los tres resultados. Es decir:
//
//"Suma de pares... x"
//"Suma de impares... y"
//"suma de terminados en 3 o en 4... z"
//
//Intenta no repetir código: una sola clase de hilo que reciba qué tipo de suma hacer.



public class Tarea10 {

    enum Tipo { PARES, IMPARES, TERMINAN_EN_3_O_4 }

    static class Sumador extends Thread {
        private final Tipo tipo;
        private final int limite;
        private long resultado;

        public Sumador(Tipo tipo, int limite) {
            super("Sumador-" + tipo);
            this.tipo = tipo;
            this.limite = limite;
        }

        @Override
        public void run() {
            for (int i = 1; i <= limite; i++) {
                if (cumple(i)) resultado += i;
            }
        }

        private boolean cumple(int i) {
            switch (tipo) {
                case PARES:   return i % 2 == 0;
                case IMPARES: return i % 2 != 0;
                default:      return i % 10 == 3 || i % 10 == 4;
            }
        }

        public long getResultado() { return resultado; }
    }

    public static void main(String[] args) throws InterruptedException {
        final int LIMITE = 1923;
        Sumador pares = new Sumador(Tipo.PARES, LIMITE);
        Sumador impares = new Sumador(Tipo.IMPARES, LIMITE);
        Sumador tresCuatro = new Sumador(Tipo.TERMINAN_EN_3_O_4, LIMITE);

        pares.start();
        impares.start();
        tresCuatro.start();

        pares.join();                  // los resultados solo son válidos
        impares.join();                // después de join()
        tresCuatro.join();

        System.out.println("Suma de pares:                   " + pares.getResultado());
        System.out.println("Suma de impares:                 " + impares.getResultado());
        System.out.println("Suma de los que acaban en 3 o 4: " + tresCuatro.getResultado());

        long esperado = (long) LIMITE * (LIMITE + 1) / 2;
        System.out.println("Comprobación pares + impares = " + esperado + " → "
                + (pares.getResultado() + impares.getResultado() == esperado ? "OK" : "ERROR"));
    }
}
