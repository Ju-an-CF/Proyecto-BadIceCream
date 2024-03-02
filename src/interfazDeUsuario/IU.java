package interfazDeUsuario;

import entidades.Corazón;
import entidades.Entidad;
import entidades.Mora;
import escenario.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

public class IU {
    Tablero tablero;
    Font font;
    Graphics2D graphics2D;
    public EstadoDeJuego estadoDeJuego;
    BufferedImage corazónFull, medioCorazón, corazónVacío;
    BufferedImage moraImagen;
    BufferedImage imagenJuegoGanado;
    public boolean juegoTerminado = false;
    double playTime;
    DecimalFormat decimalFormato = new DecimalFormat("0.00");
    public int comandoNum = 0;


    public IU(Tablero tablero) {
        this.tablero = tablero;
        importarFont();

        //objetos
        Entidad corazón = new Corazón(tablero);
        Entidad mora = new Mora(tablero);
        //Imágenes de los objetos
        corazónFull = corazón.imagen1;
        medioCorazón = corazón.imagen2;
        corazónVacío = corazón.imagen3;

        moraImagen = mora.imagen1;
    }


    public void dibujar(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        //TO DO crear un estado de juego terminado, no un atributo

        graphics2D.setFont(font.deriveFont(Font.BOLD, 15F));
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawImage(moraImagen, tablero.TAMAÑO_DE_BLOQUE / 2, tablero.TAMAÑO_DE_BLOQUE / 2, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
        graphics2D.drawString("x= " + tablero.jugador.númeroDeFrutas, 25, 21);
        //Tiempo
        playTime += (double) 1 / 60;
        graphics2D.drawString("Time= " + decimalFormato.format(playTime), tablero.TAMAÑO_DE_BLOQUE * 11, 30);

        dibujarEstadoDeJuegoSegúnEstado();
        //menu
        /*if (tablero.estadoActualDeJuego == EstadoDeJuego.TÍTULO) {
            dibujarMenú();
        }
        if (tablero.estadoActualDeJuego == EstadoDeJuego.VICTORIA) {
            dibujarVictoria();
            tablero.hiloDeJuego = null;
            tablero.reproducirSE(6);
        }
        if (tablero.estadoActualDeJuego == EstadoDeJuego.DERROTA) {
            dibujarDerrota();
            tablero.hiloDeJuego = null;
            tablero.reproducirSE(4);
        }
        //estado de juego
        if (tablero.estadoActualDeJuego == EstadoDeJuego.JUEGO) {
            dibujarVidaJugador();
        }
        //estado de pausa
        if (tablero.estadoActualDeJuego == EstadoDeJuego.PAUSA) {
            dibujarVidaJugador();
            dibujarPantallaDePausa();
        }*/
    }

    private void dibujarDerrota() {
        try {
            imagenJuegoGanado = ImageIO.read(getClass().getResourceAsStream("/fuentes/IU/game_over.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics2D.drawImage(imagenJuegoGanado, tablero.TAMAÑO_DE_BLOQUE * 5, tablero.TAMAÑO_DE_BLOQUE * 4, 200, 200, null);
    }

    public void dibujarVictoria() {
        try {
            imagenJuegoGanado = ImageIO.read(getClass().getResourceAsStream("/fuentes/IU/you_win.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics2D.drawImage(imagenJuegoGanado, tablero.TAMAÑO_DE_BLOQUE * 5, tablero.TAMAÑO_DE_BLOQUE * 4, 200, 200, null);
    }

    public void dibujarEstadoDeJuego(String imagenDeEstado) {
        try {
            imagenJuegoGanado = ImageIO.read(getClass().getResourceAsStream("/fuentes/IU/" + imagenDeEstado + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics2D.drawImage(imagenJuegoGanado, tablero.TAMAÑO_DE_BLOQUE * 5, tablero.TAMAÑO_DE_BLOQUE * 4, 200, 200, null);
    }

    public void dibujarEstadoDeJuegoSegúnEstado(){
        switch (tablero.estadoActualDeJuego){
            case TÍTULO: {
                dibujarMenú();
            } break;
            case VICTORIA: {
                dibujarEstadoDeJuego("you_win");
                tablero.hiloDeJuego = null;
                tablero.reproducirSE(6);
            } break;
            case DERROTA: {
                dibujarEstadoDeJuego("game_over");
                tablero.hiloDeJuego = null;
                tablero.reproducirSE(4);
            } break;
            case JUEGO: {
                dibujarVidaJugador();
            } break;
            case PAUSA: {
                dibujarVidaJugador();
                dibujarPantallaDePausa();
            } break;
        }
    }


    private void dibujarVidaJugador() {
        int x = tablero.TAMAÑO_DE_BLOQUE / 2;
        int y = tablero.TAMAÑO_DE_BLOQUE * 8;
        int i = 0;
//dibujar corazon vacio
        while (i < tablero.jugador.máximoVidas) {
            graphics2D.drawImage(corazónVacío, x, y, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
            i++;
            x += tablero.TAMAÑO_DE_BLOQUE;
        }
        //reset
        x = tablero.TAMAÑO_DE_BLOQUE / 2;
        y = tablero.TAMAÑO_DE_BLOQUE * 8;
        i = 0;
        //dibujar vida actual
        while (i < tablero.jugador.vida) {
            graphics2D.drawImage(corazónFull, x, y, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
            i++;
            x += tablero.TAMAÑO_DE_BLOQUE;
        }
    }

    public void dibujarMenú() {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, tablero.ANCHO, tablero.ALTO);
        //Title name
        graphics2D.setFont(font.deriveFont(Font.BOLD, 60F));
        String texto = "Bad Ice cream";
        int x = getXParaCentrarTexto(texto);
        int y = tablero.TAMAÑO_DE_BLOQUE * 3;

        graphics2D.setColor(Color.gray);
        graphics2D.drawString(texto, x + 5, y + 5);
        graphics2D.setColor(Color.white);
        graphics2D.drawString(texto, x, y);
        //dibujo muneco
        x = tablero.ANCHO / 2 - tablero.TAMAÑO_DE_BLOQUE + 75;
        y += tablero.TAMAÑO_DE_BLOQUE * 2;
        graphics2D.drawImage(tablero.jugador.abajo1, x, y, tablero.TAMAÑO_DE_BLOQUE * 2, tablero.TAMAÑO_DE_BLOQUE * 2, null);
        //Menu
        graphics2D.setFont(font.deriveFont(Font.BOLD, 35F));

        texto = "Nuevo Juego";
        x = getXParaCentrarTexto(texto);
        y += tablero.TAMAÑO_DE_BLOQUE * 4;
        graphics2D.drawString(texto, x, y);
        if (comandoNum == 0) {
            graphics2D.drawString(">", x - tablero.TAMAÑO_DE_BLOQUE, y);
        }

        texto = "Cargar Juego";
        x = getXParaCentrarTexto(texto);
        y += tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString(texto, x, y);
        if (comandoNum == 1) {
            graphics2D.drawString(">", x - tablero.TAMAÑO_DE_BLOQUE, y);
        }

        texto = "Salir";
        x = getXParaCentrarTexto(texto);
        y += tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString(texto, x, y);
        if (comandoNum == 2) {
            graphics2D.drawString(">", x - tablero.TAMAÑO_DE_BLOQUE, y);
        }
    }

    private void dibujarPantallaDePausa() {
        String texto = "Pausado";
        int x = getXParaCentrarTexto(texto);

        int y = tablero.ANCHO / 2;

        graphics2D.drawString(texto, x, y);
    }

    public int getXParaCentrarTexto(String texto) {
        // Calcula la longitud del texto en píxeles.
        int longitudTexto = (int) graphics2D.getFontMetrics().getStringBounds(texto, graphics2D).getWidth();
        // Calcula el punto de inicio x para centrar el texto en el ancho del tablero.
        // Se añade un pequeño desplazamiento a la derecha si es necesario.
        int desplazamiento = 75; // Ajusta este valor según sea necesario.
        int xParaCentrar = (tablero.ANCHO - longitudTexto) / 2 + desplazamiento;
        return xParaCentrar;
    }

    public void importarFont() {
        InputStream is = getClass().getResourceAsStream("/fuentes/font/tinypixel.otf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
