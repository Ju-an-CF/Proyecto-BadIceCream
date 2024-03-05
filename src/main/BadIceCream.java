package main;

import Negocio.escenario.PanelDeJuego;
import Negocio.niveles.*;

import javax.swing.*;

public class BadIceCream {
    public static void main(String[] args) {
        empezarJuego(new Nivel1());
    }


    private static void empezarJuego(Nivel nivel) {
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Bad Ice Cream");

        //Se manda un nivel como parámetro
        PanelDeJuego panelDeJuego = new PanelDeJuego(nivel);

        ventana.add(panelDeJuego);
        panelDeJuego.getConfiguración().cargarConfig();

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        panelDeJuego.configurarJuego();
        panelDeJuego.iniciarHiloDeJuego();
    }
}