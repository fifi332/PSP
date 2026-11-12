package tareas;

class Contadortotal{
    private int total=0;

    public synchronized void sumarCajas(int cantidad){
        this.total += cantidad;
    }
    public synchronized int getTotalCajas(){
        return total;
    }
}

class Cinta implements Runnable{
    private final Contadortotal contadorCompartido;
    private int cajasPorEstaCinta = 0;

    Cinta(Contadortotal contadorCompartido) {
        this.contadorCompartido = contadorCompartido;
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();

        int TotalProcesar = (int)(Math.random()*200)+100;

        for (int i=1;i<=TotalProcesar;i++) {
            cajasPorEstaCinta++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Hilo interrumpido: "+e.getMessage());
            }
        }

        contadorCompartido.sumarCajas(cajasPorEstaCinta);
        System.out.println(nombre + ": " + cajasPorEstaCinta + " entradas");
    }
    public int getCajasPorEstaCinta() {
        return cajasPorEstaCinta;
    }
}

public class MainR1{
    public static void main(String[] args) throws InterruptedException {
        Contadortotal contadorGlobal = new Contadortotal();

        Cinta cinta1 = new Cinta(contadorGlobal);
        Cinta cinta2 = new Cinta(contadorGlobal);
        Cinta cinta3 = new Cinta(contadorGlobal);

        Thread h1 = new Thread(cinta1,"Torno1");
        Thread h2 = new Thread(cinta2,"Torno2");
        Thread h3 = new Thread(cinta3,"Torno3");


        h1.start();
        h2.start();
        h3.start();


        h1.join();
        h2.join();
        h3.join();

        int sumaInd = cinta1.getCajasPorEstaCinta() + cinta2.getCajasPorEstaCinta() + cinta3.getCajasPorEstaCinta();

        System.out.println("Marcador: " + contadorGlobal.getTotalCajas());
        System.out.println("Suma: " + sumaInd);
    }
}