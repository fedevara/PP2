package project.pack.persistence.DAOImpl;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import project.pack.facade.Facade;

/**
 * Created by lukas on 14/05/2017.
 */

public class ConectionFile {

    public void Escribir(Object item, String File){

        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        Facade fac = Facade.getInstance();
        Context context = fac.getContext();

        if(context!=null) {
            try {
                fos = context.openFileOutput(File, Context.MODE_PRIVATE);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(item);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null)
                        fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (oos != null)
                        oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Object Leer(String File){

        ObjectInputStream ois = null;
        FileInputStream fis = null;
        Object item = null;

        Facade fac = Facade.getInstance();
        Context context = fac.getContext();

        if(context!=null) {
            try {
                fis = context.openFileInput(File);
                ois = new ObjectInputStream(fis);
                item = ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null)
                        fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (ois != null)
                        ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return item;
    }

}//--> FIN
