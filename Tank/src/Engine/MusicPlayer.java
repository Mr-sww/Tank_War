package Engine;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 音乐播放器类，用于播放音频文件
 */
public class MusicPlayer {
    private Clip clip;

    /**
     * 构造函数，用于初始化音乐播放器
     *
     * @param filePath 音频文件的路径
     */
    public MusicPlayer(String filePath) {
        try {
            // 获取音频输入流
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // 获取音频格式和信息
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // 获取并打开剪辑
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            // 打印异常堆栈跟踪
            ex.printStackTrace();
        }
    }

    /**
     * 播放音乐（只播放一次）
     */
    public void playOnce() {
        if (clip != null) {
            clip.start();
        }
    }

    /**
     * 循环播放音乐
     */
    public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    /**
     * 停止播放音乐
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
