package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para cambiar la contraseña
 */
public class CambiarPasswordGUI extends JFrame implements ActionListener {
    private JPasswordField txtPasswordActual, txtPasswordNueva, txtPistaNueva;
    private JButton btnCambiar, btnCancelar;
    private UsuarioBiblioteca usuarioActual;

    /**
     * Constructor de la clase CambiarPasswordGUI que recibe un usuario y muestra la interfaz gráfica para cambiar la contraseña
     * @param usuario Usuario de la biblioteca
     */
    public CambiarPasswordGUI(UsuarioBiblioteca usuario) {
        this.usuarioActual = usuario;

        // Configuración del JFrame
        setTitle("Cambiar Contraseña");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear y configurar JPanel con GridLayout
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(Admin.getInstance().getColorFondo());

        // Etiquetas y campos de texto (de tipo JPasswordField para que no se vea la contraseña)
        panel.add(new JLabel("Contraseña actual:"));
        txtPasswordActual = new JPasswordField();
        panel.add(txtPasswordActual);

        panel.add(new JLabel("Nueva contraseña:"));
        txtPasswordNueva = new JPasswordField();
        panel.add(txtPasswordNueva);

        panel.add(new JLabel("Nueva pista:"));
        txtPistaNueva = new JPasswordField();
        panel.add(txtPistaNueva);

        // Añadir botones
        btnCambiar = new JButton("Cambiar");
        btnCancelar = new JButton("Cancelar");

        panel.add(btnCambiar);
        panel.add(btnCancelar);

        btnCambiar.addActionListener(this);
        btnCancelar.addActionListener(this);

        add(panel);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCambiar) {
            try {
                // Guardamos la contraseña actual y la nueva en variables de tipo String
                String passwordActual = new String(txtPasswordActual.getPassword());
                String passwordNueva = new String(txtPasswordNueva.getPassword());
                String pistaNueva = new String(txtPistaNueva.getPassword());
                // Utilizamos el método cambiarPassword de la clase UsuarioBiblioteca y mostramos un mensaje de éxito
                usuarioActual.cambiarPassword(passwordActual, passwordNueva);
                usuarioActual.setPistaPassword(pistaNueva);
                JOptionPane.showMessageDialog(this, "Contraseña cambiada correctamente");
            } catch (UsuarioBiblioteca.PasswordIncorrectaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }

}