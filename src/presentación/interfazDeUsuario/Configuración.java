package presentación.interfazDeUsuario;

import Negocio.escenario.*;


import java.io.*;

public class Configuración implements Serializable{
    PanelDeJuego panelDeJuego;

    public Configuración(PanelDeJuego panelDeJuego){
        this.panelDeJuego = panelDeJuego;
    }

    public void guardarConfig(){
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter("C:\\Users\\Compu\\Documents\\workspace\\Clonación\\Proyecto---BadIceCream\\src\\datos\\fuentes\\configuración.txt"));
            //music
            bw.write(String.valueOf(panelDeJuego.getMúsica().getEscalaDeVolumen()));
            bw.newLine();

            //es
            bw.write(String.valueOf(panelDeJuego.getSe().getEscalaDeVolumen()));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void cargarConfig(){
        try {
            BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\Compu\\Documents\\workspace\\Clonación\\Proyecto---BadIceCream\\src\\datos\\fuentes\\datosDeJuego\\configuración.txt"));
            String s;

            s=br.readLine();
            panelDeJuego.getMúsica().escalaDeVolumen=Integer.parseInt(s);

            s= br.readLine();
            panelDeJuego.getSe().escalaDeVolumen=Integer.parseInt(s);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
