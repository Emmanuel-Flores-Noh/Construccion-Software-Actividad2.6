package biblioteca;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// REFACTOR INTRODUCE LOCAL EXTENSION 
public class FechaExtension {
    private final Date fecha;

    public FechaExtension(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date sumarDias(int dias) {
        long ms = fecha.getTime() + (dias * 24L * 60 * 60 * 1000);
        return new Date(ms);
    }

    public int diasHasta(Date otraFecha) {
        double milisPorDia = 24.0 * 60 * 60 * 1000;
        return (int) Math.round((otraFecha.getTime() - fecha.getTime()) / milisPorDia);
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