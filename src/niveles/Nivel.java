package niveles;

import mecánicas.ColocadorDeObjetos;

import java.io.Serializable;

public abstract class Nivel implements Serializable {
    private int númeroNivel;
    private String rutaMapa;
    private String rutaFrutas;
    private String rutaEnemigos;

    public Nivel(int númeroNivel, String rutaMapa, String rutaFrutas, String rutaEnemigos) {
        this.númeroNivel = númeroNivel;
        this.rutaMapa = rutaMapa;
        this.rutaFrutas = rutaFrutas;
        this.rutaEnemigos = rutaEnemigos;
    }

    public String getNivel(){
        return "nivel" + this.númeroNivel;
    }

    public String getRutaMapa() {
        return rutaMapa;
    }

    public String getRutaFrutas(){
        return rutaFrutas;
    }

    public String getRutaEnemigos() {
        return rutaEnemigos;
    }
}
