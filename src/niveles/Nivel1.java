package niveles;

public class Nivel1 extends Nivel{
    private static final int númeroNivel = 1;
    private static final String mapa1 = "/fuentes/datosDeJuego/mapa.txt";
    //Reemplazar las rutas por la de sus computadores o intentar con la del proyecto mismo
    private static final String frutas1 = "C:\\Users\\Compu\\Documents\\workspace\\Proyecto---BadIceCream\\src\\fuentes\\entidades\\frutas.txt";
    private static final String enemigos1 = "C:\\Users\\Compu\\Documents\\workspace\\Proyecto---BadIceCream\\src\\fuentes\\entidades\\enemigos.txt";

    public Nivel1() {
        super(númeroNivel ,mapa1, frutas1, enemigos1);
    }

}
