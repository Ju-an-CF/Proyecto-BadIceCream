package sonido;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
    private Clip clip;
    private URL soundURL[] = new URL[30];

    /**
     * Constructor de la clase Sonido.
     * Configura los diferentes sonidos asociados a índices específicos.
     * Este constructor inicializa los sonidos con nombres predefinidos.
     */
    public Sonido() {
        configurarSonido(0, "Die");       // Sonido de muerte
        configurarSonido(1, "Fruit");     // Sonido de fruta
        configurarSonido(2, "Gamemusic"); // Música del juego
        configurarSonido(3, "IceDestroy");// Sonido de destrucción de hielo
        configurarSonido(4, "LoseMusic"); // Música de pérdida
        configurarSonido(5, "MenuMusic"); // Música del menú
        configurarSonido(6, "WinMusic");  // Música de victoria
    }

    /**
     * Configura un sonido en un índice específico.
     *
     * @param índice          El índice donde se configurará el sonido.
     * @param direcciónSonido La dirección del archivo de sonido.
     */
    public void configurarSonido(int índice, String direcciónSonido){
        this.soundURL[índice] = getClass().getResource("/fuentes/sounds/" + direcciónSonido + ".wav");
    }

    /**
     * Coloca un archivo de sonido en el clip para reproducir.
     *
     * @param índiceSonido El índice del archivo de sonido en el arreglo de URLs de sonido.
     */
    public void colocarArchivo(int índiceSonido) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[índiceSonido]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
        }
    }

    /**
     * Reproduce el sonido asociado al clip.
     * Este método inicia la reproducción del clip de sonido.
     */
    public void reproducir() {
        clip.start();
    }

    public void entrarEnBucle() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Para el sonido asociado al clip.
     */
    public void parar() {
        clip.stop();
    }

}
