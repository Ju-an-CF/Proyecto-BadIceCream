package escenario;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaledImage(BufferedImage original,int ancho, int alto){
        BufferedImage scaledImage=new BufferedImage(ancho,alto,original.getType());
        Graphics2D graphics2D=scaledImage.createGraphics();
        graphics2D.drawImage(original,0,0,ancho,alto,null);
        graphics2D.dispose();;

        return  scaledImage;
    }
}
