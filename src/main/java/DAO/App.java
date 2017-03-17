package DAO;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Usuario u = new Usuario(14,"Roberto");
        u.insert();
        u.update();
        u.select();
        u.delete();

        Oficina o = new Oficina("Finanzas", "Enteça 297");
        o.insert();
        o.update();
        o.select();
        o.delete();
    }
}
