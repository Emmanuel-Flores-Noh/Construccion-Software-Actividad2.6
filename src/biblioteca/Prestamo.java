package biblioteca;

import java.util.Date;

public class Prestamo {
    private Libro libro;
    private Socio socio;
    private Telefono telefono;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    // REFACTOR MOVE FIELD
    // El atributo diasPlazoPrestamo tiene mas relacion con la clase Prestamo y sus métodos, por lo que se movió y 
    // se cambiaron las referencias a dicho atributo en el resto del código
    private int diasPlazoPrestamo;


    public Prestamo(Libro libro, Socio socio, Telefono telefono, Date fechaPrestamo, Date fechaDevolucion, int diasPlazoPrestamo) {
        this.libro = libro;
        this.socio = socio;
        this.telefono = telefono;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.diasPlazoPrestamo = diasPlazoPrestamo;
    }

    public Libro getLibro() { return libro; }
    public Socio getSocio() { return socio; }
    // REFACTOR HIDE DELEGATE 
    // Main utilizaba getSocio.getCiudad(); para obtener la ciudad
    // Se cambio para que solo utilice getCiudadSocio para ocultar el intermediario.
    public String getCiudadSocio() { return socio.getCiudad();}
    public Telefono getTelefono() {return telefono; }
    public int getDiasPlazoPrestamo() { return diasPlazoPrestamo; }


    //  REFACTOR INTRODUCE FOREIGN METHOD
    // Se introdujo el método sumarDias para ser usado directamente como argumento de Date en calcularFechaVencimiento
    public Date calcularFechaVencimiento() {
        // Uso de Local Extension
        FechaExtension extensionPrestamo = new FechaExtension(fechaPrestamo);
        return extensionPrestamo.sumarDias(diasPlazoPrestamo);
    }
    public long sumarDias(Date fechaPrestamoDate , int diasPlazoPrestamo ){
        return fechaPrestamo.getTime() + diasPlazoPrestamo  * 24L * 60 * 60 * 1000;
    }

 public int calcularDiasRetraso() {
        // Uso de Local Extension
        FechaExtension extVencimiento = new FechaExtension(calcularFechaVencimiento());
        return extVencimiento.diasHasta(fechaDevolucion);
    }
    public void imprimirResumen() {
        FechaExtension extPrestamo = new FechaExtension(fechaPrestamo);
        FechaExtension extVencimiento = new FechaExtension(calcularFechaVencimiento());
        FechaExtension extDevolucion = new FechaExtension(fechaDevolucion);

        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Socio: " + socio.getNombre());
        System.out.println("Prestado: " + extPrestamo.formatear());
        System.out.println("Vence: " + extVencimiento.formatear());
        System.out.println("Vence en fin de semana: " + extVencimiento.esFinDeSemana());
        System.out.println("Devuelto: " + extDevolucion.formatear());
    }
}

