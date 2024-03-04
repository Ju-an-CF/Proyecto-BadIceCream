package datos;

import entidades.Entidad;
import entidades.frutas.Mora;
import escenario.Tablero;
import niveles.Nivel;
import niveles.Nivel1;

import java.io.*;

public class GuardarCargar implements Serializable {

    Tablero tablero;

    public GuardarCargar(Tablero tablero){
        this.tablero = tablero;
    }

    public void guardar(Nivel nivel){

        try{

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(nivel.getNivel() + ".dat")));


            Almacenamiento almacenamiento = new Almacenamiento();

            almacenamiento.númeroDeFrutas = tablero.jugador.númeroDeFrutas;
            almacenamiento.vidaMáxima     = tablero.jugador.máximoVidas;
            almacenamiento.vida           = tablero.jugador.vida;
            almacenamiento.posX           = tablero.jugador.mundoX;
            almacenamiento.posY           = tablero.jugador.mundoY;
            almacenamiento.tiempo         = tablero.iu.playTime;
            almacenamiento.mapa           = tablero.adminBlock.mapa;

            //Enemigos
            almacenamiento.enemigos = new Entidad[tablero.enemigos.length];
            for(int i = 0; i < tablero.enemigos.length; i++){
                almacenamiento.enemigos[i] = new Entidad(tablero);
            }
            for(int i = 0; i < tablero.enemigos.length; i++){
                if(tablero.enemigos[i] != null){
                    almacenamiento.enemigos[i].mundoX = tablero.enemigos[i].mundoX;
                    almacenamiento.enemigos[i].mundoY = tablero.enemigos[i].mundoY;
                }
            }

            //Frutas
            almacenamiento.frutas = new Entidad[tablero.frutas.length];
            for(int i = 0; i < tablero.frutas.length; i++){
                almacenamiento.frutas[i] = new Mora(tablero);
            }

            for(int i = 0; i < tablero.frutas.length; i++){
                if(tablero.frutas[i] != null){
                    almacenamiento.frutas[i].mundoX = tablero.frutas[i].mundoX;
                    almacenamiento.frutas[i].mundoY = tablero.frutas[i].mundoY;
                } else {
                    almacenamiento.frutas[i] = null;
                }
            }

            //Escribir en el archivo
            oos.writeObject(almacenamiento);
            oos.close();

        }catch (Exception e){
            System.out.println("Excepción de guardado!");
            e.printStackTrace();
        }
    }

    public void cargar(Nivel nivel){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(nivel.getNivel() + ".dat")));

            //Leer del archivo
            Almacenamiento almacenamiento = (Almacenamiento) ois.readObject();

            tablero.jugador.númeroDeFrutas  = almacenamiento.númeroDeFrutas;
            tablero.jugador.máximoVidas     = almacenamiento.vidaMáxima;
            tablero.jugador.vida            = almacenamiento.vida;
            tablero.jugador.mundoX          = almacenamiento.posX;
            tablero.jugador.mundoY          = almacenamiento.posY;
            tablero.iu.playTime             = almacenamiento.tiempo;
            tablero.adminBlock.mapa         = almacenamiento.mapa;

            for(int i = 0; i < almacenamiento.enemigos.length; i++){
                if(tablero.enemigos[i] != null){
                    tablero.enemigos[i].mundoX = almacenamiento.enemigos[i].mundoX;
                    tablero.enemigos[i].mundoY = almacenamiento.enemigos[i].mundoY;
                }
            }

            for(int i = 0; i < almacenamiento.frutas.length; i++){
                if(almacenamiento.frutas[i] != null){
                    tablero.frutas[i].mundoX = almacenamiento.frutas[i].mundoX;
                    tablero.frutas[i].mundoY = almacenamiento.frutas[i].mundoY;
                } else {
                    tablero.frutas[i] = null;
                }
            }

        }catch (Exception e){
            System.out.println("Excepción de cargado");
            e.printStackTrace();
        }
    }
}
