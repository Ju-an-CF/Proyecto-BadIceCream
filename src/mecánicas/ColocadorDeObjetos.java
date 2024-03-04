package mecánicas;

import entidades.Entidad;
import entidades.frutas.Mora;
import entidades.personajes.Ogro;
import entidades.personajes.Toro;
import escenario.Tablero;

import java.io.IOException;
import java.io.Serializable;

import static mecánicas.LectorEntidades.leerEnemigos;

public class ColocadorDeObjetos implements Serializable {
    public Tablero tablero;

    public ColocadorDeObjetos(Tablero tablero) {
        this.tablero = tablero;
    }

    public void colocarMora(String rutaDeFrutas) {
        try {
            int[][] posiciones = LectorEntidades.leerPosiciones(rutaDeFrutas);

            for (int i = 0; i < posiciones.length; i++) {
                tablero.frutas[i] = new Mora(tablero);
                tablero.frutas[i].mundoX = posiciones[i][0] * tablero.TAMAÑO_DE_BLOQUE;
                tablero.frutas[i].mundoY = posiciones[i][1] * tablero.TAMAÑO_DE_BLOQUE;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de frutas: " + e.getMessage());
        }
    }

    public void colocarEnemigos(String rutaDeEnemigos) {
        try {
            Entidad[] enemigos = leerEnemigos(rutaDeEnemigos, tablero);

            for (int i = 0; i < enemigos.length; i++) {
                tablero.enemigos[i] = enemigos[i];
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de enemigos: " + e.getMessage());
        }
    }

}
