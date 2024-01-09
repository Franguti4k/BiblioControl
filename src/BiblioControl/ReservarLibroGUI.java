package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * Clase que representa la interfaz gráfica para reservar un libro
 */
public class ReservarLibroGUI extends JFrame implements ActionListener {
    private JTextField txtTitulo;
    private JButton btnBuscar, btnCancelar, btnReservar;
    private JList<String> listResultados;
    private UsuarioBiblioteca usuarioActual;
    private ArrayList<Libro> biblioteca;

    /**
     * Constructor de la clase ReservarLibroGUI
     * @param usuario Usuario actual donde se guardara el libro reservado
     * @param biblioteca ArrayList de libros de la biblioteca
     */
    public ReservarLibroGUI(UsuarioBiblioteca usuario, ArrayList<Libro> biblioteca) {
        this.usuarioActual = usuario;
        this.biblioteca = biblioteca;

        setTitle("Buscar y Reservar Libro");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(1, 3, 10, 10));
        panelSuperior.setBackground(Admin.getInstance().getColorFondo());
        panelSuperior.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panelSuperior.add(txtTitulo);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this);
        panelSuperior.add(btnBuscar);

        add(panelSuperior, BorderLayout.NORTH);

        listResultados = new JList<>();
        add(new JScrollPane(listResultados), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Admin.getInstance().getColorFondo());
        btnReservar = new JButton("Reservar");
        btnCancelar = new JButton("Cancelar");

        btnReservar.addActionListener(this);
        btnCancelar.addActionListener(this);

        panelBotones.add(btnReservar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            // Buscar libros por título dentro de la biblioteca añadiendolos a un ArrayList
            String titulo = txtTitulo.getText();
            ArrayList<String> resultados = usuarioActual.buscarLibrosPorTitulo(titulo, biblioteca);
            // Convertomos el ArrayList a un Array de Strings para poder mostrarlo en la JList
            String[] resultadosArray = new String[resultados.size()];
            resultados.toArray(resultadosArray);
            listResultados.setListData(resultadosArray);
        } else if (e.getSource() == btnCancelar) {
            dispose();
        } else if (e.getSource() == btnReservar) {
            String seleccionado = listResultados.getSelectedValue();
            if (seleccionado != null && !seleccionado.isEmpty()) {
                // Seguimos la misma lógica que en devolverLibro para obtener el ISBN del libro seleccionado
                String isbn = seleccionado.split(" - ISBN: ")[1];
                String resultadoReserva = usuarioActual.reservarLibro(isbn, biblioteca);
                JOptionPane.showMessageDialog(this, resultadoReserva);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro de la lista.");
            }
        }
    }
}