class Tare implements Runnable{
    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();

        for (int i = 1; i<=8;i++){
            System.out.println("Soy el " + nombre +" Iteracion: "+i);
        }
    }
}

public class Ejemplo {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new Tare(),"Hilo 1");
        Thread b = new Thread(new Tare(),"Hilo 2");
        Thread c = new Thread(new Tare(),"Hilo 3");


        c.start();
        c.join();

        b.start();
        b.join();

        a.start();
        a.join();

        System.out.println("Programa principal terminado");
    }
}