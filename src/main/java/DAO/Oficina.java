package DAO;

/**
 * Created by Roberto on 15/03/2017.
 */
public class Oficina extends DAO{

    private String name, direccion;

    public Oficina(){}

    public Oficina(String name, String direccion) {
        this.name = name;
        this.direccion = direccion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
