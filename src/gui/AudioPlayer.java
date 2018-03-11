package gui;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/* 
 * Audioplayer for game sounds. For now, it's only for background music
 * Because of continuous looping, needs modification to be used for basic game sounds
 * Accepted fileformat: wav
 */

public class AudioPlayer {

    private Clip clip;
    private URL url;

    public AudioPlayer(String path) {
        try {
            url = getClass().getResource(path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            AudioFormat format = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(), format.getChannels() * 2, format.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


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
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }
    public void close() {
        stop();
        clip.close();
    }

}
