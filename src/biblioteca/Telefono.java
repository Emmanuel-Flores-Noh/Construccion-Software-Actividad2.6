package biblioteca;
// REFACTOR EXTRACT CLASS
// Se extrajo los atributos de la clase Socio y se separaron en una nueva clase Telefono
// Se agregó como atributo a la clase Socio 
// Se cambiaron todas las referencias de Socio para obtener el telofono a refrenciar la clase Telefono
public class Telefono {
    private static final String CODIGO_AREA_LOCAL = "999";

    private String telefonoCodigoArea;
    private String telefonoNumero;

    public Telefono (String telefonoCodigoArea, String telefonoNumero){
        this.telefonoCodigoArea = telefonoCodigoArea;
        this.telefonoNumero = telefonoNumero;
    }

    public String obtenerTelefonoCompleto() {
        return "(" + telefonoCodigoArea + ") " + telefonoNumero;
    }

    public boolean esTelefonoLocal() {
        return telefonoCodigoArea.equals(CODIGO_AREA_LOCAL);
    }
}
