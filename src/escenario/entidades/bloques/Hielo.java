package escenario.entidades.bloques;

import escenario.Tablero;

public class Hielo extends BloqueInteractivo {

    public Hielo(Tablero tablero, int columna, int fila) {
        super(tablero);
        this.setMundoX(columna * tablero.TAMAÑO_DE_BLOQUE);
        this.setMundoY(fila * tablero.TAMAÑO_DE_BLOQUE);
        abajo1 = configurarImagen("/fuentes/bloque/hielo");
        setDestructible(true);
    }
}
