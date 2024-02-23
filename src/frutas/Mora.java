package frutas;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Mora extends SuperObjeto {
    public Mora() {
        nombre = "Mora";
        try{
            imagen = ImageIO.read(getClass().getResourceAsStream("/fuentes/frutas/mora.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
