package entidades;

import escenario.Tablero;
import escenario.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entidad {

    public Tablero tablero;
    public int mundoX, mundoY;
    public int velocidad;
    public BufferedImage arriba1, arriba2, arriba3, arriba4, abajo1, abajo2, abajo3, abajo4, izquierda1, izquierda2, izquierda3, izquierda4, derecha1, derecha2, derecha3, derecha4;
    public String dirección="abajo";
    public int contadorMovimiento = 0;
    public int numeroDeMovimiento = 1;
    public Rectangle áreaSólida = new Rectangle(0, 0, 42, 42);
    public int áreaSólidaPorDefectoX, áreaSólidaPorDefectoY;
    public boolean colisiónActiva = false;
    public int contadorBloqueoDeAcción=0;
    public BufferedImage imagen1, imagen2, imagen3, imagen4, imagen5;
    public String nombre;
    public boolean colisión = false;

    public Entidad(Tablero tablero) {
        this.tablero = tablero;
    }

    public void establecerAcción(){}

    public void actualizar(){

        establecerAcción();
        colisiónActiva=false;
        tablero.checkColisión.verificarBloque(this);
        tablero.checkColisión.verificarObjeto(this,false);
        tablero.checkColisión.verificarEntidad(this,tablero.enemigos);
        boolean contactoConJugador=tablero.checkColisión.verificarJugador(this);

        if(contactoConJugador){
            if(!tablero.jugador.invencible){
                tablero.jugador.vida-=1;
                tablero.jugador.invencible=true;
            }
        }

        //entidad puede moverse
        if (!colisiónActiva) {
            switch (dirección) {
                case ("arriba"):
                    mundoY -= velocidad;
                    break;
                case ("abajo"):
                    mundoY += velocidad;
                    break;
                case ("izquierda"):
                    mundoX -= velocidad;
                    break;
                case ("derecha"):
                    mundoX += velocidad;
                    break;
            }
        }
        contadorMovimiento++;
        if (contadorMovimiento > 10) {
            if (numeroDeMovimiento == 1) {
                numeroDeMovimiento = 2;
            } else if (numeroDeMovimiento == 2) {
                numeroDeMovimiento = 1;
            }
            contadorMovimiento = 0;
        }
    }
    public BufferedImage setUp(String nombreImagen) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(nombreImagen + ".png"));
            imagen = uTool.scaledImage(imagen, 42, 42);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }

    public void dibujar(Graphics2D graphics2D) {
        BufferedImage imagen = null;
        int ventanaX = mundoX - tablero.jugador.mundoX + tablero.jugador.ventanaX;
        int ventanaY = mundoY - tablero.jugador.mundoY + tablero.jugador.ventanaY;

        if (mundoX + tablero.TAMANIO_DE_BLOQUE * 11 > tablero.jugador.mundoX - tablero.jugador.ventanaX &&
                mundoX - tablero.TAMANIO_DE_BLOQUE * 12 < tablero.jugador.mundoX + tablero.jugador.ventanaX &&
                mundoY + (tablero.TAMANIO_DE_BLOQUE * 2) > tablero.jugador.mundoY - tablero.jugador.ventanaY &&
                mundoY - (tablero.TAMANIO_DE_BLOQUE * 2) < tablero.jugador.mundoY + tablero.jugador.ventanaY) {
            switch (dirección) {
                case "arriba":
                    if (numeroDeMovimiento == 1) {
                        imagen = arriba1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = arriba2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = arriba3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = arriba4;
                    }
                    break;
                case "abajo":
                    if (numeroDeMovimiento == 1) {
                        imagen = abajo1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = abajo2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = abajo3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = abajo4;
                    }
                    break;
                case "izquierda":
                    if (numeroDeMovimiento == 1) {
                        imagen = izquierda1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = izquierda2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = izquierda3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = izquierda4;
                    }
                    break;
                case "derecha":
                    if (numeroDeMovimiento == 1) {
                        imagen = derecha1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = derecha2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = derecha3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = derecha4;
                    }
                    break;
            }
            graphics2D.drawImage(imagen, ventanaX, ventanaY, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE, null);
        }
    }


}
