package mecánicas;

import escenario.Tablero;
import interfazDeUsuario.EstadoDeJuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class Control implements KeyListener {
    Tablero tablero;
    public boolean arribaPresionado, abajoPresionado, derechaPresionado, izquierdaPresionado,enterPresionado;


    public Control(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        //Menú Principal
        if (tablero.estadoActualDeJuego == EstadoDeJuego.TÍTULO) {
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
                    tablero.reproducirSE(2);
                }
                if (tablero.iu.comandoNum == 1) {
                    tablero.guardadoYCarga.cargar();
                    tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
                    tablero.pararMúsica();
                    tablero.reproducirSE(2);
                }
                if (tablero.iu.comandoNum == 2) {
                    System.exit(0);
                }
            }
        }

        //Movimiento de Jugador
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

        //Estado De pausa
        if (tecla == KeyEvent.VK_P) {
            if (tablero.estadoActualDeJuego == EstadoDeJuego.JUEGO) {
                tablero.estadoActualDeJuego = EstadoDeJuego.PAUSA;
            } else if (tablero.estadoActualDeJuego == EstadoDeJuego.PAUSA) {
                tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
            }
        }

        //Menú de Opciones
        if(tecla == KeyEvent.VK_ESCAPE) {
            tablero.estadoActualDeJuego = EstadoDeJuego.OPCIONES;
        }
        if(tablero.estadoActualDeJuego == EstadoDeJuego.OPCIONES){
            int maxOpciones = 0;
            if(tablero.iu.subEstado == 0){
                maxOpciones = 3;
            }
            if (tecla == KeyEvent.VK_W) {
                tablero.iu.comandoNum--;
                if (tablero.iu.comandoNum < 0) {
                    tablero.iu.comandoNum = maxOpciones;
                }
            }
            if (tecla == KeyEvent.VK_S) {
                tablero.iu.comandoNum++;
                if (tablero.iu.comandoNum > maxOpciones) {
                    tablero.iu.comandoNum = 0;
                }
            }
            if (tecla == KeyEvent.VK_ENTER) {
                if (tablero.iu.comandoNum == 0) {
                    tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
                }
                if(tablero.iu.comandoNum == 2){
                    tablero.guardadoYCarga.guardar();
                    System.out.println("Guardado");
                }
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

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
