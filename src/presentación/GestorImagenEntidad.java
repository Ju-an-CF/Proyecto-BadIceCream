package presentación;

import Negocio.escenario.HerramientaUtilidad;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GestorImagenEntidad {
    public static BufferedImage configurarImagen(String nombreImagen) {
        HerramientaUtilidad ut = new HerramientaUtilidad();
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(GestorImagenEntidad.class.getResourceAsStream(nombreImagen + ".png"));
            imagen = ut.escalarImagen(imagen, 42, 42);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }
}
