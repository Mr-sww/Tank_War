package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.*;

public class SettingsPanel extends JFrame {
    private JCheckBox bgmCheckBox;
    private JComboBox<String> bgmComboBox;
    private JSlider volumeSlider;
    private JButton saveButton;

    private SoundManager soundManager;

    public SettingsPanel(SoundManager soundManager) {
        this.soundManager = soundManager;
        setTitle("�������");
        setSize(300*2, 200*2);
        setIconImage(ResourceManager.loadImage("/Images/setting.png"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Font chineseFont = new Font("mplus_hzk_12", Font.PLAIN, 30);

        // �������
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // �����������ֿ���
        bgmCheckBox = new JCheckBox("���ű�������");
        bgmCheckBox.setFont(chineseFont);
        bgmCheckBox.setPreferredSize(new Dimension(100, 100));
        bgmCheckBox.setSelected(soundManager.isBGMEnabled());

        // ������������ѡ��������
        String[] bgmOptions = {"��������1", "��������2", "��������3"};
        bgmComboBox = new JComboBox<>(bgmOptions);
        bgmComboBox.setFont(chineseFont);
        bgmComboBox.setSelectedIndex(soundManager.getSelectedBGMIndex());

        // ������������
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (soundManager.getVolume() * 100));
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        // �������水ť
        saveButton = new JButton("��������");
        saveButton.setFont(chineseFont);

        // �����������
        panel.add(LabelFactory.createLabel("�������ֿ���:",chineseFont));
        panel.add(bgmCheckBox);
        panel.add(LabelFactory.createLabel("ѡ�񱳾�����:",chineseFont));
        panel.add(bgmComboBox);
        panel.add(LabelFactory.createLabel("��������:",chineseFont));
        panel.add(volumeSlider);
        panel.add(new JLabel());
        panel.add(saveButton);

        // ��ӱ��水ť���¼�������
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSettings();
            }
        });

        add(panel);
    }

    private void saveSettings() {
        boolean isBGMEnabled = bgmCheckBox.isSelected();
        int selectedBGMIndex = bgmComboBox.getSelectedIndex();
        float volume = volumeSlider.getValue() / 100.0f;

        soundManager.setBGMEnabled(isBGMEnabled);
        soundManager.setSelectedBGMIndex(selectedBGMIndex);
        soundManager.setVolume(volume);

        // �������õ��ļ������ݿ�
        saveToPreferences(isBGMEnabled, selectedBGMIndex, volume);

        // �ر��������
        dispose();
    }

    private void saveToPreferences(boolean isBGMEnabled, int selectedBGMIndex, float volume) {
        // �������õ��ļ������ݿ�
        // ����ʹ��Java��ƫ������API��Ϊʾ��
        Preferences prefs = Preferences.userNodeForPackage(SettingsPanel.class);
        prefs.putBoolean("isBGMEnabled", isBGMEnabled);
        prefs.putInt("selectedBGMIndex", selectedBGMIndex);
        prefs.putFloat("volume", volume);
    }

    public void loadSettings() {
        // ���ļ������ݿ��������
        // ����ʹ��Java��ƫ������API��Ϊʾ��
        Preferences prefs = Preferences.userNodeForPackage(SettingsPanel.class);
        boolean isBGMEnabled = prefs.getBoolean("isBGMEnabled", true);
        int selectedBGMIndex = prefs.getInt("selectedBGMIndex", 0);
        float volume = prefs.getFloat("volume", 1.0f);

        // ����UI�����״̬
        bgmCheckBox.setSelected(isBGMEnabled);
        bgmComboBox.setSelectedIndex(selectedBGMIndex);
        volumeSlider.setValue((int) (volume * 100));
    }
}
class LabelFactory{
    public static JLabel createLabel(String text,Font font){
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }
}
