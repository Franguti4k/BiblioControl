package BiblioControl;

/**
 * Clase Libro
 * Contiene los atributos de un libro y sus metodos get y set
 */
public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;

    /**
     * Constructor de la clase Libro
     * Recibe los parametros ISBN, titulo y autor y crea un nuevo libro
     * @param ISBN ISBN del libro
     * @param titulo Titulo del libro
     * @param autor Autor del libro
     */
    public Libro(String ISBN, String titulo, String autor){
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
    }

    /**
     * Metodo getISBN
     * @return devuelve el ISBN del libro
     */
    public String getISBN(){
        return ISBN;
    }

    /**
     * Metodo setISBN
     * @param ISBN ISBN del libro
     */
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    /**
     * Metodo getTitulo
     * @return devuelve el titulo del libro
     */
    public String getTitulo(){
        return titulo;
    }

    /**
     * Metodo setTitulo
     * @param titulo Titulo del libro
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    /**
     * Metodo getAutor
     * @return devuelve el autor del libro
     */
    public String getAutor(){
        return autor;
    }

    /**
     * Metodo toString que devuelve una cadena con los atributos del libro
     * @return devuelve una cadena con los atributos del libro
     */
    public String toArchivoString() {
        return ISBN + "," + titulo + "," + autor;
    }

    /**
     * Metodo fromString que recibe una cadena y devuelve un libro
     * @param linea cadena con los atributos del libro
     * @return devuelve un libro
     */
    public static Libro fromString(String linea) {
        String[] partes = linea.split(",");
        return new Libro(partes[0], partes[1], partes[2]);
    }
}
