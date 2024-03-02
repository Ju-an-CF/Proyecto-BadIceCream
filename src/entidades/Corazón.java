package entidades;

import escenario.Tablero;

import java.awt.image.BufferedImage;

public class Corazón extends Entidad {

    public Corazón(Tablero tablero) {
        super(tablero);
        nombre = "Corazón";
        obtenerImagen();
    }

    private void obtenerImagen() {
        imagen1 = configurarImagen("corazón1");
        imagen2 = configurarImagen("corazón2");
        imagen3 = configurarImagen("corazón3");
    }

    public BufferedImage configurarImagen(String nombreImagen) {
        return super.configurarImagen("/fuentes/IU/" + nombreImagen);
    }

}
