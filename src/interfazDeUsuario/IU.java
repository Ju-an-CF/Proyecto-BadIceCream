package interfazDeUsuario;

import entidades.objetos.Corazón;
import entidades.Entidad;
import entidades.frutas.Mora;
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
    BufferedImage corazónFull, medioCorazón, corazónVacío, panelImagen, imagenDeFondo, imagenMenú,opcionesMen, hojaDeSprites;
    BufferedImage moraImagen;
    BufferedImage imagenEstado;
    double playTime;
    DecimalFormat decimalFormato = new DecimalFormat("0.00");
    public int comandoNum = 0;
    private int anchoFrame; // Ancho de un único frame en la hoja de sprites
    private int altoFrame; // Alto de un único frame
    private final int numeroDeFrames = 15; // Número total de frames
    private final double tiempoPorFrame = 1.0; // Tiempo que cada frame se muestra, ajusta según necesidad
    private boolean relojActivo = true; // Controla si el reloj está activo



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

    public BufferedImage cargarRecursosAdicionales(String ruta){
        BufferedImage imagen=null;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(ruta));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }


    public void dibujar(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        dibujarEstadoDeJuegoSegúnEstado();
    }

    private void dibujarMoras() {
        graphics2D.setFont(font.deriveFont(Font.BOLD, 18F));
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawImage(moraImagen, 320, 525, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
        graphics2D.drawString("x" + tablero.jugador.númeroDeFrutas, 285, 553);
    }

    private void dibujarTiempo() {
        if (hojaDeSprites == null) {
            hojaDeSprites = cargarRecursosAdicionales("/fuentes/IU/reloj.png");
            anchoFrame = hojaDeSprites.getWidth() / numeroDeFrames;
            altoFrame = hojaDeSprites.getHeight();
        }

        if (relojActivo) {
            playTime += (double) 1 / 60;
        }

        int indiceFrame = (int)((playTime * numeroDeFrames) / tiempoPorFrame) % numeroDeFrames;
        int xFrame = indiceFrame * anchoFrame;

        graphics2D.drawImage(hojaDeSprites, 455, 531, 455 + anchoFrame, 531 + altoFrame,
                xFrame, 0, xFrame + anchoFrame, altoFrame, null);
        graphics2D.drawString(decimalFormato.format(playTime), 497, 500 + altoFrame + 20);
    }

    public void pararReloj() {
        relojActivo = false;
    }

    public void reiniciarReloj() {
        relojActivo = true;
    }

    public void resetearReloj() {
        playTime = 0;
        relojActivo = true; // Opcional, dependiendo de si quieres que el reloj se reinicie y continúe automáticamente
    }

    public void dibujarEstadoDeJuego(String ruta) {
        try {
            imagenEstado = ImageIO.read(getClass().getResourceAsStream("/fuentes/IU/" + ruta + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics2D.drawImage(imagenEstado,tablero.TAMAÑO_DE_BLOQUE * 5, tablero.TAMAÑO_DE_BLOQUE * 4, 200, 200, null);
    }

    public void dibujarEstadoDeJuegoSegúnEstado(){
        switch (tablero.estadoActualDeJuego){
            case TÍTULO: {
                dibujarMenú(graphics2D);
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
                reiniciarReloj();
                panelImagen=cargarRecursosAdicionales("/fuentes/IU/panel.png");
                graphics2D.drawImage(panelImagen, tablero.TAMAÑO_DE_BLOQUE, 13*tablero.TAMAÑO_DE_BLOQUE, panelImagen.getWidth(), panelImagen.getHeight(), null);
                dibujarVidaJugador();
                dibujarMoras();
                dibujarTiempo();

            } break;
            case PAUSA: {
                dibujarVidaJugador();
                dibujarPantallaDePausa();
                pararReloj();
            } break;
        }
    }


    private void dibujarVidaJugador() {
        int x = 84;
        int y = 526;
        int i = 0;
//dibujar corazon vacio
        while (i < tablero.jugador.máximoVidas) {
            graphics2D.drawImage(corazónVacío, x, y, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
            i++;
            x += tablero.TAMAÑO_DE_BLOQUE;
        }
        //reset
        x = 84;
        y = 526;
        i = 0;
        //dibujar vida actual
        while (i < tablero.jugador.vida) {
            graphics2D.drawImage(corazónFull, x, y, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
            i++;
            x += tablero.TAMAÑO_DE_BLOQUE;
        }
    }

    public void dibujarMenú(Graphics2D g2) {
        String texto;


        imagenDeFondo=cargarRecursosAdicionales("/fuentes/IU/fondo.jpg");
        g2.drawImage(imagenDeFondo, 0, 0, tablero.ANCHO+50, tablero.ALTO, null);

        imagenMenú=cargarRecursosAdicionales("/fuentes/IU/título.png");

        g2.drawImage(imagenMenú, 60, 10, 500, 500, null);

        opcionesMen=cargarRecursosAdicionales("/fuentes/IU/opcionesmen.png");
        g2.drawImage(opcionesMen, 190, 420, 250, 150, null);

        //Menu

        graphics2D.setFont(font.deriveFont(Font.BOLD, 20F));

        texto = "Nuevo Juego";
        graphics2D.drawString(texto, 240, 452);
        if (comandoNum == 0) {
            graphics2D.drawString(">", 220 - tablero.TAMAÑO_DE_BLOQUE, 450);
        }

        texto = "Cargar Juego";

        graphics2D.drawString(texto, 235, 500);
        if (comandoNum == 1) {
            graphics2D.drawString(">", 220 - tablero.TAMAÑO_DE_BLOQUE, 500);
        }

        texto = "Salir";

        graphics2D.drawString(texto, 290, 550);
        if (comandoNum == 2) {
            graphics2D.drawString(">", 220 - tablero.TAMAÑO_DE_BLOQUE, 550);
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
