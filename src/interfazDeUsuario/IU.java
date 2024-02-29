package interfazDeUsuario;

import escenario.entidades.Entidad;
import escenario.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import escenario.entidades.frutas.*;

import javax.imageio.ImageIO;

public class IU {
    Tablero tablero;
    Font font;
    Graphics2D graphics2D;
    public EstadoDeJuego estadoDeJuego;
    BufferedImage corazónFull, medioCorazón, corazónVacío;
    BufferedImage moraImagen;
    BufferedImage imagenEstadoDeJuego;
    double playTime;
    DecimalFormat decimalFormato = new DecimalFormat("0.00");
    public int comandoNum = 0;


    public IU(Tablero tablero) {
        this.tablero = tablero;
        importarFont();

        //objetos
        Entidad corazón = new Corazón(tablero);
        corazónFull = corazón.imagen1;
        medioCorazón = corazón.imagen2;
        corazónVacío = corazón.imagen3;
        Entidad mora = new Mora(tablero);
        moraImagen = mora.imagen1;

    }
    public void terminarJuego() {
        switch (this.estadoDeJuego) {
            case VICTORIA: {
                imagenEstadoDeJuego = configurarPantalla("you_win");
                dibujarMenú();
            }
            case DERROTA: {
                imagenEstadoDeJuego = configurarPantalla("game_over");
                dibujarMenú();
            }
        }
    }
    public BufferedImage configurarPantalla(String rutaImagenAMostrar) {
        BufferedImage imagenActual = null;
        try {
            imagenActual = ImageIO.read(getClass().getResourceAsStream("/fuentes/IU/" + rutaImagenAMostrar + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics2D.drawImage(imagenEstadoDeJuego, tablero.TAMAÑO_DE_BLOQUE * 5, tablero.TAMAÑO_DE_BLOQUE * 4, 200, 200, null);
        return imagenActual;
    }

    public void dibujar(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        //TODO crear un estado de juego terminado, no un atributo

        if (estadoDeJuego == EstadoDeJuego.VICTORIA) {
            terminarJuego();
        } else if (estadoDeJuego == EstadoDeJuego.DERROTA) {
            terminarJuego();
        } else {
            graphics2D.setFont(font.deriveFont(Font.BOLD, 15F));
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawImage(moraImagen, tablero.TAMAÑO_DE_BLOQUE / 2, tablero.TAMAÑO_DE_BLOQUE / 2, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
            graphics2D.drawString("x= " + tablero.getJugador().getNúmeroDeFrutas(), 25, 21);
            //Tiempo
            playTime += (double) 1 / 60;
            graphics2D.drawString("Tiempo: " + decimalFormato.format(playTime) + " s", tablero.TAMAÑO_DE_BLOQUE * 13, 25);

            //menú
            if (tablero.estadoActualDeJuego == tablero.ESTADO_DE_TITULO) {
                dibujarMenú();
            }
            //estado de juego
            if (tablero.estadoActualDeJuego == tablero.ESTADO_DE_JUEGO) {
                dibujarVidaJugador();
            }
            //estado de pausa
            if (tablero.estadoActualDeJuego == tablero.ESTADO_DE_PAUSA) {
                dibujarVidaJugador();
                dibujarPantallaDePausa();
            }
        }
    }

    private void dibujarVidaJugador() {
        int x = tablero.TAMAÑO_DE_BLOQUE / 2;
        int y = tablero.TAMAÑO_DE_BLOQUE * 14;
        int i = 0;
        //dibujar corazon vacio
        while (i < tablero.getJugador().getMáximoVidas()) {
            graphics2D.drawImage(corazónVacío, x, y, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
            i++;
            x += tablero.TAMAÑO_DE_BLOQUE;
        }
        //reset
        x = tablero.TAMAÑO_DE_BLOQUE / 2;
        y = tablero.TAMAÑO_DE_BLOQUE * 14;
        i = 0;
        //dibujar vida actual
        while (i < tablero.getJugador().getVida()) {
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
        int y = tablero.TAMAÑO_DE_BLOQUE * 2;

        graphics2D.setColor(Color.gray);
        graphics2D.drawString(texto, x + 2, y + 2);
        graphics2D.setColor(Color.white);
        graphics2D.drawString(texto, x, y);
        //dibujo Helado
        x = tablero.ANCHO / 2 - tablero.TAMAÑO_DE_BLOQUE + 42;
        y += tablero.TAMAÑO_DE_BLOQUE * 3;
        graphics2D.drawImage(tablero.getJugador().abajo1, x, y, tablero.TAMAÑO_DE_BLOQUE * 2, tablero.TAMAÑO_DE_BLOQUE * 2, null);
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
        int desplazamiento = 42; // Ajusta este valor según sea necesario.
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
