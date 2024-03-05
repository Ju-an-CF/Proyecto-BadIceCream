package presentación;

import Negocio.entidades.frutas.Fruta;
import Negocio.entidades.objetos.Corazón;
import Negocio.entidades.enemigos.Enemigo;
import Negocio.entidades.Jugador;
import Negocio.escenario.HerramientaUtilidad;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GestorImagen{
    public static void obtenerImagenDeObjetos(Corazón corazón){
        corazón.imagen1 = configurarImagen("/presentación/fuentes/IU/corazón1");
        corazón.imagen2 = configurarImagen("/presentación/fuentes/IU/corazón2");
        corazón.imagen3 = configurarImagen("/presentación/fuentes/IU/corazón3");
    }

    public static void obtenerImagenDeEnemigo(Enemigo enemigo) {
        enemigo.arriba1 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_arriba1");
        enemigo.arriba2 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_arriba2");
        enemigo.arriba3 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_arriba3");
        enemigo.arriba4 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_arriba4");
        enemigo.abajo1 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_abajo1");
        enemigo.abajo2 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_abajo2");
        enemigo.abajo3 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_abajo3");
        enemigo.abajo4 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_abajo4");
        enemigo.izquierda1 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_izquierda1");
        enemigo.izquierda2 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_izquierda2");
        enemigo.izquierda3 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_izquierda3");
        enemigo.izquierda4 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_izquierda4");
        enemigo.derecha1 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_derecha1");
        enemigo.derecha2 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_derecha2");
        enemigo.derecha3 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_derecha3");
        enemigo.derecha4 = configurarImagen("/presentación/fuentes/enemigo/" + enemigo.getNombre() + "_derecha4");
    }


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

    public static BufferedImage configurarImagen(String nombreImagen) {
        HerramientaUtilidad ut = new HerramientaUtilidad();
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(GestorImagen.class.getResourceAsStream(nombreImagen + ".png"));
            imagen = ut.escalarImagen(imagen, 42, 42);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }


}
