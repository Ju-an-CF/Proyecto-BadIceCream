package mecánicas;

import entidades.personajes.Ogro;
import entidades.personajes.Toro;
import escenario.Tablero;
import entidades.frutas.Mora;

public class ColocadorDeObjetos {
    Tablero gp;

    public ColocadorDeObjetos(Tablero gp) {
        this.gp = gp;
    }

    public void colocarMora() {
        if(gp.jugador.númeroDeFrutas==0) {
            gp.frutas[0] = new Mora(gp);
            gp.frutas[0].mundoX = 9 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[0].mundoY = 9 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[1] = new Mora(gp);
            gp.frutas[1].mundoX = 9 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[1].mundoY = 13 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[2] = new Mora(gp);
            gp.frutas[2].mundoX = 9 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[2].mundoY = 16 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[3] = new Mora(gp);
            gp.frutas[3].mundoX = 20 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[3].mundoY = 12 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[4] = new Mora(gp);
            gp.frutas[4].mundoX = 11 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[4].mundoY = 14 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[5] = new Mora(gp);
            gp.frutas[5].mundoX = 15 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[5].mundoY = 19 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[6] = new Mora(gp);
            gp.frutas[6].mundoX = 16 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[6].mundoY = 13 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[7] = new Mora(gp);
            gp.frutas[7].mundoX = 23 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[7].mundoY = 15 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[8] = new Mora(gp);
            gp.frutas[8].mundoX = 9 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[8].mundoY = 21 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[9] = new Mora(gp);
            gp.frutas[9].mundoX = 23 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[9].mundoY = 22 * gp.TAMAÑO_DE_BLOQUE;

            gp.frutas[10] = new Mora(gp);
            gp.frutas[10].mundoX = 21 * gp.TAMAÑO_DE_BLOQUE;
            gp.frutas[10].mundoY = 18 * gp.TAMAÑO_DE_BLOQUE;
        }
    }
   public void colocarEnemigos(){
        gp.enemigos[0]=new Toro(gp);
        gp.enemigos[0].mundoX=gp.TAMAÑO_DE_BLOQUE * 13;
        gp.enemigos[0].mundoY=gp.TAMAÑO_DE_BLOQUE * 16;

       gp.enemigos[1]=new Toro(gp);
       gp.enemigos[1].mundoX=gp.TAMAÑO_DE_BLOQUE *11;
       gp.enemigos[1].mundoY=gp.TAMAÑO_DE_BLOQUE *20;

       gp.enemigos[2]=new Ogro(gp);
       gp.enemigos[2].mundoX=gp.TAMAÑO_DE_BLOQUE *14;
       gp.enemigos[2].mundoY=gp.TAMAÑO_DE_BLOQUE *21;

       gp.enemigos[3]=new Ogro(gp);
       gp.enemigos[3].mundoX=gp.TAMAÑO_DE_BLOQUE *21;
       gp.enemigos[3].mundoY=gp.TAMAÑO_DE_BLOQUE *21;

       gp.enemigos[4] = new Toro(gp);
       gp.enemigos[4].mundoX=gp.TAMAÑO_DE_BLOQUE *22;
       gp.enemigos[4].mundoY=gp.TAMAÑO_DE_BLOQUE *10;

   }
}
