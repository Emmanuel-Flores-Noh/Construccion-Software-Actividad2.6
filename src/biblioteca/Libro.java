package biblioteca;

public class Libro {
    private String titulo;
    private String autor;
    // REFACTOR INLINE CLASS 
    // La clase ISBN era innecesaria por lo que se elimino y cambiaron referencias que la utilizaban dentro del codigo
    private String isbn;
    private String categoria;       // "NOVEDAD" o "NORMAL"
    private double multaPorDia;
    private double valorReposicion;

    public Libro(String titulo, String autor, String isbn, String categoria,
                 double multaPorDia, double valorReposicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.multaPorDia = multaPorDia;
        this.valorReposicion = valorReposicion;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public String getCategoria() { return categoria; }
    public double getMultaPorDia() { return multaPorDia; }
    public double getValorReposicion() { return valorReposicion; }

    // REFACTOR MOVE METHOD
    // El método estaba la clase Prestamo, pero se movio a la clase Libro que tiene mas relacion con los atributos que usa para hacer el cálculo
    
    public double calcularMulta(int diasRetraso) {
        if (diasRetraso <= 0) {
            return 0;
        }
        double multa = diasRetraso * multaPorDia;
        if ("NOVEDAD".equals(categoria)) {
            multa *= 2;
        }
        if (multa > valorReposicion) {
            multa = valorReposicion;
        }
        return multa;
    }

}
