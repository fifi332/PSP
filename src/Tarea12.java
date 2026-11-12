//Crea un programa en Java que trabaje con varios hilos y un contador compartido.
//
//El programa debe incluir:
//
//Una clase Contador que almacene el valor del contador y tenga un método para incrementarlo.
//
//Una clase Hilo que herede de Thread y reciba un objeto Contador.
//
//El programa principal deberá crear un único objeto Contador y compartirlo entre cinco hilos.
//
//Cada hilo deberá incrementar el contador 100.000 veces dentro del bucle.
//
//El programa principal deberá esperar a que los cinco hilos terminen antes de mostrar el valor final del contador.
//
//Ejecuta el programa varias veces y comprueba el resultado.


//class Contador {
//    int valor = 0;
//
//    void incrementar() {
//        valor++;
//    }
//}
//
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
//public class Tarea12 {
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