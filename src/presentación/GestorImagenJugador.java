package presentación;

import Negocio.entidades.Jugador;

import java.awt.image.BufferedImage;

public class GestorImagenJugador extends GestorImagenEntidad{
    public static void obtenerImagenDeJugador(Jugador jugador){
        // Configurar las imágenes del jugador para la dirección hacia arriba
        jugador.arriba1 = configurarImagen("/presentación/fuentes/jugador/jugador_arriba1");
        jugador.arriba2 = configurarImagen("/presentación/fuentes/jugador/jugador_arriba2");
        jugador.arriba3 = configurarImagen("/presentación/fuentes/jugador/jugador_arriba3");
        jugador.arriba4 = configurarImagen("/presentación/fuentes/jugador/jugador_arriba4");

        // Configurar las imágenes del jugador para la dirección hacia abajo
        jugador.abajo1 = configurarImagen("/presentación/fuentes/jugador/jugador_abajo1");
        jugador.abajo2 = configurarImagen("/presentación/fuentes/jugador/jugador_abajo2");
        jugador.abajo3 = configurarImagen("/presentación/fuentes/jugador/jugador_abajo3");
        jugador.abajo4 = configurarImagen("/presentación/fuentes/jugador/jugador_abajo4");

        // Configurar las imágenes del jugador para la dirección hacia izquierda
        jugador.izquierda1 = configurarImagen("/presentación/fuentes/jugador/jugador_izquierda1");
        jugador.izquierda2 = configurarImagen("/presentación/fuentes/jugador/jugador_izquierda2");
        jugador.izquierda3 = configurarImagen("/presentación/fuentes/jugador/jugador_izquierda3");
        jugador.izquierda4 = configurarImagen("/presentación/fuentes/jugador/jugador_izquierda4");

        // Configurar las imágenes del jugador para la dirección hacia derecha
        jugador.derecha1 = configurarImagen("/presentación/fuentes/jugador/jugador_derecha1");
        jugador.derecha2 = configurarImagen("/presentación/fuentes/jugador/jugador_derecha2");
        jugador.derecha3 = configurarImagen("/presentación/fuentes/jugador/jugador_derecha3");
        jugador.derecha4 = configurarImagen("/presentación/fuentes/jugador/jugador_derecha4");
    }

    public static BufferedImage obtenerImagen(Jugador jugador) {
        BufferedImage imagen = null;
        switch (jugador.getDirección()) {
            case ARRIBA:
                if (jugador.numeroDeMovimiento == 1) {
                    imagen = jugador.arriba1;
                }
                if (jugador.numeroDeMovimiento == 2) {
                    imagen = jugador.arriba2;
                }
                if (jugador.numeroDeMovimiento == 3) {
                    imagen = jugador.arriba3;
                }
                if (jugador.numeroDeMovimiento == 4) {
                    imagen = jugador.arriba4;
                }
                break;
            case ABAJO:
                if (jugador.numeroDeMovimiento == 1) {
                    imagen = jugador.abajo1;
                }
                if (jugador.numeroDeMovimiento == 2) {
                    imagen = jugador.abajo2;
                }
                if (jugador.numeroDeMovimiento == 3) {
                    imagen = jugador.abajo3;
                }
                if (jugador.numeroDeMovimiento == 4) {
                    imagen = jugador.abajo4;
                }
                break;
            case IZQUIERDA:
                if (jugador.numeroDeMovimiento == 1) {
                    imagen = jugador.izquierda1;
                }
                if (jugador.numeroDeMovimiento == 2) {
                    imagen = jugador.izquierda2;
                }
                if (jugador.numeroDeMovimiento == 3) {
                    imagen = jugador.izquierda3;
                }
                if (jugador.numeroDeMovimiento == 4) {
                    imagen = jugador.izquierda4;
                }
                break;
            case DERECHA:
                if (jugador.numeroDeMovimiento == 1) {
                    imagen = jugador.derecha1;
                }
                if (jugador.numeroDeMovimiento == 2) {
                    imagen = jugador.derecha2;
                }
                if (jugador.numeroDeMovimiento == 3) {
                    imagen = jugador.derecha3;
                }
                if (jugador.numeroDeMovimiento == 4) {
                    imagen = jugador.derecha4;
                }
                break;
        }
        return imagen;
    }

}
