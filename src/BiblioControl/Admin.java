package BiblioControl;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

// Bibliotecas para el sonido
import javax.sound.sampled.*;

/**
 * Clase Admin que hereda de Usuario.
 * Contiene los metodos para añadir y eliminar usuarios y libros, y para gestionar el sonido de la sala y las peticiones.
 */
    public class Admin extends Usuario implements Menus {
    private static Admin instance;
    private ArrayList<Libro> Peticiones, Libros;
    private ArrayList<UsuarioBiblioteca> Usuarios;
    private double umbralSonido = 60.0; // Valor predeterminado del umbral
    private static Color colorFondo = Color.WHITE; // Color por defecto

    /**
     * Constructor
     * @param DNI DNI del administrador
     * @param password Contraseña del administrador
     */
    public Admin(String DNI, String password){
        super(DNI, "", password, "");
    }

    /**
     * Metodo para obtener la instancia de Admin (Singleton)
     * @return devuelve la instancia de Admin
     */
    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin("admin", "adminpassword");
        }
        return instance;
    }

    /**
     * Metodo para inicializar el sistema
     * Carga los usuarios, libros y peticiones de los archivos
     */
    public void inicializarSistema() {
        setUsuarios(GestorDeArchivos.cargarUsuarios());
        setLibros(GestorDeArchivos.cargarLibros());
        setPeticiones(GestorDeArchivos.cargarPeticiones());
    }

    /**
     * Metodo para establecer el umbral de sonido
     * @param nuevoUmbral umbral de sonido
     */
    public void setUmbralSonido(double nuevoUmbral) {
        umbralSonido = nuevoUmbral;
    }

    /**
     * Metodo para obtener el umbral de sonido
     * @return devuelve el umbral de sonido
     */
    public double getUmbralSonido() { return umbralSonido; }

    /**
     * Metodo setPeticiones
     * @param Peticiones ArrayList de peticiones
     */
    public void setPeticiones(ArrayList<Libro> Peticiones) { this.Peticiones = Peticiones; }

    /**
     * Metodo getPeticiones
     * @return devuelve el ArrayList de peticiones
     */
    public ArrayList<Libro> getPeticiones() { return Peticiones; }

    /**
     * Metodo getLibros
     * @return devuelve el ArrayList de libros
     */
    public ArrayList<Libro> getLibros() {
        return Libros;
    }

    /**
     * Metodo setLibros
     * @param Libros ArrayList de libros
     */
    public void setLibros(ArrayList<Libro> Libros) {
        this.Libros = Libros;
    }

    /**
     * Metodo getUsuarios
     * @return devuelve el ArrayList de usuarios
     */
    public  ArrayList<UsuarioBiblioteca> getUsuarios() {
        return Usuarios;
    }

    /**
     * Metodo setUsuarios
     * @param Usuarios ArrayList de usuarios
     */
    public void setUsuarios(ArrayList<UsuarioBiblioteca> Usuarios) {
        this.Usuarios = Usuarios;
    }

    /**
     * Método para establecer el color de fondo
     * @param nuevoColor el nuevo color de fondo
     */
    public void setColorFondo(Color nuevoColor) {
        colorFondo = nuevoColor;
    }

    /**
     * Método para obtener el color de fondo
     * @return el color de fondo
     */
    public Color getColorFondo() {
        return colorFondo;
    }

    /**
     * Metodo para añadir un libro
     * @param isbn ISBN del libro
     * @param titulo Titulo del libro
     * @param autor Autor del libro
     * @param biblioteca ArrayList de libros de la biblioteca
     * @param esDePeticion booleano que indica si el libro viene de las peticiones
     */
    public void addLibro(String isbn, String titulo, String autor, ArrayList<Libro> biblioteca, boolean esDePeticion) {
        // Si el libro no esta la biblioteca creamos un nuevo libro con los datos pasados por parametro y lo agregamos a la biblioteca
        if (!libroExiste(isbn, biblioteca)) {
            Libro nuevoLibro = new Libro(isbn, titulo, autor);
            biblioteca.add(nuevoLibro);
            GestorDeArchivos.guardarLibros(biblioteca);

            // Si el libro que se quiere añadir esta en la lista de peticiones, se elimina de la lista de peticiones
            if (esDePeticion) {
                for (int i = 0; i < Peticiones.size(); i++) {
                    if (Peticiones.get(i).getISBN().equals(isbn)) {
                        eliminarPeticion(Peticiones, i);
                        break;
                    }
                }
                GestorDeArchivos.guardarPeticiones(Peticiones);
            }
        }
    }

    /**
     * Método para eliminar un libro del ArrayList de libros.
     * @param isbn ISBN del libro a eliminar.
     * @param libros ArrayList de libros de la biblioteca.
     * @return Mensaje indicando el resultado de la operación.
     */
    public String eliminarLibro(String isbn, ArrayList<Libro> libros) {
        // Buscar el libro dentro de la biblioteca y eliminarlo
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getISBN().equals(isbn)) {
                libros.remove(i);
                return "Libro eliminado con éxito";
            }
        }
        return "No se encontró un libro con ese ISBN";
    }

    /**
     * Metodo para añadir un usuario
     * @param DNI DNI del usuario
     * @param nombre Nombre del usuario
     * @param password Contraseña del usuario
     * @param pistaPassword Pista de la contraseña del usuario
     * @param Usuarios ArrayList de usuarios de la biblioteca
     */
    public void addUsuario(String DNI, String nombre, String password, String pistaPassword, ArrayList<UsuarioBiblioteca> Usuarios) throws DNIInvalidoException, UsuarioYaExisteException {
        // Validar el DNI
        if (!UsuarioBiblioteca.validarDNI(DNI)) {
            throw new DNIInvalidoException("El DNI introducido no es válido.");
        }
        // Comprobar si el usuario ya existe
        for (UsuarioBiblioteca usuario : Usuarios) {
            if (usuario.getDNI().equals(DNI)) {
                throw new UsuarioYaExisteException("Un usuario con este DNI ya existe.");
            }
        }
        // Crear un nuevo usuario y agregarlo a la lista
        UsuarioBiblioteca nuevoUsuario = new UsuarioBiblioteca(DNI, nombre, password, pistaPassword);
        Usuarios.add(nuevoUsuario);
    }

    /**
     * Método para eliminar un usuario.
     * @param Usuarios ArrayList de usuarios de la biblioteca.
     * @param DNI DNI del usuario a eliminar.
     * @param password Contraseña del administrador.
     * @return Mensaje indicando el resultado de la operación.
     */
    public String eliminarUsuario(ArrayList<UsuarioBiblioteca> Usuarios, String DNI, String password) {
        // Si la contraseña es correcta pasamos a buscar el usuario
        if (this.ComprobarPassword(password)) {
            boolean usuarioEncontrado = false;
            // Buscar el usuario y eliminarlo
            for (int i = 0; i < Usuarios.size(); i++) {
                if (Usuarios.get(i).getDNI().equals(DNI)) {
                    Usuarios.remove(i);
                    usuarioEncontrado = true;
                    break;
                }
            }
            // Devolvemos un mensaje dependiendo de si se encontró o no el usuario
            if (usuarioEncontrado) {
                return "Usuario eliminado con éxito";
            } else {
                return "Usuario no encontrado";
            }
        } else {
            return "Contraseña del administrador incorrecta";
        }
    }

    /**
     * Metodo para comprobar si existe una peticion
     * @param isbn ISBN del libro que se va a comprobar si esta en las peticiones
     * @return devuelve true si la peticion existe, false si no existe
     */
    public boolean peticionExiste(String isbn) {
        // Buscar el libro en las peticiones y devolver true si se encuentra
        for (Libro libro : getPeticiones()) {
            if (libro.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para eliminar una petición.
     * @param peticiones ArrayList de peticiones.
     * @param index Índice de la petición seleccionada.
     */
    public void eliminarPeticion(ArrayList<Libro> peticiones, int index) {
        // Si se ha seleccionado una petición, eliminarla
        if (index != -1) {
            peticiones.remove(index);
        }
    }

    /**
     * Metodo para medir el nivel de sonido de la sala
     * @param gui Interfaz gráfica de la ventana de gestión de sonido
     */
    public void medirNivelDeSonido(GestionarSonidoGUI gui) {
        // Se utiliza un hilo para no bloquear la interfaz gráfica
        // Tambien se usa invokeLater para actualizar la interfaz gráfica desde el hilo
        new Thread(() -> {
            try {
                // Obtener el número de segundos de la interfaz gráfica y crear un TargetDataLine para leer el audio del micrófono con el formato especificado
                int segundos = Integer.parseInt(gui.getTxtSegundos().getText());
                AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

                // Si el micrófono no es soportado, mostrar un mensaje de error en la interfaz gráfica
                if (!AudioSystem.isLineSupported(info)) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(gui, "Micrófono no soportado", "Error", JOptionPane.ERROR_MESSAGE);
                    });
                    return;
                }

                // Abrir el TargetDataLine y leer los datos del micrófono
                TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
                line.open(format);
                line.start();

                // Guardamos los datos del micrófono en un buffer y calculamos el nivel de sonido
                byte[] buffer = new byte[1024];
                long endTime = System.currentTimeMillis() + segundos * 1000;
                double umbral = Admin.getInstance().getUmbralSonido();

                // Mientras no se haya alcanzado el tiempo límite, leer los datos del micrófono y calcular el nivel de sonido
                while (System.currentTimeMillis() < endTime) {
                    int bytesRead = line.read(buffer, 0, buffer.length);
                    double level = calculateLevel(buffer, bytesRead);
                    SwingUtilities.invokeLater(() -> {
                        if (level > umbral) {
                            gui.getLblNivelSonido().setForeground(Color.RED);
                            gui.getLblNivelSonido().setText("SILENCIO, POR FAVOR - " + String.format("%.2f dB", level));
                        } else {
                            gui.getLblNivelSonido().setForeground(Color.BLACK);
                            gui.getLblNivelSonido().setText("Nivel de Sonido: " + String.format("%.2f dB", level));
                        }
                    });
                }
                line.stop();
                line.close();

            } catch (NumberFormatException ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(gui, "Por favor, ingresa un número válido de segundos.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                });
            } catch (LineUnavailableException ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(gui, "Línea de audio no disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }

    /**
     * Metodo para calcular el nivel de sonido
     * Como el sonido no se obtiene directamente en decibelios, se calcula el valor RMS (Root Mean Square) de la señal de audio y se convierte a decibelios
     * @param buffer Array de bytes que contiene los datos de audio del micrófono
     * @param bytesRead Número de bytes leídos del micrófono en el último ciclo
     * @return devuelve el nivel de sonido en decibelios
     */
    public double calculateLevel(byte[] buffer, int bytesRead) {
        double sum = 0.0;

        for (int i = 0; i < bytesRead; i++) {
            sum += buffer[i] * buffer[i];
        }
        // Calcular el valor RMS (Root Mean Square) de la señal de audio y convertirlo a decibelios
        double rms = Math.sqrt(sum / bytesRead);
        double dB = 20 * Math.log10(rms);
        return dB;
    }

    /**
     * Excepcion para cuando el DNI no es valido
     */
    public static class DNIInvalidoException extends Exception {
        public DNIInvalidoException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Excepcion para cuando el usuario ya existe
     */
    public static class UsuarioYaExisteException extends Exception {
        public UsuarioYaExisteException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Excepcion para cuando hay campos vacios
     */
    public static class CamposVaciosException extends Exception {
        public CamposVaciosException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Metodo para comprobar si un libro existe
     * @param isbn ISBN del libro
     * @param libros ArrayList de libros de la biblioteca
     * @return devuelve true si el libro existe, false si no existe
     */
    public boolean libroExiste(String isbn, ArrayList<Libro> libros) {
        for (Libro libro : libros) {
            if (libro.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    public void actualizarListaResultados(ArrayList<Libro> libros, JList<String> lista) {
        // Creamos un array de Strings con los resultados de la búsqueda para mostrarlos en la JList
        String[] resultadosArray = new String[libros.size()];
        // Vamos agregando los resultados al array
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            resultadosArray[i] = libro.getTitulo() + " - ISBN: " + libro.getISBN();
        }
        // Mostramos en la JList los resultados de la búsqueda
        lista.setListData(resultadosArray);
    }

    /**
     * Metodo que muestra el menu de administrador
     * Desde aqui se pueden añadir y eliminar usuarios y libros, gestionar el sonido de la sala y las peticiones
     * @param Usuarios ArrayList de usuarios de la biblioteca
     * @param Libros ArrayList de libros de la biblioteca
     */
    public void Menu(ArrayList<UsuarioBiblioteca> Usuarios, ArrayList<Libro> Libros){
        new InterfazAdmin();
    }
}