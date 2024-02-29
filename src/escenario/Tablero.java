package escenario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import escenario.entidades.*;
import escenario.entidades.bloques.AdministradorDeBloque;
import escenario.entidades.bloques.BloqueInteractivo;
import interfazDeUsuario.IU;
import mecánicas.ColocadorDeObjetos;
import mecánicas.Control;
import mecánicas.VerificadorDeColisión;
import sonido.Sonido;

public class Tablero extends JPanel implements Runnable {
    public final int TAMAÑO_BLOQUE_ORIGINAL = 14;
    public final int ESCALA = 3;
    public final int TAMAÑO_DE_BLOQUE = TAMAÑO_BLOQUE_ORIGINAL * ESCALA; //42 pixeles
    public final int COLUMNAS_MAX = 17;
    public final int FILAS_MAX = 15;

    public final int ALTO = TAMAÑO_DE_BLOQUE * COLUMNAS_MAX; // 674 pixeles
    public final int ANCHO = TAMAÑO_DE_BLOQUE * FILAS_MAX; // 504 pixeles

    //Configuración del mundo
    public final int maxColDeMundo = 17;
    public final int maxFilasDeMundo = 15;


    //FPS
    public static final int FPS = 60;

    public Control control = new Control(this);
    public Thread hiloDeJuego;
    public VerificadorDeColisión checkColisión = new VerificadorDeColisión(this);
    public IU iu = new IU(this);
    Sonido música = new Sonido();
    Sonido se = new Sonido();
    public AdministradorDeBloque adminBlock = new AdministradorDeBloque(this);
    public ColocadorDeObjetos colocador = new ColocadorDeObjetos(this);
    //jugador y escenario.entidades
    public Jugador jugador = new Jugador(this, control, 8, 7);
    public Jugador jugador2 = new Jugador(this, control, 9, 10);
    public Entidad[] frutas = new Entidad[20];
    public Entidad[] enemigos = new Entidad[10];
    public BloqueInteractivo[] bloqueInteractivos = new BloqueInteractivo[50];
    ArrayList<Entidad> entidades = new ArrayList<>();


    // estado de juego
    public int estadoActualDeJuego;
    public final int ESTADO_DE_JUEGO = 1;
    public final int ESTADO_DE_PAUSA = 2;
    public final int ESTADO_DE_TITULO = 0;


    public Tablero() {
        this.setPreferredSize(new Dimension(ALTO, ANCHO));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(control);
        this.setFocusable(true);
    }

    public void configurarJuego() {
        colocador.colocarMora();
        colocador.colocarEnemigos();
        //colocador.colocarEnemigos();
        reproducirMúsica(5);
        estadoActualDeJuego = ESTADO_DE_TITULO;
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
        if (estadoActualDeJuego == ESTADO_DE_JUEGO) {
            jugador.actualizar();

            for (int i = 0; i < enemigos.length; i++) {
                if (enemigos[i] != null) {
                    enemigos[i].actualizar();
                }
            }
        }
        if (estadoActualDeJuego == ESTADO_DE_PAUSA) {

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //Titulo estado
        if (estadoActualDeJuego == ESTADO_DE_TITULO) {
            iu.dibujar(g2);

        } else {
            //Bloques
            adminBlock.dibujar(g2);

            for (BloqueInteractivo bloqueInteractivo : bloqueInteractivos) {
                if (bloqueInteractivo != null) {
                    bloqueInteractivo.dibujar(g2);
                }
            }
            entidades.add(jugador);
            //agrega escenario.entidades.frutas a la lista de escenario.entidades
            for (Entidad fruta : frutas) {
                if (fruta != null) {
                    entidades.add(fruta);
                }
            }
            for (Entidad enemigo : enemigos) {
                if (enemigo != null) {
                    entidades.add(enemigo);
                }
            }
            //ordenar
            Collections.sort(entidades, new Comparator<Entidad>() {
                @Override
                public int compare(Entidad o1, Entidad o2) {
                    return Integer.compare(o1.getMundoY(), o2.getMundoY());
                }
            });
            //dibujar escenario.entidades
            for (Entidad entidad : entidades) {
                entidad.dibujar(g2);
            }
            //igualando la lista
            for (int i = 0; i < entidades.size(); i++) {
                entidades.remove(i);
            }
            //IU
            iu.dibujar(g2);
            // g2.dispose();

        }
        //otros
    }

    public void reproducirMúsica(int i) {
        música.colocarArchivo(i);
        música.reproducir();
        música.entrarEnBucle();
    }

    public void pararMúsica() {
        música.parar();
    }

    public void reproducirSE(int i) {
        se.colocarArchivo(i);
        se.reproducir();
    }
}
