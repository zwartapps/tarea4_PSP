package es.formacion.cip;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.*;

public class ClienteFTP {

    private static FTPClient cliente = new FTPClient();

    /**
     * Constructor
     */
    public ClienteFTP() {
    }

    /**
     * Establece una conexion con el servidor FTP
     *
     * @param server Servidor al que nos queremos conectar
     * @param user   Usuario para poder acceder
     * @param pwd    Contraseña para poder acceder
     * @return True, si la conexion se establecio correctamente,
     * False en caso contrario
     */
    public boolean conectar(String server, String user, String pwd) {
        try {
            // Conectarse y loguearse al servidor FTP
            cliente.connect(server);
            if (cliente.login(user, pwd)) {
                // Entrando en modo pasivo
                cliente.enterLocalPassiveMode();
                // Activar recibir/enviar cualquier tipo de archivo
                cliente.setFileType(FTP.BINARY_FILE_TYPE);
                // Obtener respuesta del servidor y acceder
                int respuesta = cliente.getReplyCode();
                if (FTPReply.isPositiveCompletion(respuesta) == true) {
                    System.out.println("Conexión establecida con el servidor: " + server);
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Host del servidor incorrecto: " + server);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Lista los ficheros del directorio actual del servidor FTP
     */
    public void listarFicheros() {
        // lists files and directories in the current working directory
        FTPFile[] files;
        try {
            files = cliente.listFiles();

            // iterates over the files and prints details for each
            DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (FTPFile file : files) {
                String details = file.getName();
                if (file.isDirectory()) {
                    details = "[" + details + "]";
                }
                details += "\t\t" + file.getSize();
                details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
                System.out.println(details);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Sube un fichero al servidor FTP a un directorio especificado por parametro
     *
     * @param rutaCompleta:  Ruta absoluta del fichero a subir, incluido el nombre del fichero,
     *                       por ejemplo, "D:\\\dir1\\file1.pdf"
     * @param nombreFichero: Nombre del fichero, incluida su extension, con el que se guardara
     *                       en el servidor FTP
     * @return directorioFTP: Directorio en el servidor FTP donde se guardara el
     * fichero
     */
    public void subirFichero(String rutaCompleta, String nombreFichero, String directorioFTP) {

        InputStream input;
        try {
            input = new FileInputStream(new File(rutaCompleta));
            cliente.storeFile(directorioFTP + nombreFichero, input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sube un fichero en el directorio actual del servidor FTP
     *
     * @param rutaCompleta:  Ruta absoluta del fichero a subir, incluido el nombre del fichero,
     *                       por ejemplo, "D:\\\dir1\\file1.pdf"
     * @param nombreFichero: Nombre del fichero, incluida su extension, con el que se guardara
     *                       en el servidor FTP
     */
    public void subirFichero(String rutaCompleta, String nombreFichero) {

        InputStream input;
        try {
            input = new FileInputStream(new File(rutaCompleta));
            cliente.storeFile(nombreFichero, input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imprime el directorio actual del servidor FTP
     */
    public void directorioActual() {

        try {
            String directorio = cliente.printWorkingDirectory();
            System.out.println("Directorio actual:" + directorio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Nos posicionamos en el directorio raiz del servidor FTP
     */
    public void cambiarADirectorioPadre() {

        try {
            cliente.changeToParentDirectory();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Nos posicionamos en un directorio en el servidor FTP especificado por
     * parametro, esta ruta es a partir del directorio actual
     * <p>
     * *
     *
     * @param directorio: Directorio en el servidor FTP en el que nos queremos posicionar
     *                    a partir del directorio actual
     */
    public void cambiarDirectorio(String directorio) {

        try {
            String directorioActual = cliente.printWorkingDirectory();
            cliente.changeWorkingDirectory(directorioActual + "/" + directorio);
            System.out.println("Cambiando al directorio: " + directorio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Borra un fichero en el directorio actual del servidor FTP
     *
     * @param fichero: Fichero a borrar del directorio actual en el servidor FTP
     */
    public void borrarFichero(String fichero) {
        try {
            cliente.deleteFile(fichero);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Borra un fichero del servidor FTP del directorio especificado por parametro
     *
     * @param directorio: Directorio en el servidor FTP donde se encuentra el fichero a borrar
     * @param fichero:    Fichero a borrar en el servidor FTP
     */
    //Concatenamos el directorio con el arvhico para borrar el archivo solicitado
    public void borrarFichero(String directorio, String fichero) {

        String tmp = directorio + "//" + fichero;

        try {
            cliente.deleteFile(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cerramos la sesion con el servidor FTP
     */
    public void desconectar() {

        if (cliente.isConnected()) {
            try {
                cliente.logout();
                cliente.disconnect();
                System.out.println("Desconectado del servidor FTP.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
