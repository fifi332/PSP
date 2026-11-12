//Reescribe la tarea 12 para que esté sincronizada y el contador llegue siempre a 500.000 . Ejecútalo cinco veces para comprobarlo.

//class Contador {
//    int valor = 0;
//
//    synchronized void incrementar() {
//        valor++;
//    }
//}

//class Hilo extends Thread {
//    Contador contador;
//
//    Hilo(Contador c) {
//        contador = c;
//    }
//
//    public void run() {
//        for (int i = 0; i < 100_000; i++) {
//            contador.incrementar();
//        }
//    }
//}
//
//public class Tarea13 {
//    public static void main(String[] args) throws Exception {
//
//        Contador c = new Contador();
//
//        Hilo h1 = new Hilo(c);
//        Hilo h2 = new Hilo(c);
//        Hilo h3 = new Hilo(c);
//        Hilo h4 = new Hilo(c);
//        Hilo h5 = new Hilo(c);
//
//        h1.start();
//        h2.start();
//        h3.start();
//        h4.start();
//        h5.start();
//
//        h1.join();
//        h2.join();
//        h3.join();
//        h4.join();
//        h5.join();
//
//        System.out.println(c.valor);
//    }
//}