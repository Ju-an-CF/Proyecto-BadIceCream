package entidades.personajes;

import entidades.Entidad;
import escenario.Tablero;
import interfazDeUsuario.EstadoDeJuego;
import mecánicas.Control;
import mecánicas.Dirección;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jugador extends Entidad {
    Control control;

    public final int ventanaX;
    public final int ventanaY;

    //Cantidad de frutas
    public int númeroDeFrutas;
    //Estadísticas por defecto
    public int máximoVidas;
    public int vida;
    public int tiempoDeInvencibilidad = 0;
    public boolean invencible = false;
    private final int NUM_MAX_FRUTAS=11;

    public Jugador(Tablero tablero, Control control) {
        super(tablero);
        this.control = control;
        áreaSólida = new Rectangle();
        áreaSólida.x = 7;
        áreaSólida.y = 14;
        áreaSólidaPorDefectoX = áreaSólida.x;
        áreaSólidaPorDefectoY = áreaSólida.y;
        áreaSólida.width = 28;
        áreaSólida.height = 28;
        ventanaX = tablero.TAMAÑO_DE_BLOQUE * 7; //
        ventanaY = tablero.TAMAÑO_DE_BLOQUE * 7; //
        númeroDeFrutas = 0;
        //Configuraciones iniciales del jugador
        establecerValoresPredeterminados();
        obtenerImagenDeJugador();
    }
    public void establecerPosiciónPredeterminada(){
        mundoX=336;
        mundoY=294;
        dirección=Dirección.ABAJO;
    }
    public void reestablecerVida(){
        vida=máximoVidas;
        invencible=false;
    }
    public void reestablecerFrutas(){
        númeroDeFrutas=0;
    }

    /**
     * Establece los valores predeterminados para el jugador.
     * Este método inicializa la velocidad y la dirección del jugador, así como las estadísticas de vida.
     */
    public void establecerValoresPredeterminados() {
        //Coordenadas iniciales
        mundoX = 9*tablero.TAMAÑO_DE_BLOQUE;
        mundoY = 10*tablero.TAMAÑO_DE_BLOQUE;
        // Establecer la velocidad predeterminada del jugador
        velocidad = 6;
        // Establecer la dirección predeterminada del jugador como hacia abajo
        dirección = Dirección.ABAJO;
        // Inicializar las estadísticas de vida
        máximoVidas = 3;
        vida = máximoVidas;

    }

    public void obtenerImagenDeJugador() {
        // Configurar las imágenes del jugador para la dirección hacia arriba
        arriba1 = configurarImagen("jugador_arriba1");
        arriba2 = configurarImagen("jugador_arriba2");
        arriba3 = configurarImagen("jugador_arriba3");
        arriba4 = configurarImagen("jugador_arriba4");

        // Configurar las imágenes del jugador para la dirección hacia abajo
        abajo1 = configurarImagen("jugador_abajo1");
        abajo2 = configurarImagen("jugador_abajo2");
        abajo3 = configurarImagen("jugador_abajo3");
        abajo4 = configurarImagen("jugador_abajo4");

        // Configurar las imágenes del jugador para la dirección hacia izquierda
        izquierda1 = configurarImagen("jugador_izquierda1");
        izquierda2 = configurarImagen("jugador_izquierda2");
        izquierda3 = configurarImagen("jugador_izquierda3");
        izquierda4 = configurarImagen("jugador_izquierda4");

        // Configurar las imágenes del jugador para la dirección hacia derecha
        derecha1 = configurarImagen("jugador_derecha1");
        derecha2 = configurarImagen("jugador_derecha2");
        derecha3 = configurarImagen("jugador_derecha3");
        derecha4 = configurarImagen("jugador_derecha4");
    }

    /**
     * Configura una imagen utilizando el nombre de la imagen proporcionado.
     *
     * @param nombreImagen El nombre de la imagen a configurar.
     * @return La imagen configurada.
     */
    public BufferedImage configurarImagen(String nombreImagen) {
        // Llama al método configurarImagen de la superclase con la ruta completa de la imagen
        return super.configurarImagen("/fuentes/jugador/" + nombreImagen);
    }

    public void actualizar() {
        if (control.arribaPresionado || control.abajoPresionado || control.izquierdaPresionado || control.derechaPresionado) {
            if (control.arribaPresionado) {
                dirección = Dirección.ARRIBA;
            } else if (control.abajoPresionado) {
                dirección = Dirección.ABAJO;
            } else if (control.izquierdaPresionado) {
                dirección = Dirección.IZQUIERDA;
            } else if (control.derechaPresionado) {
                dirección = Dirección.DERECHA;
            }

            //verifica Colisión de bloque
            colisiónActiva = false;
            tablero.checkColisión.verificarBloque(this);

            //verificar colisión de objetos
            int index = tablero.checkColisión.verificarObjeto(this, true);
            int enemigoIndex = tablero.checkColisión.verificarEntidad(this, tablero.enemigos);
            contactoConEnemigo(enemigoIndex);
            recogerFrutas(index);

            //Si la conlisión es falsa, el jugador se mueve
            if (!colisiónActiva) {
                switch (dirección) {
                    case ARRIBA:    mundoY -= velocidad;    break;
                    case ABAJO:     mundoY += velocidad;     break;
                    case IZQUIERDA: mundoX -= velocidad; break;
                    case DERECHA:   mundoX += velocidad;   break;
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
        if (invencible) {
            tiempoDeInvencibilidad++;
            if (tiempoDeInvencibilidad > 60) {
                invencible = false;
                tiempoDeInvencibilidad = 0;
            }
        }
        if(comprobarSiEstáMuerto()){
            tablero.estadoActualDeJuego=EstadoDeJuego.DERROTA;
            tablero.reproducirSE(4);
        }
        if(comprobarVictoria()){
            tablero.estadoActualDeJuego=EstadoDeJuego.VICTORIA;
            tablero.reproducirSE(6);
        }
    }

    private boolean comprobarVictoria() {
        return númeroDeFrutas==NUM_MAX_FRUTAS;
    }

    public boolean comprobarSiEstáMuerto(){
        return vida ==0;
    }


    private void contactoConEnemigo(int i) {
        if (i != 999) {
            if (!invencible) {
                vida -= 1;
                invencible = true;
            }
        }
    }

    /**
     * Recoge una fruta en la posición dada en el tablero.
     *
     * @param index El índice de la fruta en el arreglo de frutas del tablero.
     */
    public void recogerFrutas(int index) {
        if (index != 999) {
            tablero.frutas[index] = null;
            númeroDeFrutas++;
            tablero.reproducirSE(1);
            //  tablero.
            System.out.println("Frutas recolectadas: " + númeroDeFrutas);
        }
    }


    public void dibujar(Graphics2D g2) {
        BufferedImage imagen = null;
        switch (dirección) {
            case ARRIBA:
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
            case ABAJO:
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
            case IZQUIERDA:
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
            case DERECHA:
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

        if (invencible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(imagen, ventanaX, ventanaY, null);
        //reseteo
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g2.drawRect(ventanaX + áreaSólida.x, ventanaY + áreaSólida.y, áreaSólida.width, áreaSólida.height); //HITBOX Jugador
        //g2.drawRect(126, 84, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE); //HITBOX Bloque
    }

    public void romperOCrearHielo() {
        // Validar si el jugador está sobre un bloque de hielo
        if (colisiónActiva) {
            tablero.romperBloqueHielo();
        } else {
            tablero.crearBloqueHielo();
        }
    }

    public Dirección getDireccion() {
        return dirección;
    }
}
