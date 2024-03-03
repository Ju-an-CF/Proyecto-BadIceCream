package entidades.personajes;

import escenario.Tablero;

public class Ogro extends Enemigo{
    private final static int VELOCIDAD = 2;
    public Ogro(Tablero tablero) {
        super(tablero, VELOCIDAD);
        this.obtenerImagenDeEnemigo();
    }

    @Override
    public void obtenerImagenDeEnemigo() {
        arriba1 = configurarImagen("/fuentes/enemigo/ogro_arriba1");
        arriba2 = configurarImagen("/fuentes/enemigo/ogro_arriba2");
        arriba3 = configurarImagen("/fuentes/enemigo/ogro_arriba3");
        arriba4 = configurarImagen("/fuentes/enemigo/ogro_arriba4");
        abajo1 = configurarImagen("/fuentes/enemigo/ogro_abajo1");
        abajo2 = configurarImagen("/fuentes/enemigo/ogro_abajo2");
        abajo3 = configurarImagen("/fuentes/enemigo/ogro_abajo3");
        abajo4 = configurarImagen("/fuentes/enemigo/ogro_abajo4");
        izquierda1 = configurarImagen("/fuentes/enemigo/ogro_izquierda1");
        izquierda2 = configurarImagen("/fuentes/enemigo/ogro_izquierda2");
        izquierda3 = configurarImagen("/fuentes/enemigo/ogro_izquierda3");
        izquierda4 = configurarImagen("/fuentes/enemigo/ogro_izquierda4");
        derecha1 = configurarImagen("/fuentes/enemigo/ogro_derecha1");
        derecha2 = configurarImagen("/fuentes/enemigo/ogro_derecha2");
        derecha3 = configurarImagen("/fuentes/enemigo/ogro_derecha3");
        derecha4 = configurarImagen("/fuentes/enemigo/ogro_derecha4");
    }


}
