package escenario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entidades.*;
import interfazDeUsuario.EstadoDeJuego;
import interfazDeUsuario.IU;
import mecánicas.ColocadorDeObjetos;
import mecánicas.Control;
import mecánicas.Dirección;
import mecánicas.VerificadorDeColisión;
import sonido.Sonido;

public class Tablero extends JPanel implements Runnable {
    public final int TAMAÑO_BLOQUE_ORIGINAL = 13;
    public final int ESCALA = 3;
    public final int TAMAÑO_DE_BLOQUE = TAMAÑO_BLOQUE_ORIGINAL * ESCALA; //42 pixeles
    public final int COLUMNAS_MAX = 16;
    public final int FILAS_MAX = 12;

    public final int ALTO = TAMAÑO_DE_BLOQUE * COLUMNAS_MAX; // 674 pixeles
    public final int ANCHO = TAMAÑO_DE_BLOQUE * FILAS_MAX; // 504 pixeles

    //Configuración del mundo
    public final int maxColDeMundo = 31;
    public final int maxFilasDeMundo = 28;


    //FPS
    public static final int FPS = 60;

    Control control = new Control(this);
    public Thread hiloDeJuego;
    public VerificadorDeColisión checkColisión = new VerificadorDeColisión(this);
    public IU iu = new IU(this);
    Sonido música = new Sonido();
    Sonido se = new Sonido();
    public AdministradorDeBloque adminBlock = new AdministradorDeBloque(this);
    public ColocadorDeObjetos colocador = new ColocadorDeObjetos(this);
    //jugador y entidades
    public Jugador jugador = new Jugador(this, control);
    public Entidad[] frutas = new Entidad[20];
    public Entidad[] enemigos = new Entidad[10];
    ArrayList<Entidad> entidades = new ArrayList<>();


    // estados de juego
    public EstadoDeJuego estadoActualDeJuego = EstadoDeJuego.NEUTRO;


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
        estadoActualDeJuego = EstadoDeJuego.TÍTULO;
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
        if (estadoActualDeJuego == EstadoDeJuego.JUEGO) {
            jugador.actualizar();

            for (Entidad enemigo : enemigos) {
                if (enemigo != null) {
                    enemigo.actualizar();
                }
            }
        }

        if (estadoActualDeJuego == EstadoDeJuego.PAUSA) {

        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //Titulo estado
        if (estadoActualDeJuego == EstadoDeJuego.TÍTULO) {
            iu.dibujar(g2);
        } else {
            //Bloques
            adminBlock.dibujar(g2);
            //Añadir al jugador a la lista de entidades
            entidades.add(jugador);
            //agrega frutas a la lista de entidades
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
                    int resultado = Integer.compare(o1.mundoY, o2.mundoY);
                    return resultado;
                }
            });
            //dibujar entidades
            for (Entidad entidade : entidades) {
                entidade.dibujar(g2);
            }
            //igualando la lista
            for (int i = 0; i < entidades.size(); i++) {
                entidades.remove(i);
            }
            //IU
            iu.dibujar(g2);
            // g2.dispose();

        }
        if (jugador.númeroDeFrutas == 11) {
            estadoActualDeJuego = EstadoDeJuego.VICTORIA;
        }
        if (jugador.vida == 0) {
            estadoActualDeJuego = EstadoDeJuego.DERROTA;
        }
        //otros
    }

    public void crearBloqueHielo() {
        // Calcula el punto de inicio ajustando desde el centro del borde del área sólida en la dirección mirada
        int ajusteX = obtenerAjusteX();
        int ajusteY = obtenerAjusteY();

        int x = (jugador.mundoX + ajusteX) / TAMAÑO_DE_BLOQUE;
        int y = (jugador.mundoY + ajusteY) / TAMAÑO_DE_BLOQUE;

        Dirección dirección = jugador.getDireccion();

        while (true) {
            switch (dirección) {
                case ARRIBA:
                    y--;
                    break;
                case ABAJO:
                    y++;
                    break;
                case IZQUIERDA:
                    x--;
                    break;
                case DERECHA:
                    x++;
                    break;
            }

            if (x < 0 || y < 0 || x >= maxColDeMundo || y >= maxFilasDeMundo || esBloqueNoTransformable(adminBlock.mapa[x][y])) {
                break;
            }

            if (adminBlock.mapa[x][y] == 8) { // Si ya es un bloque de hielo, detener.
                break;
            }

            adminBlock.mapa[x][y] = 8; // Crear bloque de hielo.
        }
    }

    private boolean esBloqueNoTransformable(int bloque) {
        return bloque == 1 || bloque == 2 || bloque == 3 || bloque == 4 || bloque == 5;
    }

    public int obtenerAjusteX(){
        return jugador.getDireccion().equals(Dirección.IZQUIERDA) ? jugador.áreaSólida.x :
               jugador.getDireccion().equals(Dirección.DERECHA) ? jugador.áreaSólida.x + jugador.áreaSólida.width :
               jugador.áreaSólida.width / 2;
    }
    public int obtenerAjusteY(){
        return jugador.getDireccion().equals(Dirección.ARRIBA) ? jugador.áreaSólida.y :
               jugador.getDireccion().equals(Dirección.ABAJO) ? jugador.áreaSólida.y + jugador.áreaSólida.height :
               jugador.áreaSólida.height / 2;
    }

    public void romperBloqueHielo() {
        // Igual que el método crearBloqueHielo pero ajustando para romper
        int ajusteX = obtenerAjusteX();
        int ajusteY = obtenerAjusteY();

        int x = (jugador.mundoX + ajusteX) / TAMAÑO_DE_BLOQUE;
        int y = (jugador.mundoY + ajusteY) / TAMAÑO_DE_BLOQUE;
        Dirección dirección = jugador.getDireccion();

        while (true) {
            switch (dirección) {
                case ARRIBA:
                    y--;
                    break;
                case ABAJO:
                    y++;
                    break;
                case IZQUIERDA:
                    x--;
                    break;
                case DERECHA:
                    x++;
                    break;
            }

            if (x < 0 || y < 0 || x >= maxColDeMundo || y >= maxFilasDeMundo || adminBlock.mapa[x][y] == 0 || adminBlock.mapa[x][y] == 6 || adminBlock.mapa[x][y] == 7) {
                break;
            }

            if (esBloqueNoTransformable(adminBlock.mapa[x][y])) {
                break;
            }

            adminBlock.mapa[x][y] = 0; // Eliminar bloque de hielo.
            reproducirSE(3);
        }
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