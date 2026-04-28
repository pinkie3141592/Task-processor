public class Tarea {

    public enum Estado
    {
        PENDIENTE,
        EN_PROCESO,
        TERMINADA
    }

    protected String id;
    protected String nombre;
    protected Estado estado;

    public Tarea(String id, String nombre, Estado estado)
    {
        this.estado = estado;
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString()
    {
        return "ID: " + id + "NOMBRE: " + nombre + "ESTADO" + estado;
    }

    public String toFileString()
    {
        return  id + "," + nombre + "," + estado;
    }

    public String getId()
    {
        return id;
    }
    public Estado getEstado()
    {
        return estado;
    }
    public String getNombre()
    {
        return nombre;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }



}