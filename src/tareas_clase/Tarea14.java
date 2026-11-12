package tareas_clase;

//Antes de ejecutarlo, apunta qué imprimirá cada línea marcada con (1), (2) y (3) y qué mensaje del cocinero aparece. Después ejecútalo y comprueba si acertaste.
//
//Apuntes, páginas: 24–25 y 33–36
//
class Cocinero extends Thread {

    public Cocinero() {
        super("tareas_clase.Cocinero");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("[tareas_clase.Cocinero] El guiso está listo");
        } catch (InterruptedException e) {
            System.out.println("[tareas_clase.Cocinero] ¡Me han cortado la luz!");
        }
    }
}

public class Tarea14 {
    public static void main(String[] args) throws InterruptedException {
        Cocinero cocinero = new Cocinero();

        System.out.println("(1) " + cocinero.getState());
        cocinero.start();
        Thread.sleep(200);
        System.out.println("(2) " + cocinero.getState());
        cocinero.interrupt();
        cocinero.join();
        System.out.println("(3) " + cocinero.getState());
    }
}

//(1) NEW
//(2) TIMED_WAITING
//[tareas_clase.Cocinero] ¡Me han cortado la luz!
//(3) TERMINATED
//(1) NEW: el hilo está creado pero no se ha llamado a start().
//(2) TIMED_WAITING: a los 200 ms está dentro del sleep(3000), una espera con límite de tiempo.
//interrupt() lo saca del sleep con InterruptedException, así que se imprime el mensaje del catch y nunca «El guiso está listo».
//(3) TERMINATED: tras el join() el run() ha acabado.