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

    private Control control = new Control(this);
    private VerificadorDeColisión verificadorDeColisión = new VerificadorDeColisión(this);
    private AdministradorDeBloque adminBlock = new AdministradorDeBloque(this);
    private ColocadorDeObjetos colocador = new ColocadorDeObjetos(this);
    private Thread hiloDeJuego;
    public IU iu = new IU(this);
    Sonido música = new Sonido();
    Sonido se = new Sonido();
    //Jugador y Entidades
    private Jugador jugador = new Jugador(this, getControl(), 8, 7);
    private Entidad[] frutas = new Entidad[20];
    private Entidad[] enemigos = new Entidad[10];
    private BloqueInteractivo[] bloqueInteractivos = new BloqueInteractivo[50];
    private ArrayList<Entidad> entidades = new ArrayList<>();


    // estado de juego
    public int estadoActualDeJuego;
    public final int ESTADO_DE_JUEGO = 1;
    public final int ESTADO_DE_PAUSA = 2;
    public final int ESTADO_DE_TITULO = 0;


    public Tablero() {
        this.setPreferredSize(new Dimension(ALTO, ANCHO));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(getControl());
        this.setFocusable(true);
    }

    public void configurarJuego() {
        getColocador().colocarMora();
        getColocador().colocarEnemigos();
        getColocador().colocarBloquesInteractivos();
        //colocador.colocarEnemigos();
        reproducirMúsica(5);
        estadoActualDeJuego = ESTADO_DE_TITULO;
    }

    public void iniciarHiloDeJuego() {
        setHiloDeJuego(new Thread(this));
        getHiloDeJuego().start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long temporizador = 0;
        int dibujarContar = 0;

        while (getHiloDeJuego() != null) {
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
            getJugador().actualizar();

            for (int i = 0; i < getEnemigos().length; i++) {
                if (getEnemigos()[i] != null) {
                    getEnemigos()[i].actualizar();
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
            getAdminBlock().dibujar(g2);

            for (BloqueInteractivo bloqueInteractivo : getBloqueInteractivos()) {
                if (bloqueInteractivo != null) {
                    bloqueInteractivo.dibujar(g2);
                }
            }
            getEntidades().add(getJugador());
            //agrega escenario.entidades.frutas a la lista de escenario.entidades
            for (Entidad fruta : getFrutas()) {
                if (fruta != null) {
                    getEntidades().add(fruta);
                }
            }
            for (Entidad enemigo : getEnemigos()) {
                if (enemigo != null) {
                    getEntidades().add(enemigo);
                }
            }
            //ordenar
            Collections.sort(getEntidades(), new Comparator<Entidad>() {
                @Override
                public int compare(Entidad o1, Entidad o2) {
                    return Integer.compare(o1.getMundoY(), o2.getMundoY());
                }
            });
            //dibujar escenario.entidades
            for (Entidad entidad : getEntidades()) {
                entidad.dibujar(g2);
            }
            //igualando la lista
            for (int i = 0; i < getEntidades().size(); i++) {
                getEntidades().remove(i);
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

    public Control getControl() {
        return control;
    }

    public VerificadorDeColisión getVerificadorDeColisión() {
        return verificadorDeColisión;
    }

    public AdministradorDeBloque getAdminBlock() {
        return adminBlock;
    }

    public ColocadorDeObjetos getColocador() {
        return colocador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Entidad[] getFrutas() {
        return frutas;
    }

    public Entidad[] getEnemigos() {
        return enemigos;
    }

    public BloqueInteractivo[] getBloqueInteractivos() {
        return bloqueInteractivos;
    }

    public ArrayList<Entidad> getEntidades() {
        return entidades;
    }

    public void setEntidades(ArrayList<Entidad> entidades) {
        this.entidades = entidades;
    }

    public Thread getHiloDeJuego() {
        return hiloDeJuego;
    }

    public void setHiloDeJuego(Thread hiloDeJuego) {
        this.hiloDeJuego = hiloDeJuego;
    }
}
