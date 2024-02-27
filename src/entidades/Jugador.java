package entidades;

import escenario.Tablero;
import escenario.UtilityTool;
import mecánicas.Control;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugador extends Entidad {
    Control control;
    public final int ventanaX;
    public final int ventanaY;
    public int númeroDeFrutas;
    //estadisticas personaje
    public int máximoVidas;
    public int vida;
    public int tiempoDeInvencibilidad=0;
    public boolean invencible=false;
    public Jugador(Tablero tablero, Control control) {
        super(tablero);
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
        máximoVidas=3;
        vida=máximoVidas;

    }
    public void obtenerImagenDeJugador() {

        arriba1 = setUp("/fuentes/jugador/jugador_arriba1");
        arriba2 = setUp("/fuentes/jugador/jugador_arriba2");
        arriba3 = setUp("/fuentes/jugador/jugador_arriba3");
        arriba4 = setUp("/fuentes/jugador/jugador_arriba4");
        abajo1 = setUp("/fuentes/jugador/jugador_abajo1");
        abajo2 = setUp("/fuentes/jugador/jugador_abajo2");
        abajo3 = setUp("/fuentes/jugador/jugador_abajo3");
        abajo4 = setUp("/fuentes/jugador/jugador_abajo4");
        izquierda1 = setUp("/fuentes/jugador/jugador_izquierda1");
        izquierda2 = setUp("/fuentes/jugador/jugador_izquierda2");
        izquierda3 = setUp("/fuentes/jugador/jugador_izquierda3");
        izquierda4 = setUp("/fuentes/jugador/jugador_izquierda4");
        derecha1 = setUp("/fuentes/jugador/jugador_derecha1");
        derecha2 = setUp("/fuentes/jugador/jugador_derecha2");
        derecha3 = setUp("/fuentes/jugador/jugador_derecha3");
        derecha4 = setUp("/fuentes/jugador/jugador_derecha4");
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
            //verifica Colisión de bloque
            colisiónActiva = false;
            tablero.checkColisión.verificarBloque(this);
            //verificar colisión de objetos
            int index = tablero.checkColisión.verificarObjeto(this, true);
            int enemigoIndex=tablero.checkColisión.verificarEntidad(this,tablero.enemigos);
            contactoConEnemigo(enemigoIndex);
            recogerFrutas(index);
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
        if(invencible==true){
            tiempoDeInvencibilidad++;
            if(tiempoDeInvencibilidad>60){
                invencible=false;
                tiempoDeInvencibilidad=0;
            }
        }
    }

    private void contactoConEnemigo(int i) {
        if(i!=999){
            if(invencible==false) {
                vida -= 1;
                invencible=true;
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
        if(númeroDeFrutas==11){
            tablero.iu.juegoTerminado=true;
            tablero.pararMúsica();
            tablero.reproducirSE(6);
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
        if(invencible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }

        g2.drawImage(imagen, ventanaX, ventanaY, null);
        //reseteo
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

        g2.drawRect(ventanaX + áreaSólida.x, ventanaY + áreaSólida.y, áreaSólida.width, áreaSólida.height); //HITBOX Jugador
        //g2.drawRect(126, 84, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE); //HITBOX Bloque
    }

}
