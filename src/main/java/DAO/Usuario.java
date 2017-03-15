package DAO;

/**
 * Created by Roberto on 15/03/2017.
 */
public class Usuario extends DAO {

    private int id;
    private String name;

    public Usuario(){}

    public Usuario(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
