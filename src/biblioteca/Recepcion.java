package biblioteca;

// REMOVE MIDDLE MAN
public class Recepcion {
    private Catalogo catalogo;

    public Recepcion(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public void registrarLibro(Libro libro) {
        catalogo.agregar(libro);
    }

    public Libro consultarLibro(String titulo) {
        return catalogo.buscarPorTitulo(titulo);
    }

    public int totalLibros() {
        return catalogo.total();
    }
}
