package mecánicas;

import escenario.entidades.Enemigo;
import escenario.Tablero;
import escenario.entidades.bloques.Hielo;
import escenario.entidades.frutas.Mora;

public class ColocadorDeObjetos {
    Tablero gp;

    public ColocadorDeObjetos(Tablero gp) {
        this.gp = gp;
    }

    public void colocarMora() {
        if (gp.jugador.númeroDeFrutas == 0) {
            gp.frutas[0] = new Mora(gp);
            gp.frutas[0].setMundoX(4 * gp.TAMAÑO_DE_BLOQUE);
            gp.frutas[0].setMundoY(2 * gp.TAMAÑO_DE_BLOQUE);
            gp.frutas[1] = new Mora(gp);
            gp.frutas[1].setMundoX(13 * gp.TAMAÑO_DE_BLOQUE);
            gp.frutas[1].setMundoY(11 * gp.TAMAÑO_DE_BLOQUE);
        }
    }

    public void colocarEnemigos() {
        gp.enemigos[0] = new Enemigo(gp);
        gp.enemigos[0].setMundoX(gp.TAMAÑO_DE_BLOQUE * 5);
        gp.enemigos[0].setMundoY(gp.TAMAÑO_DE_BLOQUE * 5);
        gp.enemigos[1] = new Enemigo(gp);
        gp.enemigos[1].setMundoX(gp.TAMAÑO_DE_BLOQUE * 10);
        gp.enemigos[1].setMundoY(gp.TAMAÑO_DE_BLOQUE * 10);
    }
    public void colocarBloquesInteractivos(){
        int i = 0;
        gp.bloqueInteractivos[i] = new Hielo(gp,9, 8); i++;
        gp.bloqueInteractivos[i] = new Hielo(gp,11, 8); i++;
        gp.bloqueInteractivos[i] = new Hielo(gp,12, 8); i++;
        gp.bloqueInteractivos[i] = new Hielo(gp,13, 8); i++;

    }
    public void colocarBloquesInteractivos(int x, int y, int i, Dirección dirección){
        int posX = (int) x / gp.TAMAÑO_DE_BLOQUE;
        int posY = (int) y / gp.TAMAÑO_DE_BLOQUE;

        switch (dirección){
            case ARRIBA: gp.bloqueInteractivos[i] = new Hielo(gp, posX, posY); break;
            case ABAJO: gp.bloqueInteractivos[i] = new Hielo(gp, posX, posY + 1); break;
            case IZQUIERDA: gp.bloqueInteractivos[i] = new Hielo(gp, posX - 1, posY + 1); break;
            case DERECHA: gp.bloqueInteractivos[i] = new Hielo(gp, posX + 1, posY + 1); break;
        }
    }
}
