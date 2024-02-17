package escenario;

import javax.swing.*;
import java.awt.*;

import entidades.Jugador;
import mecánicas.Control;
import mecánicas.VerificadorDeColisión;

public class Tablero extends JPanel implements Runnable {
    public final int TAMANIO_BLOQUE_ORIGINAL = 14;
    public final int ESCALA = 3;
    public final int TAMANIO_DE_BLOQUE = TAMANIO_BLOQUE_ORIGINAL * ESCALA;
    public final int COLUMNAS_MAX = 20;
    public final int FILAS_MAX = 18;
    public final int ALTO = TAMANIO_DE_BLOQUE * COLUMNAS_MAX;
    public final int ANCHO = TAMANIO_DE_BLOQUE * FILAS_MAX;

    //FPS
    int FPS = 60;

    Control control = new Control();
    Thread hiloDeJuego;
    public VerificadorDeColisión checkColisión = new VerificadorDeColisión(this);
    Jugador jugador = new Jugador(this, control);
    public AdministradorDeBloque adminBlock = new AdministradorDeBloque(this);

    public Tablero() {
        this.setPreferredSize(new Dimension(ALTO, ANCHO));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(control);
        this.setFocusable(true);
    }

    public void iniciarHiloDeJuego() {
        hiloDeJuego = new Thread(this);
        hiloDeJuego.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long temporizador = 0;
        int dibujarContar = 0;

        while (hiloDeJuego != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            temporizador += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                actualizar();
                repaint();
                delta--;
                dibujarContar++;
            }

            if (temporizador >= 1000000000) {
                System.out.println("FPS: " + dibujarContar);
                dibujarContar = 0;
                temporizador = 0;
            }

        }

    }

    public void actualizar() {
        jugador.actualizar();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        adminBlock.dibujar(g2);
        jugador.dibujar(g2);
        g2.dispose();
    }
}
