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
    BufferedImage corazónFull, medioCorazón, corazónVacío, panelImagen, imagenDeFondo, imagenMenú,opcionesMen, hojaDeSprites, imagenDerrota;
    BufferedImage moraImagen;
    public double playTime;
    DecimalFormat decimalFormato = new DecimalFormat("0.00");
    public int comandoNum = 0;
    private int anchoFrame; // Ancho de un único frame en la hoja de sprites
    private int altoFrame; // Alto de un único frame
    private final int numeroDeFrames = 15; // Número total de frames
    private final double tiempoPorFrame = 1.0; // Tiempo que cada frame se muestra, ajusta según necesidad
    private boolean relojActivo = true; // Controla si el reloj está activo
    public int subEstado=0;



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



    public void dibujarEstadoDeJuegoSegúnEstado(){
        switch (tablero.estadoActualDeJuego){
            case TÍTULO: {
                dibujarMenú(graphics2D);
            } break;
            case VICTORIA: {
                dibujarPantallaDeVictoria();
            } break;
            case DERROTA: {
                dibujarPantallaDeDerrota();
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
               // pararReloj();
            } break;
            case OPCIONES:{
                dibujarPantallaDeOpciones();
            } break;
        }
    }

    private void dibujarPantallaDeVictoria() {
        Color colorFondo = new Color(0, 0, 0, 150); // 127 es aproximadamente 50% de transparencia
        graphics2D.setColor(colorFondo);

        // Dibuja el rectángulo de fondo cubriendo toda la pantalla
        graphics2D.fillRect(0, 0, tablero.ANCHO+42, tablero.ALTO);

        imagenDerrota=cargarRecursosAdicionales("/fuentes/IU/you_win.png");
        graphics2D.drawImage(imagenDerrota,tablero.TAMAÑO_DE_BLOQUE*5,tablero.TAMAÑO_DE_BLOQUE*3,250,250,null);

        //reintentar
        String text;
        graphics2D.setColor(Color.BLACK);
        int x,y;
        graphics2D.setFont(font.deriveFont(Font.BOLD, 28F));
        text="Salir a pantalla principal";
        x=getXParaCentrarTexto(text);
        y=tablero.TAMAÑO_DE_BLOQUE*11;
        graphics2D.drawString(text,x,y);

        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(text,x-4,y-4);

        if(comandoNum==0){
            graphics2D.drawString(">",x-40,y);
        }

    }

    private void dibujarPantallaDeDerrota() {
        //imagenDerrota=cargarRecursosAdicionales("/fuentes/IU/game_over.png");
        //graphics2D.drawImage(imagenDerrota, tablero.TAMAÑO_DE_BLOQUE*5,tablero.TAMAÑO_DE_BLOQUE*4,200,200,null);
        Color colorFondo = new Color(0, 0, 0, 150); // 127 es aproximadamente 50% de transparencia
        graphics2D.setColor(colorFondo);

        // Dibuja el rectángulo de fondo cubriendo toda la pantalla
        graphics2D.fillRect(0, 0, tablero.ANCHO+42, tablero.ALTO);

        imagenDerrota=cargarRecursosAdicionales("/fuentes/IU/game_over.png");
        graphics2D.drawImage(imagenDerrota,tablero.TAMAÑO_DE_BLOQUE*5,tablero.TAMAÑO_DE_BLOQUE*3,250,250,null);

        //reintentar
        String text;
        graphics2D.setColor(Color.BLACK);
        int x,y;
        graphics2D.setFont(font.deriveFont(Font.BOLD, 28F));
        text="Reintentar";
        x=getXParaCentrarTexto(text);
        y=tablero.TAMAÑO_DE_BLOQUE*11;
        graphics2D.drawString(text,x,y);

        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(text,x-4,y-4);

        if(comandoNum==0){
            graphics2D.drawString(">",x-40,y);
        }

        //regresar pantalla titulo
        graphics2D.setColor(Color.BLACK);

        text="Salir a pantalla principal";
        x=getXParaCentrarTexto(text);
        y+=55;
        graphics2D.drawString(text,x,y);

        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(text,x-4,y-4);

        if(comandoNum==1){
            graphics2D.drawString(">",x-100,y);
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
        Color colorFondo = new Color(0, 0, 0, 127); // 127 es aproximadamente 50% de transparencia
        graphics2D.setColor(colorFondo);

        // Dibuja el rectángulo de fondo cubriendo toda la pantalla
        graphics2D.fillRect(0, 0, tablero.ANCHO+42, tablero.ALTO);

        // Configura el color para el texto
        graphics2D.setColor(Color.WHITE);

        // Calcula la posición para centrar el texto
        graphics2D.setFont(font.deriveFont(Font.BOLD, 35F));
        String texto = "Pausado";
        int x = getXParaCentrarTexto(texto);

        int y = tablero.ALTO / 2;

        // Dibuja el texto
        graphics2D.drawString(texto, x, y);
    }

    public int getXParaCentrarTexto(String texto) {
        // Calcula la longitud del texto en píxeles.
        int longitudTexto = (int) graphics2D.getFontMetrics().getStringBounds(texto, graphics2D).getWidth();

        int desplazamiento = 22; // Ajusta este valor según sea necesario.
        int xParaCentrar = (tablero.ALTO - longitudTexto) / 2 + desplazamiento;
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
    public void dibujarPantallaDeOpciones(){
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(font.deriveFont(Font.BOLD, 20F));

        //sub ventana
        int frameX=tablero.TAMAÑO_DE_BLOQUE*4;
        int frameY=tablero.TAMAÑO_DE_BLOQUE;
        int frameAncho=tablero.TAMAÑO_DE_BLOQUE*9;
        int frameAlto=tablero.TAMAÑO_DE_BLOQUE*8;
        dibujarSubVentana(frameX,frameY,frameAncho,frameAlto);
        switch (subEstado) {
            case 0:
                opcionesF(frameX, frameY);
                break;
            case 1:
                opcionesControl(frameX,frameY); break;
            case 2:
                opcionesFinDeJuego(frameX,frameY);
                break;
        }
    }

    private void opcionesFinDeJuego(int frameX, int frameY) {
        int textX=frameX+tablero.TAMAÑO_DE_BLOQUE*2;
        int textY=frameY+tablero.TAMAÑO_DE_BLOQUE;

        String text="Salir y Guardar";
        graphics2D.drawString(text,textX,textY);

        //si
        text="Si";
        textX=getXParaCentrarTexto(text);
        textY+=tablero.TAMAÑO_DE_BLOQUE*3;
        graphics2D.drawString(text,textX,textY);
        if(comandoNum==0){
            graphics2D.drawString(">",textX-25,textY);
            if(tablero.getControl().enterPresionado){
                subEstado=0;
                tablero.guardarCargar.guardar();
                tablero.estadoActualDeJuego=EstadoDeJuego.TÍTULO;
            }
        }
        //no
        text="No";
        textX=getXParaCentrarTexto(text);
        textY+=tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString(text,textX,textY);
        if(comandoNum==1){
            graphics2D.drawString(">",textX-25,textY);
            if(tablero.getControl().enterPresionado){
                subEstado=0;
                comandoNum=3;
            }
        }
    }

    private void dibujarSubVentana(int x, int y, int ancho, int alto) {
        Color c =new Color(0,0,0,220);
        graphics2D.setColor(c);
        graphics2D.fillRoundRect(x,y,ancho,alto,35,35);

        c=new Color(255,255,255);
        graphics2D.setColor(c);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x+5,y+5,ancho-10,alto-10,25,25);

    }

    private void opcionesF(int frameX, int frameY) {
        int textX;
        int textY;

        String text = "Opciones";
        textX = getXParaCentrarTexto(text);
        textY = frameY + tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString(text, textX, textY);

        //musica
        textX = frameX + tablero.TAMAÑO_DE_BLOQUE;
        textY += tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString("Music", textX, textY);
        if (comandoNum == 0) {
            graphics2D.drawString(">", textX - 25, textY);
        }
        //SE
        textY += tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString("ES", textX, textY);
        if (comandoNum == 1) {
            graphics2D.drawString(">", textX - 25, textY);
        }

        //Control
        textY += tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString("Control", textX, textY);
        if (comandoNum == 2) {
            graphics2D.drawString(">", textX - 25, textY);
            if(tablero.getControl().enterPresionado){
                subEstado=1;
                comandoNum=0;
            }
        }

        //terminarJuego
        textY += tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString("Terminar Juego", textX, textY);
        if (comandoNum == 3) {
            graphics2D.drawString(">", textX - 25, textY);
            if(tablero.getControl().enterPresionado){
                subEstado=2;
                comandoNum=0;
            }
        }

        //regresar
        textY += tablero.TAMAÑO_DE_BLOQUE * 2;
        graphics2D.drawString("Regresar", textX, textY);
        if (comandoNum == 4) {
            graphics2D.drawString(">", textX - 25, textY);
            if(tablero.getControl().enterPresionado){
                tablero.estadoActualDeJuego=EstadoDeJuego.JUEGO;
                comandoNum=0;
            }
        }

        //music volumen
        textX=frameX+(int)(tablero.TAMAÑO_DE_BLOQUE*4.5);
        textY= frameY+60;
        graphics2D.drawRect(textX, textY, 120, 24);
        int volumenAncho=24*tablero.getMúsica().getEscalaDeVolumen();
        graphics2D.fillRect(textX,textY,volumenAncho,24);

        //es
        textY+=tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawRect(textX,textY,120,24);
        volumenAncho=24*tablero.getSe().getEscalaDeVolumen();
        graphics2D.fillRect(textX,textY,volumenAncho,24);

        tablero.getConfiguración().guardarConfig();

    }
    public void opcionesControl(int frameX, int frameY){
        int textX;
        int textY;

        //Titulo
        String text="Controles";
        textX=getXParaCentrarTexto(text);
        textY=frameY+tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString(text,textX,textY);


        textX=frameX+tablero.TAMAÑO_DE_BLOQUE;
        textY+=tablero.TAMAÑO_DE_BLOQUE;

        graphics2D.drawString("Moverse", textX,textY);textY+=tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString("Pausa",textX,textY);textY+=tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString("Opciones", textX,textY);textY+=tablero.TAMAÑO_DE_BLOQUE;
        //graphics2D.drawString("Moverse",textX,textY)textY+=tablero.TAMAÑO_DE_BLOQUE;

        textX=frameX+tablero.TAMAÑO_DE_BLOQUE*6;
        textY=frameY+tablero.TAMAÑO_DE_BLOQUE*2;
        graphics2D.drawString("WASD",textX,textY);textY+=tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString("P",textX,textY);textY+=tablero.TAMAÑO_DE_BLOQUE;
        graphics2D.drawString("F",textX,textY);textY+=tablero.TAMAÑO_DE_BLOQUE;
        //graphics2D.drawString("Enter",textX,textY);textY+=tablero.TAMAÑO_DE_BLOQUE;

        //BACK
        textX=frameX+tablero.TAMAÑO_DE_BLOQUE;
        textY=frameY+tablero.TAMAÑO_DE_BLOQUE*5;

        graphics2D.drawString("Regresar",textX,textY);
        if(comandoNum==0){
            graphics2D.drawString(">",textX-25,textY);
            if(tablero.getControl().enterPresionado){
                subEstado=0;
                comandoNum=2;
            }
        }
    }


}
