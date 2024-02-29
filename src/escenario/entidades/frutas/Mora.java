package escenario.entidades.frutas;

import escenario.entidades.Entidad;
import escenario.Tablero;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mora extends Entidad {
    private int animaciónIndex = 0;
    private long últimoTiempoCambio = System.currentTimeMillis();
    private int velocidadAnimación = 100; // Cambia cada 100ms

    public Mora(Tablero tablero) {
        super(tablero);
        setNombre("Mora");
        imagen1 = configurarImagen("/fuentes/frutas/mora1");
        imagen2 = configurarImagen("/fuentes/frutas/mora2");
        imagen3 = configurarImagen("/fuentes/frutas/mora3");
        imagen4 = configurarImagen("/fuentes/frutas/mora4");
        imagen5 = configurarImagen("/fuentes/frutas/mora5");
    }

    public void dibujar(Graphics2D g2) {
        actualizarAnimación();
        BufferedImage imagenActual = getImagenActual();
        g2.drawImage(imagenActual, getMundoX(), getMundoY(), getTablero().TAMAÑO_DE_BLOQUE, getTablero().TAMAÑO_DE_BLOQUE, null);

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
        return switch (animaciónIndex) {
            case 0 -> imagen1;
            case 1 -> imagen2;
            case 2 -> imagen3;
            case 3 -> imagen4;
            case 4 -> imagen5;
            default -> imagen1;
            // En caso de un índice inválido, retorna imagen1 como valor por defecto.
        };
    }

}
