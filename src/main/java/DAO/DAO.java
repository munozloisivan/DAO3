package DAO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Roberto on 15/03/2017.
 */
public abstract class DAO {


    public void insert(){

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(this.getClass().getSimpleName()+ " ("); //Usuario
        Field[] atributes = this.getClass().getDeclaredFields();

        int i =0;
        for (Field f : atributes){
            sb.append(f.getName());
            i++;
            if (i!= atributes.length)
                sb.append(", ");
        }
        sb.append(") VALUES (?, ?);");

       /* Method[] values = this.getClass().getDeclaredMethods();
        Method m=null;
        String ret = null;
        try {
           m= this.getClass().getDeclaredMethod("getName",null);
           ret =  (String)m.invoke(this, null);
           System.out.println(ret);

         }
         catch (Exception e) {
         e.printStackTrace();

       } */
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
        System.out.println(sb);
    }

    public void select(){

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(this.getClass().getSimpleName());
        System.out.println(sb);
    }

    public void delete(){
        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(this.getClass().getSimpleName());
        sb.append(" WHERE id= ?;");
        System.out.println(sb);
    }

    public void findAll(){

    }
}
