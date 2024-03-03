package interfazDeUsuario;

import escenario.*;


import java.io.*;

public class Configuración implements Serializable{
    Tablero tablero;

    public Configuración(Tablero tablero){
        this.tablero=tablero;
    }

    public void guardarConfig(){
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter("C:/Users/MY LENOVO/IdeaProjects/Proyecto---BadIceCream/src/fuentes/datosDeJuego/configuración.txt"));
            //music
            bw.write(String.valueOf(tablero.getMúsica().getEscalaDeVolumen()));
            bw.newLine();

            //es
            bw.write(String.valueOf(tablero.getSe().getEscalaDeVolumen()));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void cargarConfig(){
        try {
            BufferedReader br=new BufferedReader(new FileReader("C:/Users/MY LENOVO/IdeaProjects/Proyecto---BadIceCream/src/fuentes/datosDeJuego/configuración.txt"));
            String s;

            s=br.readLine();
            tablero.getMúsica().escalaDeVolumen=Integer.parseInt(s);

            s= br.readLine();
            tablero.getSe().escalaDeVolumen=Integer.parseInt(s);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
