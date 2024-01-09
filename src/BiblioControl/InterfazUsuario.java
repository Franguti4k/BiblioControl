package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para el usuario
 */
public class InterfazUsuario extends JFrame implements ActionListener {
    private UsuarioBiblioteca usuarioActual;
    private JButton btnReservarLibro, btnSolicitarLibro, btnDevolverLibro, btnCambiarPassword, btnSalir;

    /**
     * Constructor de la clase InterfazUsuario que recibe un usuario y muestra la interfaz gráfica para el usuario
     * @param usuario el usuario
     */
    public InterfazUsuario(UsuarioBiblioteca usuario) {
        this.usuarioActual = usuario;
        // Titulo y tamaño de la ventana
        setTitle("Menú Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(210, 250);

        // Creación del panel
        JPanel panel = new JPanel();
        panel.setBackground(Admin.getInstance().getColorFondo());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // JLabel de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido, " + usuarioActual.getNombre());
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lblBienvenida);

        // Inicialización y configuración de los botones
        btnReservarLibro = new JButton("Reservar Libro");
        btnSolicitarLibro = new JButton("Solicitar Libro");
        btnDevolverLibro = new JButton("Devolver Libro");
        btnCambiarPassword = new JButton("Cambiar Contraseña");
        btnSalir = new JButton("Salir");

        Dimension buttonSize = new Dimension(70, 50);
        btnReservarLibro.setPreferredSize(buttonSize);
        btnSolicitarLibro.setPreferredSize(buttonSize);
        btnDevolverLibro.setPreferredSize(buttonSize);
        btnCambiarPassword.setPreferredSize(buttonSize);
        btnSalir.setPreferredSize(buttonSize);

        btnReservarLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSolicitarLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDevolverLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCambiarPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadimos los botones al panel
        panel.add(btnReservarLibro);
        panel.add(btnSolicitarLibro);
        panel.add(btnDevolverLibro);
        panel.add(btnCambiarPassword);
        panel.add(btnSalir);

        // Añadimos los listeners a los botones
        btnReservarLibro.addActionListener(this);
        btnSolicitarLibro.addActionListener(this);
        btnDevolverLibro.addActionListener(this);
        btnCambiarPassword.addActionListener(this);
        btnSalir.addActionListener(this);

        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReservarLibro) {
            new ReservarLibroGUI(usuarioActual,Admin.getInstance().getLibros()); // Abre la ventana para reservar un libro
        } else if (e.getSource() == btnSolicitarLibro) {
            new SolicitarLibroGUI(usuarioActual, Admin.getInstance().getLibros()); // Abre la ventana para solicitar un libro
        } else if (e.getSource() == btnDevolverLibro) {
            new DevolverLibroGUI(usuarioActual, Admin.getInstance().getLibros()); // Abre la ventana para devolver un libro
        } else if (e.getSource() == btnCambiarPassword) {
            new CambiarPasswordGUI(usuarioActual); // Abre la ventana para cambiar la contraseña
        } else if (e.getSource() == btnSalir) {
            dispose(); // Cierra la ventana
            new IniciarSesionGUI(); // Abre la interfaz de inicio de sesión
        }
    }
}
