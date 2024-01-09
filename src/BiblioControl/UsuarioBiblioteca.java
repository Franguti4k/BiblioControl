package BiblioControl;

import java.util.ArrayList;

/**
 * Clase Usuario
 * Contiene los atributos de un usuario y sus metodos get y set ademas del menu de usuario
 */
public class UsuarioBiblioteca extends Usuario implements Menus{
    private ArrayList<Libro> librosReservados;
    private String DNI;
    private String nombre;
    private String password;
    private String pistaPassword;

    /**
     * Constructor de la clase Usuario
     * @param DNI DNI del usuario
     * @param nombre Nombre del usuario
     * @param password Contraseña del usuario
     * @param pistaPassword Pista de la contraseña del usuario
     */
    public UsuarioBiblioteca(String DNI, String nombre, String password, String pistaPassword) {
        super(DNI, nombre, password, pistaPassword);
        this.DNI = DNI;
        this.nombre = nombre;
        this.password = password;
        this.pistaPassword = pistaPassword;
        this.librosReservados = new ArrayList<>();
    }

    /**
     * Método para reservar un libro por su ISBN.
     * @param isbn ISBN del libro a reservar.
     * @param biblioteca Lista de libros en la biblioteca.
     * @return Un mensaje indicando el resultado de la reserva.
     */
    public String reservarLibro(String isbn, ArrayList<Libro> biblioteca) {
        for (Libro libro : biblioteca) {
            if (libro.getISBN().equals(isbn)) {
                librosReservados.add(libro);
                biblioteca.remove(libro);
                GestorDeArchivos.guardarLibros(biblioteca); // Actualiza el archivo de libros
                GestorDeArchivos.guardarUsuarios(Admin.getInstance().getUsuarios()); // Actualiza el archivo de usuarios
                return "Has reservado el libro: " + libro.getTitulo();
            }
        }
        return "El libro no está disponible o no existe.";
    }

    /**
     * Método para devolver un libro por ISBN.
     * @param isbn ISBN del libro a devolver.
     * @param biblioteca Biblioteca de la que se devuelve el libro.
     * @return Mensaje con el resultado de la operación.
     */
    public String devolverLibro(String isbn, ArrayList<Libro> biblioteca) {
        Libro libroADevolver = null;
        for (Libro libro : librosReservados) {
            if (libro.getISBN().equals(isbn)) {
                libroADevolver = libro;
                break;
            }
        }
        if (libroADevolver != null) {
            librosReservados.remove(libroADevolver);
            biblioteca.add(libroADevolver);
            return "Has devuelto el libro: " + libroADevolver.getTitulo();
        }
        return "No has reservado este libro o no existe.";
    }


    /**
     * Método para solicitar un libro por su ISBN, título y autor.
     * @param isbn ISBN del libro a solicitar.
     * @param titulo Título del libro a solicitar.
     * @param autor Autor del libro a solicitar.
     * @param biblioteca Lista de libros en la biblioteca.
     * @return Un mensaje indicando el resultado de la solicitud.
     */
    public String solicitarLibro(String isbn, String titulo, String autor, ArrayList<Libro> biblioteca) {
        Admin admin = Admin.getInstance();

        // Comprobar si el libro ya está en la biblioteca o en las peticiones
        if (admin.libroExiste(isbn, biblioteca)) {
            return "El libro ya está en la biblioteca.";
        } else if (admin.peticionExiste(isbn)) {
            return "El libro ya ha sido solicitado.";
        }

        // El libro no existe en la biblioteca ni en las peticiones, se puede solicitar
        Libro libroSolicitado = new Libro(isbn, titulo, autor);
        admin.getPeticiones().add(libroSolicitado);

        // Guardar las peticiones actualizadas en el archivo
        GestorDeArchivos.guardarPeticiones(admin.getPeticiones());

        return "Has solicitado el libro: " + titulo;
    }

    /**
     * Método que cambia la contraseña del usuario actual
     * @param passwordActual La contraseña actual
     * @param passwordNueva La nueva contraseña
     */
    public void cambiarPassword(String passwordActual, String passwordNueva) throws PasswordIncorrectaException {
        if (!ComprobarPassword(passwordActual)) {
            throw new PasswordIncorrectaException("Contraseña actual incorrecta");
        }
        setPassword(passwordNueva);
        System.out.println("Contraseña cambiada correctamente");
    }

    /**
     * Excepción propia para cuando la contraseña actual es incorrecta
     */
    public class PasswordIncorrectaException extends Exception {
        public PasswordIncorrectaException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Metodo que devuelve un String con los libros reservados a partir de un ArrayList
     * Se utiliza para poder devolver los libros reservados en DevolverLibroGUI
     * @return Devuelve un String con los libros reservados
     */
    public String[] getLibrosReservados() {
        String[] librosReservadosArray = new String[this.librosReservados.size()];
        for (int i = 0; i < this.librosReservados.size(); i++) {
            Libro libro = this.librosReservados.get(i);
            librosReservadosArray[i] = libro.getTitulo() + " - ISBN: " + libro.getISBN();
        }
        return librosReservadosArray;
    }

    /**
     * Método que busca un usuario por su DNI
     * @param DNI DNI del usuario
     * @param Usuarios ArrayList de usuarios
     * @return El usuario encontrado o null si no se encuentra
     */
    public static UsuarioBiblioteca buscarUsuarioPorDNI(String DNI, ArrayList<UsuarioBiblioteca> Usuarios) {
        if (Usuarios == null) {
            return null;
        }
        for (UsuarioBiblioteca usuario : Usuarios) {
            if (usuario.getDNI().equals(DNI)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Metodo toArchivoString que devuelve un string con los datos del usuario
     * @return Devuelve un string con los datos del usuario
     */
    public String toArchivoString() {
        // Convierte los libros reservados en una cadena
        String reservados = "";
        for (int i = 0; i < librosReservados.size(); i++) {
            Libro libro = librosReservados.get(i);
            // Convierte cada libro en una cadena con el formato ISBN|Título|Autor
            String libroInfo = libro.getISBN() + "|" + libro.getTitulo() + "|" + libro.getAutor();

            // Añade el libro a la cadena de reservados
            reservados += libroInfo;

            // Añade un punto y coma después de cada libro, excepto después del último
            if (i < librosReservados.size() - 1) {
                reservados += ";";
            }
        }

        // Devuelve una cadena con el formato DNI,nombre,password,pistaPassword y con los libros reservados preparados anteriormente
        return DNI + "," + nombre + "," + password + "," + pistaPassword + "," + reservados;
    }

    /**
     * Metodo fromString que crea un usuario a partir de una linea del archivo de usuarios
     * @param linea  Linea de texto
     * @return Devuelve un usuario
     */
    public static UsuarioBiblioteca fromString(String linea) {
        // Divide la línea por comas y crea un usuario con los datos
        String[] partes = linea.split(",");
        UsuarioBiblioteca usuario = new UsuarioBiblioteca(partes[0], partes[1], partes[2], partes[3]);
        // Si hay una cuarta parte, es que tiene libros reservados, que los divide por punto y coma
        if (partes.length > 4 && !partes[4].isEmpty()) {
            String[] reservas = partes[4].split(";");
            // Crea un libro por cada reserva y lo añade a los libros reservados del usuario actual
            for (String reserva : reservas) {
                String[] detallesLibro = reserva.split("\\|");
                Libro libroReservado = new Libro(detallesLibro[0], detallesLibro[1], detallesLibro[2]);
                usuario.librosReservados.add(libroReservado);
            }
        }
        return usuario;
    }

    /**
     * Metodo buscarLibrosPorTitulo
     * Busca libros por titulo pero dentro de la biblioteca
     * @param titulo Titulo del libro
     * @param biblioteca ArrayList de libros
     * @return Devuelve un ArrayList de libros
     */
    public ArrayList<String> buscarLibrosPorTitulo(String titulo, ArrayList<Libro> biblioteca) {
        ArrayList<String> resultados = new ArrayList<>();
        for (Libro libro : biblioteca) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(libro.getTitulo() + " - ISBN: " + libro.getISBN());
            }
        }
        return resultados;
    }

    /**
     * Metodo Menu
     * Inicia una interfaz grafica para el usuario
     * @param Usuarios ArrayList de usuarios
     * @param Libros ArrayList de libros
     */
    public void Menu(ArrayList<UsuarioBiblioteca> Usuarios, ArrayList<Libro> Libros) {
        new InterfazUsuario(this); // Abre la interfaz gráfica del usuario
    }
}