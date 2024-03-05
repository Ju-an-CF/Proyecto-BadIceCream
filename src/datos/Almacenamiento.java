package datos;

import Negocio.entidades.Entidad;

import java.io.Serializable;

public class Almacenamiento implements Serializable {
    //Estadísticas del juegador
    int puntuación;
    int númeroDeFrutas;
    int vidaMáxima;
    int vida;
    int posX;
    int posY;
    double tiempo;
    Entidad[] frutas;
    int[][] mapa;
    Entidad[] enemigos;

}
