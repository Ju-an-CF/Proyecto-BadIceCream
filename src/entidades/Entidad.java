package entidades;

import escenario.Tablero;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entidad {
    public Tablero tablero;
    public int mundoX, mundoY;
    public int velocidad;

    public BufferedImage arriba1, arriba2, arriba3, arriba4, abajo1, abajo2, abajo3, abajo4, izquierda1, izquierda2, izquierda3, izquierda4, derecha1, derecha2, derecha3, derecha4;
    public String dirección;

    public int contadorMovimiento = 0;
    public int numeroDeMovimiento = 1;
    public Rectangle áreaSólida=new Rectangle(0,0,42,42);
    public int áreaSólidaPorDefectoX, áreaSólidaPorDefectoY;
    public boolean colisiónActiva = false;

}
