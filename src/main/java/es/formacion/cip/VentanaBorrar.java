package es.formacion.cip;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaBorrar extends JFrame {
    private JTextField textFieldDirectorio;
    private JTextField textFieldFichero;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JPanel panel;

    ClienteFTP c = new ClienteFTP();

    public VentanaBorrar() {

        setSize(450, 200);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
        setTitle("BORRAR FICHERO");

        //Al presionar el boton de cancelar cerramos la venta de Borrar Fichero
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaBorrar.this.dispose();
            }
        });

        /*Al presionar el boton de aceptar pasamos por parametro la ruta y el fichero al metodo de borrar de
        la clase principal*/
        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.borrarFichero(textFieldDirectorio.getText(), textFieldFichero.getText());
            }
        });
    }
}
