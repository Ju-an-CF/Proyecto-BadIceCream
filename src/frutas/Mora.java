package frutas;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Mora extends SuperObjeto {
    public Mora() {
        nombre = "Mora";
        try{
            imagen1 = ImageIO.read(getClass().getResourceAsStream("/fuentes/frutas/mora1.png"));
            imagen2 = ImageIO.read(getClass().getResourceAsStream("/fuentes/frutas/mora2.png"));
            imagen3 = ImageIO.read(getClass().getResourceAsStream("/fuentes/frutas/mora3.png"));
            imagen4 = ImageIO.read(getClass().getResourceAsStream("/fuentes/frutas/mora4.png"));
            imagen5 = ImageIO.read(getClass().getResourceAsStream("/fuentes/frutas/mora5.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
