package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para gestionar el sonido
 */
public class GestionarSonidoGUI extends JFrame implements ActionListener {
    private JTextField txtSegundos;
    private JButton btnMedirSonido, btnCancelar;
    private JLabel lblNivelSonido;

    /**
     * Constructor de la clase GestionarSonidoGUI
     */
    public GestionarSonidoGUI() {

        // Configuración del JFrame
        setTitle("Gestionar Sonido");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Admin.getInstance().getColorFondo());

        setLayout(new FlowLayout());

        txtSegundos = new JTextField(15);
        btnMedirSonido = new JButton("Medir Nivel de Sonido");
        btnCancelar = new JButton("Cancelar");
        lblNivelSonido = new JLabel("Nivel de Sonido: 0 dB");

        btnMedirSonido.addActionListener(this);
        btnCancelar.addActionListener(this);

        add(new JLabel("Segundos: "));
        add(txtSegundos);
        add(btnMedirSonido);
        add(btnCancelar);
        add(lblNivelSonido);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        // Si se pulsa el botón de medir sonido, se mide el nivel de sonido
        if (e.getSource() == btnMedirSonido) {
            try {
                // Se comprueba que el tiempo introducido sea un número válido y se pasa a int
                int segundos = Integer.parseInt(txtSegundos.getText());
                if (segundos < 0) {
                    throw new IllegalArgumentException("El tiempo no puede ser negativo.");
                }
                // Llamada al método de Admin para medir el nivel de sonido
                Admin.getInstance().medirNivelDeSonido(this);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }

    public JTextField getTxtSegundos() {
        return txtSegundos;
    }
    public JLabel getLblNivelSonido() {
        return lblNivelSonido;
    }

}