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
    // �洢�Ѽ��ص���Ƶ������ӳ��
    private Map<String, Clip> clips = new HashMap<>();
    // �Ƿ����ñ������ֵı�־
    private boolean isBGMEnabled = true;
    // ��ǰѡ�еı�����������
    private int selectedBGMIndex = 0;
    // ������С
    private float volume = 1.0f;

    // ��ǰ���ڲ��ŵı������ּ���
    private Clip currentBGMClip;

    // ����ģʽ��ʵ��
    private static SoundManager instance;

    // ˽�й��캯������ֹ�ⲿʵ����
    private SoundManager() {}

    // ��ȡ����ʵ���ķ���
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * ��ָ�����ļ�·��������Ƶ�ļ���������洢�� clips ӳ���У��Ա�������š�
     *
     * @param fileName ��Ƶ�ļ���·����
     * @param key      ���ڴ洢��Ƶ�����ļ���
     */
    public void loadSound(String fileName, String key) {
        try {
            // ����һ�� File ����ָ��ָ�����ļ���
            File soundFile = ResourceManager.loadFile(fileName);
            // ʹ�� AudioSystem ��ȡ��Ƶ������
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // ��ȡһ����Ƶ��������
            Clip clip = AudioSystem.getClip();
            // ����Ƶ��������׼������
            clip.open(audioIn);
            // ����Ƶ�����洢�� clips ӳ���У���Ϊ key
            clips.put(key, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // ��ӡ�쳣��ջ����
            e.printStackTrace();
        }
    }

    /**
     * ����ָ��������Ƶ������
     *
     * @param key ��Ƶ�����ļ���
     */
    public void playSound(String key) {
        Clip clip = clips.get(key);
        if (clip == null) return;
        stopSound(key);
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * ָֹͣ��������Ƶ������
     *
     * @param key ��Ƶ�����ļ���
     */
    public void stopSound(String key) {
        Clip clip = clips.get(key);
        if (clip != null) {
            clip.stop();
        }
    }

    /**
     * ѭ������ָ��������Ƶ������
     *
     * @param key ��Ƶ�����ļ���
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
     * ֹͣ�������ڲ��ŵ���Ƶ������
     */
    public void stopAllSounds() {
        for (Clip clip : clips.values()) {
            clip.stop();
        }
    }

    /**
     * �����Ƿ����ñ������֡�
     *
     * @param enabled �Ƿ����ñ������֡�
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
     * ����Ƿ����ñ������֡�
     *
     * @return ������ñ������֣��򷵻� true�����򷵻� false��
     */
    public boolean isBGMEnabled() {
        return isBGMEnabled;
    }

    /**
     * ����ѡ�еı�������������
     *
     * @param index ѡ�еı�������������
     */
    public void setSelectedBGMIndex(int index) {
        stopBGM();
        selectedBGMIndex = index;
        if (isBGMEnabled) {
            playSelectedBGM();
        }
    }

    /**
     * ��ȡѡ�еı�������������
     *
     * @return ѡ�еı�������������
     */
    public int getSelectedBGMIndex() {
        return selectedBGMIndex;
    }

    /**
     * ����������С��
     *
     * @param volume ������С����Χ�� 0.0f �� 1.0f��
     */
    public void setVolume(float volume) {
        this.volume = volume;
        if (isBGMEnabled && currentBGMClip != null) {
            setVolume(currentBGMClip, volume);
        }
    }

    /**
     * ��ȡ��ǰ������С��
     *
     * @return ��ǰ������С����Χ�� 0.0f �� 1.0f��
     */
    public float getVolume() {
        return volume;
    }

    /**
     * ����ѡ�еı������֡�
     */
    public void playSelectedBGM() {
        String key = "BGM" + (selectedBGMIndex + 1);
        loopSound(key);
    }

    /**
     * ֹͣ��ǰ���ڲ��ŵı������֡�
     */
    public void stopBGM() {
        if (currentBGMClip != null) {
            currentBGMClip.stop();
        }
    }

        /**
     * ����ָ����Ƶ������������
     *
     * @param clip   Ҫ������������Ƶ������
     * @param volume ������С����Χ�� 0.0f �� 1.0f��
     */
    public void setVolume(Clip clip, float volume) {
        // ��ȡ��Ƶ�����������������
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        // �����������Է�Χת��Ϊ������Χ
        float logarithmicVolume = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        // ���������������ֵ������������
        gainControl.setValue(logarithmicVolume);
    }

}

