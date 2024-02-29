package escenario.entidades;

import escenario.Tablero;
import mecánicas.Dirección;

import java.util.Random;

public class Enemigo extends Entidad {
    public Enemigo(Tablero tablero) {
        super(tablero);
        setVelocidad(1);
        getÁreaSólida().x = 3;
        getÁreaSólida().y = 10;
        getÁreaSólida().width = 42;
        getÁreaSólida().height = 30;
        setÁreaSólidaPorDefectoX(getÁreaSólida().x);
        setÁreaSólidaPorDefectoY(getÁreaSólida().y);
        obtenerImagenDeEnemigo();
    }

    public void obtenerImagenDeEnemigo() {
        arriba1 = configurarImagen("/fuentes/enemigo/jugador_arriba1");
        arriba2 = configurarImagen("/fuentes/enemigo/jugador_arriba2");
        arriba3 = configurarImagen("/fuentes/enemigo/jugador_arriba3");
        arriba4 = configurarImagen("/fuentes/enemigo/jugador_arriba4");
        abajo1 = configurarImagen("/fuentes/enemigo/jugador_abajo1");
        abajo2 = configurarImagen("/fuentes/enemigo/jugador_abajo2");
        abajo3 = configurarImagen("/fuentes/enemigo/jugador_abajo3");
        abajo4 = configurarImagen("/fuentes/enemigo/jugador_abajo4");
        izquierda1 = configurarImagen("/fuentes/enemigo/jugador_izquierda1");
        izquierda2 = configurarImagen("/fuentes/enemigo/jugador_izquierda2");
        izquierda3 = configurarImagen("/fuentes/enemigo/jugador_izquierda3");
        izquierda4 = configurarImagen("/fuentes/enemigo/jugador_izquierda4");
        derecha1 = configurarImagen("/fuentes/enemigo/jugador_derecha1");
        derecha2 = configurarImagen("/fuentes/enemigo/jugador_derecha2");
        derecha3 = configurarImagen("/fuentes/enemigo/jugador_derecha3");
        derecha4 = configurarImagen("/fuentes/enemigo/jugador_derecha4");
    }

    public void establecerAcción() {
        contadorBloqueoDeAcción++;
        if (contadorBloqueoDeAcción == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                dirección = Dirección.ARRIBA;
            }
            if (i > 25 && i <= 50) {
                dirección = Dirección.ABAJO;
            }
            if (i > 50 && i <= 75) {
                dirección = Dirección.IZQUIERDA;
            }
            if (i > 75 && i <= 100) {
                dirección = Dirección.DERECHA;
            }
            contadorBloqueoDeAcción = 0;
        }
    }

}
