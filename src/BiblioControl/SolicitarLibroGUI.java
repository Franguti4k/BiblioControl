package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para solicitar un libro
 */
public class SolicitarLibroGUI extends JFrame implements ActionListener {
    private JTextField txtTitulo;
    private JButton btnBuscar, btnCancelar, btnSolicitar;
    private JList<String> listResultados;
    private UsuarioBiblioteca usuarioActual;
    private ArrayList<Libro> biblioteca;
    private ArrayList<Libro> resultadosBusqueda; // Almacenar los resultados de la búsqueda

    /**
     * Constructor de la clase SolicitarLibroGUI
     * @param usuario Usuario actual donde se guardara el libro reservado
     * @param biblioteca ArrayList de libros de la biblioteca
     */
    public SolicitarLibroGUI(UsuarioBiblioteca usuario, ArrayList<Libro> biblioteca) {
        this.usuarioActual = usuario;
        this.biblioteca = biblioteca;
        this.resultadosBusqueda = new ArrayList<>();

        setTitle("Solicitar Libro");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Superior
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.setBackground(Admin.getInstance().getColorFondo());
        panelSuperior.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panelSuperior.add(txtTitulo);
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this);
        panelSuperior.add(btnBuscar);
        add(panelSuperior, BorderLayout.NORTH);

        // Lista de Resultados
        listResultados = new JList<>();
        add(new JScrollPane(listResultados), BorderLayout.CENTER);

        // Panel de Botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Admin.getInstance().getColorFondo());
        btnSolicitar = new JButton("Solicitar");
        btnCancelar = new JButton("Cancelar");
        btnSolicitar.addActionListener(this);
        btnCancelar.addActionListener(this);
        panelBotones.add(btnSolicitar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de los botones
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            // Si le damos a boton buscar, se busca el libro por titulo ingresado en la interfaz
            String titulo = txtTitulo.getText();
            resultadosBusqueda = BusquedaInternet.buscarPorTitulo(titulo);
            Admin.getInstance().actualizarListaResultados(resultadosBusqueda, listResultados);
        } else if (e.getSource() == btnCancelar) {
            dispose(); // Si le damos a boton cancelar, se cierra la ventana
        } else if (e.getSource() == btnSolicitar) {
            // Si le damos a SOlicitar, se solicita el libro seleccionado en la lista
            int selectedIndex = listResultados.getSelectedIndex();
            if (selectedIndex != -1) {
                // Capturamos los datos del libro seleccionado
                Libro libroSeleccionado = resultadosBusqueda.get(selectedIndex);
                String isbn = libroSeleccionado.getISBN();
                String titulo = libroSeleccionado.getTitulo();
                String autor = libroSeleccionado.getAutor();

                // Solicitamos el libro con los datos capturados
                String mensaje = usuarioActual.solicitarLibro(isbn, titulo, autor, biblioteca);
                JOptionPane.showMessageDialog(this, mensaje);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro de la lista");
            }
        }
    }

}
