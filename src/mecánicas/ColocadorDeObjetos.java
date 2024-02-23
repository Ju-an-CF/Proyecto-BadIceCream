package mecánicas;

import escenario.Tablero;
import frutas.Mora;

public class ColocadorDeObjetos {
    Tablero gp;

    public ColocadorDeObjetos(Tablero gp) {
        this.gp = gp;
    }

    public void colocarObjeto() {
        gp.frutas[0] = new Mora();
        gp.frutas[0].mundoX = 8 * gp.TAMANIO_DE_BLOQUE;
        gp.frutas[0].mundoY = 9 * gp.TAMANIO_DE_BLOQUE;

        gp.frutas[1] = new Mora();
        gp.frutas[1].mundoX = 8 * gp.TAMANIO_DE_BLOQUE;
        gp.frutas[1].mundoY = 12 * gp.TAMANIO_DE_BLOQUE;
    }
}
