# tarea4_PSP
Tarea 4 del modulo Programación de Servicios y Procesos.

## Descripción
UT04. Generación de Servicios en Red
Práctica a entregar

Práctica 1. Se cuenta con la siguiente librería clienteFTP.java
Para ver los detalles, ver el PDF: 
- [PDF Tarea 3](../master/0490_PSP_Practica_UT04_2018_v1.0.pdf)

**Se pide realizar lo siguiente:**
 Se pide crear una aplicación que permita, mediante un menú interactivo o una interfaz gráfica,
gestionar todos estos métodos para poder subir un fichero al servidor FTP.
 Además, habrá que crear dos métodos:
o **borrarFichero(String fichero):** método que permite borrar un fichero del servidor FTP en el
directorio actual.
o **borrarFichero(String directorio, String fichero):** método que permite borrar u fichero del
servidor FTP en un directorio especificado por parámetro.

**Nota:** El objetivo de esta práctica es subir un fichero al servidor FTP utilizando los métodos de la clase
clienteFTP.java y además, implementar los métodos para poder borrar el fichero que hemos subido al servidor.

El código para conectar con un servidor que está en la ip 192.168.1.38 con el usuario/password
formacion/formacion sería:

```
ClienteFTP c = new ClienteFTP();

c.conectar("192.168.1.38", "formacion", "formacion");
c.desconectar(); 
```

Hemos creado una interfaz gráfica conectando todo con el codigo facilitado.
Este sería el menu principal:

<img src="http://i65.tinypic.com/91h3bb.jpg" >

Cada boton ejecuta un metodo de la clase principal ClienteFTP. Para algunos metodos necesitamos parametros que tiene que introducir el usuario. Para ello hemos creado más ventanas. Son los siguientes:

**Boton Conectar nos abre la ventana de Login para especificar Server, Username y Password:**
<img src="http://i66.tinypic.com/1fek1t.jpg" >

Esta ventana recoge los datos necesarios para el metodo conectar:

```public boolean conectar(String server, String user, String pwd)```

El password no se debería de verse en la ventana

**Boton Subir Fichero:**

<img src="http://i63.tinypic.com/2s84mf7.jpg" >

Esta ventana recoge los datos necesarios para el metodo subirFichero:

```public void subirFichero(String rutaCompleta, String nombreFichero, String directorioFTP) ```

**Boton Cambiar Diretorio:**

<img src="http://i67.tinypic.com/eq33sy.jpg" >

Esta ventana recoge los datos necesarios para el metodo cambiarDirectorio 
Como solamente necesita un parametro, lo pasamos con un JOptionPane.showInputDialog
```public void cambiarDirectorio(String directorio) ```

**Boton Borrar fichero (Directorio Actual):**

<img src="http://i66.tinypic.com/2mx0idc.jpg" >

Esta ventana recoge los datos necesarios para el metodo borrarFichero 
Hemos tenido que crear este metodo en la clase principal:
```
public void borrarFichero(String fichero) {
        try {
            cliente.deleteFile(fichero);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 ```
    
Como solamente necesita un parametro, lo pasamos con un JOptionPane.showInputDialog
``` public void borrarFichero(String fichero) ```

**Boton Borrar fichero (Especifique Directorio):**

<img src="http://i63.tinypic.com/oqvgpx.jpg" >

Hemos tenido que crear este metodo en la clase principal:
```
  public void borrarFichero(String directorio, String fichero) {
        String tmp = directorio + "//" + fichero;

        try {
            cliente.deleteFile(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
```
Hemos optado por crear un String temporal con ambos string que introduce el usuario y concatenarlos para borrar el fichero de un directorio especifico.     
     
**Boton Desconectar:**

Activa el metodo deconectar y muestra un aviso que hemos deconectado:

<img src="http://i63.tinypic.com/1581ac2.jpg" >
    
En el codigo está todo comentado para entender que hacemos.    
    
    
    
    
    
    
    
    
    
