package bloques;

import escenario.Tablero;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AdministradorDeBloque {
    Tablero tablero;
    public Bloque[] bloques;
    public int mapa[][];

    public AdministradorDeBloque(Tablero tablero) {
        this.tablero = tablero;
        bloques = new Bloque[100];
        mapa = new int[tablero.maxColDeMundo][tablero.maxFilasDeMundo];
        obtenerImagenDeBloque();
        cargarMapa("/escenario/mapa.txt");
    }

    public void obtenerImagenDeBloque() {
        try {
            bloques[0] = new Bloque();
            bloques[0].imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/nieve.png"));

            bloques[1] = new BloqueEstático();
            bloques[1].imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/esquina1.png"));

            bloques[2] = new BloqueEstático();
            bloques[2].imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/esquina2.png"));

            bloques[3] = new BloqueEstático();
            bloques[3].imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/esquina3.png"));

            bloques[4] = new BloqueEstático();
            bloques[4].imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/esquina4.png"));

            bloques[5] = new BloqueEstático();
            bloques[5].imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/muro.png"));

            bloques[6] = new Bloque();
            bloques[6].imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/bolaNieve.png"));

            bloques[7] = new Bloque();
            bloques[7].imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/florNieve.png"));

            bloques[8] = new BloqueEstático();
            bloques[8].imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/hielo.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cargarMapa(String direcciónArchivo) {
        try {
            InputStream mapaDeEntrada = getClass().getResourceAsStream(direcciónArchivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(mapaDeEntrada));

            int columna = 0;
            int fila = 0;

            while (columna < tablero.maxColDeMundo && fila < tablero.maxFilasDeMundo) {
                String line = br.readLine();

                while (columna < tablero.maxColDeMundo) {
                    String numeros[] = line.split(" ");

                    int numero = Integer.parseInt(numeros[columna]);

                    mapa[columna][fila] = numero;
                    columna++;
                }
                if (columna == tablero.maxColDeMundo) {
                    columna = 0;
                    fila++;
                }

            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void dibujar(Graphics2D g2) {
        int columnasDeMundo = 0;
        int filasDeMundo = 0;

        while (columnasDeMundo < tablero.maxColDeMundo && filasDeMundo < tablero.maxFilasDeMundo) {

            int numBloque = mapa[columnasDeMundo][filasDeMundo];
            int mundoX = columnasDeMundo * tablero.TAMANIO_DE_BLOQUE;
            int mundoY = filasDeMundo * tablero.TAMANIO_DE_BLOQUE;
            int ventanaX = mundoX - tablero.jugador.mundoX + tablero.jugador.ventanaX;
            int ventanaY = mundoY - tablero.jugador.mundoY + tablero.jugador.ventanaY;

            if(jugadorEstáEnPantalla(mundoX, mundoY)) {
                g2.drawImage(bloques[numBloque].imagen, ventanaX, ventanaY, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE, null);
            }
            columnasDeMundo++;

            if (columnasDeMundo == tablero.maxColDeMundo) {
                columnasDeMundo = 0;
                filasDeMundo++;
            }
        }
    }

    public boolean jugadorEstáEnPantalla(int mundoX, int mundoY) {
        return mundoX + tablero.TAMANIO_DE_BLOQUE*11 > tablero.jugador.mundoX - tablero.jugador.ventanaX &&
                mundoX - tablero.TAMANIO_DE_BLOQUE*12 < tablero.jugador.mundoX + tablero.jugador.ventanaX &&
                mundoY + (tablero.TAMANIO_DE_BLOQUE*2) > tablero.jugador.mundoY - tablero.jugador.ventanaY &&
                mundoY - (tablero.TAMANIO_DE_BLOQUE*2) < tablero.jugador.mundoY + tablero.jugador.ventanaY;
    }

    public void romperBloque() {

    }

}
