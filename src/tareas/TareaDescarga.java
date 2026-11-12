package tareas;

class Tareas implements Runnable {
    private String nombreArchivo;

    public Tareas(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }


    @Override
    public void run() {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Iniciando descarga de "+nombreHilo);

        for(int i=1;i<=5;i++){
            int porcentaje = i *20;
            System.out.println("Descarga: "+porcentaje+"%");

            try{
                Thread.sleep(500);
            }   catch (InterruptedException e){
                System.err.println("Hilo interrumpido "+ e.getMessage());
            }
        }
        System.out.println("Descarga completada");
    }

}

public class TareaDescarga {
    static void main(String[] args) {
        System.out.println("Iniciando Descargas");

        Tareas tarea1 = new Tareas("Video.mp4");
        Tareas tarea2 = new Tareas("Musica.mp3");
        Tareas tarea3 = new Tareas("Documento.pdf");

        Thread hilo1 = new Thread(tarea1, "Hilo-Video");
        Thread hilo2 = new Thread(tarea2, "Hilo-Musica");
        Thread hilo3 = new Thread(tarea3, "Hilo-Documento");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        }catch (InterruptedException e){
            System.err.println("Error esperando hilos "+ e.getMessage());
        }
        System.out.println("\nTodas las descargas terminaron");
    }
}