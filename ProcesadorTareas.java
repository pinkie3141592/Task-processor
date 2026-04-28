public class ProcesadorTareas implements Runnable {

    private Tarea tarea;

    public ProcesadorTareas(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public void run() {
        if(tarea.getEstado() != Tarea.Estado.TERMINADA)
        {
            System.out.println("\n\tProcesando: " + tarea.getNombre());

            try
            {

                for(int i = 0; i<3; i++)
                {
                    System.out.println("\t.");
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\n\tTerminada: " + tarea.getNombre());
            tarea.setEstado(Tarea.Estado.TERMINADA);
        }
        else
            System.out.println("\n\tLa tarea " + tarea.getNombre() + " ya esta terminada.");

        System.out.println("\n\t-Presiona Enter para continuar-");
    }
}