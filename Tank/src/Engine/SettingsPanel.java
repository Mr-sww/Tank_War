package Engine;

// �����Ҫ�İ�
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.*;

/**
 * ����һ��SettingsPanel�࣬�̳���JFrame��������ʾ�͹�����Ϸ���á�
 */
public class SettingsPanel extends JFrame {
    // ���帴ѡ�����ڿ��Ʊ������ֵĿ���
    private JCheckBox bgmCheckBox;
    // ��������������ѡ�񱳾�����
    private JComboBox<String> bgmComboBox;
    // ���廬�飬���ڵ���������С
    private JSlider volumeSlider;
    // ���尴ť�����ڱ�������
    private JButton saveButton;

    // ����SoundManager�������ڹ�����Ϸ������
    private SoundManager soundManager;

    /**
     * ���캯�������ڳ�ʼ��SettingsPanel����
     *
     * @param soundManager �����������������ڿ�����Ϸ��������
     */
    public SettingsPanel(SoundManager soundManager) {
        this.soundManager = soundManager;
        // ���ô��ڱ���
        setTitle("�������");
        // ���ô��ڴ�С
        setSize(300*2, 200*2);
        // ���ô���ͼ��
        setIconImage(ResourceManager.loadImage("/Images/setting.png"));
        // ���ô��ڹرղ���
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // ���ô���λ��Ϊ��Ļ����
        setLocationRelativeTo(null);

        // ������������
        Font chineseFont = new Font("mplus_hzk_12", Font.PLAIN, 30);

        // ������壬�����ò���
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // �����������ֿ��ظ�ѡ�򣬲���������ͳ�ʼ״̬
        bgmCheckBox = new JCheckBox("���ű�������");
        bgmCheckBox.setFont(chineseFont);
        bgmCheckBox.setPreferredSize(new Dimension(100, 100));
        bgmCheckBox.setSelected(soundManager.isBGMEnabled());

        // ������������ѡ�������򣬲���������ͳ�ʼѡ��
        String[] bgmOptions = {"��������1", "��������2", "��������3"};
        bgmComboBox = new JComboBox<>(bgmOptions);
        bgmComboBox.setFont(chineseFont);
        bgmComboBox.setSelectedIndex(soundManager.getSelectedBGMIndex());

        // ����һ��ˮƽ�Ļ��飬��Χ�Ǵ�0��100����ʼֵ�ǵ�ǰ��������100
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (soundManager.getVolume() * 100));
        // ���û�������̶ȼ��Ϊ25
        volumeSlider.setMajorTickSpacing(25);
        // ���û���Ĵο̶ȼ��Ϊ5
        volumeSlider.setMinorTickSpacing(5);
        // ���û�����ƿ̶���
        volumeSlider.setPaintTicks(true);
        // ���û�����ƿ̶ȱ�ǩ
        volumeSlider.setPaintLabels(true);


        // �������水ť������������
        saveButton = new JButton("��������");
        saveButton.setFont(chineseFont);

        // �������ӵ������
        panel.add(LabelFactory.createLabel("�������ֿ���:",chineseFont));
        panel.add(bgmCheckBox);
        panel.add(LabelFactory.createLabel("ѡ�񱳾�����:",chineseFont));
        panel.add(bgmComboBox);
        panel.add(LabelFactory.createLabel("��������:",chineseFont));
        panel.add(volumeSlider);
        panel.add(new JLabel());
        panel.add(saveButton);

        // Ϊ���水ť����¼�������
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSettings();
            }
        });

        // �������ӵ�������
        add(panel);
    }

    /**
     * ���浱ǰ���õ�SoundManager���󣬲������ó־û���ƫ�������С�
     */
    private void saveSettings() {
        boolean isBGMEnabled = bgmCheckBox.isSelected();
        int selectedBGMIndex = bgmComboBox.getSelectedIndex();
        float volume = volumeSlider.getValue() / 100.0f;

        soundManager.setBGMEnabled(isBGMEnabled);
        soundManager.setSelectedBGMIndex(selectedBGMIndex);
        soundManager.setVolume(volume);

        // �����ñ��浽ƫ��������
        saveToPreferences(isBGMEnabled, selectedBGMIndex, volume);

        // �ر��������
        dispose();
    }

    /**
     * �����ñ��浽Javaƫ������API�С�
     *
     * @param isBGMEnabled �Ƿ����ñ������֡�
     * @param selectedBGMIndex ѡ�еı�������������
     * @param volume ������С��
     */
    private void saveToPreferences(boolean isBGMEnabled, int selectedBGMIndex, float volume) {
        // ��ȡ��ǰ���ƫ�����ýڵ�
        Preferences prefs = Preferences.userNodeForPackage(SettingsPanel.class);
        // ���Ƿ����ñ������ֵ����ñ��浽ƫ��������
        prefs.putBoolean("isBGMEnabled", isBGMEnabled);
        // ��ѡ�еı��������������浽ƫ��������
        prefs.putInt("selectedBGMIndex", selectedBGMIndex);
        // ��������С���浽ƫ��������
        prefs.putFloat("volume", volume);

    }


    /**
     * ��ƫ�������м������ã�������UI�����״̬��
     */
    public void loadSettings() {
        // ��ȡ��ǰ���ƫ�����ýڵ�
        Preferences prefs = Preferences.userNodeForPackage(SettingsPanel.class);
        // ��ƫ�������л�ȡ�Ƿ����ñ������ֵ����ã�Ĭ��ֵΪ true
        boolean isBGMEnabled = prefs.getBoolean("isBGMEnabled", true);
        // ��ƫ�������л�ȡѡ�еı�������������Ĭ��ֵΪ 0
        int selectedBGMIndex = prefs.getInt("selectedBGMIndex", 0);
        // ��ƫ�������л�ȡ������С��Ĭ��ֵΪ 1.0f
        float volume = prefs.getFloat("volume", 1.0f);

        // ���ñ������ֿ��ظ�ѡ���ѡ��״̬
        bgmCheckBox.setSelected(isBGMEnabled);
        // ���ñ�������ѡ���������ѡ������
        bgmComboBox.setSelectedIndex(selectedBGMIndex);
        // �������������ֵ
        volumeSlider.setValue((int) (volume * 100));

        // ���� saveSettings �������浱ǰ����
        saveSettings();
    }

}

class LabelFactory{
    public static JLabel createLabel(String text,Font font){
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }
}
