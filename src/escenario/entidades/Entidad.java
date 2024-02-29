package escenario.entidades;

import escenario.Tablero;
import escenario.HerramientaUtilidad;
import mecánicas.Dirección;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entidad {

    private Tablero tablero;
    //Coordenadas del jugador
    private int mundoX;
    private int mundoY;
    //Velocidad de la entidad
    private int velocidad;
    private String nombre;
    private boolean rompiendo;
    private boolean colisiónActiva;

    private Rectangle áreaSólida = new Rectangle(0, 0, 42, 42);

    private int áreaSólidaPorDefectoY;
    private int áreaSólidaPorDefectoX;
    //Imágenes de la entidad
    public BufferedImage arriba1, arriba2, arriba3, arriba4, abajo1, abajo2, abajo3, abajo4, izquierda1, izquierda2, izquierda3, izquierda4, derecha1, derecha2, derecha3, derecha4;
    //Dirección por defecto
    public Dirección dirección = Dirección.ABAJO;

    public int contadorMovimiento = 0;
    public int numeroDeMovimiento = 1;
    public int contadorBloqueoDeAcción = 0;

    //Imágenes de los objetos en pantalla
    public BufferedImage imagen1, imagen2, imagen3, imagen4, imagen5;
    public Entidad(Tablero tablero) {
        this.setTablero(tablero);
    }

    public void establecerAcción() {
    }

    public void actualizar() {

        establecerAcción();
        setColisiónActiva(false);
        getTablero().getVerificadorDeColisión().verificarBloque(this);
        getTablero().getVerificadorDeColisión().verificarObjeto(this, false);
        getTablero().getVerificadorDeColisión().verificarEntidad(this, getTablero().getEnemigos());
        boolean contactoConJugador = getTablero().getVerificadorDeColisión().verificarJugador(this);

        if (contactoConJugador) {
            if (!getTablero().getJugador().isInvencible()) {
                getTablero().getJugador().setVida(getTablero().getJugador().getVida() - 1);
                getTablero().getJugador().setInvencible(true);
            }
        }

        //entidad puede moverse
        if (!isColisiónActiva()) {
            switch (dirección) {
                case ARRIBA:
                    setMundoY(getMundoY() - getVelocidad());
                    break;
                case ABAJO:
                    setMundoY(getMundoY() + getVelocidad());
                    break;
                case IZQUIERDA:
                    setMundoX(getMundoX() - getVelocidad());
                    break;
                case DERECHA:
                    setMundoX(getMundoX() + getVelocidad());
                    break;
            }
        }
        contadorMovimiento++;
        if (contadorMovimiento > 10) {
            if (numeroDeMovimiento == 1) {
                numeroDeMovimiento = 2;
            } else if (numeroDeMovimiento == 2) {
                numeroDeMovimiento = 1;
            }
            contadorMovimiento = 0;
        }
    }

    public BufferedImage configurarImagen(String nombreImagen) {
        HerramientaUtilidad uTool = new HerramientaUtilidad();
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(nombreImagen + ".png"));
            imagen = uTool.scaledImage(imagen, getTablero().TAMAÑO_DE_BLOQUE, getTablero().TAMAÑO_DE_BLOQUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }

    public void dibujar(Graphics2D graphics2D) {
        BufferedImage imagen = null;

        switch (dirección) {
            case ARRIBA:
                if (numeroDeMovimiento == 1) {
                    imagen = arriba1;
                }
                if (numeroDeMovimiento == 2) {
                    imagen = arriba2;
                }
                if (numeroDeMovimiento == 3) {
                    imagen = arriba3;
                }
                if (numeroDeMovimiento == 4) {
                    imagen = arriba4;
                }
                break;
            case ABAJO:
                if (numeroDeMovimiento == 1) {
                    imagen = abajo1;
                }
                if (numeroDeMovimiento == 2) {
                    imagen = abajo2;
                }
                if (numeroDeMovimiento == 3) {
                    imagen = abajo3;
                }
                if (numeroDeMovimiento == 4) {
                    imagen = abajo4;
                }
                break;
            case IZQUIERDA:
                if (numeroDeMovimiento == 1) {
                    imagen = izquierda1;
                }
                if (numeroDeMovimiento == 2) {
                    imagen = izquierda2;
                }
                if (numeroDeMovimiento == 3) {
                    imagen = izquierda3;
                }
                if (numeroDeMovimiento == 4) {
                    imagen = izquierda4;
                }
                break;
            case DERECHA:
                if (numeroDeMovimiento == 1) {
                    imagen = derecha1;
                }
                if (numeroDeMovimiento == 2) {
                    imagen = derecha2;
                }
                if (numeroDeMovimiento == 3) {
                    imagen = derecha3;
                }
                if (numeroDeMovimiento == 4) {
                    imagen = derecha4;
                }
                break;
        }
        graphics2D.drawImage(imagen, getMundoX(), getMundoY(), getTablero().TAMAÑO_DE_BLOQUE, getTablero().TAMAÑO_DE_BLOQUE, null);
    }


    public int getMundoX() {
        return mundoX;
    }

    public void setMundoX(int mundoX) {
        this.mundoX = mundoX;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getMundoY() {
        return mundoY;
    }

    public void setMundoY(int mundoY) {
        this.mundoY = mundoY;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRompiendo(boolean rompiendo) {
        this.rompiendo = rompiendo;
    }

    public boolean isColisiónActiva() {
        return colisiónActiva;
    }

    public void setColisiónActiva(boolean colisiónActiva) {
        this.colisiónActiva = colisiónActiva;
    }

    public Rectangle getÁreaSólida() {
        return áreaSólida;
    }

    public void setÁreaSólida(Rectangle áreaSólida) {
        this.áreaSólida = áreaSólida;
    }

    public int getÁreaSólidaPorDefectoX() {
        return áreaSólidaPorDefectoX;
    }

    public void setÁreaSólidaPorDefectoX(int áreaSólidaPorDefectoX) {
        this.áreaSólidaPorDefectoX = áreaSólidaPorDefectoX;
    }

    public int getÁreaSólidaPorDefectoY() {
        return áreaSólidaPorDefectoY;
    }

    public void setÁreaSólidaPorDefectoY(int áreaSólidaPorDefectoY) {
        this.áreaSólidaPorDefectoY = áreaSólidaPorDefectoY;
    }
}
