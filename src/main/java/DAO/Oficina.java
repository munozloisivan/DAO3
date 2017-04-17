package DAO;

/**
 * Created by Roberto on 15/03/2017.
 */
public class Oficina extends DAO{

    private int id;
    private String nombre, direccion;

    public Oficina(){}

    public Oficina(String name, String direccion) {
        this.nombre = name;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
