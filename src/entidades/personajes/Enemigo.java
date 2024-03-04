package entidades.personajes;

import entidades.Entidad;
import escenario.Tablero;
import mecánicas.Dirección;

import java.util.Random;
/**
 * Clase abstracta que representa a los enemigos del juego.
 * Extiende de la clase Entidad.
 */
public abstract class Enemigo extends Entidad {
    /**
     * Constructor de la clase Enemigo.
     * @param tablero Referencia al objeto Tablero.
     * @param velocidad Velocidad de movimiento del enemigo.
     */
    public Enemigo(Tablero tablero, int velocidad) {
        super(tablero);
        // Inicialización de las propiedades de área sólida de la entidad
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
    /**
     * Método para establecer la acción del enemigo, que cambia su dirección de movimiento de forma aleatoria.
     */
    public void establecerAcción() {
        contadorBloqueoDeAcción++;
        if (contadorBloqueoDeAcción == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            // Después de 120 actualizaciones, se cambia aleatoriamente la dirección de movimiento del enemigo
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
            // Reiniciar el contador de bloqueo de acción
            contadorBloqueoDeAcción = 0;
        }
    }

}
