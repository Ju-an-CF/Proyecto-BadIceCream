package mecánicas;

import escenario.Tablero;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener {
    Tablero tablero;
    public boolean arribaPresionado, abajoPresionado, derechaPresionado, izquierdaPresionado, sePuedeRomper;


    public Control(Tablero tablero) {
       this.tablero=tablero;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        //Menu estado
        if(tablero.estadoActualDeJuego == tablero.ESTADO_DE_TITULO){
            if(tecla == KeyEvent.VK_W){
                tablero.iu.comandoNum--;
                if(tablero.iu.comandoNum<0){
                    tablero.iu.comandoNum=2;
                }
            }
            if(tecla == KeyEvent.VK_S){
                tablero.iu.comandoNum++;
                if(tablero.iu.comandoNum>2){
                    tablero.iu.comandoNum=0;
                }
            }
        }
        if(tecla==KeyEvent.VK_ENTER){
            if(tablero.iu.comandoNum==0){
                tablero.estadoActualDeJuego=tablero.ESTADO_DE_JUEGO;
                tablero.pararMúsica();
                tablero.reproducirSE(2);
            }
            if(tablero.iu.comandoNum==1){
                //add later
            }
            if(tablero.iu.comandoNum==2){
                System.exit(0);
            }
        }
        //jugador estado
        if (tecla == KeyEvent.VK_W) {
            arribaPresionado = true;
        }
        if (tecla == KeyEvent.VK_S) {
            abajoPresionado = true;
        }
        if (tecla == KeyEvent.VK_A) {
            izquierdaPresionado = true;
        }
        if (tecla == KeyEvent.VK_D) {
            derechaPresionado = true;
        }
        if (tecla == KeyEvent.VK_SPACE) {
            espacioPresionado = true;
        }
        if (tecla == KeyEvent.VK_ESCAPE){
            sePuedeRomper = true;
        }
        if (tecla == KeyEvent.VK_P){
            if(tablero.estadoActualDeJuego== tablero.ESTADO_DE_JUEGO){
                tablero.estadoActualDeJuego=tablero.ESTADO_DE_PAUSA;
            } else if (tablero.estadoActualDeJuego==tablero.ESTADO_DE_PAUSA) {
                tablero.estadoActualDeJuego=tablero.ESTADO_DE_JUEGO;
            }
        }

    }

    private void romperBloque() {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_W) {
            arribaPresionado = false;
        }
        if (tecla == KeyEvent.VK_S) {
            abajoPresionado = false;
        }
        if (tecla == KeyEvent.VK_A) {
            izquierdaPresionado = false;
        }
        if (tecla == KeyEvent.VK_D) {
            derechaPresionado = false;
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
