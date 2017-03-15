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
        sb.append(") VALUES (");

        Method[] values = this.getClass().getDeclaredMethods();
        Method m=null;
        String ret = null;
       try {
           m= this.getClass().getDeclaredMethod("getName",null);
           ret =  (String)m.invoke(this, null);
           System.out.println(ret);

       }
       catch (Exception e) {
           e.printStackTrace();

       }




        int j=0;


        System.out.println(sb);
    }

    public void update(){

        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(this.getClass().getSimpleName()); //

    }

    public void select(){

    }

    public void delete(){

    }

    public void findAll(){

    }
}
