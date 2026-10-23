package biblioteca;

import java.util.Date;

public class Prestamo {
    private Libro libro;
    private Socio socio;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    // REFACTOR MOVE FIELD
    // El atributo diasPlazoPrestamo tiene mas relacion con la clase Prestamo y sus métodos, por lo que se movió y 
    // se cambiaron las referencias a dicho atributo en el resto del código
    private int diasPlazoPrestamo;

    public Prestamo(Libro libro, Socio socio, Date fechaPrestamo, Date fechaDevolucion, int diasPlazoPrestamo) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.diasPlazoPrestamo = diasPlazoPrestamo;
    }

    public Libro getLibro() { return libro; }
    public Socio getSocio() { return socio; }

    // REFACTOR HIDE DELEGATE 
    // Main utilizaba getSocio.getCiudad(); para obtener la ciudad
    // Se cambio para que solo utilice getCiudadSocio para ocultar el intermediario.
    // Tambien se agrego getTelefonoSocio() para obtener el telefono

    public String getCiudadSocio() { return socio.getCiudad();}
    public String getTelefonoSocio() {return socio.getTelefono(); }
    public int getDiasPlazoPrestamo() { return diasPlazoPrestamo; }

    //  REFACTOR INTRODUCE FOREIGN METHOD
    // Se introdujo el método sumarDias para ser usado directamente como argumento de Date en calcularFechaVencimiento
    // Luego se terminó moviendo a la clase FechaExtension
    
    public Date calcularFechaVencimiento() {
        FechaExtension extensionPrestamo = new FechaExtension(fechaPrestamo);
        return extensionPrestamo.sumarDias(diasPlazoPrestamo);
    }

    public int calcularDiasRetraso() {
        FechaExtension extensionVencimiento = new FechaExtension(calcularFechaVencimiento());
        return extensionVencimiento.diasHasta(fechaDevolucion);
    }
    public void imprimirResumen() {
        FechaExtension extensionPrestamo = new FechaExtension(fechaPrestamo);
        FechaExtension extensionVencimiento = new FechaExtension(calcularFechaVencimiento());
        FechaExtension extensionDevolucion = new FechaExtension(fechaDevolucion);

        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Socio: " + socio.getNombre());
        System.out.println("Prestado: " + extensionPrestamo.formatear());
        System.out.println("Vence: " + extensionVencimiento.formatear());
        System.out.println("Vence en fin de semana: " + extensionVencimiento.esFinDeSemana());
        System.out.println("Devuelto: " + extensionDevolucion.formatear());
    }
}

