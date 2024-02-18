import escenario.Tablero;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Bad Ice Cream");

        Tablero tablero = new Tablero();
        ventana.add(tablero);

        ventana.pack();

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        tablero.iniciarHiloDeJuego();

    }
}