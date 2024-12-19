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
    // 存储已加载的音频剪辑的映射
    private Map<String, Clip> clips = new HashMap<>();
    // 是否启用背景音乐的标志
    private boolean isBGMEnabled = true;
    // 当前选中的背景音乐索引
    private int selectedBGMIndex = 0;
    // 音量大小
    private float volume = 1.0f;

    // 当前正在播放的背景音乐剪辑
    private Clip currentBGMClip;

    // 单例模式的实例
    private static SoundManager instance;

    // 私有构造函数，防止外部实例化
    private SoundManager() {}

    // 获取单例实例的方法
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * 从指定的文件路径加载音频文件，并将其存储在 clips 映射中，以便后续播放。
     *
     * @param fileName 音频文件的路径。
     * @param key      用于存储音频剪辑的键。
     */
    public void loadSound(String fileName, String key) {
        try {
            // 创建一个 File 对象，指向指定的文件名
            File soundFile = ResourceManager.loadFile(fileName);
            // 使用 AudioSystem 获取音频输入流
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // 获取一个音频剪辑对象
            Clip clip = AudioSystem.getClip();
            // 打开音频输入流，准备播放
            clip.open(audioIn);
            // 将音频剪辑存储在 clips 映射中，键为 key
            clips.put(key, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // 打印异常堆栈跟踪
            e.printStackTrace();
        }
    }

    /**
     * 播放指定键的音频剪辑。
     *
     * @param key 音频剪辑的键。
     */
    public void playSound(String key) {
        Clip clip = clips.get(key);
        if (clip == null) return;
        stopSound(key);
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * 停止指定键的音频剪辑。
     *
     * @param key 音频剪辑的键。
     */
    public void stopSound(String key) {
        Clip clip = clips.get(key);
        if (clip != null) {
            clip.stop();
        }
    }

    /**
     * 循环播放指定键的音频剪辑。
     *
     * @param key 音频剪辑的键。
     */
    public void loopSound(String key) {
        Clip clip = clips.get(key);
        if (clip == null) return;
        stopSound(key);
        clip.setFramePosition(0);
        setVolume(clip, volume);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        currentBGMClip = clip;
    }

    /**
     * 停止所有正在播放的音频剪辑。
     */
    public void stopAllSounds() {
        for (Clip clip : clips.values()) {
            clip.stop();
        }
    }

    /**
     * 设置是否启用背景音乐。
     *
     * @param enabled 是否启用背景音乐。
     */
    public void setBGMEnabled(boolean enabled) {
        if (isBGMEnabled == enabled) return;
        isBGMEnabled = enabled;
        if (isBGMEnabled) {
            playSelectedBGM();
        } else {
            stopBGM();
        }
    }

    /**
     * 检查是否启用背景音乐。
     *
     * @return 如果启用背景音乐，则返回 true，否则返回 false。
     */
    public boolean isBGMEnabled() {
        return isBGMEnabled;
    }

    /**
     * 设置选中的背景音乐索引。
     *
     * @param index 选中的背景音乐索引。
     */
    public void setSelectedBGMIndex(int index) {
        stopBGM();
        selectedBGMIndex = index;
        if (isBGMEnabled) {
            playSelectedBGM();
        }
    }

    /**
     * 获取选中的背景音乐索引。
     *
     * @return 选中的背景音乐索引。
     */
    public int getSelectedBGMIndex() {
        return selectedBGMIndex;
    }

    /**
     * 设置音量大小。
     *
     * @param volume 音量大小，范围从 0.0f 到 1.0f。
     */
    public void setVolume(float volume) {
        this.volume = volume;
        if (isBGMEnabled && currentBGMClip != null) {
            setVolume(currentBGMClip, volume);
        }
    }

    /**
     * 获取当前音量大小。
     *
     * @return 当前音量大小，范围从 0.0f 到 1.0f。
     */
    public float getVolume() {
        return volume;
    }

    /**
     * 播放选中的背景音乐。
     */
    public void playSelectedBGM() {
        String key = "BGM" + (selectedBGMIndex + 1);
        loopSound(key);
    }

    /**
     * 停止当前正在播放的背景音乐。
     */
    public void stopBGM() {
        if (currentBGMClip != null) {
            currentBGMClip.stop();
        }
    }

        /**
     * 设置指定音频剪辑的音量。
     *
     * @param clip   要设置音量的音频剪辑。
     * @param volume 音量大小，范围从 0.0f 到 1.0f。
     */
    public void setVolume(Clip clip, float volume) {
        // 获取音频剪辑的主增益控制器
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        // 将音量从线性范围转换为对数范围
        float logarithmicVolume = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        // 设置增益控制器的值，即设置音量
        gainControl.setValue(logarithmicVolume);
    }

}

