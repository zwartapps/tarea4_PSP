package es.formacion.cip;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {
    private JPanel panel;
    private JTextField textFieldServer;
    private JLabel userNameLabel;
    private JTextField textFieldUsername;
    private JLabel pwdLabel;
    private JLabel serverLabel;
    private JPasswordField passwordField1; //Asi no se ve el password que escribimos
    private JButton conectarButton;
    private JButton cancelarButton;

    ClienteFTP c = new ClienteFTP();

    public VentanaLogin() {

        setSize(500, 200);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
        setTitle("LOGIN");

        //Al presionar el boton de cancelar cerramos la venta del Login
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaLogin.this.dispose();
            }
        });

        //Al presionar el boton de conectar intentamos conectar con los datos que introduce el usuario
        conectarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.conectar(textFieldServer.getText(), textFieldUsername.getText(), String.valueOf(passwordField1));
            }
        });
    }
}
