package mecánicas;

import escenario.Tablero;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener {
    private Tablero tablero;
    private boolean arribaPresionado;
    private boolean abajoPresionado;
    private boolean derechaPresionado;
    private boolean izquierdaPresionado;
    private boolean espacioPresionado;
    private boolean fPresionada;


    public Control(Tablero tablero) {
        this.setTablero(tablero);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        //Menu estado
        if (getTablero().estadoActualDeJuego == getTablero().ESTADO_DE_TITULO) {
            if (tecla == KeyEvent.VK_W) {
                getTablero().iu.comandoNum--;
                if (getTablero().iu.comandoNum < 0) {
                    getTablero().iu.comandoNum = 2;
                }
            }
            if (tecla == KeyEvent.VK_S) {
                getTablero().iu.comandoNum++;
                if (getTablero().iu.comandoNum > 2) {
                    getTablero().iu.comandoNum = 0;
                }
            }
        }
        if (tecla == KeyEvent.VK_ENTER) {
            if (getTablero().iu.comandoNum == 0) {
                getTablero().estadoActualDeJuego = getTablero().ESTADO_DE_JUEGO;
                getTablero().pararMúsica();
                getTablero().reproducirSE(2);
            }
            if (getTablero().iu.comandoNum == 1) {
                //add later
            }
            if (getTablero().iu.comandoNum == 2) {
                System.exit(0);
            }
        }
        //jugador estado
        if (tecla == KeyEvent.VK_W) {
            setArribaPresionado(true);
        }
        if (tecla == KeyEvent.VK_S) {
            setAbajoPresionado(true);
        }
        if (tecla == KeyEvent.VK_A) {
            setIzquierdaPresionado(true);
        }
        if (tecla == KeyEvent.VK_D) {
            setDerechaPresionado(true);
        }
        if (tecla == KeyEvent.VK_SPACE) {
            setEspacioPresionado(true);
        }
        if (tecla == KeyEvent.VK_F) {
            setfPresionada(true);
        }
        if (tecla == KeyEvent.VK_P) {
            if (getTablero().estadoActualDeJuego == getTablero().ESTADO_DE_JUEGO) {
                getTablero().estadoActualDeJuego = getTablero().ESTADO_DE_PAUSA;
            } else if (getTablero().estadoActualDeJuego == getTablero().ESTADO_DE_PAUSA) {
                getTablero().estadoActualDeJuego = getTablero().ESTADO_DE_JUEGO;
            }
        }

    }

    private void romperBloque() {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_W) {
            setArribaPresionado(false);
        }
        if (tecla == KeyEvent.VK_S) {
            setAbajoPresionado(false);
        }
        if (tecla == KeyEvent.VK_A) {
            setIzquierdaPresionado(false);
        }
        if (tecla == KeyEvent.VK_D) {
            setDerechaPresionado(false);
        }
        if (tecla == KeyEvent.VK_SPACE) {
            setEspacioPresionado(false);
        }
        if (tecla == KeyEvent.VK_F) {
            setfPresionada(false);
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean isArribaPresionado() {
        return arribaPresionado;
    }

    public void setArribaPresionado(boolean arribaPresionado) {
        this.arribaPresionado = arribaPresionado;
    }

    public boolean isAbajoPresionado() {
        return abajoPresionado;
    }

    public void setAbajoPresionado(boolean abajoPresionado) {
        this.abajoPresionado = abajoPresionado;
    }

    public boolean isDerechaPresionado() {
        return derechaPresionado;
    }

    public void setDerechaPresionado(boolean derechaPresionado) {
        this.derechaPresionado = derechaPresionado;
    }

    public boolean isIzquierdaPresionado() {
        return izquierdaPresionado;
    }

    public void setIzquierdaPresionado(boolean izquierdaPresionado) {
        this.izquierdaPresionado = izquierdaPresionado;
    }

    public boolean isEspacioPresionado() {
        return espacioPresionado;
    }

    public void setEspacioPresionado(boolean espacioPresionado) {
        this.espacioPresionado = espacioPresionado;
    }

    public boolean isfPresionada() {
        return fPresionada;
    }

    public void setfPresionada(boolean fPresionada) {
        this.fPresionada = fPresionada;
    }
}
