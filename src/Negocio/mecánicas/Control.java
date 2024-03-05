package Negocio.mecánicas;

import Negocio.escenario.PanelDeJuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class Control implements KeyListener, Serializable {
    private PanelDeJuego panelDeJuego;
    private boolean arribaPresionado;
    private boolean abajoPresionado;
    private boolean derechaPresionado;
    private boolean izquierdaPresionado;
    private boolean enterPresionado;


    public Control(PanelDeJuego panelDeJuego) {
        this.setTablero(panelDeJuego);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        // Manejo de teclas comunes
        switch (tecla) {
            case KeyEvent.VK_W:
                setArribaPresionado(true);
                break;
            case KeyEvent.VK_S:
                setAbajoPresionado(true);
                break;
            case KeyEvent.VK_A:
                setIzquierdaPresionado(true);
                break;
            case KeyEvent.VK_D:
                setDerechaPresionado(true);
                break;
            case KeyEvent.VK_ENTER:
                setEnterPresionado(true);
                break;
        }

        // Manejo de estados específicos
        switch (getTablero().getEstadoActualDeJuego()) {
            case TÍTULO:
                estadoTítulo(tecla);
                break;
            case JUEGO:
                estadoJugador(tecla);
                break;
            case PAUSA:
                estadoPausa(tecla);
                break;
            case OPCIONES:
                estadoOpciones(tecla);
                break;
            case DERROTA:
                estadoDerrota(tecla);
                break;
            case VICTORIA:
                estadoVictoria(tecla);
                break;
        }
    }

    private void estadoVictoria(int tecla) {
        if (tecla == KeyEvent.VK_ENTER) {
            if (getTablero().getIu().comandoNum == 0) {
                getTablero().setEstadoActualDeJuego(EstadoDeJuego.TÍTULO);
            }
        }
    }


    private void estadoPausa(int tecla) {
        if (tecla == KeyEvent.VK_ESCAPE) {
            getTablero().setEstadoActualDeJuego(EstadoDeJuego.JUEGO);
        }
    }


    private void estadoOpciones(int tecla) {
        if (tecla == KeyEvent.VK_ENTER) {
            setEnterPresionado(true);
        }
        if (tecla == KeyEvent.VK_ESCAPE) {
            getTablero().setEstadoActualDeJuego(EstadoDeJuego.JUEGO);
        }
        int maxComandoNum = 0;
        switch (getTablero().getIu().subEstado) {
            case 0:
                maxComandoNum = 4;
                break;
            case 2:
                maxComandoNum = 1;
                break;
        }
        if (tecla == KeyEvent.VK_W) {
            getTablero().getIu().comandoNum--;
            if (getTablero().getIu().comandoNum < 0) {
                getTablero().getIu().comandoNum = maxComandoNum;
            }
        }
        if (tecla == KeyEvent.VK_S) {
            getTablero().getIu().comandoNum++;
            //music
            if (getTablero().getIu().comandoNum > maxComandoNum) {
                getTablero().getIu().comandoNum = 0;
            }
        }
        if (tecla == KeyEvent.VK_A) {
            if (getTablero().getIu().subEstado == 0) {
                if (getTablero().getIu().comandoNum == 0 && getTablero().getMúsica().getEscalaDeVolumen() > 0) {
                    getTablero().getMúsica().disminuirVolumen();
                    getTablero().getMúsica().verificarVolumen();
                    //tablero.reproducirSE();
                }
                if (getTablero().getIu().comandoNum == 1 && getTablero().getSe().getEscalaDeVolumen() > 0) {
                    getTablero().getSe().disminuirVolumen();
                    //tablero.reproducirSE();
                }
            }
        }
        if (tecla == KeyEvent.VK_D) {
            if (getTablero().getIu().subEstado == 0) {
                if (getTablero().getIu().comandoNum == 0 && getTablero().getMúsica().getEscalaDeVolumen() < 5) {
                    getTablero().getMúsica().aumentarVolumen();
                    getTablero().getMúsica().verificarVolumen();
                    //tablero.reproducirSE();
                }
            }
            if (getTablero().getIu().comandoNum == 1 && getTablero().getSe().getEscalaDeVolumen() < 5) {
                getTablero().getSe().aumentarVolumen();
                //tablero.reproducirSE();
            }
        }
    }


    private void estadoJugador(int tecla) {
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
            getTablero().getJugador().romperOCrearHielo();
        }
        if (tecla == KeyEvent.VK_F) {
            getTablero().setEstadoActualDeJuego(EstadoDeJuego.OPCIONES);
        }
        if (tecla == KeyEvent.VK_P) {
            getTablero().setEstadoActualDeJuego(EstadoDeJuego.PAUSA);
        }
    }


    public void estadoTítulo(int tecla) {
        if (tecla == KeyEvent.VK_W) {
            getTablero().getIu().comandoNum--;
            if (getTablero().getIu().comandoNum < 0) {
                getTablero().getIu().comandoNum = 2;
            }
        }
        if (tecla == KeyEvent.VK_S) {
            getTablero().getIu().comandoNum++;
            if (getTablero().getIu().comandoNum > 2) {
                getTablero().getIu().comandoNum = 0;
            }
        }

        if (tecla == KeyEvent.VK_ENTER) {
            if (getTablero().getIu().comandoNum == 0) {
                getTablero().setEstadoActualDeJuego(EstadoDeJuego.JUEGO);
                getTablero().pararMúsica();
                getTablero().reproducirMúsica(2);
            }
            if (getTablero().getIu().comandoNum == 1) {
                getTablero().getGuardarCargar().cargar(getTablero().getNivel());
                getTablero().setEstadoActualDeJuego(EstadoDeJuego.JUEGO);
                getTablero().pararMúsica();
                getTablero().reproducirMúsica(2);
            }
            if (getTablero().getIu().comandoNum == 2) {
                System.exit(0);
            }
        }

    }

    private void estadoDerrota(int tecla) {
        if (tecla == KeyEvent.VK_W) {
            getTablero().getIu().comandoNum--;
            if (getTablero().getIu().comandoNum < 0) {
                getTablero().getIu().comandoNum = 1;
            }
            //efecto presentación.sonido
        }
        if (tecla == KeyEvent.VK_S) {
            getTablero().getIu().comandoNum++;
            if (getTablero().getIu().comandoNum > 1) {
                getTablero().getIu().comandoNum = 0;
            }
            //efecto presentación.sonido
        }
        if (tecla == KeyEvent.VK_ENTER) {
            if (getTablero().getIu().comandoNum == 0) {
                getTablero().setEstadoActualDeJuego(EstadoDeJuego.JUEGO);
                getTablero().reintentar();
            } else if (getTablero().getIu().comandoNum == 1) {
                getTablero().setEstadoActualDeJuego(EstadoDeJuego.TÍTULO);
                getTablero().reestablecer();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();

        // Actualización del estado de las teclas al soltarlas
        switch (tecla) {
            case KeyEvent.VK_W:
                setArribaPresionado(false);
                break;
            case KeyEvent.VK_S:
                setAbajoPresionado(false);
                break;
            case KeyEvent.VK_A:
                setIzquierdaPresionado(false);
                break;
            case KeyEvent.VK_D:
                setDerechaPresionado(false);
                break;
            case KeyEvent.VK_ENTER:
                setEnterPresionado(false);
                break;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public PanelDeJuego getTablero() {
        return panelDeJuego;
    }

    public void setTablero(PanelDeJuego panelDeJuego) {
        this.panelDeJuego = panelDeJuego;
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

    public boolean isEnterPresionado() {
        return enterPresionado;
    }

    public void setEnterPresionado(boolean enterPresionado) {
        this.enterPresionado = enterPresionado;
    }
}
