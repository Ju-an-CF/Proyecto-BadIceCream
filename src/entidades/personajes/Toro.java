package entidades.personajes;

import escenario.Tablero;

public class Toro extends Enemigo{
    public final static int VELOCIDAD = 1;
    public Toro(Tablero tablero) {
        super(tablero, VELOCIDAD);
    }

    @Override
    public void obtenerImagenDeEnemigo() {
        arriba1 = configurarImagen("/fuentes/enemigo/enemigo_arriba1");
        arriba2 = configurarImagen("/fuentes/enemigo/enemigo_arriba2");
        arriba3 = configurarImagen("/fuentes/enemigo/enemigo_arriba3");
        arriba4 = configurarImagen("/fuentes/enemigo/enemigo_arriba4");
        abajo1 = configurarImagen("/fuentes/enemigo/enemigo_abajo1");
        abajo2 = configurarImagen("/fuentes/enemigo/enemigo_abajo2");
        abajo3 = configurarImagen("/fuentes/enemigo/enemigo_abajo3");
        abajo4 = configurarImagen("/fuentes/enemigo/enemigo_abajo4");
        izquierda1 = configurarImagen("/fuentes/enemigo/enemigo_izquierda1");
        izquierda2 = configurarImagen("/fuentes/enemigo/enemigo_izquierda2");
        izquierda3 = configurarImagen("/fuentes/enemigo/enemigo_izquierda3");
        izquierda4 = configurarImagen("/fuentes/enemigo/enemigo_izquierda4");
        derecha1 = configurarImagen("/fuentes/enemigo/enemigo_derecha1");
        derecha2 = configurarImagen("/fuentes/enemigo/enemigo_derecha2");
        derecha3 = configurarImagen("/fuentes/enemigo/enemigo_derecha3");
        derecha4 = configurarImagen("/fuentes/enemigo/enemigo_derecha4");
    }

}
