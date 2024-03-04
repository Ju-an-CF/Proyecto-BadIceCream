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
    // Método que maneja eventos de tecla presionada
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
            case NIVELES:
                estadoNiveles(tecla);
                break;
        }
    }
    // Método para gestionar eventos de tecla en estado de victoria
    private void estadoVictoria(int tecla) {
        if (tecla == KeyEvent.VK_ENTER) {
            if (tablero.iu.comandoNum == 0) {
                tablero.estadoActualDeJuego = EstadoDeJuego.TÍTULO;
            }
        }
    }

    private void estadoNiveles(int tecla) {
        int maxComandoNum = 3;
        if (tecla == KeyEvent.VK_A) {
            tablero.iu.comandoNum--;
            if (tablero.iu.comandoNum < 0) {
                tablero.iu.comandoNum = maxComandoNum;
            }
        }
        if (tecla == KeyEvent.VK_D) {
            tablero.iu.comandoNum++;
            if (tablero.iu.comandoNum > maxComandoNum) {
                tablero.iu.comandoNum = 0;
            }
        }
        if (tecla == KeyEvent.VK_ENTER) {
            if (tablero.iu.comandoNum == 0) {
                tablero.estadoActualDeJuego = EstadoDeJuego.TÍTULO;
            }
            if(tablero.iu.comandoNum == 1){
                //Nivel 2
            }
            if (tablero.iu.comandoNum == 2) {

            }
            if (tablero.iu.comandoNum == 3) {

            }

        }

    }

    // Método para gestionar eventos de tecla en estado de pausa
    private void estadoPausa(int tecla) {
        if (tecla == KeyEvent.VK_ESCAPE) {
            // Regresar al juego si se presiona Escape
            tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
        }
    }

    // Método para gestionar eventos de tecla en estado de opciones
    private void estadoOpciones(int tecla) {
        if (tecla == KeyEvent.VK_ENTER) {
            // Marcar la tecla Enter como presionada
            enterPresionado = true;
        }
        if (tecla == KeyEvent.VK_ESCAPE) {
            // Regresar al juego si se presiona Escape
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
            // Navegar hacia arriba en las opciones
            tablero.iu.comandoNum--;
            if (tablero.iu.comandoNum < 0) {
                tablero.iu.comandoNum = maxComandoNum;
            }
        }
        if (tecla == KeyEvent.VK_S) {
            // Navegar hacia abajo en las opciones
            tablero.iu.comandoNum++;
            //music
            if (tablero.iu.comandoNum > maxComandoNum) {
                tablero.iu.comandoNum = 0;
            }
        }
        if (tecla == KeyEvent.VK_A) {
            if (tablero.iu.subEstado == 0) {
                if (tablero.iu.comandoNum == 0 && tablero.getMúsica().getEscalaDeVolumen() > 0) {
                    // Disminuir volumen de la música
                    tablero.getMúsica().disminuirVolumen();
                    tablero.getMúsica().verificarVolumen();
                    //tablero.reproducirSE();
                }
                if (tablero.iu.comandoNum == 1 && tablero.getSe().getEscalaDeVolumen() > 0) {
                    // Disminuir volumen de los efectos de sonido
                    tablero.getSe().disminuirVolumen();
                    //tablero.reproducirSE();
                }
            }
        }
        if (tecla == KeyEvent.VK_D) {
            if (tablero.iu.subEstado == 0) {
                if (tablero.iu.comandoNum == 0 && tablero.getMúsica().getEscalaDeVolumen() < 5) {
                    // Aumentar volumen de la música
                    tablero.getMúsica().aumentarVolumen();
                    tablero.getMúsica().verificarVolumen();
                    //tablero.reproducirSE();
                }
            }
            if (tablero.iu.comandoNum == 1 && tablero.getSe().getEscalaDeVolumen() < 5) {
                // Aumentar volumen de los efectos de sonido
                tablero.getSe().aumentarVolumen();
                //tablero.reproducirSE();
            }
        }
    }

    // Método para gestionar eventos de tecla en estado de jugador (juego)
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
            // Romper o crear hielo al presionar la barra espaciadora
            tablero.jugador.romperOCrearHielo();
        }
        if (tecla == KeyEvent.VK_F) {
            // Ir al estado de opciones al presionar F
            tablero.estadoActualDeJuego = EstadoDeJuego.OPCIONES;
        }
        if (tecla == KeyEvent.VK_P) {
            // Ir al estado de pausa al presionar P
            tablero.estadoActualDeJuego = EstadoDeJuego.PAUSA;
        }
    }

    // Método para gestionar eventos de tecla en estado de título
    public void estadoTítulo(int tecla) {
        if (tecla == KeyEvent.VK_W) {
            // Navegar hacia arriba en las opciones
            tablero.iu.comandoNum--;
            if (tablero.iu.comandoNum < 0) {
                tablero.iu.comandoNum = 2;
            }
        }
        if (tecla == KeyEvent.VK_S) {
            // Navegar hacia abajo en las opciones
            tablero.iu.comandoNum++;
            if (tablero.iu.comandoNum > 2) {
                tablero.iu.comandoNum = 0;
            }
        }
        if (tecla == KeyEvent.VK_ENTER) {
            // Realizar acciones según la opción seleccionada
            if (tablero.iu.comandoNum == 0) {
                /*tablero.estadoActualDeJuego = EstadoDeJuego.JUEGO;
                tablero.pararMúsica();
                tablero.reproducirMúsica(2);*/
                tablero.estadoActualDeJuego = EstadoDeJuego.NIVELES;
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
    // Método para gestionar eventos de tecla en estado de derrota
    private void estadoDerrota(int tecla) {
        if (tecla == KeyEvent.VK_W) {
            // Navegar hacia arriba en las opciones
            tablero.iu.comandoNum--;
            if (tablero.iu.comandoNum < 0) {
                tablero.iu.comandoNum = 1;
            }
            //efecto sonido
        }
        if (tecla == KeyEvent.VK_S) {
            // Navegar hacia abajo en las opciones
            tablero.iu.comandoNum++;
            if (tablero.iu.comandoNum > 1) {
                tablero.iu.comandoNum = 0;
            }
            //efecto sonido
        }
        if (tecla == KeyEvent.VK_ENTER) {
            // Realizar acciones según la opción seleccionada
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
