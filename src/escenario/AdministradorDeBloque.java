package escenario;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AdministradorDeBloque  {
    Tablero tablero;
    public Bloque[] bloques;
    public int mapa[][];
    public AdministradorDeBloque(Tablero tablero) {
        this.tablero = tablero;
        bloques=new Bloque[100];
        mapa=new int[tablero.COLUMNAS_MAX][tablero.FILAS_MAX];
        obtenerImagenDeBloque();
        cargarMapa("/escenario/mapa.txt");
    }

    public void obtenerImagenDeBloque(){
        try{
            bloques[0]=new Bloque();
            bloques[0].imagen= ImageIO.read(getClass().getResourceAsStream("/gráficos/bloque/nieve.png"));

            bloques[1]=new Bloque();
            bloques[1].imagen= ImageIO.read(getClass().getResourceAsStream("/gráficos/bloque/esquina1.png"));
            bloques[1].colisión=true;

            bloques[2]=new Bloque();
            bloques[2].imagen= ImageIO.read(getClass().getResourceAsStream("/gráficos/bloque/esquina2.png"));
            bloques[2].colisión=true;

            bloques[3]=new Bloque();
            bloques[3].imagen= ImageIO.read(getClass().getResourceAsStream("/gráficos/bloque/esquina3.png"));
            bloques[3].colisión=true;

            bloques[4]=new Bloque();
            bloques[4].imagen= ImageIO.read(getClass().getResourceAsStream("/gráficos/bloque/esquina4.png"));
            bloques[4].colisión=true;

            bloques[5]=new Bloque();
            bloques[5].imagen= ImageIO.read(getClass().getResourceAsStream("/gráficos/bloque/muro.png"));
            bloques[5].colisión=true;

            bloques[6]=new Bloque();
            bloques[6].imagen= ImageIO.read(getClass().getResourceAsStream("/gráficos/bloque/bolaNieve.png"));

            bloques[7]=new Bloque();
            bloques[7].imagen= ImageIO.read(getClass().getResourceAsStream("/gráficos/bloque/florNieve.png"));

            bloques[8]=new Bloque();
            bloques[8].imagen= ImageIO.read(getClass().getResourceAsStream("/gráficos/bloque/hielo.png"));
            bloques[8].colisión=true;



        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void cargarMapa(String direcciónArchivo){
        try{
            InputStream mapaDeEntrada=getClass().getResourceAsStream(direcciónArchivo);
            BufferedReader br=new BufferedReader(new InputStreamReader(mapaDeEntrada));

            int columna=0;
            int fila=0;

            while(columna< tablero.COLUMNAS_MAX && fila< tablero.FILAS_MAX){
                String line= br.readLine();

                while(columna< tablero.COLUMNAS_MAX) {
                    String numeros[] = line.split(" ");

                    int numero = Integer.parseInt(numeros[columna]);

                    mapa[columna][fila] = numero;
                    columna++;
                }
                if(columna== tablero.COLUMNAS_MAX){
                    columna=0;
                    fila++;
                }

            }
            br.close();
        }catch(Exception e){

        }
    }

    public void dibujar(Graphics2D g2){
        int columnas=0;
        int filas=0;
        int x=0;
        int y=0;

        while(columnas< tablero.COLUMNAS_MAX&&filas<tablero.FILAS_MAX){

            int numBloque=mapa [columnas][filas];
            g2.drawImage(bloques[numBloque].imagen,x,y,tablero.TAMANIO_DE_BLOQUE,tablero.TAMANIO_DE_BLOQUE,null);
            columnas++;
            x+=tablero.TAMANIO_DE_BLOQUE;

            if(columnas==tablero.COLUMNAS_MAX){
                columnas=0;
                x=0;
                filas++;
                y+=tablero.TAMANIO_DE_BLOQUE;
            }
        }
    }
}
