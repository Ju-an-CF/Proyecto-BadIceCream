package presentación;

import Negocio.entidades.enemigos.Enemigo;

public class GestorImagenEnemigo extends GestorImagenEntidad{

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
}
