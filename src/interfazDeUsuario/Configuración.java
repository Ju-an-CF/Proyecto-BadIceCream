package interfazDeUsuario;

import escenario.*;


import java.io.*;

// Clase para gestionar la configuración del juego
public class Configuración implements Serializable{
    Tablero tablero;

    // Constructor que recibe el tablero asociado
    public Configuración(Tablero tablero){
        this.tablero=tablero;
    }

    // Método para guardar la configuración en un archivo de texto
    public void guardarConfig(){
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter("C:/Users/MY LENOVO/IdeaProjects/Proyecto---BadIceCream/src/fuentes/datosDeJuego/configuración.txt"));
            // Guardar volumen de la música
            bw.write(String.valueOf(tablero.getMúsica().getEscalaDeVolumen()));
            bw.newLine();

            // Guardar volumen de los efectos de sonido
            bw.write(String.valueOf(tablero.getSe().getEscalaDeVolumen()));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // Método para cargar la configuración desde un archivo de texto
    public void cargarConfig(){
        try {
            BufferedReader br=new BufferedReader(new FileReader("C:/Users/MY LENOVO/IdeaProjects/Proyecto---BadIceCream/src/fuentes/datosDeJuego/configuración.txt"));
            String s;
            // Leer y asignar el volumen de la música
            s=br.readLine();
            tablero.getMúsica().escalaDeVolumen=Integer.parseInt(s);
            // Leer y asignar el volumen de los efectos de sonido
            s= br.readLine();
            tablero.getSe().escalaDeVolumen=Integer.parseInt(s);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
