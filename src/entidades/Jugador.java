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
        try {
            arriba1 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_arriba1.png"));
            arriba2 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_arriba2.png"));
            arriba3 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_arriba3.png"));
            arriba4 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_arriba4.png"));
            abajo1 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_abajo1.png"));
            abajo2 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_abajo2.png"));
            abajo3 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_abajo3.png"));
            abajo4 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_abajo4.png"));
            izquierda1 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_izquierda1.png"));
            izquierda2 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_izquierda2.png"));
            izquierda3 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_izquierda3.png"));
            izquierda4 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_izquierda4.png"));
            derecha1 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_derecha1.png"));
            derecha2 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_derecha2.png"));
            derecha3 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_derecha3.png"));
            derecha4 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_derecha4.png"));
            jugadorRomper1 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_romper1.png"));
            jugadorRomper2 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_romper2.png"));
            jugadorRomper3 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_romper3.png"));
            jugadorRomper4 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_romper4.png"));
            jugadorRomper5 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_romper5.png"));
            jugadorRomper6 = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/jugador_romper6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void actualizar() {
        if (rompiendo == true) {
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
            if (colisiónActiva == false) {
                switch (dirección) {
                    case ("arriba"):
                        mundoY -= velocidad;
                        break;
                    case ("abajo"):
                        mundoY += velocidad;
                        break;
                    case ("izquierda"):
                        mundoX -= velocidad;
                        break;
                    case ("derecha"):
                        mundoX += velocidad;
                        break;
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
        if (control.espacioPresionado == true) {
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
                if (rompiendo == false) {
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
                if (rompiendo == true) {
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
                if (rompiendo == false) {
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
                if (rompiendo == true) {
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
                if (rompiendo == false) {
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
                if (rompiendo == true) {
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
                if (rompiendo == false) {
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
                if (rompiendo == true) {
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
