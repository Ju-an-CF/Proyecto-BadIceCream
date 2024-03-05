package Negocio.niveles;

public class Nivel1 extends Nivel{
    private static int númeroNivel = 1;
    private static final String mapa1 = "/presentación/fuentes/datosDeJuego/mapa.txt";
    //Reemplazar las rutas por la de sus computadores o intentar con la del proyecto mismo
    private static final String frutas1 = "C:\\Users\\Compu\\Documents\\workspace\\Clonación\\Proyecto---BadIceCream\\src\\presentación\\fuentes\\entidades\\frutas.txt";
    private static final String enemigos1 = "C:\\Users\\Compu\\Documents\\workspace\\Clonación\\Proyecto---BadIceCream\\src\\presentación\\fuentes\\entidades\\enemigos.txt";


    public Nivel1() {
        super(númeroNivel, mapa1, frutas1, enemigos1);
    }

}
