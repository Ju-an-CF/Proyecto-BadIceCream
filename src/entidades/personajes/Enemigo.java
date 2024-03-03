package entidades.personajes;

import entidades.Entidad;
import escenario.Tablero;
import mecánicas.Dirección;

import java.util.Random;

public abstract class Enemigo extends Entidad {
    public Enemigo(Tablero tablero, int velocidad) {
        super(tablero);
        this.velocidad = velocidad;
        áreaSólida.x = 3;
        áreaSólida.y = 10;
        áreaSólida.width = 42;
        áreaSólida.height = 30;
        áreaSólidaPorDefectoX = áreaSólida.x;
        áreaSólidaPorDefectoY = áreaSólida.y;
        obtenerImagenDeEnemigo();
    }

    public abstract void obtenerImagenDeEnemigo();

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
