package entidades;

import escenario.Tablero;
import mecánicas.Control;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jugador extends Entidad {
    Control control;
    public final int ventanaX;
    public final int ventanaY;
    public int númeroDeFrutas;
    //estadisticas personaje
    public int máximoVidas;
    public int vida;
    public int tiempoDeInvencibilidad = 0;
    public boolean invencible = false;
    private int mundoXOriginal;
    private int mundoYOriginal;

    public Jugador(Tablero tablero, Control control, int posiciónX, int posiciónY) {
        super(tablero);
        this.control = control;
        áreaSólida = new Rectangle();
        áreaSólida.x = 4;
        áreaSólida.y = 14;
        áreaSólidaPorDefectoX = áreaSólida.x;
        áreaSólidaPorDefectoY = áreaSólida.y;
        áreaSólida.width = 34;
        áreaSólida.height = 28;
        ventanaX = tablero.TAMANIO_DE_BLOQUE * 7; // 7
        ventanaY = tablero.TAMANIO_DE_BLOQUE * 5; // 5
        númeroDeFrutas = 0;
        mundoX = posiciónX * tablero.TAMANIO_DE_BLOQUE;
        mundoY = posiciónY * tablero.TAMANIO_DE_BLOQUE;

        establecerValoresPredeterminados();
        obtenerImagenDeJugador();
    }

    public void establecerValoresPredeterminados() {
        velocidad = 6;
        dirección = "abajo";
        //estadisticas
        máximoVidas = 3;
        vida = máximoVidas;

    }

    public void obtenerImagenDeJugador() {

        arriba1 = configurarImagen("/fuentes/jugador/jugador_arriba1");
        arriba2 = configurarImagen("/fuentes/jugador/jugador_arriba2");
        arriba3 = configurarImagen("/fuentes/jugador/jugador_arriba3");
        arriba4 = configurarImagen("/fuentes/jugador/jugador_arriba4");
        abajo1 = configurarImagen("/fuentes/jugador/jugador_abajo1");
        abajo2 = configurarImagen("/fuentes/jugador/jugador_abajo2");
        abajo3 = configurarImagen("/fuentes/jugador/jugador_abajo3");
        abajo4 = configurarImagen("/fuentes/jugador/jugador_abajo4");
        izquierda1 = configurarImagen("/fuentes/jugador/jugador_izquierda1");
        izquierda2 = configurarImagen("/fuentes/jugador/jugador_izquierda2");
        izquierda3 = configurarImagen("/fuentes/jugador/jugador_izquierda3");
        izquierda4 = configurarImagen("/fuentes/jugador/jugador_izquierda4");
        derecha1 = configurarImagen("/fuentes/jugador/jugador_derecha1");
        derecha2 = configurarImagen("/fuentes/jugador/jugador_derecha2");
        derecha3 = configurarImagen("/fuentes/jugador/jugador_derecha3");
        derecha4 = configurarImagen("/fuentes/jugador/jugador_derecha4");
        jugadorRomper1 = configurarImagen("/fuentes/jugador/jugador_romper1");
        jugadorRomper2 = configurarImagen("/fuentes/jugador/jugador_romper2");
        jugadorRomper3 = configurarImagen("/fuentes/jugador/jugador_romper3");
        jugadorRomper4 = configurarImagen("/fuentes/jugador/jugador_romper4");
        jugadorRomper5 = configurarImagen("/fuentes/jugador/jugador_romper5");
        jugadorRomper6 = configurarImagen("/fuentes/jugador/jugador_romper6");
    }

    public void actualizar() {
        if (rompiendo == true) {
            rompiendo();
        } else {
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
                int enemigoIndex = tablero.checkColisión.verificarEntidad(this, tablero.enemigos);
                contactoConEnemigo(enemigoIndex);
                recogerFrutas(index);
                activarRompiendo(index);
                control.espacioPresionado = false;
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
        if (invencible == true) {
            tiempoDeInvencibilidad++;
            if (tiempoDeInvencibilidad > 60) {
                invencible = false;
                tiempoDeInvencibilidad = 0;
            }
        }
    }
    public void activarRompiendo (int i) {
        if (control.espacioPresionado == true) {
            rompiendo = true;
            mundoXOriginal = mundoX;
            mundoYOriginal = mundoY;
        }
    }
    private void rompiendo() {
        contadorMovimiento++;
        if(contadorMovimiento <= 2) {
            numeroDeMovimiento = 1;
        }
        if(contadorMovimiento > 2 && contadorMovimiento <= 6) {
            numeroDeMovimiento = 2;
        }
        if(contadorMovimiento > 6 && contadorMovimiento <= 10) {
            numeroDeMovimiento = 3;
        }
        if(contadorMovimiento > 10 && contadorMovimiento <= 14) {
            numeroDeMovimiento = 4;
        }
        if(contadorMovimiento > 14 && contadorMovimiento <= 18) {
            numeroDeMovimiento = 5;
        }
        if(contadorMovimiento > 18 && contadorMovimiento <= 22) {
            numeroDeMovimiento = 6;
        }
        if(contadorMovimiento > 24) {
            numeroDeMovimiento = 1;
            contadorMovimiento = 0;
            rompiendo = false;
            mundoX = mundoXOriginal;
            mundoY = mundoYOriginal;
        }
    }


    private void contactoConEnemigo(int i) {
        if (i != 999) {
            if (invencible == false) {
                vida -= 1;
                invencible = true;
            }
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
        if (invencible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(imagen, ventanaX, ventanaY - áreaSólida.y, null);
        //reseteo
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g2.drawRect(ventanaX + áreaSólida.x, ventanaY + áreaSólida.y, áreaSólida.width, áreaSólida.height); //HITBOX Jugador
        //g2.drawRect(126, 84, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE); //HITBOX Bloque
    }

}
