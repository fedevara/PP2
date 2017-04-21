package project.pack.utilities;

import java.util.Date;

/**
 * Created by sgarcete on 4/16/17.
 */

public class DateUtils {
    private static final DateUtils ourInstance = new DateUtils();

    public static DateUtils getInstance() {
        return ourInstance;
    }

    private DateUtils() {
    }

    public long getDiferenciaPorDia(Date fechaAnterior, Date fechaPosterior) {
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
        long diferencia = (fechaPosterior.getTime() - fechaAnterior.getTime()) / MILLSECS_PER_DAY;
        return diferencia;
    }

    public long getDiferenciaPorDiaFechaActual(Date fechaAnterior) {
        Date fechaActual = new Date();
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
        long diferencia = (fechaActual.getTime() - fechaAnterior.getTime()) / MILLSECS_PER_DAY;
        return diferencia;
    }

}
