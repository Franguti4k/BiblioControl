package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para gestionar las peticiones de libros
 */
public class GestionarPeticionesGUI extends JFrame implements ActionListener {
    private JList<String> listPeticiones;
    private JButton btnAñadir, btnEliminar, btnSalir;
    private ArrayList<Libro> libros, peticiones;

    /**
     * Constructor de la clase ManageRequestsGUI
     * @param libros ArrayList de libros
     * @param peticiones ArrayList de peticiones
     */
    public GestionarPeticionesGUI(ArrayList<Libro> libros, ArrayList<Libro> peticiones) {
        this.libros = libros;
        this.peticiones = peticiones;

        setTitle("Gestionar Peticiones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        listPeticiones = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listPeticiones);
        scrollPane.setBackground(Admin.getInstance().getColorFondo());

        actualizarListaPeticiones();

        btnAñadir = new JButton("Añadir Libro");
        btnEliminar = new JButton("Eliminar Petición");
        btnSalir = new JButton("Salir");

        btnAñadir.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnSalir.addActionListener(this);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Admin.getInstance().getColorFondo());
        panelBotones.add(btnAñadir);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSalir);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        // Si se pulsa el botón de añadir, se añade el libro a la biblioteca
        if (e.getSource() == btnAñadir) {
            int selectedIndex = listPeticiones.getSelectedIndex();
                // Seleccionamos el libro, capturamos los datos y lo añadimos a la biblioteca
                Libro libroSeleccionado = peticiones.get(selectedIndex);
                String isbn = libroSeleccionado.getISBN();
                String titulo = libroSeleccionado.getTitulo();
                String autor = libroSeleccionado.getAutor();
                Admin.getInstance().addLibro(isbn, titulo, autor, Admin.getInstance().getLibros(), true); // true porque viene de peticiones

            // Actualizar las JList
            GestorDeArchivos.guardarLibros(libros);
            GestorDeArchivos.guardarPeticiones(peticiones);
            actualizarListaPeticiones();
        } else if (e.getSource() == btnEliminar) {
            // Eliminar la petición seleccionada
            int selectedIndex = listPeticiones.getSelectedIndex();
            Admin.getInstance().eliminarPeticion(peticiones, selectedIndex);
            // Guardamos los cambios en los archivos
            GestorDeArchivos.guardarLibros(libros);
            GestorDeArchivos.guardarPeticiones(peticiones);
            actualizarListaPeticiones();
        } else if (e.getSource() == btnSalir) {
            dispose();
        }
    }

    /**
     * Método que actualiza la lista de peticiones
     */
    private void actualizarListaPeticiones() {
        String[] peticionesArray = new String[peticiones.size()];
        for (int i = 0; i < peticiones.size(); i++) {
            peticionesArray[i] = peticiones.get(i).getTitulo() + " (ISBN: " + peticiones.get(i).getISBN() + ")";
        }
        listPeticiones.setListData(peticionesArray);
    }
}
