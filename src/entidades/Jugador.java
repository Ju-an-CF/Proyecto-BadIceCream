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

    public Jugador(Tablero tablero, Control control) {
        this.tablero = tablero;
        this.control = control;
        áreaSólida = new Rectangle();
        áreaSólida.x = 4;
        áreaSólida.y = 14;
        áreaSólida.width = 34;
        áreaSólida.height = 28;
        ventanaX = tablero.TAMANIO_DE_BLOQUE*7; //
        ventanaY = tablero.TAMANIO_DE_BLOQUE*5; //

        establecerValoresPredeterminados();
        obtenerImagenDeJugador();
    }

    public void establecerValoresPredeterminados() {
        mundoX = 336;
        mundoY = 294;
        velocidad = 6;
        dirección = "abajo";

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        if (control.arribaPresionado || control.abajoPresionado || control.izquierdaPresionado || control.derechaPresionado) {
            if (control.arribaPresionado) {
                dirección = "arriba";
            } else if (control.abajoPresionado) {
                dirección = "abajo";
            } else if (control.izquierdaPresionado) {
                dirección = "izquierda";
            } else if (control.derechaPresionado) {
                dirección = "derecha";
            }
            //verifica Colisión
            colisiónActiva = false;
            tablero.checkColisión.verificarBloque(this);
            //if colision=false, jugador se mueve
            if (!colisiónActiva) {
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

    public void dibujar(Graphics2D g2) {
        BufferedImage imagen = null;
        switch (dirección) {
            case "arriba":
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
                break;
            case "abajo":
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
                break;
            case "izquierda":
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
                break;
            case "derecha":
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
                break;
        }
        g2.drawImage(imagen, ventanaX, ventanaY, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE, null);
        g2.drawRect(ventanaX + áreaSólida.x, ventanaY + áreaSólida.y, áreaSólida.width, áreaSólida.height); //HITBOX Jugador
        g2.drawRect(126, 84, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE); //HITBOX Bloque
    }

}
