package mecánicas;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HerramientaUtilidad {
    /**
     * Configura la imagen cargándola desde la ruta correspondiente en el recurso del proyecto.
     *
     * @param nombreImagen  Nombre de la imagen (sin extensión) a cargar desde el recurso.
     * @param tamañoBloque  Tamaño del bloque para ajustar la escala de la imagen.
     * @return              La imagen configurada o null si hay un error durante la lectura.
     */
    public BufferedImage configurar(String nombreImagen, int tamañoBloque) {
        BufferedImage imagen = null;
        try {
            // Lee la imagen desde la ruta en el recurso del proyecto
            imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/jugador/" + nombreImagen + ".png"));
        } catch (IOException e) {
            // Imprime el rastreo de la excepción en caso de error durante la lectura
            e.printStackTrace();
        }

        return imagen;
    }


}
