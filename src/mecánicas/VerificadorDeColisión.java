package mecánicas;

import entidades.Entidad;
import escenario.BloqueEstático;
import escenario.Tablero;

public class VerificadorDeColisión {
    Tablero tablero;

    public VerificadorDeColisión(Tablero tablero) {
        this.tablero = tablero;
    }

    public void verificarBloque(Entidad entidad) {
        int entidadDimIzquierdaX = entidad.mundoX + entidad.áreaSólida.x;
        int entidadDimDerechaX = entidad.mundoX + entidad.áreaSólida.x + entidad.áreaSólida.width;
        int entidadDimSuperiorY = entidad.mundoY + entidad.áreaSólida.y;
        int entidadDimInferiorY = entidad.mundoY + entidad.áreaSólida.y + entidad.áreaSólida.height;

        int columnaIzquierdaDeEntidad = entidadDimIzquierdaX / tablero.TAMANIO_DE_BLOQUE;
        int columnaDerechaDeEntidad = entidadDimDerechaX / tablero.TAMANIO_DE_BLOQUE;
        int filaSuperiorDeEntidad = entidadDimSuperiorY / tablero.TAMANIO_DE_BLOQUE;
        int filaInferiorDeEntidad = entidadDimInferiorY / tablero.TAMANIO_DE_BLOQUE;

        int numBloque1, numBloque2;

        switch (entidad.dirección) {
            case ("arriba"):
                filaSuperiorDeEntidad = (entidadDimSuperiorY - entidad.velocidad) / tablero.TAMANIO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaSuperiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaSuperiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.colisiónActiva = true;
                }
                break;
            case ("abajo"):
                filaInferiorDeEntidad = (entidadDimInferiorY + entidad.velocidad) / tablero.TAMANIO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaInferiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaInferiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.colisiónActiva = true;
                }
                break;
            case ("izquierda"):
                columnaIzquierdaDeEntidad = (entidadDimIzquierdaX - entidad.velocidad) / tablero.TAMANIO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaSuperiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaInferiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.colisiónActiva = true;
                }
                break;
            case ("derecha"):
                columnaDerechaDeEntidad = (entidadDimDerechaX + entidad.velocidad) / tablero.TAMANIO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaSuperiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaInferiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.colisiónActiva = true;
                }
                break;
        }
    }
}
