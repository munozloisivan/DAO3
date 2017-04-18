package DAO;

import Models.Oficina;
import Models.Usuario;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Roberto on 15/03/2017.
 */
public abstract class DAO {

    static DBConnection connection = new DBConnection();
    static Connection con = connection.getCon();

    //Una función para coger los valores
    public String getValues(Field field) {
        String val = null;
        try {
            Method method = this.getClass().getDeclaredMethod(getUpper(field.getName()), null);
            val = method.invoke(this, null).toString();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return val;
    }

    //Gitanada chunga de stackoverflow
    private String getUpper(String m){
        String res = Character.toUpperCase(m.charAt(0)) + m.substring(1);
        return "get".concat(res);
    }

    //Una función para rellenarlos a todos
    public void insertElements(PreparedStatement preparedStatement) throws SQLException {
        int i = 1;
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields){
            String res = getValues(f);
            preparedStatement.setObject(i,res);
            i++;
        }
    }

    public void insert(){

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(this.getClass().getSimpleName()+ " ("); //Usuario
        Field[] atributes = this.getClass().getDeclaredFields();

        //bucle de atributos
        int i =0;
        for (Field f : atributes){
            sb.append(f.getName());
            i++;
            if (i!= atributes.length)
                sb.append(", ");
        }

        sb.append(") VALUES (");
        //bucle de interrogantes
        int j = 0;
        for (Field f: atributes){
            sb.append("?");
            j++;
            if (j!=atributes.length)
            sb.append(",");
        }
        sb.append(")");

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            insertElements(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

       System.out.println(sb);
    }

    public void update(){

        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(this.getClass().getSimpleName()+ " SET ");
        Field[] atributes = this.getClass().getDeclaredFields();

        int i =0;
        for (Field f : atributes){
            sb.append(f.getName()+ "= ?");
            i++;
            if (i!= atributes.length)
                sb.append(", ");
        }
        sb.append(" WHERE (conditions);");
    }

    public void select(int id){

        StringBuffer sb = new StringBuffer("SELECT *");

        sb.append(" FROM ");

        sb.append(this.getClass().getSimpleName());

        sb.append(" WHERE id = "+id);

        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sb.toString());
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.next();

            for (int i=1; i<rsmd.getColumnCount() +1; i++){

            }

            for (int i=1; i<rsmd.getColumnCount() + 1; i++){

                if (rsmd.getColumnTypeName(i).equals("INT")){
                    System.out.println(rsmd.getColumnLabel(i)+ ": "+rs.getInt(i));
                }
                if (rsmd.getColumnTypeName(i).equals("VARCHAR")){
                    System.out.println(rsmd.getColumnLabel(i)+ ": " +rs.getString(i));
                }
                if (i==rsmd.getColumnCount()){
                    rs.next();
                    i = 0;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id){
        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(this.getClass().getSimpleName());
        sb.append(" WHERE id = " + id);
        System.out.println(sb);

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static List<Usuario> getAllUsers() throws SQLException {
        List<Usuario> listaUs = new ArrayList<Usuario>();
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario");
        while(rs.next()){
            Usuario us = new Usuario();
            us.setId(rs.getInt("id"));
            us.setNombre(rs.getString("nombre"));
            us.setEmail(rs.getString("email"));
            us.setPassword(rs.getString("password"));

            listaUs.add(us);
        }
        return listaUs;
    }

    public static List<Oficina> getAllOficinas() throws SQLException {
        List<Oficina> listaOf = new ArrayList<Oficina>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Oficina");
        while(rs.next()){
            Oficina of = new Oficina();
            of.setId(rs.getInt("id"));
            of.setNombre(rs.getString("nombre"));
            of.setDireccion(rs.getString("direccion"));
            listaOf.add(of);
        }
        return listaOf;
    }
}
