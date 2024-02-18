package mecánicas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener {
    public boolean arribaPresionado, abajoPresionado, derechaPresionado, izquierdaPresionado;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int código = e.getKeyCode();
        if (código == KeyEvent.VK_W) {
            arribaPresionado = true;
        }
        if (código == KeyEvent.VK_S) {
            abajoPresionado = true;
        }
        if (código == KeyEvent.VK_A) {
            izquierdaPresionado = true;
        }
        if (código == KeyEvent.VK_D) {
            derechaPresionado = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int código = e.getKeyCode();
        if (código == KeyEvent.VK_W) {
            arribaPresionado = false;
        }
        if (código == KeyEvent.VK_S) {
            abajoPresionado = false;
        }
        if (código == KeyEvent.VK_A) {
            izquierdaPresionado = false;
        }
        if (código == KeyEvent.VK_D) {
            derechaPresionado = false;
        }

    }
}
