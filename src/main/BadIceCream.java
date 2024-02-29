package main;

import escenario.Tablero;

import javax.swing.*;

public class BadIceCream {

    public static void main(String[] args) {
        empezarJuego();
    }

    private static void empezarJuego() {
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Bad Ice Cream");

        Tablero tablero = new Tablero();
        ventana.add(tablero);

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        tablero.configurarJuego();
        tablero.iniciarHiloDeJuego();
    }
}