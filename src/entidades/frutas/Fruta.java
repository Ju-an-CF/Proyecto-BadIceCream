package entidades.frutas;

import entidades.Entidad;
import escenario.Tablero;

public abstract class Fruta extends Entidad {
    private int puntaje;
    /**
     * Constructor de la clase Fruta.
     *
     * @param tablero   La referencia al tablero en el que se encuentra la fruta.
     * @param puntaje   El puntaje asociado a la fruta.
     * @param nombre    El nombre de la fruta.
     */
    public Fruta(Tablero tablero, int puntaje, String nombre) {
        super(tablero);
        this.puntaje = puntaje;
        this.nombre = nombre;
        obtenerImagen();
    }
    /**
     * Método abstracto para obtener la imagen de la fruta.
     * Cada subclase concreta de Fruta deberá implementar este método
     * para configurar la imagen específica de la fruta.
     */
    public abstract void obtenerImagen();

    public int getPuntaje(){
        return puntaje;
    }

}
