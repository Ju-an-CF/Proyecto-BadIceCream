package mecánicas;

import escenario.Tablero;
import interfazDeUsuario.EstadoDeJuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class Control implements KeyListener, Serializable {
    Tablero tablero;
    public boolean arribaPresionado, abajoPresionado, derechaPresionado, izquierdaPresionado, enterPresionado;


    public Control(Tablero tablero) {
        this.tablero = tablero;
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
                arribaPresionado = true;
                break;
            case KeyEvent.VK_S:
                abajoPresionado = true;
                break;
            case KeyEvent.VK_A:
                izquierdaPresionado = true;
                break;
            case KeyEvent.VK_D:
                derechaPresionado = true;
                break;
            case KeyEvent.VK_ENTER:
                enterPresionado = true;
                break;
        }

        // Manejo de estados específicos
        switch (tablero.estadoActualDeJuego) {
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
            if (tablero.iu.comandoNum == 0) {
                tablero.estadoActualDeJuego = EstadoDeJuego.TÍTULO;
            }
        }
    }


    private void estadoPausa(int tecla) {
        if (tecla == KeyEvent.VK_ESCAPE) {
            tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
        }
    }


    private void estadoOpciones(int tecla) {
        if (tecla == KeyEvent.VK_ENTER) {
            enterPresionado = true;
        }
        if (tecla == KeyEvent.VK_ESCAPE) {
            tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
        }
        int maxComandoNum = 0;
        switch (tablero.iu.subEstado) {
            case 0:
                maxComandoNum = 4;
                break;
            case 2:
                maxComandoNum = 1;
                break;
        }
        if (tecla == KeyEvent.VK_W) {
            tablero.iu.comandoNum--;
            if (tablero.iu.comandoNum < 0) {
                tablero.iu.comandoNum = maxComandoNum;
            }
        }
        if (tecla == KeyEvent.VK_S) {
            tablero.iu.comandoNum++;
            //music
            if (tablero.iu.comandoNum > maxComandoNum) {
                tablero.iu.comandoNum = 0;
            }
        }
        if (tecla == KeyEvent.VK_A) {
            if (tablero.iu.subEstado == 0) {
                if (tablero.iu.comandoNum == 0 && tablero.getMúsica().getEscalaDeVolumen() > 0) {
                    tablero.getMúsica().disminuirVolumen();
                    tablero.getMúsica().verificarVolumen();
                    //tablero.reproducirSE();
                }
                if (tablero.iu.comandoNum == 1 && tablero.getSe().getEscalaDeVolumen() > 0) {
                    tablero.getSe().disminuirVolumen();
                    //tablero.reproducirSE();
                }
            }
        }
        if (tecla == KeyEvent.VK_D) {
            if (tablero.iu.subEstado == 0) {
                if (tablero.iu.comandoNum == 0 && tablero.getMúsica().getEscalaDeVolumen() < 5) {
                    tablero.getMúsica().aumentarVolumen();
                    tablero.getMúsica().verificarVolumen();
                    //tablero.reproducirSE();
                }
            }
            if (tablero.iu.comandoNum == 1 && tablero.getSe().getEscalaDeVolumen() < 5) {
                tablero.getSe().aumentarVolumen();
                //tablero.reproducirSE();
            }
        }
    }


    private void estadoJugador(int tecla) {
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
            tablero.jugador.romperOCrearHielo();
        }
        if (tecla == KeyEvent.VK_F) {
            tablero.estadoActualDeJuego = EstadoDeJuego.OPCIONES;
        }
        if (tecla == KeyEvent.VK_P) {
            tablero.estadoActualDeJuego = EstadoDeJuego.PAUSA;
        }
    }


    public void estadoTítulo(int tecla) {
        if (tecla == KeyEvent.VK_W) {
            tablero.iu.comandoNum--;
            if (tablero.iu.comandoNum < 0) {
                tablero.iu.comandoNum = 2;
            }
        }
        if (tecla == KeyEvent.VK_S) {
            tablero.iu.comandoNum++;
            if (tablero.iu.comandoNum > 2) {
                tablero.iu.comandoNum = 0;
            }
        }

        if (tecla == KeyEvent.VK_ENTER) {
            if (tablero.iu.comandoNum == 0) {
                tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
                tablero.pararMúsica();
                tablero.reproducirMúsica(2);
            }
            if (tablero.iu.comandoNum == 1) {
                tablero.guardarCargar.cargar();
                tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
                tablero.pararMúsica();
                tablero.reproducirMúsica(2);
            }
            if (tablero.iu.comandoNum == 2) {
                System.exit(0);
            }
        }

    }

    private void estadoDerrota(int tecla) {
        if (tecla == KeyEvent.VK_W) {
            tablero.iu.comandoNum--;
            if (tablero.iu.comandoNum < 0) {
                tablero.iu.comandoNum = 1;
            }
            //efecto sonido
        }
        if (tecla == KeyEvent.VK_S) {
            tablero.iu.comandoNum++;
            if (tablero.iu.comandoNum > 1) {
                tablero.iu.comandoNum = 0;
            }
            //efecto sonido
        }
        if (tecla == KeyEvent.VK_ENTER) {
            if (tablero.iu.comandoNum == 0) {
                tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
                tablero.reintentar();
            } else if (tablero.iu.comandoNum == 1) {
                tablero.estadoActualDeJuego = EstadoDeJuego.TÍTULO;
                tablero.reestablecer();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();

        // Actualización del estado de las teclas al soltarlas
        switch (tecla) {
            case KeyEvent.VK_W:
                arribaPresionado = false;
                break;
            case KeyEvent.VK_S:
                abajoPresionado = false;
                break;
            case KeyEvent.VK_A:
                izquierdaPresionado = false;
                break;
            case KeyEvent.VK_D:
                derechaPresionado = false;
                break;
            case KeyEvent.VK_ENTER:
                enterPresionado = false;
                break;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
