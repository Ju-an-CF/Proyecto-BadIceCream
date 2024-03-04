package escenario;

import niveles.Nivel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

// Clase que gestiona los bloques en el juego
public class AdministradorDeBloque implements Serializable {
    Tablero tablero;
    public Bloque[] bloques;
    public int mapa[][];
    HerramientaUtilidad utilidad;
    Nivel nivel;

    // Constructor que inicializa el administrador de bloques
    public AdministradorDeBloque(Tablero tablero, Nivel nivel) {
        this.nivel = nivel;
        this.tablero = tablero;
        bloques = new Bloque[100];
        mapa = new int[tablero.maxColDeMundo][tablero.maxFilasDeMundo];
        inicializarBloques();
        cargarImagenesDeBloques();
        cargarMapa(nivel.getRutaMapa());
    }
    // Método para inicializar los diferentes tipos de bloques
    private void inicializarBloques() {
        bloques[0] = new Bloque();
        bloques[1] = new BloqueEstático();
        bloques[2] = new BloqueEstático();
        bloques[3] = new BloqueEstático();
        bloques[4] = new BloqueEstático();
        bloques[5] = new BloqueEstático();
        bloques[6] = new Bloque();
        bloques[7] = new Bloque();
        bloques[8] = new BloqueDeHielo();
    }

    /**
     * Carga las imágenes para cada bloque.
     */

    private void cargarImagenesDeBloques() {
        for (int i = 0; i < bloques.length; i++) {
            Bloque bloque = bloques[i];
            if (bloque != null) {
                String nombreImagen = obtenerNombreImagenPorIndice(i);
                bloque.imagen = cargarImagen("/fuentes/bloque/" + nombreImagen + ".png");
            }
        }
    }

    /**
     * Obtiene el nombre de la imagen basado en el índice del bloque.
     * @param índice El índice del bloque.
     * @return El nombre de la imagen correspondiente.
     */
    private String obtenerNombreImagenPorIndice(int índice) {
        // Implementación para obtener el nombre de la imagen basado en el índice
        switch (índice) {
            case 0:
                return "nieve";
            case 1:
                return "esquina1";
            case 2:
                return "esquina2";
            case 3:
                return "esquina3";
            case 4:
                return "esquina4";
            case 5:
                return "muro";
            case 6:
                return "florNieve";
            case 7:
                return "bolaNieve";
            case 8:
                return "hielo";
            default:
                return "default"; // O maneja una imagen por defecto.
        }
    }

    /**
     * Carga una imagen desde el sistema de archivos.
     * @param rutaImagen La ruta de la imagen.
     * @return La imagen cargada.
     */
    private BufferedImage cargarImagen(String rutaImagen) {
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(rutaImagen));
            HerramientaUtilidad uTool = new HerramientaUtilidad();
            imagen = uTool.escalarImagen(imagen, tablero.TAMAÑO_DE_BLOQUE, tablero.TAMAÑO_DE_BLOQUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }

    /**
     * Configura las imágenes de los bloques.
     * Este método configura varios bloques con sus respectivas imágenes y tipos de bloque.
     */


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
            // Manejo de excepciones (puede ser mejorado)
        }
    }

    // Método para dibujar el mapa en el panel
    public void dibujar(Graphics2D g2) {
        int columnasDeMundo = 0;
        int filasDeMundo = 0;

        while (columnasDeMundo < tablero.maxColDeMundo && filasDeMundo < tablero.maxFilasDeMundo) {

            int numBloque = mapa[columnasDeMundo][filasDeMundo];
            int mundoX = columnasDeMundo * tablero.TAMAÑO_DE_BLOQUE;
            int mundoY = filasDeMundo * tablero.TAMAÑO_DE_BLOQUE;
            int ventanaX = mundoX - tablero.jugador.mundoX + tablero.jugador.ventanaX;
            int ventanaY = mundoY - tablero.jugador.mundoY + tablero.jugador.ventanaY;
            // Si el jugador está en pantalla, dibujar el bloque
            if(jugadorEstáEnPantalla(mundoX, mundoY)) {
                g2.drawImage(bloques[numBloque].imagen, ventanaX, ventanaY, null);
            }
            columnasDeMundo++;

            if (columnasDeMundo == tablero.maxColDeMundo) {
                columnasDeMundo = 0;
                filasDeMundo++;
            }
        }
    }
    // Método para verificar si el jugador está en pantalla
    public boolean jugadorEstáEnPantalla(int mundoX, int mundoY) {
        return mundoX + tablero.TAMAÑO_DE_BLOQUE *11 > tablero.jugador.mundoX - tablero.jugador.ventanaX &&
                mundoX - tablero.TAMAÑO_DE_BLOQUE *12 < tablero.jugador.mundoX + tablero.jugador.ventanaX &&
                mundoY + (tablero.TAMAÑO_DE_BLOQUE *2) > tablero.jugador.mundoY - tablero.jugador.ventanaY &&
                mundoY - (tablero.TAMAÑO_DE_BLOQUE *2) < tablero.jugador.mundoY + tablero.jugador.ventanaY;
    }



}
