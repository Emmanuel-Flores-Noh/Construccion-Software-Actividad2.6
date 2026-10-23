package biblioteca;

// EXTRACT CLASS


// MOVE FIELD
public class Socio {
    private String nombre;
    private String ciudad;
    


    public Socio(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }

}
