package escenario.entidades;

import escenario.Tablero;
import interfazDeUsuario.EstadoDeJuego;
import mecánicas.Control;
import mecánicas.Dirección;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jugador extends Entidad {
    Control control;
    //Cantidad De Frutas
    public int númeroDeFrutas;
    //Estadísticas personaje
    public int máximoVidas;
    public int vida;
    public int tiempoDeInvencibilidad = 0;
    public boolean invencible = false;

    public Jugador(Tablero tablero, Control control, int posiciónX, int posiciónY) {
        super(tablero);
        this.control = control;
        setÁreaSólida(new Rectangle());
        getÁreaSólida().x = 4;
        getÁreaSólida().y = 14;
        setÁreaSólidaPorDefectoX(getÁreaSólida().x);
        setÁreaSólidaPorDefectoY(getÁreaSólida().y);
        getÁreaSólida().width = 34;
        getÁreaSólida().height = 28;
        númeroDeFrutas = 0;
        setMundoX(posiciónX * tablero.TAMAÑO_DE_BLOQUE);
        setMundoY(posiciónY * tablero.TAMAÑO_DE_BLOQUE);
        //Configuraciones Especiales
        establecerValoresPredeterminados();
        obtenerImagenDeJugador();
    }
    /**
     * Establece los valores predeterminados para el jugador.
     * Este método inicializa la velocidad y la dirección del jugador, así como las estadísticas de vida.
     */
    public void establecerValoresPredeterminados() {
        // Establecer la velocidad predeterminada del jugador
        setVelocidad(6);
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
    public BufferedImage configurarImagen(String nombreImagen){
        // Llama al método configurarImagen de la superclase con la ruta completa de la imagen
        return super.configurarImagen("/fuentes/jugador/" + nombreImagen);
    }

    public boolean estáMuerto(){
        return vida == 0;
    }

    public void actualizar() {
        if (    control.arribaPresionado    ||
                control.abajoPresionado     ||
                control.izquierdaPresionado ||
                control.derechaPresionado   ||
                control.espacioPresionado || control.fPresionada) {
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
            setColisiónActiva(false);
            getTablero().checkColisión.verificarBloque(this);
            //verificar colisión de objetos
            int index = getTablero().checkColisión.verificarObjeto(this, true);
            int enemigoIndex = getTablero().checkColisión.verificarEntidad(this, getTablero().enemigos);

            int índiceBloqueInteractivo = getTablero().checkColisión.verificarEntidad(this, getTablero().bloqueInteractivos);
            destruirBloque(índiceBloqueInteractivo);

            int i = 0;
            colocarBloque(i);

            contactoConEnemigo(enemigoIndex);
            recogerFrutas(index);
            comprobarSiEstáMuerto();


            //if colisión=false, jugador se mueve
            if (!isColisiónActiva() && !control.espacioPresionado && !control.fPresionada) {
                switch (dirección) {
                    case ARRIBA: setMundoY(getMundoY() - getVelocidad()); break;
                    case ABAJO: setMundoY(getMundoY() + getVelocidad()); break;
                    case IZQUIERDA: setMundoX(getMundoX() - getVelocidad()); break;
                    case DERECHA: setMundoX(getMundoX() + getVelocidad()); break;
                }
            }

            getTablero().control.espacioPresionado = false;
            getTablero().control.fPresionada = false;

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
    }

    public void destruirBloque(int índice){
        if(índice != 999 && getTablero().bloqueInteractivos[índice].destructible && control.espacioPresionado){
            getTablero().bloqueInteractivos[índice] = null;
        }

    }
    private void comprobarSiEstáMuerto() {
        if(estáMuerto()){
            getTablero().iu.estadoDeJuego = EstadoDeJuego.DERROTA;
            getTablero().pararMúsica();
            getTablero().reproducirSE(4);
        }
    }


    public void colocarBloque(int índice){
        int x = getMundoX();
        int y = getMundoY();

        if(control.fPresionada){
            getTablero().colocador.colocarBloquesInteractivos(x, y, índice, dirección); índice++;
        }
    }
    private void contactoConEnemigo(int i) {
        if(!getTablero().control.espacioPresionado){
            if (i != 999) {
                if (!invencible) {
                    vida -= 1;
                    invencible = true;
                }
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
            getTablero().frutas[index] = null;
            númeroDeFrutas++;
            getTablero().reproducirSE(1);
            //  tablero.
            System.out.println("Frutas recolectadas: " + númeroDeFrutas);
        }
        if (númeroDeFrutas == 2) {
            getTablero().iu.estadoDeJuego = EstadoDeJuego.VICTORIA;
            //tablero.iu.juegoTerminado = true;
            getTablero().pararMúsica();
            getTablero().reproducirSE(6);
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

        g2.drawImage(imagen, getMundoX(), getMundoY(), null);
        //reseteo
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g2.drawRect(getMundoX(), getMundoY() + getÁreaSólida().y, getÁreaSólida().width, getÁreaSólida().height); //HITBOX Jugador
    }

}
