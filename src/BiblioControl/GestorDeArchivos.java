package BiblioControl;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa el gestor de archivos
 * Contiene los métodos para guardar y cargar los archivos de la biblioteca
 */
public class GestorDeArchivos {

    // Ruta de los archivos (ponemos final para que no se puedan modificar)
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    private static final String ARCHIVO_LIBROS = "libros.txt";
    private static final String ARCHIVO_PETICIONES = "peticiones.txt";

    /**
     * Metodo guardarUsuarios que guarda los usuarios en un archivo
     * @param usuarios ArrayList de usuarios
     */
    public static void guardarUsuarios(ArrayList<UsuarioBiblioteca> usuarios) {
        try (PrintWriter out = new PrintWriter(new FileWriter(ARCHIVO_USUARIOS))) {
            for (UsuarioBiblioteca usuario : usuarios) {
                out.println(usuario.toArchivoString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo cargarUsuarios que carga los usuarios de un archivo
     * @return devuelve un ArrayList de usuarios
     */
    public static ArrayList<UsuarioBiblioteca> cargarUsuarios() {
        // Creamos un ArrayList de usuarios
        ArrayList<UsuarioBiblioteca> usuarios = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(ARCHIVO_USUARIOS))) {
            // Mientras que haya una siguiente linea, la añadimos al ArrayList
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                usuarios.add(UsuarioBiblioteca.fromString(linea));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Metodo guardarLibros que guarda los libros en un archivo
     * @param libros ArrayList de libros
     */
    public static void guardarLibros(ArrayList<Libro> libros) {
        try (PrintWriter out = new PrintWriter(new FileWriter(ARCHIVO_LIBROS))) {
            for (Libro libro : libros) {
                out.println(libro.toArchivoString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo cargarLibros que carga los libros de un archivo
     * @return devuelve un ArrayList de libros
     */
    public static ArrayList<Libro> cargarLibros() {
        ArrayList<Libro> libros = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(ARCHIVO_LIBROS))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                libros.add(Libro.fromString(linea));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return libros;
    }

    /**
     * Metodo guardarPeticiones que guarda las peticiones en un archivo
     * @param peticiones ArrayList de peticiones
     */
    public static void guardarPeticiones(ArrayList<Libro> peticiones) {
        try (PrintWriter out = new PrintWriter(new FileWriter(ARCHIVO_PETICIONES))) {
            for (Libro libro : peticiones) {
                out.println(libro.toArchivoString()); // Guardamos las peticiones como libros
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo cargarPeticiones que carga las peticiones de un archivo
     * @return devuelve un ArrayList de peticiones
     */
    public static ArrayList<Libro> cargarPeticiones() {
        ArrayList<Libro> peticiones = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(ARCHIVO_PETICIONES))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                peticiones.add(Libro.fromString(linea));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return peticiones;
    }
}
