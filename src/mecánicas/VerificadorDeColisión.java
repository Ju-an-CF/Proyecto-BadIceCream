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

    public int verificarObjeto(Entidad entidad, boolean esJugador) {
        int index = 999;
        for (int i = 0; i < tablero.frutas.length; i++) {
            if (tablero.frutas[i] != null) {
                entidad.áreaSólida.x = entidad.mundoX + entidad.áreaSólida.x;
                entidad.áreaSólida.y = entidad.mundoY + entidad.áreaSólida.y;
                tablero.frutas[i].áreaSólida.x = tablero.frutas[i].mundoX + tablero.frutas[i].áreaSólida.x;
                tablero.frutas[i].áreaSólida.y = tablero.frutas[i].mundoY + tablero.frutas[i].áreaSólida.y;
                switch (entidad.dirección) {
                    case "arriba":
                        entidad.áreaSólida.y -= entidad.velocidad;
                        break;
                    case "abajo":
                        entidad.áreaSólida.y += entidad.velocidad;
                        break;
                    case "izquierda":
                        entidad.áreaSólida.x -= entidad.velocidad;
                        break;
                    case "derecha":
                        entidad.áreaSólida.x += entidad.velocidad;
                        break;
                }
                if (entidad.áreaSólida.intersects(tablero.frutas[i].áreaSólida)) {
                    System.out.println("arriba colisión");
                    if (tablero.frutas[i].colisión) {
                        entidad.colisiónActiva = true;
                    }
                    if (esJugador) {
                        index = i;
                    }
                }
                    entidad.áreaSólida.x = entidad.áreaSólidaPorDefectoX;
                    entidad.áreaSólida.y = entidad.áreaSólidaPorDefectoY;
                    tablero.frutas[i].áreaSólida.x = tablero.frutas[i].áreaSólidaPorDefectoX;
                    tablero.frutas[i].áreaSólida.y = tablero.frutas[i].áreaSólidaPorDefectoY;
                }

            }
            return index;
        }
    public int verificarEntidad(Entidad entidad, Entidad[] objetivo){
        int index = 999;
        for (int i = 0; i < objetivo.length; i++) {
            if (objetivo[i] != null) {
                entidad.áreaSólida.x = entidad.mundoX + entidad.áreaSólida.x;
                entidad.áreaSólida.y = entidad.mundoY + entidad.áreaSólida.y;
                objetivo[i].áreaSólida.x = objetivo[i].mundoX + objetivo[i].áreaSólida.x;
                objetivo[i].áreaSólida.y = objetivo[i].mundoY + objetivo[i].áreaSólida.y;
                switch (entidad.dirección) {
                    case "arriba":
                        entidad.áreaSólida.y -= entidad.velocidad; break;
                    case "abajo":
                        entidad.áreaSólida.y += entidad.velocidad; break;
                    case "izquierda":
                        entidad.áreaSólida.x -= entidad.velocidad; break;
                    case "derecha":
                        entidad.áreaSólida.x += entidad.velocidad;break;
                }
                if(entidad.áreaSólida.intersects(objetivo[i].áreaSólida)){
                    if(objetivo[i]!=entidad) {
                        entidad.colisiónActiva = true;
                        index = i;
                    }
                }
                entidad.áreaSólida.x = entidad.áreaSólidaPorDefectoX;
                entidad.áreaSólida.y = entidad.áreaSólidaPorDefectoY;
                objetivo[i].áreaSólida.x = objetivo[i].áreaSólidaPorDefectoX;
                objetivo[i].áreaSólida.y = objetivo[i].áreaSólidaPorDefectoY;
            }

        }
        return index;
    }
    public boolean verificarJugador(Entidad entidad){
        boolean contactoConJugador=false;
        entidad.áreaSólida.x = entidad.mundoX + entidad.áreaSólida.x;
        entidad.áreaSólida.y = entidad.mundoY + entidad.áreaSólida.y;
        tablero.jugador.áreaSólida.x = tablero.jugador.mundoX + tablero.jugador.áreaSólida.x;
        tablero.jugador.áreaSólida.y = tablero.jugador.mundoY + tablero.jugador.áreaSólida.y;
        switch (entidad.dirección) {
            case "arriba":
                entidad.áreaSólida.y -= entidad.velocidad;
                break;
            case "abajo":
                entidad.áreaSólida.y += entidad.velocidad;
                break;
            case "izquierda":
                entidad.áreaSólida.x -= entidad.velocidad;
                break;
            case "derecha":
                entidad.áreaSólida.x += entidad.velocidad;
                break;
        }
        if (entidad.áreaSólida.intersects(tablero.jugador.áreaSólida)) {
            entidad.colisiónActiva = true;
            contactoConJugador=true;
        }
        entidad.áreaSólida.x = entidad.áreaSólidaPorDefectoX;
        entidad.áreaSólida.y = entidad.áreaSólidaPorDefectoY;
        tablero.jugador.áreaSólida.x = tablero.jugador.áreaSólidaPorDefectoX;
        tablero.jugador.áreaSólida.y = tablero.jugador.áreaSólidaPorDefectoY;
        return contactoConJugador;
    }

}
