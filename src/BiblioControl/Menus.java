package BiblioControl;

import java.util.ArrayList;

/**
 * Interfaz Menus
 * Contiene el metodo Menu que se implementa en UsuarioBiblioteca y Admin
 */
public interface Menus {
/**
     * Metodo Menu
     * @param Usuarios ArrayList de usuarios de la biblioteca
     * @param Libros ArrayList de libros de la biblioteca
     */
    void Menu(ArrayList<UsuarioBiblioteca> Usuarios, ArrayList<Libro> Libros);
}
