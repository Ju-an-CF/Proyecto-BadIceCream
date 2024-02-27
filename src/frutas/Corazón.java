package frutas;

import entidades.Entidad;
import escenario.Tablero;

public class Corazón extends Entidad {
    public Corazón(Tablero tablero) {
        super(tablero);

        nombre = "Corazón";
        imagen1 = configurarImagen("/fuentes/IU/corazón1");
        imagen2 = configurarImagen("/fuentes/IU/corazón2");
        imagen3 = configurarImagen("/fuentes/IU/corazón3");
    }
}
