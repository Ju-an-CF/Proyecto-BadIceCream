package entidades;

import escenario.HerramientaUtilidad;
import escenario.Tablero;
import mecánicas.Dirección;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class Entidad implements Serializable {

    public Tablero tablero;
    //Coordenadas de la entidad
    public int mundoX, mundoY;
    //Velocidad del jugador
    public int velocidad;

    //Imágenes que usarán las entidades
    public transient BufferedImage arriba1, arriba2, arriba3, arriba4, abajo1, abajo2, abajo3, abajo4, izquierda1, izquierda2, izquierda3, izquierda4, derecha1, derecha2, derecha3, derecha4;
    public transient BufferedImage imagen1, imagen2, imagen3, imagen4, imagen5;

    // Dirección por defecto de la entidad
    public Dirección dirección = Dirección.ABAJO;
    // Contadores y números para controlar la animación de la entidad
    public int contadorMovimiento = 0;
    public int numeroDeMovimiento = 1;
    // Área sólida de la entidad para detección de colisiones
    public Rectangle áreaSólida = new Rectangle(0, 0, 42, 42);
    public int áreaSólidaPorDefectoX, áreaSólidaPorDefectoY;
    // Estado de colisión de la entidad
    public boolean colisiónActiva = false;
    public boolean colisión = false;
    // Contador para bloquear la acción de la entidad temporalmente
    public int contadorBloqueoDeAcción = 0;
    // Nombre de la entidad
    public String nombre;
    /**
     * Constructor de la clase Entidad.
     *
     * @param tablero   La referencia al tablero en el que se encuentra la entidad.
     */
    public Entidad(Tablero tablero) {
        this.tablero = tablero;
    }
    /**
     * Método para establecer la acción de la entidad.
     * Este método puede ser sobrescrito por clases derivadas para definir comportamientos específicos.
     */
    public void establecerAcción() {
    }
    /**
     * Método para actualizar la entidad en el juego.
     * Realiza acciones como establecer su comportamiento, verificar colisiones con bloques, objetos, enemigos y jugadores,
     * y actualizar su posición en el mundo según su dirección y velocidad.
     */
    public void actualizar() {

        establecerAcción();
        colisiónActiva = false; // Reinicia el estado de colisión

        // Verifica colisiones con bloques, objetos, enemigos y jugadores
        tablero.checkColisión.verificarBloque(this);
        tablero.checkColisión.verificarObjeto(this, false);
        tablero.checkColisión.verificarEntidad(this, tablero.enemigos);
        boolean contactoConJugador = tablero.checkColisión.verificarJugador(this);
        // Realiza acciones específicas en caso de colisión con el jugador
        if (contactoConJugador) {
            if (!tablero.jugador.invencible) {
                tablero.jugador.vida -= 1;
                tablero.jugador.invencible = true;
            }
        }

        // Mueve la entidad si no hay colisión activa
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
        // Actualiza la animación de la entidad
        contadorMovimiento++;
        if (contadorMovimiento > 10) {
            if (numeroDeMovimiento == 1) {
                numeroDeMovimiento = 2;
            } else if (numeroDeMovimiento == 2) {
                numeroDeMovimiento = 1;
            }
            contadorMovimiento = 0;
        }
    }
    /**
     * Método para configurar la imagen de la entidad a partir de su nombre.
     * @param nombreImagen Nombre del archivo de imagen.
     * @return BufferedImage con la imagen configurada y escalada a 42x42 píxeles.
     */
    public BufferedImage configurarImagen(String nombreImagen) {
        HerramientaUtilidad ut = new HerramientaUtilidad();
        BufferedImage imagen = null;
        try {
            // Lee la imagen y la escala a 42x42 píxeles
            imagen = ImageIO.read(getClass().getResourceAsStream(nombreImagen + ".png"));
            imagen = ut.escalarImagen(imagen, 42, 42);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }
    /**
     * Método para dibujar la entidad en el juego.
     * @param graphics2D Objeto Graphics2D utilizado para dibujar la entidad.
     */
    public void dibujar(Graphics2D graphics2D) {
        BufferedImage imagen = null;
        // Calcula las coordenadas de la entidad en relación con el jugador y la ventana de visualización
        int ventanaX = mundoX - tablero.jugador.mundoX + tablero.jugador.ventanaX;
        int ventanaY = mundoY - tablero.jugador.mundoY + tablero.jugador.ventanaY;
        // Verifica si la entidad se encuentra dentro del área visible del jugador
        if (mundoX + tablero.TAMAÑO_DE_BLOQUE * 11 > tablero.jugador.mundoX - tablero.jugador.ventanaX &&
                mundoX - tablero.TAMAÑO_DE_BLOQUE * 12 < tablero.jugador.mundoX + tablero.jugador.ventanaX &&
                mundoY + (tablero.TAMAÑO_DE_BLOQUE * 2) > tablero.jugador.mundoY - tablero.jugador.ventanaY &&
                mundoY - (tablero.TAMAÑO_DE_BLOQUE * 2) < tablero.jugador.mundoY + tablero.jugador.ventanaY) {
            // Asigna la imagen correspondiente a la dirección y número de movimiento de la entidad
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
            // Dibuja la imagen en las coordenadas calculadas
            graphics2D.drawImage(imagen, ventanaX, ventanaY, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE, null);
        }
    }


}
