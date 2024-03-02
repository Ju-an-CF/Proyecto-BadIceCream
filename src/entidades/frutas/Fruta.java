package entidades.frutas;

import entidades.Entidad;
import escenario.Tablero;

public abstract class Fruta extends Entidad {
    private int puntaje;

    public Fruta(Tablero tablero, int puntaje, String nombre) {
        super(tablero);
        this.puntaje = puntaje;
        this.nombre = nombre;
        obtenerImagen();
    }

    public abstract void obtenerImagen();

}
