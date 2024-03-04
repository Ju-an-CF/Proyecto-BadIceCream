package entidades.personajes;

import entidades.Entidad;
import entidades.frutas.Fruta;
import escenario.Tablero;
import interfazDeUsuario.EstadoDeJuego;
import mecánicas.Control;
import mecánicas.Dirección;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jugador extends Entidad {
    Control control;
    // Coordenadas de la ventana que sigue al jugador
    public final int ventanaX;
    public final int ventanaY;

    // Cantidad de frutas recolectadas por el jugador
    public int númeroDeFrutas;
    // Estadísticas por defecto del jugador
    public int máximoVidas;
    public int vida;
    public int tiempoDeInvencibilidad = 0;
    public boolean invencible = false;
    // Número máximo de frutas que puede recolectar el jugador
    private final int NUM_MAX_FRUTAS = 11;
    public int puntaje = 0;

    /**
     * Constructor de la clase Jugador.
     *
     * @param tablero Referencia al objeto Tablero.
     * @param control Referencia al objeto Control para manejar la entrada del jugador.
     */
    public Jugador(Tablero tablero, Control control) {
        super(tablero);
        this.control = control;
        // Inicialización de las propiedades del área sólida del jugador
        áreaSólida = new Rectangle();
        áreaSólida.x = 7;
        áreaSólida.y = 14;
        áreaSólidaPorDefectoX = áreaSólida.x;
        áreaSólidaPorDefectoY = áreaSólida.y;
        áreaSólida.width = 28;
        áreaSólida.height = 28;
        // Coordenadas de la ventana que sigue al jugador
        ventanaX = tablero.TAMAÑO_DE_BLOQUE * 7; //
        ventanaY = tablero.TAMAÑO_DE_BLOQUE * 7; //
        númeroDeFrutas = 0;
        //Configuraciones iniciales del jugador
        establecerValoresPredeterminados();
        obtenerImagenDeJugador();
    }

    /**
     * Establece la posición predeterminada del jugador.
     * Este método coloca al jugador en una posición específica y con una dirección inicial.
     */
    public void establecerPosiciónPredeterminada() {
        mundoX = 9 * tablero.TAMAÑO_DE_BLOQUE;
        mundoY = 10 * tablero.TAMAÑO_DE_BLOQUE;
        dirección = Dirección.ABAJO;
    }

    /**
     * Restablece la vida del jugador a su valor máximo.
     * Este método se utiliza al reiniciar el juego o al reintentar.
     */
    public void reestablecerVida() {
        vida = máximoVidas;
        invencible = false;
    }

    /**
     * Restablece la cantidad de frutas recolectadas por el jugador.
     * Este método se utiliza al reiniciar el juego o al reintentar.
     */
    public void reestablecerFrutas() {
        númeroDeFrutas = 0;
    }

    /**
     * Establece los valores predeterminados para el jugador.
     * Este método inicializa la velocidad y la dirección del jugador, así como las estadísticas de vida.
     */
    public void establecerValoresPredeterminados() {
        //Coordenadas iniciales
        mundoX = 9 * tablero.TAMAÑO_DE_BLOQUE;
        mundoY = 10 * tablero.TAMAÑO_DE_BLOQUE;
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

    /**
     * Actualiza el estado del jugador en el juego.
     * Este método maneja la entrada del jugador, verifica colisiones con bloques, objetos y enemigos,
     * y actualiza la posición del jugador en consecuencia.
     */
    public void actualizar() {
        // Verificar si alguna tecla de dirección está presionada
        if (control.arribaPresionado || control.abajoPresionado || control.izquierdaPresionado || control.derechaPresionado) {
            // Establecer la dirección según la tecla presionada
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
                    case ARRIBA:
                        mundoY -= velocidad;
                        break;
                    case ABAJO:
                        mundoY += velocidad;
                        break;
                    case IZQUIERDA:
                        mundoX -= velocidad;
                        break;
                    case DERECHA:
                        mundoX += velocidad;
                        break;
                }
            }
            // Actualizar contador de movimiento para cambiar entre sprites de animación
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
        // Verificar invencibilidad y actualizar estado
        if (invencible) {
            tiempoDeInvencibilidad++;
            if (tiempoDeInvencibilidad > 60) {
                invencible = false;
                tiempoDeInvencibilidad = 0;
            }
        }
        // Verificar si el jugador ha muerto y actualizar el estado del juego
        if (comprobarSiEstáMuerto()) {
            tablero.estadoActualDeJuego = EstadoDeJuego.DERROTA;
            tablero.reproducirSE(4);
        }
        // Verificar si el jugador ha ganado y actualizar el estado del juego
        if (comprobarVictoria()) {
            tablero.estadoActualDeJuego = EstadoDeJuego.VICTORIA;
            tablero.reproducirSE(6);
        }
    }

    /**
     * Comprueba si el jugador ha alcanzado la victoria al recoger todas las frutas disponibles.
     *
     * @return true si el número de frutas recolectadas es igual al número máximo de frutas, false en caso contrario.
     */
    private boolean comprobarVictoria() {
        return númeroDeFrutas == NUM_MAX_FRUTAS;
    }

    /**
     * Comprueba si el jugador ha perdido todas sus vidas.
     *
     * @return true si la vida del jugador es igual a cero, false en caso contrario.
     */
    public boolean comprobarSiEstáMuerto() {
        return vida == 0;
    }

    /**
     * Maneja el contacto con un enemigo, reduciendo la vida del jugador si no está en estado invencible.
     *
     * @param i El índice del enemigo en el arreglo de enemigos del tablero.
     */
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
            Fruta fruta = (Fruta)tablero.frutas[index];
            puntaje += fruta.getPuntaje();
            tablero.frutas[index] = null;
            númeroDeFrutas++;
            tablero.reproducirSE(1);
            //  tablero.
            System.out.println("Frutas recolectadas: " + númeroDeFrutas);
        }
    }

    /**
     * Dibuja la representación gráfica del jugador en la pantalla, considerando la dirección y el movimiento actual.
     *
     * @param g2 El contexto gráfico en el que se dibujará el jugador.
     */
    public void dibujar(Graphics2D g2) {
        BufferedImage imagen = null;
        // Seleccionar la imagen correspondiente según la dirección y el movimiento del jugador
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
        // Aplicar transparencia si el jugador está en estado invencible
        if (invencible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        // Dibujar la imagen del jugador en la posición de la ventana
        g2.drawImage(imagen, ventanaX, ventanaY, null);
        // Resetear la transparencia
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        // Dibujar el hitbox del jugador
        g2.drawRect(ventanaX + áreaSólida.x, ventanaY + áreaSólida.y, áreaSólida.width, áreaSólida.height); //HITBOX Jugador
        //g2.drawRect(126, 84, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE); //HITBOX Bloque
    }

    /**
     * Realiza la acción de romper o crear hielo según la posición del jugador en el tablero.
     * Si el jugador está sobre un bloque de hielo, lo rompe; de lo contrario, crea un bloque de hielo.
     */
    public void romperOCrearHielo() {
        // Validar si el jugador está sobre un bloque de hielo
        if (colisiónActiva) {
            tablero.romperBloqueHielo();
        } else {
            tablero.crearBloqueHielo();
        }
    }

    /**
     * Obtiene la dirección actual del jugador.
     *
     * @return La dirección actual del jugador.
     */
    public Dirección getDireccion() {
        return dirección;
    }
}
