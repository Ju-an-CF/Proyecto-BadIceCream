package entidades;

import escenario.Tablero;
import mecánicas.Control;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugador extends Entidad {
    Tablero tablero;
    Control control;
    public final int ventanaX;
    public final int ventanaY;
    public int númeroDeFrutas;

    //estadisticas personaje
    public int máximoVidas;
    public int vida;

    public Jugador(Tablero tablero, Control control) {
        this.tablero = tablero;
        this.control = control;
        áreaSólida = new Rectangle();
        áreaSólida.x = 4;
        áreaSólida.y = 14;
        áreaSólidaPorDefectoX = áreaSólida.x;
        áreaSólidaPorDefectoY = áreaSólida.y;
        áreaSólida.width = 34;
        áreaSólida.height = 28;
        ventanaX = tablero.TAMANIO_DE_BLOQUE * 6; //
        ventanaY = tablero.TAMANIO_DE_BLOQUE * 5; //
        númeroDeFrutas = 0;

        establecerValoresPredeterminados();
        obtenerImagenDeJugador();
    }

    public void establecerValoresPredeterminados() {
        mundoX = 336;
        mundoY = 294;
        velocidad = 6;
        dirección = "abajo";
        //estadisticas
        máximoVidas = 3;
        vida = máximoVidas;

    }

    public void obtenerImagenDeJugador() {
        arriba1 = ut.configurar("jugador_arriba1", tablero.TAMANIO_DE_BLOQUE);
        arriba2 = ut.configurar("jugador_arriba2", tablero.TAMANIO_DE_BLOQUE);
        arriba3 = ut.configurar("jugador_arriba3", tablero.TAMANIO_DE_BLOQUE);
        arriba4 = ut.configurar("jugador_arriba4", tablero.TAMANIO_DE_BLOQUE);

        abajo1 = ut.configurar("jugador_abajo1", tablero.TAMANIO_DE_BLOQUE);
        abajo2 = ut.configurar("jugador_abajo2", tablero.TAMANIO_DE_BLOQUE);
        abajo3 = ut.configurar("jugador_abajo3", tablero.TAMANIO_DE_BLOQUE);
        abajo4 = ut.configurar("jugador_abajo4", tablero.TAMANIO_DE_BLOQUE);

        izquierda1 = ut.configurar("jugador_izquierda1", tablero.TAMANIO_DE_BLOQUE);
        izquierda2 = ut.configurar("jugador_izquierda2", tablero.TAMANIO_DE_BLOQUE);
        izquierda3 = ut.configurar("jugador_izquierda3", tablero.TAMANIO_DE_BLOQUE);
        izquierda4 = ut.configurar("jugador_izquierda4", tablero.TAMANIO_DE_BLOQUE);

        derecha1 = ut.configurar("jugador_derecha1", tablero.TAMANIO_DE_BLOQUE);
        derecha2 = ut.configurar("jugador_derecha2", tablero.TAMANIO_DE_BLOQUE);
        derecha3 = ut.configurar("jugador_derecha3", tablero.TAMANIO_DE_BLOQUE);
        derecha4 = ut.configurar("jugador_derecha4", tablero.TAMANIO_DE_BLOQUE);

        jugadorRomper1 = ut.configurar("jugador_romper1", tablero.TAMANIO_DE_BLOQUE);
        jugadorRomper2 = ut.configurar("jugador_romper2", tablero.TAMANIO_DE_BLOQUE);
        jugadorRomper3 = ut.configurar("jugador_romper3", tablero.TAMANIO_DE_BLOQUE);
        jugadorRomper4 = ut.configurar("jugador_romper4", tablero.TAMANIO_DE_BLOQUE);
        jugadorRomper5 = ut.configurar("jugador_romper5", tablero.TAMANIO_DE_BLOQUE);
        jugadorRomper6 = ut.configurar("jugador_romper6", tablero.TAMANIO_DE_BLOQUE);
    }

    public void actualizar() {

        if (rompiendo) {
            rompiendo();
        }

        if (control.arribaPresionado || control.abajoPresionado || control.izquierdaPresionado || control.derechaPresionado || control.espacioPresionado) {
            if (control.arribaPresionado) {
                dirección = "arriba";
            } else if (control.abajoPresionado) {
                dirección = "abajo";
            } else if (control.izquierdaPresionado) {
                dirección = "izquierda";
            } else if (control.derechaPresionado) {
                dirección = "derecha";
            }

            //verifica Colisión de bloque
            colisiónActiva = false;
            tablero.checkColisión.verificarBloque(this);
            //verificar colisión de objetos
            int index = tablero.checkColisión.verificarObjeto(this, true);
            recogerFrutas(index);

            activarRompiendo(index);
            control.espacioPresionado = false;
            //if colision=false, jugador se mueve
            if (!colisiónActiva) {
                switch (dirección) {
                    case "arriba": mundoY -= velocidad; break;
                    case "abajo": mundoY += velocidad; break;
                    case "izquierda": mundoX -= velocidad; break;
                    case "derecha": mundoX += velocidad; break;
                }
            }

            contadorMovimiento++;
            if (contadorMovimiento > 10) {
                if (numeroDeMovimiento == 1) {
                    numeroDeMovimiento = 2;
                } else if (numeroDeMovimiento == 2) {
                    numeroDeMovimiento = 1;
                } else if (numeroDeMovimiento == 3) {
                    numeroDeMovimiento = 2;
                } else if (numeroDeMovimiento == 4) {
                    numeroDeMovimiento = 3;
                }
                contadorMovimiento = 0;
            }
        }
    }

    public void activarRompiendo(int i) {
        if (control.espacioPresionado) {
            rompiendo = true;
        }
    }

    private void rompiendo() {
        contadorMovimiento++;
        if (contadorMovimiento <= 1) {
            numeroDeMovimiento = 1;
        }
        if (contadorMovimiento > 1 && contadorMovimiento <= 2) {
            numeroDeMovimiento = 2;
        }
        if (contadorMovimiento > 2 && contadorMovimiento <= 4) {
            numeroDeMovimiento = 3;
        }
        if (contadorMovimiento > 4 && contadorMovimiento <= 5) {
            numeroDeMovimiento = 4;
        }
        if (contadorMovimiento > 5 && contadorMovimiento <= 7) {
            numeroDeMovimiento = 5;
        }
        if (contadorMovimiento > 7 && contadorMovimiento <= 8) {
            numeroDeMovimiento = 6;
        }
        if (contadorMovimiento > 10) {
            numeroDeMovimiento = 1;
            contadorMovimiento = 0;
            rompiendo = false;
        }
    }


    public void recogerFrutas(int index) {
        if (index != 999) {
            tablero.frutas[index] = null;
            númeroDeFrutas++;
            tablero.reproducirSE(1);
            //  tablero.
            System.out.println("Frutas recolectadas: " + númeroDeFrutas);
        }
        if (númeroDeFrutas == 11) {
            tablero.iu.juegoTerminado = true;
            tablero.pararMúsica();
            tablero.reproducirSE(6);
        }
    }

    public void dibujar(Graphics2D g2) {
        BufferedImage imagen = null;
        switch (dirección) {
            case "arriba":
                if (!rompiendo) {
                    if (numeroDeMovimiento == 1) {
                        imagen = arriba1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = arriba2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = arriba3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = arriba4;
                    }
                }
                if (rompiendo) {
                    if (numeroDeMovimiento == 1) {
                        imagen = jugadorRomper1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = jugadorRomper2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = jugadorRomper3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = jugadorRomper4;
                    }
                    if (numeroDeMovimiento == 5) {
                        imagen = jugadorRomper5;
                    }
                    if (numeroDeMovimiento == 6) {
                        imagen = jugadorRomper6;
                    }
                }
                break;

            case "abajo":
                if (!rompiendo) {
                    if (numeroDeMovimiento == 1) {
                        imagen = abajo1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = abajo2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = abajo3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = abajo4;
                    }
                }
                if (rompiendo) {
                    if (numeroDeMovimiento == 1) {
                        imagen = jugadorRomper1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = jugadorRomper2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = jugadorRomper3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = jugadorRomper4;
                    }
                    if (numeroDeMovimiento == 5) {
                        imagen = jugadorRomper5;
                    }
                    if (numeroDeMovimiento == 6) {
                        imagen = jugadorRomper6;
                    }
                }
                break;

            case "izquierda":
                if (!rompiendo) {
                    if (numeroDeMovimiento == 1) {
                        imagen = izquierda1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = izquierda2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = izquierda3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = izquierda4;
                    }
                }
                if (rompiendo) {
                    if (numeroDeMovimiento == 1) {
                        imagen = jugadorRomper1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = jugadorRomper2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = jugadorRomper3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = jugadorRomper4;
                    }
                    if (numeroDeMovimiento == 5) {
                        imagen = jugadorRomper5;
                    }
                    if (numeroDeMovimiento == 6) {
                        imagen = jugadorRomper6;
                    }
                }
                break;

            case "derecha":
                if (!rompiendo) {
                    if (numeroDeMovimiento == 1) {
                        imagen = derecha1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = derecha2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = derecha3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = derecha4;
                    }
                }
                if (rompiendo) {
                    if (numeroDeMovimiento == 1) {
                        imagen = jugadorRomper1;
                    }
                    if (numeroDeMovimiento == 2) {
                        imagen = jugadorRomper2;
                    }
                    if (numeroDeMovimiento == 3) {
                        imagen = jugadorRomper3;
                    }
                    if (numeroDeMovimiento == 4) {
                        imagen = jugadorRomper4;
                    }
                    if (numeroDeMovimiento == 5) {
                        imagen = jugadorRomper5;
                    }
                    if (numeroDeMovimiento == 6) {
                        imagen = jugadorRomper6;
                    }
                }
                break;
        }
        g2.drawImage(imagen, (ventanaX - 8), (ventanaY - 14), 50, 64, null);
        g2.drawRect(ventanaX + áreaSólida.x, ventanaY + áreaSólida.y, áreaSólida.width, áreaSólida.height); //HITBOX Jugador
        //g2.drawRect(126, 84, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE); //HITBOX Bloque
    }

}
