# Sistema de Gestión de Biblioteca - Refactorización

## Codigo

El codigo fuente se encuentra en [`src/biblioteca/`](src/biblioteca/).

## Diagrama de clases - Antes de la refactorización

```mermaid
---
config:
  theme: redux-color
---
classDiagram
direction TB
    class Main {
	    +main(args: String[]) void$
    }

    class Recepcion {
	    -catalogo: Catalogo
	    +Recepcion(catalogo: Catalogo)
	    +registrarLibro(libro: Libro) void
	    +consultarLibro(titulo: String) Libro
	    +totalLibros() int
    }

    class Catalogo {
	    -libros: List~Libro~
	    +agregar(libro: Libro) void
	    +buscarPorTitulo(titulo: String) Libro
	    +total() int
    }

    class Libro {
	    -titulo: String
	    -autor: String
	    -isbn: Isbn
	    -categoria: String
	    -multaPorDia: double
	    -valorReposicion: double
	    +Libro(titulo: String, autor: String, isbn: String, categoria: String, multaPorDia: double, valorReposicion: double)
	    +getTitulo() String
	    +getAutor() String
	    +getIsbn() String
	    +getCategoria() String
	    +getMultaPorDia() double
	    +getValorReposicion() double
    }

    class Isbn {
	    -valor: String
	    +Isbn(valor: String)
	    +getValor() String
    }

    class Socio {
	    -nombre: String
	    -ciudad: String
	    -telefonoCodigoArea: String
	    -telefonoNumero: String
	    -diasPlazoPrestamo: int
	    +Socio(nombre: String, ciudad: String, telefonoCodigoArea: String, telefonoNumero: String, diasPlazoPrestamo: int)
	    +getNombre() String
	    +getCiudad() String
	    +getDiasPlazoPrestamo() int
	    +obtenerTelefonoCompleto() String
	    +esTelefonoLocal() boolean
    }

    class Prestamo {
	    -libro: Libro
	    -socio: Socio
	    -fechaPrestamo: Date
	    -fechaDevolucion: Date
	    +Prestamo(libro: Libro, socio: Socio, fechaPrestamo: Date, fechaDevolucion: Date)
	    +getLibro() Libro
	    +getSocio() Socio
	    +calcularFechaVencimiento() Date
	    +calcularDiasRetraso() int
	    +calcularMulta(diasRetraso: int) double
	    -diasEntre(desde: Date, hasta: Date) int
	    -formatearFecha(fecha: Date) String
	    -esFinDeSemana(fecha: Date) boolean
	    +imprimirResumen() void
    }

    Recepcion --> "1" Catalogo : catalogo
    Catalogo o-- "0..*" Libro : libros
    Libro *-- "1" Isbn : isbn
    Prestamo --> "1" Libro : libro
    Prestamo --> "1" Socio : socio
    Main ..> Recepcion : crea
    Main ..> Catalogo : crea
    Main ..> Libro : crea
    Main ..> Socio : crea
    Main ..> Prestamo : crea
```

## Diagrama de clases — Después de la refactorización

```mermaid
---
config:
  theme: redux-color
---
classDiagram
direction TB
    class Main {
        +main(args: String[]) void$
    }

    class Catalogo {
        -libros: List~Libro~
        +agregar(libro: Libro) void
        +buscarPorTitulo(titulo: String) Libro
        +total() int
    }

    class Libro {
        -MULTIPLICADOR_MULTA_NOVEDAD: double$
        -titulo: String
        -autor: String
        -isbn: String
        -categoria: String
        -multaPorDia: double
        -valorReposicion: double
        +Libro(titulo: String, autor: String, isbn: String, categoria: String, multaPorDia: double, valorReposicion: double)
        +getTitulo() String
        +getAutor() String
        +getIsbn() String
        +getCategoria() String
        +getMultaPorDia() double
        +getValorReposicion() double
        +calcularMulta(diasRetraso: int) double
    }

    class Socio {
        -nombre: String
        -ciudad: String
        -telefono: Telefono
        +Socio(nombre: String, ciudad: String, telefono: Telefono)
        +getNombre() String
        +getCiudad() String
        +getTelefono() String
        +esTelefonoLocal() boolean
    }

    class Telefono {
        -CODIGO_AREA_LOCAL: String$
        -telefonoCodigoArea: String
        -telefonoNumero: String
        +Telefono(telefonoCodigoArea: String, telefonoNumero: String)
        +obtenerTelefonoCompleto() String
        +esTelefonoLocal() boolean
    }

    class FechaExtension {
        -MILIS_POR_DIA: long$
        -fecha: Date
        +FechaExtension(fecha: Date)
        +getFecha() Date
        +sumarDias(dias: int) Date
        +diasHasta(otraFecha: Date) int
        +esFinDeSemana() boolean
        +formatear() String
    }

    class Prestamo {
        -libro: Libro
        -socio: Socio
        -fechaPrestamo: Date
        -fechaDevolucion: Date
        -diasPlazoPrestamo: int
        +Prestamo(libro: Libro, socio: Socio, fechaPrestamo: Date, fechaDevolucion: Date, diasPlazoPrestamo: int)
        +getLibro() Libro
        +getSocio() Socio
        +getCiudadSocio() String
        +getTelefonoSocio() String
        +getDiasPlazoPrestamo() int
        +calcularFechaVencimiento() Date
        +calcularDiasRetraso() int
        +imprimirResumen() void
    }

    Catalogo o-- "0..*" Libro : libros
    Socio *-- "1" Telefono : telefono
    Prestamo --> "1" Libro : libro
    Prestamo --> "1" Socio : socio
    Prestamo ..> FechaExtension : usa
    Main ..> Catalogo : crea y usa
    Main ..> Libro : crea
    Main ..> Telefono : crea
    Main ..> Socio : crea
    Main ..> Prestamo : crea
```
