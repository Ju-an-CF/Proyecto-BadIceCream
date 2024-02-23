package frutas;

import escenario.Tablero;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjeto {
    public BufferedImage imagen;
    public String nombre;
    public boolean colisión = false;
    public int mundoX, mundoY;
    public Rectangle áreaSólida = new Rectangle(0,0,42,42);
    public int áreaSólidaPorDefectoX = 0;
    public int áreaSólidaPorDefectoY = 0;
    public void dibujar(Graphics2D g2, Tablero gp){
        int ventanaX = mundoX - gp.jugador.mundoX + gp.jugador.ventanaX;
        int ventanaY = mundoY - gp.jugador.mundoY + gp.jugador.ventanaY;

        if(mundoX + gp.TAMANIO_DE_BLOQUE*11 > gp.jugador.mundoX - gp.jugador.ventanaX &&
                mundoX - gp.TAMANIO_DE_BLOQUE*12 < gp.jugador.mundoX + gp.jugador.ventanaX &&
                mundoY + (gp.TAMANIO_DE_BLOQUE*2) > gp.jugador.mundoY - gp.jugador.ventanaY &&
                mundoY - (gp.TAMANIO_DE_BLOQUE*2) < gp.jugador.mundoY + gp.jugador.ventanaY) {
            g2.drawImage(imagen, ventanaX, ventanaY, gp.TAMANIO_DE_BLOQUE, gp.TAMANIO_DE_BLOQUE, null);
        }
    }

}
