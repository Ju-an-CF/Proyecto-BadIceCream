package mecánicas;
import entidades.personajes.Enemigo;
import entidades.personajes.Ogro;
import entidades.personajes.Toro;
import escenario.Tablero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorEntidades {
    public static int[][] leerPosiciones(String nombreArchivo) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
        int cantidadFrutas = 11; // Suponiendo que hay 11 frutas en el archivo
        int[][] posiciones = new int[cantidadFrutas][2];

        String linea;
        int indice = 0;
        while ((linea = lector.readLine()) != null && indice < cantidadFrutas) {
            String[] partes = linea.split(",");
            posiciones[indice][0] = Integer.parseInt(partes[0]);
            posiciones[indice][1] = Integer.parseInt(partes[1]);
            indice++;
        }

        lector.close();
        return posiciones;
    }

    public static Enemigo[] leerEnemigos(String nombreArchivo, Tablero tablero) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
        Enemigo[] enemigos = new Enemigo[tablero.enemigos.length]; // Suponiendo que hay 5 enemigos en el archivo

        String linea;
        int indice = 0;
        while ((linea = lector.readLine()) != null && indice < enemigos.length) {
            String[] partes = linea.split(",");
            String tipoEnemigo = partes[0];
            int mundoX = Integer.parseInt(partes[1]);
            int mundoY = Integer.parseInt(partes[2]);

            if (tipoEnemigo.equals("Toro")) {
                enemigos[indice] = new Toro(tablero);
            } else if (tipoEnemigo.equals("Ogro")) {
                enemigos[indice] = new Ogro(tablero);
            } // Añade más condiciones para otros tipos de enemigos si es necesario

            enemigos[indice].mundoX = mundoX * tablero.TAMAÑO_DE_BLOQUE;
            enemigos[indice].mundoY = mundoY * tablero.TAMAÑO_DE_BLOQUE;

            indice++;
        }

        lector.close();
        return enemigos;
    }
}
