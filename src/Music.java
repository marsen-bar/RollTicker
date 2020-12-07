import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * 音楽再生に関するクラス
 */
public class Music {
    private final Clip clip;

    public Music(String filename){
        URL url = this.getClass().getResource("music/" + filename + ".wav");
        clip = createClip(url);
    }


    public static Clip createClip(URL url) {
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(url)){

            AudioFormat af = ais.getFormat();

            DataLine.Info dataLine = new DataLine.Info(Clip.class,af);

            Clip c = (Clip)AudioSystem.getLine(dataLine);

            c.open(ais);

            return c;
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ignored) {

        }
        return null;
    }

    public static void start(Clip clip){
        clip.start();
    }
    public static void loop(Clip clip){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void stop(Clip clip){
        clip.stop();
    }
    public static void balanceLoop(Clip clip,double rate){
        FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        controlByLinearScalar(control,rate);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void controlByLinearScalar(FloatControl control,double linearScalar){
        control.setValue((float)Math.log10(linearScalar)*20);
    }

    public Clip getClip() {
        return clip;
    }
}
