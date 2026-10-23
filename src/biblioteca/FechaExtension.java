package biblioteca;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// REFACTOR INTRODUCE LOCAL EXTENSION 
// Se añadió la clase FechaExtension para ampliar los métodos disponibles de Date

public class FechaExtension {
    private static final long MILIS_POR_DIA = 24L * 60 * 60 * 1000;

    private final Date fecha;

    public FechaExtension(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date sumarDias(int dias) {
        long milisegundoResultado = fecha.getTime() + (dias * MILIS_POR_DIA);
        return new Date(milisegundoResultado);
    }

    public int diasHasta(Date otraFecha) {
        return (int) Math.round((otraFecha.getTime() - fecha.getTime()) / (double) MILIS_POR_DIA);
    }

    public boolean esFinDeSemana() {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int dia = calendario.get(Calendar.DAY_OF_WEEK);
        return dia == Calendar.SATURDAY || dia == Calendar.SUNDAY;
    }

    public String formatear() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }
}