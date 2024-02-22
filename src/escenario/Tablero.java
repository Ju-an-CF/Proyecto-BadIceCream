package escenario;

import javax.swing.*;
import java.awt.*;

import entidades.Jugador;
import sonido.Sonido;
import mecánicas.Control;
import mecánicas.VerificadorDeColisión;

public class Tablero extends JPanel implements Runnable {
    public final int TAMANIO_BLOQUE_ORIGINAL = 14;
    public final int ESCALA = 3;
    public final int TAMANIO_DE_BLOQUE = TAMANIO_BLOQUE_ORIGINAL * ESCALA; //42 pixeles
    public final int COLUMNAS_MAX = 16;
    public final int FILAS_MAX = 12;
    public final int ALTO = TAMANIO_DE_BLOQUE * COLUMNAS_MAX; // 674 pixeles
    public final int ANCHO = TAMANIO_DE_BLOQUE * FILAS_MAX; // 504 pixeles

    //Configuración del mundo
    public final int maxColDeMundo = 31;
    public final int maxFilasDeMundo = 28;
    public final int anchoDeMundo = TAMANIO_DE_BLOQUE * maxColDeMundo;
    public final int altoDeMundo = TAMANIO_DE_BLOQUE * maxFilasDeMundo;

    //FPS
    public static final int FPS = 60;
//
    Control control = new Control();
    Thread hiloDeJuego;
    public VerificadorDeColisión checkColisión = new VerificadorDeColisión(this);
  //
    Sonido música=new Sonido();
    Sonido se=new Sonido();
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
        reproducirMúsica(2);
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

    public void reproducirMúsica(int i){
        música.colocarArchivo(i);
        música.reproducir();
        música.entrarEnBucle();
    }

    public void pararMúsica(){
        música.parar();
    }
    public void reproducirSE(int i){
        se.colocarArchivo(i);
        se.reproducir();
    }
}
