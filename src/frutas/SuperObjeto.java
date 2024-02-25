package frutas;

import escenario.Tablero;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjeto {
    public BufferedImage imagen1, imagen2, imagen3, imagen4, imagen5;
    public String nombre;
    public boolean colisión = false;
    public int mundoX, mundoY;
    public Rectangle áreaSólida = new Rectangle(0,0,42,42);
    public int áreaSólidaPorDefectoX = 0;
    public int áreaSólidaPorDefectoY = 0;
    private int animaciónIndex = 0;
    private long últimoTiempoCambio = System.currentTimeMillis();
    private int velocidadAnimación = 100; // Cambia cada 100ms

    public void dibujar(Graphics2D g2, Tablero gp) {
        actualizarAnimación();

        int ventanaX = mundoX - gp.jugador.mundoX + gp.jugador.ventanaX;
        int ventanaY = mundoY - gp.jugador.mundoY + gp.jugador.ventanaY;

        if (mundoX + gp.TAMANIO_DE_BLOQUE * 11 > gp.jugador.mundoX - gp.jugador.ventanaX &&
                mundoX - gp.TAMANIO_DE_BLOQUE * 12 < gp.jugador.mundoX + gp.jugador.ventanaX &&
                mundoY + (gp.TAMANIO_DE_BLOQUE * 2) > gp.jugador.mundoY - gp.jugador.ventanaY &&
                mundoY - (gp.TAMANIO_DE_BLOQUE * 2) < gp.jugador.mundoY + gp.jugador.ventanaY) {

            BufferedImage imagenActual = getImagenActual();
            g2.drawImage(imagenActual, ventanaX, ventanaY, gp.TAMANIO_DE_BLOQUE, gp.TAMANIO_DE_BLOQUE, null);
        }
    }

    private void actualizarAnimación() {
        if(System.currentTimeMillis() - últimoTiempoCambio >= velocidadAnimación) {
            animaciónIndex++;
            // Asegura que el índice de animación varíe entre 0 y 4.
            if(animaciónIndex > 4) animaciónIndex = 0;
            últimoTiempoCambio = System.currentTimeMillis();
        }
    }

    private BufferedImage getImagenActual() {
        // Retorna la imagen actual basada en el índice de animación.
        switch (animaciónIndex) {
            case 0:
                return imagen1;
            case 1:
                return imagen2;
            case 2:
                return imagen3;
            case 3:
                return imagen4;
            case 4:
                return imagen5;
            default:
                // En caso de un índice inválido, retorna imagen1 como valor por defecto.
                return imagen1;
        }
    }
}
