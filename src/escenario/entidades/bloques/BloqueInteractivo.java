package escenario.entidades.bloques;

import escenario.Tablero;
import escenario.entidades.Entidad;

public class BloqueInteractivo extends Entidad {
    Tablero tablero;
    public boolean destructible;
    public BloqueInteractivo(Tablero tablero) {
        super(tablero);
        this.tablero = tablero;
    }
    public void actualizar(){

    }

}
