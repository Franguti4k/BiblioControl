package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para la administración
 */
public class InterfazAdmin extends JFrame implements ActionListener {
    private JButton btnAddUsuario, btnDelUsuario, btnAddLibro, btnDelLibro, btnGestionarSonido, btnGestionarPeticiones, btnSalir, btnConfiguracion;

    /**
     * Constructor de la clase InterfazAdmin que muestra la interfaz gráfica para la administración
     */
    public InterfazAdmin() {
        setTitle("Administración");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 280);

        // Inicialización y configuración de los botones
        btnAddUsuario = new JButton("Añadir usuario");
        btnDelUsuario = new JButton("Eliminar usuario");
        btnAddLibro = new JButton("Añadir libro");
        btnDelLibro = new JButton("Eliminar libro");
        btnGestionarSonido = new JButton("Gestionar sonido");
        btnGestionarPeticiones = new JButton("Gestionar peticiones");
        btnConfiguracion = new JButton("Configuración");
        btnSalir = new JButton("Salir");


        btnAddUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGestionarSonido.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGestionarPeticiones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfiguracion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnAddUsuario.addActionListener(this);
        btnDelUsuario.addActionListener(this);
        btnAddLibro.addActionListener(this);
        btnDelLibro.addActionListener(this);
        btnGestionarSonido.addActionListener(this);
        btnGestionarPeticiones.addActionListener(this);
        btnConfiguracion.addActionListener(this);
        btnSalir.addActionListener(this);

        // Creación del panel y añadir los botones a él
        JPanel panel = new JPanel();
        panel.setBackground(Admin.getInstance().getColorFondo());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(btnAddUsuario);
        panel.add(btnDelUsuario);
        panel.add(btnAddLibro);
        panel.add(btnDelLibro);
        panel.add(btnGestionarSonido);
        panel.add(btnGestionarPeticiones);
        panel.add(btnConfiguracion);
        panel.add(btnSalir);

        // Añadir el panel al JFrame
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddUsuario) {
            new AddUsuarioGUI(Admin.getInstance().getUsuarios()); // Abre la ventana para añadir un usuario
        } else if (e.getSource() == btnDelUsuario) {
            new EliminarUsuarioGUI(Admin.getInstance().getUsuarios()); // Abre la ventana para eliminar un usuario
        } else if (e.getSource() == btnAddLibro) {
            new AddLibroGUI(Admin.getInstance().getLibros()); // Abre la ventana para añadir un libro
        } else if (e.getSource() == btnDelLibro) {
            new EliminarLibroGUI(Admin.getInstance().getLibros()); // Abre la ventana para eliminar un libro
        } else if (e.getSource() == btnGestionarSonido) {
            new GestionarSonidoGUI(); // Abre la ventana para gestionar el sonido
        } else if (e.getSource() == btnGestionarPeticiones) {
            new GestionarPeticionesGUI(Admin.getInstance().getLibros(), Admin.getInstance().getPeticiones()); // Abre la ventana para gestionar peticiones
        } else if (e.getSource() == btnConfiguracion) {
            new ConfiguracionGUI(this); // Abre la ventana para configurar el sistema
        } else if (e.getSource() == btnSalir) {
            dispose(); // Cierra la ventana actual
            new IniciarSesionGUI(); // Abre la interfaz de inicio de sesión
        }
    }
}
