package project.pack.persistence.DAOImpl;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import project.pack.domain.Incidente;

/**
 * Created by lukas on 14/05/2017.
 */

public class ConectionFile {
    // En esta clase se obtienen los datos desde un archivo plano
    private String CacheFile = "cache.ch";


    public void Serializar(Object item){
        ObjectOutputStream s = null;
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(CacheFile);
            s = new ObjectOutputStream(f);
            s.writeObject(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                f.close();
            } catch (IOException e) {e.printStackTrace();}
            try {
                s.close();
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    public Object Deserializar(){
        Object item = null;
        ObjectInputStream s = null;
        FileInputStream f = null;
        try {
            f = new FileInputStream(CacheFile);
            s = new ObjectInputStream(f);
            item = (Object) s.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                f.close();
            } catch (IOException e) {e.printStackTrace();}
            try {
                s.close();
            } catch (IOException e) {e.printStackTrace();}

        }
        return item;
    }



/*
    // Lee el archivo, si no existe el archivo, crealo
    public String readFile(String filename){
        try{
            if(isExternalStorageAvailable()){
                File file = new File(Environment.getExternalStorageDirectory(), filename);
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String t = br.readLine();
                br.close();
                return t;
            } else {return "";}
        } catch(Exception e){return "";}
    }

    private void writeFile(String DATO){
        try {
            if (isExternalStorageAvailable() && !isExternalStorageReadOnly()) {
                File file = new File(Environment.getExternalStorageDirectory(),  CacheFile);
                OutputStreamWriter outw = new OutputStreamWriter(new FileOutputStream(file));
                outw.write(DATO);
                outw.close();
            }
        } catch (Exception e) {}


        try{
            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("prueba_int.txt", Context.MODE_PRIVATE));
            fout.write("Texto de prueba.");
            fout.close();
        }catch (Exception ex){
            //Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }


    }

    private ArrayList<Incidente> getListaIncidente() {
        BufferedReader reader = null;
        ArrayList<Incidente> listaIncidente = null;
        try {
            reader = new BufferedReader(new FileReader(CacheFile));
            String line;

            // Lee las lineas del archivo
            while ( (line = reader.readLine()) != null ) {
                String[] elements = line.split("\\|");

                // Extrae los datos y los guarda
                String title = elements[0];
                String year = elements[1];
                String genre = elements[2];

                // Agrega los atributos en el item
                Incidente item = new Incidente(title, year, genre);
                // Agrego el item a la lista
                listaIncidente.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( reader != null ) {
                try { reader.close(); } catch (Exception e) {}
            }
        }
        return listaIncidente;
    }

    // Agrega una linea
    public void add(Incidente incidente){
        PrintWriter libraryWriter = null;
        try {
            // Open a writer stream and mark it to append the new data
            libraryWriter = new PrintWriter(new FileWriter(CacheFile, true));

            libraryWriter.print(incidente.getId());
            libraryWriter.print('|' + incidente.getCategoria());
            libraryWriter.print('|' + incidente.getDescripcion());
            libraryWriter.print('|' + incidente.getTitulo());
            libraryWriter.print('|' + incidente.getTitulo());
            libraryWriter.print('|' + incidente.getTitulo());
            libraryWriter.println();

        } catch (Exception e) {
            throw new RuntimeException(e);

        // Cierro el archivo
        } finally {
            if ( libraryWriter != null ) {
                try { libraryWriter.close(); } catch (Exception e) { System.err.println(e); }
            }
        }
    }


    // Busca item

    // Modificar item

    // Remover item

    // Obtener lista items
    */
}
