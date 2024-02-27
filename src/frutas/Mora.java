package frutas;

import entidades.Entidad;
import escenario.Tablero;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mora extends Entidad {
    private int animaciónIndex = 0;
    private long últimoTiempoCambio = System.currentTimeMillis();
    private int velocidadAnimación = 100; // Cambia cada 100ms

    public Mora(Tablero tablero) {
        super(tablero);
        nombre = "Mora";
        imagen1 = setUp("/fuentes/frutas/mora1");
        imagen2 = setUp("/fuentes/frutas/mora2");
        imagen3 = setUp("/fuentes/frutas/mora3");
        imagen4 = setUp("/fuentes/frutas/mora4");
        imagen5 = setUp("/fuentes/frutas/mora5");
    }

    public void dibujar(Graphics2D g2) {
        actualizarAnimación();

        int ventanaX = mundoX - tablero.jugador.mundoX + tablero.jugador.ventanaX;
        int ventanaY = mundoY - tablero.jugador.mundoY + tablero.jugador.ventanaY;

        if (mundoX + tablero.TAMANIO_DE_BLOQUE * 11 > tablero.jugador.mundoX - tablero.jugador.ventanaX &&
                mundoX - tablero.TAMANIO_DE_BLOQUE * 12 < tablero.jugador.mundoX + tablero.jugador.ventanaX &&
                mundoY + (tablero.TAMANIO_DE_BLOQUE * 2) > tablero.jugador.mundoY - tablero.jugador.ventanaY &&
                mundoY - (tablero.TAMANIO_DE_BLOQUE * 2) < tablero.jugador.mundoY + tablero.jugador.ventanaY) {

            BufferedImage imagenActual = getImagenActual();
            g2.drawImage(imagenActual, ventanaX, ventanaY, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE, null);
        }
    }

    private void actualizarAnimación() {
        if (System.currentTimeMillis() - últimoTiempoCambio >= velocidadAnimación) {
            animaciónIndex++;
            // Asegura que el índice de animación varíe entre 0 y 4.
            if (animaciónIndex > 4) animaciónIndex = 0;
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
