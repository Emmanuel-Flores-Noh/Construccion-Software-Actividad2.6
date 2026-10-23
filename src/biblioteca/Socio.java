package biblioteca;

public class Socio {
    private String nombre;
    private String ciudad;
    private Telefono telefono;
    
    public Socio(String nombre, String ciudad, Telefono telefono) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public String getTelefono() { return telefono.obtenerTelefonoCompleto(); }
    public boolean esTelefonoLocal() {  return telefono.esTelefonoLocal(); }

}
