package escenario.entidades.bloques;

import escenario.Tablero;
import escenario.entidades.Entidad;

public class BloqueInteractivo extends Entidad {
    private Tablero tablero;
    private boolean destructible;
    public BloqueInteractivo(Tablero tablero) {
        super(tablero);
        this.setTablero(tablero);
    }
    public void actualizar(){}

    @Override
    public Tablero getTablero() {
        return tablero;
    }

    @Override
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean isDestructible() {
        return destructible;
    }

    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }
}
