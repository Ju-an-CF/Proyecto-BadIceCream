package escenario;

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
        configurarBloque(0, "nieve", TipoBloque.NORMAL);
        configurarBloque(1, "esquina1", TipoBloque.NORMAL);
        configurarBloque(2, "esquina2", TipoBloque.ESTÁTICO);
        configurarBloque(3, "esquina3", TipoBloque.ESTÁTICO);
        configurarBloque(4, "esquina4", TipoBloque.ESTÁTICO);
        configurarBloque(5, "muro", TipoBloque.ESTÁTICO);
        configurarBloque(6, "bolaNieve", TipoBloque.NORMAL);
        configurarBloque(7, "florNieve", TipoBloque.NORMAL);
        configurarBloque(8, "hielo", TipoBloque.HIELO);
    }

    public void configurarBloque(int index, String direccionImagen, TipoBloque tipoBloque) {
        UtilityTool uTool = new UtilityTool();
        try {
            Bloque bloque = null; // Variable genérica para el bloque

            // Determinar el tipo de bloque a instanciar
            switch (tipoBloque) {
                case ESTÁTICO:
                    bloque = new BloqueEstático();
                    break;
                case HIELO:
                    bloque = new BloqueDeHielo();
                    break;
                default:
                    bloque = new Bloque();
                    break;
            }

            bloque.imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/" + direccionImagen + ".png"));
            bloque.imagen = uTool.scaledImage(bloque.imagen, tablero.TAMANIO_DE_BLOQUE, tablero.TAMANIO_DE_BLOQUE);

            bloques[index] = bloque; // Asignar el bloque instanciado al array
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

            if (jugadorEstáEnPantalla(mundoX, mundoY)) {
                g2.drawImage(bloques[numBloque].imagen, ventanaX, ventanaY, null);
            }
            columnasDeMundo++;

            if (columnasDeMundo == tablero.maxColDeMundo) {
                columnasDeMundo = 0;
                filasDeMundo++;
            }
        }
    }

    public boolean jugadorEstáEnPantalla(int mundoX, int mundoY) {
        return mundoX + tablero.TAMANIO_DE_BLOQUE * 11 > tablero.jugador.mundoX - tablero.jugador.ventanaX &&
                mundoX - tablero.TAMANIO_DE_BLOQUE * 12 < tablero.jugador.mundoX + tablero.jugador.ventanaX &&
                mundoY + (tablero.TAMANIO_DE_BLOQUE * 2) > tablero.jugador.mundoY - tablero.jugador.ventanaY &&
                mundoY - (tablero.TAMANIO_DE_BLOQUE * 2) < tablero.jugador.mundoY + tablero.jugador.ventanaY;
    }


}
