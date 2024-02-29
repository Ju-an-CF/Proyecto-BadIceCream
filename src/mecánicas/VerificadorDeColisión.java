package mecánicas;

import escenario.entidades.Entidad;
import escenario.entidades.bloques.BloqueEstático;
import escenario.Tablero;

public class VerificadorDeColisión {
    Tablero tablero;

    public VerificadorDeColisión(Tablero tablero) {
        this.tablero = tablero;
    }

    public void verificarBloque(Entidad entidad) {
        int entidadDimIzquierdaX = entidad.getMundoX() + entidad.getÁreaSólida().x;
        int entidadDimDerechaX = entidad.getMundoX() + entidad.getÁreaSólida().x + entidad.getÁreaSólida().width;
        int entidadDimSuperiorY = entidad.getMundoY() + entidad.getÁreaSólida().y;
        int entidadDimInferiorY = entidad.getMundoY() + entidad.getÁreaSólida().y + entidad.getÁreaSólida().height;

        int columnaIzquierdaDeEntidad = entidadDimIzquierdaX / tablero.TAMAÑO_DE_BLOQUE;
        int columnaDerechaDeEntidad = entidadDimDerechaX / tablero.TAMAÑO_DE_BLOQUE;
        int filaSuperiorDeEntidad = entidadDimSuperiorY / tablero.TAMAÑO_DE_BLOQUE;
        int filaInferiorDeEntidad = entidadDimInferiorY / tablero.TAMAÑO_DE_BLOQUE;

        int numBloque1, numBloque2;

        switch (entidad.dirección) {
            case ARRIBA:
                filaSuperiorDeEntidad = (entidadDimSuperiorY - entidad.getVelocidad()) / tablero.TAMAÑO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaSuperiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaSuperiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.setColisiónActiva(true);
                }
                break;
            case ABAJO:
                filaInferiorDeEntidad = (entidadDimInferiorY + entidad.getVelocidad()) / tablero.TAMAÑO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaInferiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaInferiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.setColisiónActiva(true);
                }
                break;
            case IZQUIERDA:
                columnaIzquierdaDeEntidad = (entidadDimIzquierdaX - entidad.getVelocidad()) / tablero.TAMAÑO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaSuperiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaInferiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.setColisiónActiva(true);
                }
                break;
            case DERECHA:
                columnaDerechaDeEntidad = (entidadDimDerechaX + entidad.getVelocidad()) / tablero.TAMAÑO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaSuperiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaInferiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.setColisiónActiva(true);
                }
                break;
        }
    }

    public int verificarObjeto(Entidad entidad, boolean esJugador) {
        int index = 999;
        for (int i = 0; i < tablero.frutas.length; i++) {
            if (tablero.frutas[i] != null) {
                entidad.getÁreaSólida().x = entidad.getMundoX() + entidad.getÁreaSólida().x;
                entidad.getÁreaSólida().y = entidad.getMundoY() + entidad.getÁreaSólida().y;
                tablero.frutas[i].getÁreaSólida().x = tablero.frutas[i].getMundoX() + tablero.frutas[i].getÁreaSólida().x;
                tablero.frutas[i].getÁreaSólida().y = tablero.frutas[i].getMundoY() + tablero.frutas[i].getÁreaSólida().y;
                switch (entidad.dirección) {
                    case ARRIBA:
                        entidad.getÁreaSólida().y -= entidad.getVelocidad();
                        break;
                    case ABAJO:
                        entidad.getÁreaSólida().y += entidad.getVelocidad();
                        break;
                    case IZQUIERDA:
                        entidad.getÁreaSólida().x -= entidad.getVelocidad();
                        break;
                    case DERECHA:
                        entidad.getÁreaSólida().x += entidad.getVelocidad();
                        break;
                }
                if (entidad.getÁreaSólida().intersects(tablero.frutas[i].getÁreaSólida())) {
                    if (tablero.frutas[i].isColisiónActiva()) {
                        entidad.setColisiónActiva(true);
                    }
                    if (esJugador) {
                        index = i;
                    }
                }
                entidad.getÁreaSólida().x = entidad.getÁreaSólidaPorDefectoX();
                entidad.getÁreaSólida().y = entidad.getÁreaSólidaPorDefectoY();
                tablero.frutas[i].getÁreaSólida().x = tablero.frutas[i].getÁreaSólidaPorDefectoX();
                tablero.frutas[i].getÁreaSólida().y = tablero.frutas[i].getÁreaSólidaPorDefectoY();
            }

        }
        return index;
    }

    public int verificarEntidad(Entidad entidad, Entidad[] objetivo) {
        int index = 999;
        for (int i = 0; i < objetivo.length; i++) {
            if (objetivo[i] != null) {
                entidad.getÁreaSólida().x = entidad.getMundoX() + entidad.getÁreaSólida().x;
                entidad.getÁreaSólida().y = entidad.getMundoY() + entidad.getÁreaSólida().y;
                objetivo[i].getÁreaSólida().x = objetivo[i].getMundoX() + objetivo[i].getÁreaSólida().x;
                objetivo[i].getÁreaSólida().y = objetivo[i].getMundoY() + objetivo[i].getÁreaSólida().y;
                switch (entidad.dirección) {
                    case ARRIBA:
                        entidad.getÁreaSólida().y -= entidad.getVelocidad();
                        break;
                    case ABAJO:
                        entidad.getÁreaSólida().y += entidad.getVelocidad();
                        break;
                    case IZQUIERDA:
                        entidad.getÁreaSólida().x -= entidad.getVelocidad();
                        break;
                    case DERECHA:
                        entidad.getÁreaSólida().x += entidad.getVelocidad();
                        break;
                }
                if (entidad.getÁreaSólida().intersects(objetivo[i].getÁreaSólida())) {
                    if (objetivo[i] != entidad) {
                        entidad.setColisiónActiva(true);
                        index = i;
                    }
                }
                entidad.getÁreaSólida().x = entidad.getÁreaSólidaPorDefectoX();
                entidad.getÁreaSólida().y = entidad.getÁreaSólidaPorDefectoY();
                objetivo[i].getÁreaSólida().x = objetivo[i].getÁreaSólidaPorDefectoX();
                objetivo[i].getÁreaSólida().y = objetivo[i].getÁreaSólidaPorDefectoY();
            }

        }
        return index;
    }

    public boolean verificarJugador(Entidad entidad) {
        boolean contactoConJugador = false;
        entidad.getÁreaSólida().x = entidad.getMundoX() + entidad.getÁreaSólida().x;
        entidad.getÁreaSólida().y = entidad.getMundoY() + entidad.getÁreaSólida().y;
        tablero.jugador.getÁreaSólida().x = tablero.jugador.getMundoX() + tablero.jugador.getÁreaSólida().x;
        tablero.jugador.getÁreaSólida().y = tablero.jugador.getMundoY() + tablero.jugador.getÁreaSólida().y;
        switch (entidad.dirección) {
            case ARRIBA:
                entidad.getÁreaSólida().y -= entidad.getVelocidad();
                break;
            case ABAJO:
                entidad.getÁreaSólida().y += entidad.getVelocidad();
                break;
            case IZQUIERDA:
                entidad.getÁreaSólida().x -= entidad.getVelocidad();
                break;
            case DERECHA:
                entidad.getÁreaSólida().x += entidad.getVelocidad();
                break;
        }
        if (entidad.getÁreaSólida().intersects(tablero.jugador.getÁreaSólida())) {
            entidad.setColisiónActiva(true);
            contactoConJugador = true;
        }
        entidad.getÁreaSólida().x = entidad.getÁreaSólidaPorDefectoX();
        entidad.getÁreaSólida().y = entidad.getÁreaSólidaPorDefectoY();
        tablero.jugador.getÁreaSólida().x = tablero.jugador.getÁreaSólidaPorDefectoX();
        tablero.jugador.getÁreaSólida().y = tablero.jugador.getÁreaSólidaPorDefectoY();
        return contactoConJugador;
    }

}
