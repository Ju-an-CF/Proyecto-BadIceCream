package Negocio.niveles;

public class Nivel2 extends Nivel{
    private static final int númeroNivel = 2;
    private static final String mapa2 = "/fuentes/datosDeJuego/mapa2.txt";
    //Reemplazar las rutas por la de sus computadores o intentar con la del proyecto mismo
    private static final String frutas2 = "F:\\Alessss\\1 semestre\\Programación\\Java\\Proyecto---BadIceCream\\src\\fuentes\\entidades\\frutas2.txt";
    private static final String enemigos2 = "F:\\Alessss\\1 semestre\\Programación\\Java\\Proyecto---BadIceCream\\src\\fuentes\\entidades\\enemigos2.txt";

    public Nivel2() {
        super(númeroNivel, mapa2, frutas2, enemigos2);
    }
}
