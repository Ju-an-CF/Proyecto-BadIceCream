package entidades.objetos;

import entidades.Entidad;
import escenario.Tablero;

import java.awt.image.BufferedImage;

public class Corazón extends Entidad {
    /**
     * Constructor de la clase Corazón.
     *
     * @param tablero   La referencia al tablero en el que se encuentra el corazón.
     */
    public Corazón(Tablero tablero) {
        super(tablero);
        nombre = "Corazón";
        obtenerImagen();
    }
    /**
     * Método privado para configurar las imágenes del corazón.
     * Invoca el método configurarImagen de la clase base para obtener las imágenes específicas del corazón.
     */
    private void obtenerImagen() {
        imagen1 = configurarImagen("corazón1");
        imagen2 = configurarImagen("corazón2");
        imagen3 = configurarImagen("corazón3");
    }
    /**
     * Método para configurar la imagen del corazón mediante el nombre de la imagen.
     *
     * @param nombreImagen   El nombre de la imagen del corazón.
     * @return               La imagen del corazón configurada.
     */
    public BufferedImage configurarImagen(String nombreImagen) {
        return super.configurarImagen("/fuentes/IU/" + nombreImagen);
    }

}
