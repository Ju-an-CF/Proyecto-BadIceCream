package entidades;

import escenario.Tablero;

import java.util.Random;

public class Enemigo extends Entidad {
    public Enemigo(Tablero tablero) {
        super(tablero);
        velocidad = 1;
        áreaSólida.x = 3;
        áreaSólida.y = 10;
        áreaSólida.width = 42;
        áreaSólida.height = 30;
        áreaSólidaPorDefectoX = áreaSólida.x;
        áreaSólidaPorDefectoY = áreaSólida.y;
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
                dirección = "arriba";
            }
            if (i > 25 && i <= 50) {
                dirección = "abajo";
            }
            if (i > 50 && i <= 75) {
                dirección = "izquierda";
            }
            if (i > 75 && i <= 100) {
                dirección = "derecha";
            }
            contadorBloqueoDeAcción = 0;
        }
    }

}
