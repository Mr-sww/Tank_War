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
        setTitle("设置面板");
        setSize(300*2, 200*2);
        setIconImage(ResourceManager.loadImage("/Images/setting.png"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Font chineseFont = new Font("mplus_hzk_12", Font.PLAIN, 30);

        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // 创建背景音乐开关
        bgmCheckBox = new JCheckBox("播放背景音乐");
        bgmCheckBox.setFont(chineseFont);
        bgmCheckBox.setPreferredSize(new Dimension(100, 100));
        bgmCheckBox.setSelected(soundManager.isBGMEnabled());

        // 创建背景音乐选择下拉框
        String[] bgmOptions = {"背景音乐1", "背景音乐2", "背景音乐3"};
        bgmComboBox = new JComboBox<>(bgmOptions);
        bgmComboBox.setFont(chineseFont);
        bgmComboBox.setSelectedIndex(soundManager.getSelectedBGMIndex());

        // 创建音量滑块
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (soundManager.getVolume() * 100));
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        // 创建保存按钮
        saveButton = new JButton("保存设置");
        saveButton.setFont(chineseFont);

        // 添加组件到面板
        panel.add(LabelFactory.createLabel("背景音乐开关:",chineseFont));
        panel.add(bgmCheckBox);
        panel.add(LabelFactory.createLabel("选择背景音乐:",chineseFont));
        panel.add(bgmComboBox);
        panel.add(LabelFactory.createLabel("调整音量:",chineseFont));
        panel.add(volumeSlider);
        panel.add(new JLabel());
        panel.add(saveButton);

        // 添加保存按钮的事件监听器
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

        // 保存设置到文件或数据库
        saveToPreferences(isBGMEnabled, selectedBGMIndex, volume);

        // 关闭设置面板
        dispose();
    }

    private void saveToPreferences(boolean isBGMEnabled, int selectedBGMIndex, float volume) {
        // 保存设置到文件或数据库
        // 这里使用Java的偏好设置API作为示例
        Preferences prefs = Preferences.userNodeForPackage(SettingsPanel.class);
        prefs.putBoolean("isBGMEnabled", isBGMEnabled);
        prefs.putInt("selectedBGMIndex", selectedBGMIndex);
        prefs.putFloat("volume", volume);
    }

    public void loadSettings() {
        // 从文件或数据库加载设置
        // 这里使用Java的偏好设置API作为示例
        Preferences prefs = Preferences.userNodeForPackage(SettingsPanel.class);
        boolean isBGMEnabled = prefs.getBoolean("isBGMEnabled", true);
        int selectedBGMIndex = prefs.getInt("selectedBGMIndex", 0);
        float volume = prefs.getFloat("volume", 1.0f);

        // 更新UI组件的状态
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
