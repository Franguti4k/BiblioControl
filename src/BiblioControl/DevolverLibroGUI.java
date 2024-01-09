package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para devolver un libro
 */
public class DevolverLibroGUI extends JFrame implements ActionListener {
    private JButton btnDevolver, btnCancelar;
    private JList<String> listLibrosReservados;
    private UsuarioBiblioteca usuarioActual;
    private ArrayList<Libro> biblioteca;

    /**
     * Constructor de la clase DevolverLibroGUI
     * @param usuario el usuario
     * @param biblioteca ArrayList de libros de la biblioteca
     */
    public DevolverLibroGUI(UsuarioBiblioteca usuario, ArrayList<Libro> biblioteca) {
        this.usuarioActual = usuario;
        this.biblioteca = biblioteca;

        // Configuración del JFrame
        setTitle("Devolver Libro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Admin.getInstance().getColorFondo());

        // Lista de libros reservados
        listLibrosReservados = new JList<>();
        panel.add(new JScrollPane(listLibrosReservados), BorderLayout.CENTER);
        listLibrosReservados.setListData(usuarioActual.getLibrosReservados());

        // Botones
        btnDevolver = new JButton("Devolver");
        btnCancelar = new JButton("Cancelar");

        btnDevolver.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Creamos el JPanel y añadimos los botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnDevolver);
        panelBotones.add(btnCancelar);

        // Añadir paneles al JFrame
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDevolver) {
            int selectedIndex = listLibrosReservados.getSelectedIndex();
            if (selectedIndex != -1) {
                // Se obtiene el ISBN del libro seleccionado y se devuelve el libro
                String seleccionado = listLibrosReservados.getSelectedValue();
                String isbn = seleccionado.split(" - ISBN: ")[1]; // Se divide el string por " - ISBN: " y se obtiene el segundo elemento
                String mensaje = usuarioActual.devolverLibro(isbn, biblioteca);

                // Se guardan los cambios en los archivos
                GestorDeArchivos.guardarLibros(biblioteca);
                GestorDeArchivos.guardarUsuarios(Admin.getInstance().getUsuarios());
                JOptionPane.showMessageDialog(this, mensaje);
                listLibrosReservados.setListData(usuarioActual.getLibrosReservados());
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro de la lista.");
            }
        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }
}
