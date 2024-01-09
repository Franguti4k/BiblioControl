package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para eliminar un usuario
 */
public class EliminarUsuarioGUI extends JFrame implements ActionListener {
    private JTextField txtDNI, txtPasswordAdmin;
    private JButton btnEliminar, btnCancelar;
    private ArrayList<UsuarioBiblioteca> Usuarios;

    /**
     * Constructor de la clase EliminarUsuarioGUI que recibe un ArrayList de usuarios y muestra la interfaz gráfica para eliminar un usuario
     * @param Usuarios ArrayList de usuarios de la biblioteca
     */
    public EliminarUsuarioGUI(ArrayList<UsuarioBiblioteca> Usuarios) {
        this.Usuarios = Usuarios;

        // Titulo y tamaño de la ventana
        setTitle("Eliminar Usuario");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear y configurar JPanel con GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(Admin.getInstance().getColorFondo());

        // Etiquetas y campos de texto
        panel.add(new JLabel("DNI:"));
        txtDNI = new JTextField();
        panel.add(txtDNI);

        panel.add(new JLabel("Contraseña Admin:"));
        txtPasswordAdmin = new JPasswordField();
        panel.add(txtPasswordAdmin);

        // Botones
        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");

        panel.add(btnEliminar);
        panel.add(btnCancelar);

        btnEliminar.addActionListener(this);
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
        if (e.getSource() == btnEliminar) {
            String DNI = txtDNI.getText();
            String password = txtPasswordAdmin.getText();
            // Eliminamos el usuario y guardamos los cambios en el archivo de texto
            String resultado = Admin.getInstance().eliminarUsuario(Usuarios, DNI, password);
            GestorDeArchivos.guardarUsuarios(Usuarios);
            JOptionPane.showMessageDialog(null, resultado);
        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }
}
