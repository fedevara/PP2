package project.pack.persistence;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lukas on 18/05/2017.
 */
public class PersistenciaEstablecimientoTest {
    @Test
    public void addEstablecimiento() throws Exception {

    }

    @Test
    public void getListaEstablecimiento() throws Exception {

    }






    /* Criterio de aceptacion:
    Si hay conexión, cada vez que se agregan cosas, se actualiza la caché */
    @Test
    public void HayConexion_actualizaCache() throws Exception{
        // Forzamos al modo con conexion
        //ConnectionUtilities.forzarConexion(true);
        // cada vez que se agregan cosas, se actualiza la caché
    }

    /* Criterio de aceptacion:
    Si no hay conexión, se obtienen los datos almacenados en la caché    */
    @Test
    public void SinConexion_usaCache() throws Exception{
        // Forzamos al modo sin conexion
        //ConnectionUtilities.forzarConexion(false);
        // se obtienen los datos almacenados en la caché
    }

    /* Criterio de aceptacion:
    Si no hay conexión, y cierro la aplicación, cuando la abra de nuevo,
    quiero que se carguen los datos de la caché que tenía almacenado */
    @Test
    public void SinConexion_reiniciarApp() throws Exception{
        // Forzamos al modo sin conexion

        // cierro la aplicación,

        // cuando la abra de nuevo

        // quiero que se carguen los datos de la caché que tenía almacenado
    }

}