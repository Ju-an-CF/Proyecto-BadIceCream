package presentación;

import Negocio.entidades.frutas.Fruta;

import java.awt.image.BufferedImage;

public class GestorImagenFruta extends GestorImagenEntidad{
    public static void obtenerImagenDeFruta(Fruta fruta){
        fruta.imagen1 = configurarImagen("/presentación/fuentes/frutas/" + fruta.getNombre() + 1);
        fruta.imagen2 = configurarImagen("/presentación/fuentes/frutas/" + fruta.getNombre() + 2);
        fruta.imagen3 = configurarImagen("/presentación/fuentes/frutas/" + fruta.getNombre() + 3);
        fruta.imagen4 = configurarImagen("/presentación/fuentes/frutas/" + fruta.getNombre() + 4);
        fruta.imagen5 = configurarImagen("/presentación/fuentes/frutas/" + fruta.getNombre() + 5);
    }

    public static BufferedImage obtenerImagenActual(Fruta fruta) {
        // Retorna la imagen actual basada en el índice de animación.
        return switch (fruta.animaciónIndex) {
            case 0 -> fruta.imagen1;
            case 1 -> fruta.imagen2;
            case 2 -> fruta.imagen3;
            case 3 -> fruta.imagen4;
            case 4 -> fruta.imagen5;
            // En caso de un índice inválido, retorna imagen1 como valor por defecto.
            default -> fruta.imagen1;
        };
    }

    public static void actualizarAnimación(Fruta fruta) {
        if (System.currentTimeMillis() - fruta.últimoTiempoCambio >= fruta.velocidadAnimación) {
            fruta.animaciónIndex++;
            // Asegura que el índice de animación varíe entre 0 y 4.
            if (fruta.animaciónIndex > 4) fruta.animaciónIndex = 0;
            fruta.últimoTiempoCambio = System.currentTimeMillis();
        }
    }
}
