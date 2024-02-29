package escenario.entidades.bloques;

import escenario.Tablero;
import escenario.HerramientaUtilidad;

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
        cargarMapa("/escenario/mapa3.txt");
    }

    /**
     * Configura las imágenes de los bloques.
     * Este método configura varios bloques con sus respectivas imágenes y tipos de bloque.
     */
    public void obtenerImagenDeBloque() {
        configurar(0, "nieve", TipoBloque.NORMAL);      // Bloque de nieve normal
        configurar(1, "esquina1", TipoBloque.NORMAL);   // Bloque de nieve normal
        configurar(2, "esquina2", TipoBloque.ESTÁTICO); // Bloque de esquina estático
        configurar(3, "esquina3", TipoBloque.ESTÁTICO); // Bloque de esquina estático
        configurar(4, "esquina4", TipoBloque.ESTÁTICO); // Bloque de esquina estático
        configurar(5, "muro", TipoBloque.ESTÁTICO);     // Bloque de muro estático
        configurar(6, "bolaNieve", TipoBloque.NORMAL);  // Bloque de nieve normal
        configurar(7, "florNieve", TipoBloque.NORMAL);  // Bloque de nieve normal
        configurar(8, "hielo", TipoBloque.HIELO);       // Bloque de hielo
    }

    /**
     * Configura un bloque en el índice especificado con la imagen y tipo de bloque dados.
     *
     * @param índice          El índice donde se configurará el bloque.
     * @param direcciónImagen La dirección de la imagen del bloque
     * @param tipoBloque      El tipo de bloque a instanciar
     */
    public void configurar(int índice, String direcciónImagen, TipoBloque tipoBloque) {
        HerramientaUtilidad uT = new HerramientaUtilidad();

        try {
            // Determinar el tipo de bloque a instanciar
            Bloque bloque = switch (tipoBloque) {
                case ESTÁTICO -> new BloqueEstático();
                case HIELO -> new BloqueDeHielo();
                default -> new Bloque();
            };

            bloque.imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/bloque/" + direcciónImagen + ".png"));
            bloque.imagen = uT.scaledImage(bloque.imagen, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE);

            bloques[índice] = bloque; // Asignar el bloque instanciado al arreglo de bloques.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee el mapa de una ruta y la carga para generar los bloques respectivos
     *
     * @param direcciónArchivo Ruta del mapa
     */
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
            int mundoX = columnasDeMundo * tablero.TAMAÑO_DE_BLOQUE;
            int mundoY = filasDeMundo * tablero.TAMAÑO_DE_BLOQUE;

            g2.drawImage(bloques[numBloque].imagen, mundoX, mundoY, null);
            columnasDeMundo++;

            if (columnasDeMundo == tablero.COLUMNAS_MAX) {
                columnasDeMundo = 0;
                mundoX = 0;
                filasDeMundo++;
                mundoY += tablero.TAMAÑO_DE_BLOQUE;
            }
        }
    }

}
