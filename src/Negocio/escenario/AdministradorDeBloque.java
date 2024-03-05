package Negocio.escenario;

import Negocio.escenario.bloques.Bloque;
import Negocio.escenario.bloques.BloqueDeHielo;
import Negocio.escenario.bloques.BloqueEstático;
import Negocio.niveles.Nivel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class AdministradorDeBloque implements Serializable {
    private PanelDeJuego panelDeJuego;
    private Bloque[] bloques;
    private int[][] mapa;
    private HerramientaUtilidad utilidad;
    private Nivel nivel;

    public AdministradorDeBloque(PanelDeJuego panelDeJuego, Nivel nivel) {
        this.setNivel(nivel);
        this.setTablero(panelDeJuego);
        setBloques(new Bloque[100]);
        setMapa(new int[panelDeJuego.getMaxColDeMundo()][panelDeJuego.getMaxFilasDeMundo()]);
        inicializarBloques();
        cargarImagenesDeBloques();
        cargarMapa();
    }
    private void inicializarBloques() {
        getBloques()[0] = new Bloque();
        getBloques()[1] = new BloqueEstático();
        getBloques()[2] = new BloqueEstático();
        getBloques()[3] = new BloqueEstático();
        getBloques()[4] = new BloqueEstático();
        getBloques()[5] = new BloqueEstático();
        getBloques()[6] = new Bloque();
        getBloques()[7] = new Bloque();
        getBloques()[8] = new BloqueDeHielo();
    }

    /**
     * Carga las imágenes para cada bloque.
     */
    private void cargarImagenesDeBloques() {
        for (int i = 0; i < getBloques().length; i++) {
            Bloque bloque = getBloques()[i];
            if (bloque != null) {
                String nombreImagen = obtenerNombreImagenPorIndice(i);
                bloque.imagen = cargarImagen("/datos/fuentes/bloque/" + nombreImagen + ".png");
            }
        }
    }

    /**
     * Obtiene el nombre de la imagen basado en el índice del bloque.
     * @param índice El índice del bloque.
     * @return El nombre de la imagen correspondiente.
     */
    private String obtenerNombreImagenPorIndice(int índice) {
        // Aquí retornas el nombre de la imagen basado en el índice.
        // Por ejemplo:
        return switch (índice) {
            case 0 -> "nieve";
            case 1 -> "esquina1";
            case 2 -> "esquina2";
            case 3 -> "esquina3";
            case 4 -> "esquina4";
            case 5 -> "muro";
            case 6 -> "florNieve";
            case 7 -> "bolaNieve";
            case 8 -> "hielo";
            default -> "default"; // O maneja una imagen por defecto.
        };
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
            imagen = uTool.escalarImagen(imagen, getTablero().getTAMAÑO_DE_BLOQUE(), getTablero().getTAMAÑO_DE_BLOQUE());
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
     */
    public void cargarMapa() {

        String direcciónArchivo = this.getNivel().getRutaMapa();

        try {
            InputStream mapaDeEntrada = getClass().getResourceAsStream(direcciónArchivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(mapaDeEntrada));

            int columna = 0;
            int fila = 0;

            while (columna < getTablero().getMaxColDeMundo() && fila < getTablero().getMaxFilasDeMundo()) {
                String line = br.readLine();

                while (columna < getTablero().getMaxColDeMundo()) {
                    String numeros[] = line.split(" ");

                    int numero = Integer.parseInt(numeros[columna]);

                    getMapa()[columna][fila] = numero;
                    columna++;
                }
                if (columna == getTablero().getMaxColDeMundo()) {
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

        while (columnasDeMundo < getTablero().getMaxColDeMundo() && filasDeMundo < getTablero().getMaxFilasDeMundo()) {

            int numBloque = getMapa()[columnasDeMundo][filasDeMundo];
            int mundoX = columnasDeMundo * getTablero().getTAMAÑO_DE_BLOQUE();
            int mundoY = filasDeMundo * getTablero().getTAMAÑO_DE_BLOQUE();
            int ventanaX = mundoX - getTablero().getJugador().getMundoX() + getTablero().getJugador().getVentanaX();
            int ventanaY = mundoY - getTablero().getJugador().getMundoY() + getTablero().getJugador().getVentanaY();

            if(jugadorEstáEnPantalla(mundoX, mundoY)) {
                g2.drawImage(getBloques()[numBloque].imagen, ventanaX, ventanaY, null);
            }
            columnasDeMundo++;

            if (columnasDeMundo == getTablero().getMaxColDeMundo()) {
                columnasDeMundo = 0;
                filasDeMundo++;
            }
        }
    }

    public boolean jugadorEstáEnPantalla(int mundoX, int mundoY) {
        return mundoX + getTablero().getTAMAÑO_DE_BLOQUE() *11 > getTablero().getJugador().getMundoX() - getTablero().getJugador().getVentanaX() &&
                mundoX - getTablero().getTAMAÑO_DE_BLOQUE() *12 < getTablero().getJugador().getMundoX() + getTablero().getJugador().getVentanaX() &&
                mundoY + (getTablero().getTAMAÑO_DE_BLOQUE() *2) > getTablero().getJugador().getMundoY() - getTablero().getJugador().getVentanaY() &&
                mundoY - (getTablero().getTAMAÑO_DE_BLOQUE() *2) < getTablero().getJugador().getMundoY() + getTablero().getJugador().getVentanaY();
    }


    public PanelDeJuego getTablero() {
        return panelDeJuego;
    }

    public void setTablero(PanelDeJuego panelDeJuego) {
        this.panelDeJuego = panelDeJuego;
    }

    public Bloque[] getBloques() {
        return bloques;
    }

    public void setBloques(Bloque[] bloques) {
        this.bloques = bloques;
    }

    public int[][] getMapa() {
        return mapa;
    }

    public void setMapa(int[][] mapa) {
        this.mapa = mapa;
    }

    public HerramientaUtilidad getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(HerramientaUtilidad utilidad) {
        this.utilidad = utilidad;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
