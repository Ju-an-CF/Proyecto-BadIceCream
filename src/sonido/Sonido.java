package sonido;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sonido() {
        soundURL[0] = getClass().getResource("/fuentes/sounds/Die.wav");
        soundURL[1] = getClass().getResource("/fuentes/sounds/Fruit.wav");
        soundURL[2] = getClass().getResource("/fuentes/sounds/Gamemusic.wav");
        soundURL[3] = getClass().getResource("/fuentes/sounds/IceDestroy.wav");
        soundURL[4] = getClass().getResource("/fuentes/sounds/LoseMusic.wav");
        soundURL[5] = getClass().getResource("/fuentes/sounds/MenuMusic.wav");
        soundURL[6] = getClass().getResource("/fuentes/sounds/WinMusic.wav");
    }

    public void colocarArchivo(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
        }
    }

    public void reproducir() {
        clip.start();
    }

    public void entrarEnBucle() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void parar() {
        clip.stop();
    }

}
