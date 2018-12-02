package es.formacion.cip;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSubir extends JFrame {

    private JPanel panel;
    private JTextField textFieldRuta;
    private JTextField textFieldNombre;
    private JTextField textFieldServer;
    private JButton aceptarButton;
    private JButton cancelarButton;

    ClienteFTP c = new ClienteFTP();

    public VentanaSubir() {

        setSize(500, 200);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
        setTitle("SUBIR FICHERO");

        //Al presionar el boton de cancelar cerramos la venta de Subir Fichero
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaSubir.this.dispose();
            }
        });

        //Al presionar el boton aceptar, recoge los Strings de los parametros para subir un fichero
        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.subirFichero(textFieldRuta.getText(), textFieldNombre.getText(), textFieldServer.getToolTipText());
            }
        });
    }
}
