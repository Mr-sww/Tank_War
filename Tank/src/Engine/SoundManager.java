package Engine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
    private Map<String, Clip> clips = new HashMap<>();
    private boolean isBGMEnabled = true;
    private int selectedBGMIndex = 0;
    private float volume = 1.0f;

    private Clip currentBGMClip;

    public void loadSound(String fileName, String key) {
        try {
            File soundFile = new File(fileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clips.put(key, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSound(String key) {
        Clip clip = clips.get(key);
        if (clip == null) return;
        stopSound(key);
        clip.setFramePosition(0);
        clip.start();
    }

    public void stopSound(String key) {
        Clip clip = clips.get(key);
        if (clip != null) {
            clip.stop();
        }
    }

    public void loopSound(String key) {
        Clip clip = clips.get(key);
        if (clip == null) return;
        stopSound(key);
        clip.setFramePosition(0);
        setVolume(clip, volume);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        currentBGMClip = clip;
    }

    public void stopAllSounds() {
        for (Clip clip : clips.values()) {
            clip.stop();
        }
    }

    public void setBGMEnabled(boolean enabled) {
        if (isBGMEnabled == enabled) return;
        isBGMEnabled = enabled;
        if (isBGMEnabled) {
            playSelectedBGM();
        } else {
            stopBGM();
        }
    }

    public boolean isBGMEnabled() {
        return isBGMEnabled;
    }

    public void setSelectedBGMIndex(int index) {
        stopBGM();
        selectedBGMIndex = index;
        if (isBGMEnabled) {
            playSelectedBGM();
        }
    }

    public int getSelectedBGMIndex() {
        return selectedBGMIndex;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        if (isBGMEnabled && currentBGMClip != null) {
            setVolume(currentBGMClip, volume);
        }
    }

    public float getVolume() {
        return volume;
    }

    public void playSelectedBGM() {
        String key = "BGM" + (selectedBGMIndex + 1);
        loopSound(key);
    }

    public void stopBGM() {
        if (currentBGMClip != null) {
            currentBGMClip.stop();
        }
    }

    public void setVolume(Clip clip, float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float logarithmicVolume = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        gainControl.setValue(logarithmicVolume);
    }
}
