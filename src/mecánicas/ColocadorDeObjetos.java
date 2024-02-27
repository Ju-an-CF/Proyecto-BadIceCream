package mecánicas;

import entidades.Enemigo;
import escenario.Tablero;
import frutas.Mora;

public class ColocadorDeObjetos {
    Tablero gp;

    public ColocadorDeObjetos(Tablero gp) {
        this.gp = gp;
    }

    public void colocarMora() {
        if (gp.jugador.númeroDeFrutas == 0) {
            gp.frutas[0] = new Mora(gp);
            gp.frutas[0].mundoX = 8 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[0].mundoY = 8 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[1] = new Mora(gp);
            gp.frutas[1].mundoX = 8 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[1].mundoY = 11 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[2] = new Mora(gp);
            gp.frutas[2].mundoX = 8 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[2].mundoY = 14 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[3] = new Mora(gp);
            gp.frutas[3].mundoX = 19 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[3].mundoY = 10 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[4] = new Mora(gp);
            gp.frutas[4].mundoX = 10 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[4].mundoY = 12 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[5] = new Mora(gp);
            gp.frutas[5].mundoX = 14 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[5].mundoY = 17 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[6] = new Mora(gp);
            gp.frutas[6].mundoX = 15 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[6].mundoY = 11 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[7] = new Mora(gp);
            gp.frutas[7].mundoX = 22 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[7].mundoY = 13 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[8] = new Mora(gp);
            gp.frutas[8].mundoX = 8 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[8].mundoY = 19 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[9] = new Mora(gp);
            gp.frutas[9].mundoX = 22 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[9].mundoY = 20 * gp.TAMANIO_DE_BLOQUE;

            gp.frutas[10] = new Mora(gp);
            gp.frutas[10].mundoX = 22 * gp.TAMANIO_DE_BLOQUE;
            gp.frutas[10].mundoY = 16 * gp.TAMANIO_DE_BLOQUE;
        }
    }

    public void colocarEnemigos() {
        gp.enemigos[0] = new Enemigo(gp);
        gp.enemigos[0].mundoX = gp.TAMANIO_DE_BLOQUE * 12;
        gp.enemigos[0].mundoY = gp.TAMANIO_DE_BLOQUE * 14;
        gp.enemigos[1] = new Enemigo(gp);
        gp.enemigos[1].mundoX = gp.TAMANIO_DE_BLOQUE * 8;
        gp.enemigos[1].mundoY = gp.TAMANIO_DE_BLOQUE * 8;

    }
}
