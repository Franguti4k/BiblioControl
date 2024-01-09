package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para añadir un libro
 */
public class AddLibroGUI extends JFrame implements ActionListener {
    private JTextField txtTitulo;
    private JButton btnBuscar, btnCancelar, btnAgregar;
    private JList<String> listResultados;
    private ArrayList<Libro> biblioteca, resultadosBusqueda;

    /**
     * Constructor de la clase AddLibroGUI
     * @param biblioteca ArrayList de libros de la biblioteca
     */
    public AddLibroGUI(ArrayList<Libro> biblioteca) {
        this.biblioteca = biblioteca;

        // Configuración del JFrame
        setTitle("Añadir Libro");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Admin.getInstance().getColorFondo());

        // Crear y configurar JPanel superior con GridLayout
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(1, 2, 10, 10));

        panelSuperior.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panelSuperior.add(txtTitulo);

        btnBuscar = new JButton("Buscar");
        panelSuperior.add(btnBuscar);

        listResultados = new JList<>();
        add(new JScrollPane(listResultados), BorderLayout.CENTER);

        // Crear y configurar JPanel inferior
        JPanel panelBotones = new JPanel();
        btnCancelar = new JButton("Cancelar");
        btnAgregar = new JButton("Agregar");

        btnBuscar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnAgregar.addActionListener(this);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnCancelar);

        // Añadir paneles al JFrame
        add(panelSuperior, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        // Si el evento es el botón de buscar se busca en internet y se actualiza la JList
        if (e.getSource() == btnBuscar) {
            String titulo = txtTitulo.getText();
            resultadosBusqueda = BusquedaInternet.buscarPorTitulo(titulo);
            Admin.getInstance().actualizarListaResultados(resultadosBusqueda, listResultados);
        } else if (e.getSource() == btnCancelar) {
            dispose();
        // Si el evento es el botón de agregar se agrega el libro seleccionado a la biblioteca
        } else if (e.getSource() == btnAgregar) {
            int selectedIndex = listResultados.getSelectedIndex();
            if (selectedIndex != -1) {
                Libro libroSeleccionado = resultadosBusqueda.get(selectedIndex);
                String isbn = libroSeleccionado.getISBN();
                String titulo = libroSeleccionado.getTitulo();
                String autor = libroSeleccionado.getAutor();

                // Se verifica si el libro es de petición o no y se agrega a la biblioteca
                boolean esDePeticion = Admin.getInstance().peticionExiste(isbn);
                Admin.getInstance().addLibro(isbn, titulo, autor, biblioteca, esDePeticion);
                JOptionPane.showMessageDialog(this, "Libro agregado correctamente.");
            }
        }
    }
}
