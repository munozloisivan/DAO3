package DAO;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        Usuario u = new Usuario("Roberto", "roberto@gmail.com", "robrobrob");
        //u.insert();
        u.select(5);
        u.delete(2);


        Oficina o = new Oficina("Finanzas", "Enteça 297");
        //o.insert();
        o.select(2);
        o.delete(1);
    }
}
