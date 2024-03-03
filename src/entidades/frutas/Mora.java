package entidades.frutas;

import entidades.Entidad;
import escenario.Tablero;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mora extends Fruta {

    private int animaciónIndex = 0;
    private long últimoTiempoCambio = System.currentTimeMillis();
    private int velocidadAnimación = 100; // Cambia cada 100ms
    private final static int PUNTAJE = 2;
    private final static String nombre = "Mora";

    public Mora(Tablero tablero) {
        super(tablero, PUNTAJE, nombre);
    }

    /**
     * Este método asigna imágenes a cinco variables utilizando el método configurarImagen().
     */
    public void obtenerImagen() {
        imagen1 = configurarImagen("mora1");
        imagen2 = configurarImagen("mora2");
        imagen3 = configurarImagen("mora3");
        imagen4 = configurarImagen("mora4");
        imagen5 = configurarImagen("mora5");
    }

    /**
     * Este método configura la imagen con el nombre especificado.
     *
     * @param nombreImagen El nombre de la imagen a configurar.
     * @return La imagen configurada.
     */
    public BufferedImage configurarImagen(String nombreImagen) {
        return super.configurarImagen("/fuentes/frutas/" + nombreImagen);
    }

    public void dibujar(Graphics2D g2) {
        actualizarAnimación();

        int ventanaX = mundoX - tablero.jugador.mundoX + tablero.jugador.ventanaX;
        int ventanaY = mundoY - tablero.jugador.mundoY + tablero.jugador.ventanaY;

        if (mundoX + tablero.TAMAÑO_DE_BLOQUE * 11 > tablero.jugador.mundoX - tablero.jugador.ventanaX &&
                mundoX - tablero.TAMAÑO_DE_BLOQUE * 12 < tablero.jugador.mundoX + tablero.jugador.ventanaX &&
                mundoY + (tablero.TAMAÑO_DE_BLOQUE * 2) > tablero.jugador.mundoY - tablero.jugador.ventanaY &&
                mundoY - (tablero.TAMAÑO_DE_BLOQUE * 2) < tablero.jugador.mundoY + tablero.jugador.ventanaY) {

            BufferedImage imagenActual = obtenerImagenActual();
            g2.drawImage(imagenActual, ventanaX, ventanaY, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
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
    /**
     * Este método devuelve la imagen actual basada en el índice de animación.
     *
     * @return La imagen actual.
     */
    private BufferedImage obtenerImagenActual() {
        // Retorna la imagen actual basada en el índice de animación.
        return switch (animaciónIndex) {
            case 0 -> imagen1;
            case 1 -> imagen2;
            case 2 -> imagen3;
            case 3 -> imagen4;
            case 4 -> imagen5;
                // En caso de un índice inválido, retorna imagen1 como valor por defecto.
            default -> imagen1;
        };
    }

}
