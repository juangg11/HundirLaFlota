import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sonido {
    private Clip clip;

    public void cargarSonido(String ruta) {
        try {
            File archivoSonido = new File(ruta);
            if (archivoSonido.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoSonido);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            } else {
                System.out.println("El archivo de sonido no existe");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void reproducir() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void cerrar() {
        if (clip != null) {
            clip.close();
        }
    }
}