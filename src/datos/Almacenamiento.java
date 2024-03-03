package datos;

import entidades.Entidad;

import java.io.Serializable;

public class Almacenamiento implements Serializable {
    //Estadísticas del juegador
    int númeroDeFrutas;
    int vidaMáxima;
    int vida;
    int posX;
    int posY;
    double tiempo;
    int fPosX;
    int fPosY;
    Entidad[] frutas;
    int[][] mapa;
    Entidad[] enemigos;

}
