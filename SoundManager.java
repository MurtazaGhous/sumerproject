import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.ArrayList;

public class SoundManager {
    private boolean muted = false;
    private ArrayList<Clip> clips = new ArrayList<Clip>();
    private HashMap<String, Clip> soundClips = new HashMap<>();

    public void preloadSound(String soundFile) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(soundFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedInputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            soundClips.put(soundFile, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSound(String soundFile) {
        if (!muted) {
            Clip clip = soundClips.get(soundFile);
            if (clip == null) {
                System.out.println("Sound clip not found: " + soundFile);
                return;
            }
            clip.setFramePosition(0); // Reset to the beginning
            clips.add(clip); //add clip the the listto use for mute
            try {
                Thread.sleep(20); // Add a small delay before starting the clip
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clip.start();
        }
    }

    public void mute() {
        muted = true;
        for (Clip clip : clips) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }

    public void unmute() {
        muted = false;
    }

}
