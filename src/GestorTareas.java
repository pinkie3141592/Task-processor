import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.io.*;

public class GestorTareas {

    ArrayList<Tarea> tareas;
    HashMap<String, Tarea> busqueda;
    HashSet<String> duplicados;
    LinkedList<Tarea> cola;

    File archivo = new File("tareas.txt");

    public void crearArchivo()
    {
        try
        {
            if(!archivo.exists())
                archivo.createNewFile();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public GestorTareas()
    {
        tareas = new ArrayList<>();
        busqueda = new HashMap<>();
        duplicados = new HashSet<>();
        cola = new LinkedList<>();
    }


    public void agregarTarea(Tarea tarea)
    {
        if(tarea == null)
            return;

        if(duplicados.contains(tarea.getId()))
        {
            System.out.println("\n\tYa existe una tarea con ese ID.");
            return;
        }

        tareas.add(tarea);

        busqueda.put(tarea.getId(), tarea);

        duplicados.add(tarea.getId());

        cola.add(tarea);
    }

    public void guardarArchivo()
    {
        try{

            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for(Tarea t : tareas)
            {
                bw.write(t.toFileString());
                bw.newLine();
            }

            bw.close();
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void leerArchivo()
    {

        Tarea.Estado estado = Tarea.Estado.PENDIENTE;
        tareas.clear();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while((linea = br.readLine()) != null)
            {
                String[] partes = linea.split(","); //divide cada linea por comas!!

                try{
                    estado = Tarea.Estado.valueOf(partes[2]); //convierto el string extraido del archivo en un valor del enum
                }
                catch(IllegalArgumentException e){
                    System.err.println(e);
                }


                Tarea t = new Tarea(partes[0],partes[1],estado);
                System.out.println();

                tareas.add(t);
                busqueda.put(t.getId(), t);
                duplicados.add(t.getId());
                cola.add(t);

            }
            br.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public void mostrarTareas()
    {
        for(Tarea t : tareas)
        {
            imprimirTarea(t);
        }
    }

    public LinkedList<Tarea> getCola() 
    {
        return cola;
    }

    public Tarea buscarTarea(String id)
    {
        return busqueda.get(id);
    }

    public void procesarTareas()
    {
        Tarea t = cola.poll();

        Thread hilo = new Thread(new ProcesadorTareas(t));
        hilo.start();
    }

    public void imprimirTarea(Tarea tarea)
    {
        System.out.print("\n\t---------------------");
        System.out.print("\n\tID: " + tarea.getId());
        System.out.print("\n\tNOMBRE: " + tarea.getNombre());
        System.out.print("\n\tESTADO: " + tarea.getEstado());
        System.out.print("\n\t---------------------");

    }


}
