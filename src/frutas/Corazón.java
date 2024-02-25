package frutas;

import javax.imageio.ImageIO;
import java.io.IOException;
import escenario.Tablero;

public class Corazón extends SuperObjeto{

    public Corazón() {

        nombre = "Corazón";
        try{
            imagen1 = ImageIO.read(getClass().getResourceAsStream("/fuentes/IU/corazón1.png"));
            imagen2 = ImageIO.read(getClass().getResourceAsStream("/fuentes/IU/corazón2.png"));
            imagen3 = ImageIO.read(getClass().getResourceAsStream("/fuentes/IU/corazón3.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
