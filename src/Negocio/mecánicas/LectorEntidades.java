package Negocio.mecánicas;
import Negocio.entidades.frutas.Banana;
import Negocio.entidades.frutas.Fruta;
import Negocio.entidades.frutas.Mora;
import Negocio.entidades.enemigos.Enemigo;
import Negocio.entidades.enemigos.Ogro;
import Negocio.entidades.enemigos.Toro;
import Negocio.escenario.PanelDeJuego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorEntidades {
    public static Fruta[] leerFrutas(String nombreArchivo, PanelDeJuego panelDeJuego) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
        Fruta[] frutas = new Fruta[11]; // Suponiendo que hay 11 frutas en el archivo

        String linea;
        int indice = 0;
        while ((linea = lector.readLine()) != null && indice < frutas.length) {
            String[] partes = linea.split(",");
            String tipoFruta = partes[0];
            int mundoX = Integer.parseInt(partes[1]);
            int mundoY = Integer.parseInt(partes[2]);

            if (tipoFruta.equals("Mora")) {
                frutas[indice] = new Mora(panelDeJuego);
            } else if (tipoFruta.equals("Banana")) {
                frutas[indice] = new Banana(panelDeJuego);
            } // Añade más condiciones para otros tipos de frutas si es necesario

            frutas[indice].setMundoX(mundoX * panelDeJuego.getTAMAÑO_DE_BLOQUE());
            frutas[indice].setMundoY(mundoY * panelDeJuego.getTAMAÑO_DE_BLOQUE());

            indice++;
        }

        lector.close();
        return frutas;
    }

    public static Enemigo[] leerEnemigos(String nombreArchivo, PanelDeJuego panelDeJuego) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
        Enemigo[] enemigos = new Enemigo[panelDeJuego.getEnemigos().length]; // Suponiendo que hay 5 enemigos en el archivo

        String linea;
        int indice = 0;
        while ((linea = lector.readLine()) != null && indice < enemigos.length) {
            String[] partes = linea.split(",");
            String tipoEnemigo = partes[0];
            int mundoX = Integer.parseInt(partes[1]);
            int mundoY = Integer.parseInt(partes[2]);

            if (tipoEnemigo.equals("Toro")) {
                enemigos[indice] = new Toro(panelDeJuego);
            } else if (tipoEnemigo.equals("Ogro")) {
                enemigos[indice] = new Ogro(panelDeJuego);
            } // Añade más condiciones para otros tipos de enemigos si es necesario

            enemigos[indice].setMundoX(mundoX * panelDeJuego.getTAMAÑO_DE_BLOQUE());
            enemigos[indice].setMundoY(mundoY * panelDeJuego.getTAMAÑO_DE_BLOQUE());

            indice++;
        }

        lector.close();
        return enemigos;
    }
}
