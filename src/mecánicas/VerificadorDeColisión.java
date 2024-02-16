package mecánicas;
import entidades.Entidad;
import escenario.Tablero;

public class VerificadorDeColisión {
    Tablero tablero;
    public VerificadorDeColisión(Tablero tablero) {
        this.tablero=tablero;
    }
    public void verificarBloque(Entidad entidad){
        int entidadDimIzquierdaX= entidad.x+entidad.áreaSólida.x;
        int entidadDimDerechaX=entidad.x+entidad.áreaSólida.x+entidad.áreaSólida.width;
        int entidadDimSuperiorY= entidad.y+entidad.áreaSólida.y;
        int entidadDimInferiorY=entidad.y+entidad.áreaSólida.y+entidad.áreaSólida.height;

        int columnaIzquierdaDeEntidad=entidadDimIzquierdaX/tablero.TAMANIO_DE_BLOQUE;
        int columnaDerechaDeEntidad=entidadDimDerechaX/tablero.TAMANIO_DE_BLOQUE;
        int filaSuperiorDeEntidad=entidadDimSuperiorY/tablero.TAMANIO_DE_BLOQUE;
        int filaInferiorDeEntidad=entidadDimInferiorY/tablero.TAMANIO_DE_BLOQUE;

        int numBloque1, numBloque2;

        switch(entidad.dirección){
            case ("arriba"):
                filaSuperiorDeEntidad=(entidadDimSuperiorY-entidad.velocidad)/tablero.TAMANIO_DE_BLOQUE;
                numBloque1=tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaSuperiorDeEntidad];
                numBloque2=tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaSuperiorDeEntidad];
                if(tablero.adminBlock.bloques[numBloque1].colisión==true||tablero.adminBlock.bloques[numBloque2].colisión==true){
                    entidad.colisiónActiva=true;
                }
                break;
            case ("abajo"):
                filaInferiorDeEntidad=(entidadDimInferiorY+entidad.velocidad)/tablero.TAMANIO_DE_BLOQUE;
                numBloque1=tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaInferiorDeEntidad];
                numBloque2=tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaInferiorDeEntidad];
                if(tablero.adminBlock.bloques[numBloque1].colisión==true||tablero.adminBlock.bloques[numBloque2].colisión==true){
                    entidad.colisiónActiva=true;
                }
                break;
            case("izquierda"):
                columnaIzquierdaDeEntidad=(entidadDimIzquierdaX-entidad.velocidad)/tablero.TAMANIO_DE_BLOQUE;
                numBloque1=tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaSuperiorDeEntidad];
                numBloque2=tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaInferiorDeEntidad];
                if(tablero.adminBlock.bloques[numBloque1].colisión==true||tablero.adminBlock.bloques[numBloque2].colisión==true) {
                    entidad.colisiónActiva = true;
                }
                break;
            case("derecha"):
                columnaDerechaDeEntidad=(entidadDimIzquierdaX+entidad.velocidad)/tablero.TAMANIO_DE_BLOQUE;
                numBloque1=tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaSuperiorDeEntidad];
                numBloque2=tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaInferiorDeEntidad];
                if(tablero.adminBlock.bloques[numBloque1].colisión==true||tablero.adminBlock.bloques[numBloque2].colisión==true) {
                    entidad.colisiónActiva = true;
                }
                break;
        }
    }
}
