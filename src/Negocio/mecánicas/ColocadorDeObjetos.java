package Negocio.mecánicas;


import Negocio.entidades.Entidad;
import Negocio.escenario.PanelDeJuego;

import java.io.IOException;
import java.io.Serializable;

import static Negocio.mecánicas.LectorEntidades.leerEnemigos;


public class ColocadorDeObjetos implements Serializable {
    private PanelDeJuego panelDeJuego;

    public ColocadorDeObjetos(PanelDeJuego panelDeJuego) {
        this.setTablero(panelDeJuego);
    }

    public void colocarFrutas(String rutaDeFrutas) {
        try {
            Entidad[] frutas = LectorEntidades.leerFrutas(rutaDeFrutas, getTablero());

            for (int i = 0; i < frutas.length; i++) {
                getTablero().getFrutas()[i] = frutas[i];
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de frutas: " + e.getMessage());
        }
    }

    public void colocarEnemigos(String rutaDeEnemigos) {
        try {
            Entidad[] enemigos = leerEnemigos(rutaDeEnemigos, getTablero());

            for (int i = 0; i < enemigos.length; i++) {
                getTablero().getEnemigos()[i] = enemigos[i];
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de enemigos: " + e.getMessage());
        }
    }

    public PanelDeJuego getTablero() {
        return panelDeJuego;
    }

    public void setTablero(PanelDeJuego panelDeJuego) {
        this.panelDeJuego = panelDeJuego;
    }
}
