package gui;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/** 
 * Audioplayer for game sounds.
 * For now, only for background music.
 * Accepted file format: wav
 * @author Maks Turtiainen
 */

public class AudioPlayer {

    private Clip clip;
    private URL url;

    public AudioPlayer(String path) {
        try {
            url = getClass().getResource(path);
            final AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            final AudioFormat format = ais.getFormat();
            final AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(), format.getChannels() * 2, format.getSampleRate(), false);
            final AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
            final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
        } catch(final Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Starts new thread for the background music
     * @throws Exception
     */
    public void play() throws Exception {
        if (clip == null) {
            return;
        }
        stop();
        clip.setFramePosition(0);

        /* Starts new thread and loops sound forewer */

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        Thread.sleep(10000);
    }

    /**
     * Stops playing audio
     */
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Closes clip
     */
    public void close() {
        stop();
        clip.close();
    }
}
