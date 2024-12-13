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
 * ���ֲ������࣬���ڲ�����Ƶ�ļ�
 */
public class MusicPlayer {
    private Clip clip;

    /**
     * ���캯�������ڳ�ʼ�����ֲ�����
     *
     * @param filePath ��Ƶ�ļ���·��
     */
    public MusicPlayer(String filePath) {
        try {
            // ��ȡ��Ƶ������
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // ��ȡ��Ƶ��ʽ����Ϣ
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // ��ȡ���򿪼���
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            // ��ӡ�쳣��ջ����
            ex.printStackTrace();
        }
    }

    /**
     * �������֣�ֻ����һ�Σ�
     */
    public void playOnce() {
        if (clip != null) {
            clip.start();
        }
    }

    /**
     * ѭ����������
     */
    public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    /**
     * ֹͣ��������
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
