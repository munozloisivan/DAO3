package DAO;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Roberto on 18/04/2017.
 */
public class DBConnection {

    private Connection con;

    @Singleton
    public DBConnection(){
        Connection con = null;
        try{
            String host = "localhost", database = "dao";
            int port = 3306;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "mysql");
            info.setProperty("useSSL", "false");
            info.setProperty("serverTimezone", "UTC");
            con = DriverManager.getConnection(url, info);
            System.out.println("Conexión BBDD creada \n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        setCon(con);
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
