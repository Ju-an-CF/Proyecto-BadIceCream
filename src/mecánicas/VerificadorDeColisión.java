package mecánicas;

import entidades.Entidad;
import escenario.BloqueEstático;
import escenario.Tablero;

import java.io.Serializable;

public class VerificadorDeColisión implements Serializable {
    Tablero tablero;

    public VerificadorDeColisión(Tablero tablero) {
        this.tablero = tablero;
    }

    // Método para verificar colisiones con bloques estáticos
    public void verificarBloque(Entidad entidad) {
        // Coordenadas del área sólida de la entidad
        int entidadDimIzquierdaX = entidad.mundoX + entidad.áreaSólida.x;
        int entidadDimDerechaX = entidad.mundoX + entidad.áreaSólida.x + entidad.áreaSólida.width;
        int entidadDimSuperiorY = entidad.mundoY + entidad.áreaSólida.y;
        int entidadDimInferiorY = entidad.mundoY + entidad.áreaSólida.y + entidad.áreaSólida.height;
        // Convertir coordenadas a índices de bloque
        int columnaIzquierdaDeEntidad = entidadDimIzquierdaX / tablero.TAMAÑO_DE_BLOQUE;
        int columnaDerechaDeEntidad = entidadDimDerechaX / tablero.TAMAÑO_DE_BLOQUE;
        int filaSuperiorDeEntidad = entidadDimSuperiorY / tablero.TAMAÑO_DE_BLOQUE;
        int filaInferiorDeEntidad = entidadDimInferiorY / tablero.TAMAÑO_DE_BLOQUE;

        int numBloque1, numBloque2;
        // Verificar colisión en la dirección de movimiento de la entidad
        switch (entidad.dirección) {
            case ARRIBA:
                filaSuperiorDeEntidad = (entidadDimSuperiorY - entidad.velocidad) / tablero.TAMAÑO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaSuperiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaSuperiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.colisiónActiva = true;
                }
                break;
            case ABAJO:
                filaInferiorDeEntidad = (entidadDimInferiorY + entidad.velocidad) / tablero.TAMAÑO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaInferiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaInferiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.colisiónActiva = true;
                }
                break;
            case IZQUIERDA:
                columnaIzquierdaDeEntidad = (entidadDimIzquierdaX - entidad.velocidad) / tablero.TAMAÑO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaSuperiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaIzquierdaDeEntidad][filaInferiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.colisiónActiva = true;
                }
                break;
            case DERECHA:
                columnaDerechaDeEntidad = (entidadDimDerechaX + entidad.velocidad) / tablero.TAMAÑO_DE_BLOQUE;
                numBloque1 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaSuperiorDeEntidad];
                numBloque2 = tablero.adminBlock.mapa[columnaDerechaDeEntidad][filaInferiorDeEntidad];
                if (tablero.adminBlock.bloques[numBloque1] instanceof BloqueEstático || tablero.adminBlock.bloques[numBloque2] instanceof BloqueEstático) {
                    entidad.colisiónActiva = true;
                }
                break;
        }
    }
    // Método para verificar colisiones con objetos en el tablero (frutas)
    public int verificarObjeto(Entidad entidad, boolean esJugador) {
        int index = 999;
        for (int i = 0; i < tablero.frutas.length; i++) {
            if (tablero.frutas[i] != null) {
                // Almacenar las posiciones originales de las áreas sólidas
                entidad.áreaSólida.x = entidad.mundoX + entidad.áreaSólida.x;
                entidad.áreaSólida.y = entidad.mundoY + entidad.áreaSólida.y;
                // Mover las áreas sólidas de las entidades temporalmente
                tablero.frutas[i].áreaSólida.x = tablero.frutas[i].mundoX + tablero.frutas[i].áreaSólida.x;
                tablero.frutas[i].áreaSólida.y = tablero.frutas[i].mundoY + tablero.frutas[i].áreaSólida.y;
                // Mover la entidad y verificar colisiones
                moverEntidad(entidad);
                if (entidad.áreaSólida.intersects(tablero.frutas[i].áreaSólida)) {
                    if (tablero.frutas[i].colisión) {
                        entidad.colisiónActiva = true;
                    }
                    if (esJugador) {
                        index = i;
                    }
                }
                // Restaurar las posiciones originales de las áreas sólidas
                entidad.áreaSólida.x = entidad.áreaSólidaPorDefectoX;
                entidad.áreaSólida.y = entidad.áreaSólidaPorDefectoY;
                tablero.frutas[i].áreaSólida.x = tablero.frutas[i].áreaSólidaPorDefectoX;
                tablero.frutas[i].áreaSólida.y = tablero.frutas[i].áreaSólidaPorDefectoY;
            }

        }
        return index;
    }
    /**
     * Verifica la colisión de una entidad con un conjunto de objetivos.
     *
     * @param entidad   La entidad a verificar.
     * @param objetivo  Un array de entidades objetivo.
     * @return          El índice del objetivo con el que ha colisionado, o 999 si no hay colisión.
     */
    public int verificarEntidad(Entidad entidad, Entidad[] objetivo) {
        int index = 999;

        for (int i = 0; i < objetivo.length; i++) {
            if (objetivo[i] != null) {
                // Mover temporalmente las áreas sólidas de las entidades
                entidad.áreaSólida.x = entidad.mundoX + entidad.áreaSólida.x;
                entidad.áreaSólida.y = entidad.mundoY + entidad.áreaSólida.y;
                objetivo[i].áreaSólida.x = objetivo[i].mundoX + objetivo[i].áreaSólida.x;
                objetivo[i].áreaSólida.y = objetivo[i].mundoY + objetivo[i].áreaSólida.y;
                // Mover la entidad temporalmente y verificar colisiones
                moverEntidad(entidad);

                if (entidad.áreaSólida.intersects(objetivo[i].áreaSólida)) {
                    if (objetivo[i] != entidad) {
                        entidad.colisiónActiva = true;
                        index = i;
                    }
                }
                // Restaurar las posiciones originales de las áreas sólidas
                entidad.áreaSólida.x = entidad.áreaSólidaPorDefectoX;
                entidad.áreaSólida.y = entidad.áreaSólidaPorDefectoY;
                objetivo[i].áreaSólida.x = objetivo[i].áreaSólidaPorDefectoX;
                objetivo[i].áreaSólida.y = objetivo[i].áreaSólidaPorDefectoY;
            }
        }

        return index;
    }
    /**
     * Método para mover temporalmente una entidad y facilitar la detección de colisiones.
     *
     * @param entidad La entidad a mover.
     */
    private void moverEntidad(Entidad entidad) {
        switch (entidad.dirección) {
            case ARRIBA:
                entidad.áreaSólida.y -= entidad.velocidad;
                break;
            case ABAJO:
                entidad.áreaSólida.y += entidad.velocidad;
                break;
            case IZQUIERDA:
                entidad.áreaSólida.x -= entidad.velocidad;
                break;
            case DERECHA:
                entidad.áreaSólida.x += entidad.velocidad;
                break;
        }
    }
    /**
     * Verifica la colisión de una entidad con el jugador.
     *
     * @param entidad La entidad a verificar.
     * @return        True si hay colisión con el jugador, false en caso contrario.
     */
    public boolean verificarJugador(Entidad entidad) {
        boolean contactoConJugador = false;
        // Mover temporalmente las áreas sólidas de la entidad y el jugador
        entidad.áreaSólida.x = entidad.mundoX + entidad.áreaSólida.x;
        entidad.áreaSólida.y = entidad.mundoY + entidad.áreaSólida.y;

        tablero.jugador.áreaSólida.x = tablero.jugador.mundoX + tablero.jugador.áreaSólida.x;
        tablero.jugador.áreaSólida.y = tablero.jugador.mundoY + tablero.jugador.áreaSólida.y;
        // Mover la entidad temporalmente y verificar colisión con el jugador
        moverEntidad(entidad);

        if (entidad.áreaSólida.intersects(tablero.jugador.áreaSólida)) {
            entidad.colisiónActiva = true;
            contactoConJugador = true;
        }
        // Restaurar las posiciones originales de las áreas sólidas
        entidad.áreaSólida.x = entidad.áreaSólidaPorDefectoX;
        entidad.áreaSólida.y = entidad.áreaSólidaPorDefectoY;
        tablero.jugador.áreaSólida.x = tablero.jugador.áreaSólidaPorDefectoX;
        tablero.jugador.áreaSólida.y = tablero.jugador.áreaSólidaPorDefectoY;

        return contactoConJugador;
    }

}
