package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para la configuración por parte del administrador
 */
public class ConfiguracionGUI extends JFrame implements ActionListener{
    private JTextField txtUmbral;
    private JButton btnGuardar, btnCancelar, btnCambiarColor;
    private JFrame ventanaAdmin;

    /**
     * Constructor de la clase ConfiguracionGUI
     */
    public ConfiguracionGUI(JFrame ventanaAdmin) {
        this.ventanaAdmin = ventanaAdmin;
        // Configuración del JFrame
        setTitle("Configuración");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 180);

        // Panel principal con GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBackground(Admin.getInstance().getColorFondo());

        // Panel para umbral
        JPanel panelUmbral = new JPanel(new GridLayout(1, 2));
        panelUmbral.setBackground(Admin.getInstance().getColorFondo());
        panelUmbral.add(new JLabel("Umbral (dB):"));
        txtUmbral = new JTextField();
        panelUmbral.add(txtUmbral);
        panel.add(panelUmbral);

        // Botón para cambiar el color de fondo
        btnCambiarColor = new JButton("Cambiar color de fondo");
        panel.add(btnCambiarColor);
        btnCambiarColor.addActionListener(this);

        // Panel para botones Guardar y Cancelar
        JPanel panelBotones = new JPanel(new GridLayout(1, 2));
        panelBotones.setBackground(Admin.getInstance().getColorFondo());
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        panel.add(panelBotones);

        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);

        getContentPane().add(panel);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                // Solo actualizar umbral si el campo de texto no está vacío
                if (!txtUmbral.getText().isEmpty()) {
                    double nuevoUmbral = Double.parseDouble(txtUmbral.getText());
                    Admin.getInstance().setUmbralSonido(nuevoUmbral);
                    JOptionPane.showMessageDialog(this, "Umbral actualizado a " + nuevoUmbral + " dB");
                } else {
                    JOptionPane.showMessageDialog(this, "No se ha cambiado el umbral");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para el umbral");
            }
        } else if (e.getSource() == btnCambiarColor) {
            // Cambiar el color de fondo
            Color color = JColorChooser.showDialog(this, "Selecciona un color", Admin.getInstance().getColorFondo());
            if (color != null) {
                Admin.getInstance().setColorFondo(color);
                JOptionPane.showMessageDialog(this, "Color de fondo actualizado");
                ventanaAdmin.dispose();
                dispose();
            }
        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }
}
