package mecánicas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener {
    public boolean arribaPresionado, abajoPresionado, derechaPresionado, izquierdaPresionado, sePuedeRomper;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
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
        if (tecla == KeyEvent.VK_ESCAPE){
            sePuedeRomper = true;
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
}
