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
        //Menu estado
        if (tablero.estadoActualDeJuego == EstadoDeJuego.TÍTULO) {
            estadoTítulo(tecla);
        }
        //jugador estado
        if (tablero.estadoActualDeJuego == EstadoDeJuego.JUEGO) {
            estadoJugador(tecla);
        }

        //Pausa
        if (tablero.estadoActualDeJuego == EstadoDeJuego.PAUSA) {
            estadoPausa(tecla);
        }

        //opciones
        if (tablero.estadoActualDeJuego == EstadoDeJuego.OPCIONES) {
            estadoOpciones(tecla);
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
        if (tecla == KeyEvent.VK_ENTER) {
            enterPresionado = false;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
