import java.util.Scanner;

public class Main {
    
    static Scanner sc = new Scanner(System.in);
    static GestorTareas gestor = new GestorTareas();

    public static void main(String[] args)
    {
        int opcion;

        do
        {

            do
            {
                Utilidades.limpiarConsola();
                System.out.println("\t\tGESTOR DE TAREAS");
                System.out.println("\n\t1. Agregar tarea");
                System.out.println("\n\t2. Guardar archivo");
                System.out.println("\n\t3. Leer archivo");
                System.out.println("\n\t4. Mostrar tareas");
                System.out.println("\n\t5. Procesar tareas");
                System.out.println("\n\t6. Buscar tarea");
                System.out.println("\n\t7. Salir");

                opcion = sc.nextInt();
                sc.nextLine();

                if(!Validaciones.validarEntero(opcion, 1, 7))
                {
                    System.out.println("\nEscoge una opcion valida");
                    Utilidades.pausaMensaje();
                }

            }while(!Validaciones.validarEntero(opcion, 1, 7));

            switch(opcion)
            {
                case 1:
                    gestor.agregarTarea(leerTarea());
                    Utilidades.pausaMensaje();
                    break;
                case 2:
                    gestor.guardarArchivo();
                    Utilidades.pausaMensaje();
                    break;
                case 3:
                    gestor.leerArchivo();
                    Utilidades.pausaMensaje();
                    break;
                case 4: 
                    gestor.mostrarTareas();
                    Utilidades.pausaMensaje();
                    break;
                case 5:
                    gestor.procesarTareas();
                    Utilidades.pausa();
                    break;
                case 6:
                    System.out.println("\n\tCODIGO BUSCADO: ");
                    String codigo = sc.next();

                    if(gestor.buscarTarea(codigo) == null)
                    {
                        System.out.println("\n\tNo se encontraron tareas con ese codigo.");
                        Utilidades.pausaMensaje();
                    }
                    else
                        gestor.imprimirTarea(gestor.buscarTarea(codigo));

                    Utilidades.pausaMensaje();
                    break;
            }
        }while(opcion != 7);

    }

    public static Tarea leerTarea()
    {
        String id, nombre;
        int idint = 0;
        boolean valido = false;

        System.out.println("\t\tAGREGAR TAREA");
        
        //CICLO PARA EL ID -------------------------------------------------------
        do {
            System.out.print("\tID: ");
            id = sc.nextLine();

            try{
                idint = Integer.parseInt(id);
                if(!Validaciones.validarEntero(idint, 0, 1000))
                {
                    System.out.println("\n\tUtiliza un numero del 0 al 1000");
                    Utilidades.pausaMensaje();
                }
                else 
                    valido = true;
            }
            catch(NumberFormatException E)
            {
                System.out.println("\n\tEscribe solo numeros");
            }

        } while (!valido);

        //CICLO PARA NOMBRE -------------------------------------------------------
        do
        {
            System.out.print("\tNOMBRE: ");
            nombre = sc.nextLine();

            if(!Validaciones.validarLinea(nombre))
            {
                System.out.println("\n\tPor favor, utiliza solo letras y espacios.");
                Utilidades.pausaMensaje();
            }

        }while(!Validaciones.validarLinea(nombre));

        //CICLO PARA ESTADO -------------------------------------------------------
        
            
        Tarea.Estado estadoEnum = leerEstado();
            
        return new Tarea(id, nombre, estadoEnum);
    }


    public static Tarea.Estado leerEstado() //hice otra f-ing funcion porque me da asco tener bloques dowhile tan grandes...
    {
        boolean valido = false;
        int estado = 0;
        String estadostr;

        do 
        {
            System.out.print("\tESTADO: ");
            System.out.println("\n\t1. Pendiente\n\t2. En proceso\n\t3. Terminada\n");
            estadostr = sc.nextLine();


            try{
                estado = Integer.parseInt(estadostr);

                if(!Validaciones.validarEntero(estado,1,3))
                {
                    System.out.println("\n\tEscoge una opcion valida.");
                    Utilidades.pausaMensaje();
                }
                else
                    valido  = true;
            }
            catch(NumberFormatException e){
                System.out.println("\n\tPOR FAVOR INTRODUCE SOLO NUMEROS");
                Utilidades.pausaMensaje();
            }
        } while (!valido);

        Tarea.Estado[] estados = Tarea.Estado.values();
        Tarea.Estado estadoEnum = estados[estado - 1];

        return estadoEnum;

    }


    /* 
    public static Tarea.Estado leerEstado(String estadostr) //QUW HUEVA PINCHES VALIDACIOLNES DE M,IERFA DAA'¿SDSLAKHFÑOAWJHEBGÑIOAEB
    {
        int estado;

        try {
                estado = Integer.parseInt(estadostr);

                if(!Validaciones.validarEntero(estado,1,3))
                    System.out.println("\n\tEscoge un numero del 1 al 3");
                else
                    valido = true;

            } catch (NumberFormatException e) {
                System.out.println("\n\tEscoge un numero del 1 al 3");
            }

        }while(!valido);

        Tarea.Estado[] estados = Tarea.Estado.values();
        Tarea.Estado estadoEnum = estados[estadoint - 1];

        return estadoEnum;
    }
    */

}
