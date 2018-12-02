package es.formacion.cip;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JButton conectarButton;
    private JButton listarFicherosButton;
    private JButton subirFicheroButton;
    private JButton directorioActualButton;
    private JButton irADirectorioPadreButton;
    private JButton cambiarDirectorioButton;
    private JButton desconectarButton;
    private JPanel panel;
    private JButton borrarFicheroDirectorioActualButton;
    private JButton borrarFicheroEspecifiqueDirectorioButton;
    private String server, user, pwd;

    //creamos objeto ClienteFTP para usar los metodos de esta clase
    ClienteFTP c = new ClienteFTP();

    //Constructor para la VentanaPrincipal tamaño, centrar, mostrar, titulo
    public VentanaPrincipal() {

        setSize(400, 400);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
        setTitle("MENU FTP");

        //Si queremos conectar se abre una ventana nueva para intrudicir los datos necesarios para la conexion
        conectarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaLogin l = new VentanaLogin();

            }
        });

        //Boton para listar los fichers
        listarFicherosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.listarFicheros();
            }
        });

        //Boton para subir Fichero, se abre una ventana nueva para especificar los
        subirFicheroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaSubir s = new VentanaSubir();
            }
        });

        //Boton para imprimir el directorio actual del servidor FTP
        directorioActualButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.directorioActual();
            }
        });

        //Bonot para cambiar al directorio raiz del servidor FTP
        irADirectorioPadreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.cambiarADirectorioPadre();
            }
        });

        //Boton para cambiar a un directorio especifico
        cambiarDirectorioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Pedimos que introduzca el directorio a donde quiere cambiar y lo pasamos por parametro
                String directorio = JOptionPane.showInputDialog("Introduzca Directorio");
                c.cambiarDirectorio(directorio);
            }
        });

        //Boton para borrar fichero del directorio actual, se introduce el nombre por ventana showinputdialog
        borrarFicheroDirectorioActualButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fichero = JOptionPane.showInputDialog("Introduzca el nombre del fichero completo");
                c.borrarFichero(fichero);
            }
        });

        //Boton para borrar fichero de un directorio especifico
        borrarFicheroEspecifiqueDirectorioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaBorrar b = new VentanaBorrar();
            }
        });

        //Al deconectar mostramos un mensaje que se ha desconectado del servidor
        desconectarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.desconectar();
                JOptionPane.showMessageDialog(panel, "Se ha desconectado del Servidor");
            }
        });
    }
}
