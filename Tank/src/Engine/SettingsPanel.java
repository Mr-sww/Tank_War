package Engine;

// 导入必要的包
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.*;

/**
 * 定义一个SettingsPanel类，继承自JFrame，用于显示和管理游戏设置。
 */
public class SettingsPanel extends JFrame {
    // 定义复选框，用于控制背景音乐的开关
    private JCheckBox bgmCheckBox;
    // 定义下拉框，用于选择背景音乐
    private JComboBox<String> bgmComboBox;
    // 定义滑块，用于调整音量大小
    private JSlider volumeSlider;
    // 定义按钮，用于保存设置
    private JButton saveButton;

    // 定义SoundManager对象，用于管理游戏的声音
    private SoundManager soundManager;

    /**
     * 构造函数，用于初始化SettingsPanel对象。
     *
     * @param soundManager 声音管理器对象，用于控制游戏的声音。
     */
    public SettingsPanel(SoundManager soundManager) {
        this.soundManager = soundManager;
        // 设置窗口标题
        setTitle("设置面板");
        // 设置窗口大小
        setSize(300*2, 200*2);
        // 设置窗口图标
        setIconImage(ResourceManager.loadImage("/Images/setting.png"));
        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 设置窗口位置为屏幕中央
        setLocationRelativeTo(null);

        // 创建中文字体
        Font chineseFont = new Font("mplus_hzk_12", Font.PLAIN, 30);

        // 创建面板，并设置布局
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // 创建背景音乐开关复选框，并设置字体和初始状态
        bgmCheckBox = new JCheckBox("播放背景音乐");
        bgmCheckBox.setFont(chineseFont);
        bgmCheckBox.setPreferredSize(new Dimension(100, 100));
        bgmCheckBox.setSelected(soundManager.isBGMEnabled());

        // 创建背景音乐选择下拉框，并设置字体和初始选项
        String[] bgmOptions = {"背景音乐1", "背景音乐2", "背景音乐3"};
        bgmComboBox = new JComboBox<>(bgmOptions);
        bgmComboBox.setFont(chineseFont);
        bgmComboBox.setSelectedIndex(soundManager.getSelectedBGMIndex());

        // 创建一个水平的滑块，范围是从0到100，初始值是当前音量乘以100
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (soundManager.getVolume() * 100));
        // 设置滑块的主刻度间隔为25
        volumeSlider.setMajorTickSpacing(25);
        // 设置滑块的次刻度间隔为5
        volumeSlider.setMinorTickSpacing(5);
        // 设置滑块绘制刻度线
        volumeSlider.setPaintTicks(true);
        // 设置滑块绘制刻度标签
        volumeSlider.setPaintLabels(true);


        // 创建保存按钮，并设置字体
        saveButton = new JButton("保存设置");
        saveButton.setFont(chineseFont);

        // 将组件添加到面板中
        panel.add(LabelFactory.createLabel("背景音乐开关:",chineseFont));
        panel.add(bgmCheckBox);
        panel.add(LabelFactory.createLabel("选择背景音乐:",chineseFont));
        panel.add(bgmComboBox);
        panel.add(LabelFactory.createLabel("调整音量:",chineseFont));
        panel.add(volumeSlider);
        panel.add(new JLabel());
        panel.add(saveButton);

        // 为保存按钮添加事件监听器
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSettings();
            }
        });

        // 将面板添加到窗口中
        add(panel);
    }

    /**
     * 保存当前设置到SoundManager对象，并将设置持久化到偏好设置中。
     */
    private void saveSettings() {
        boolean isBGMEnabled = bgmCheckBox.isSelected();
        int selectedBGMIndex = bgmComboBox.getSelectedIndex();
        float volume = volumeSlider.getValue() / 100.0f;

        soundManager.setBGMEnabled(isBGMEnabled);
        soundManager.setSelectedBGMIndex(selectedBGMIndex);
        soundManager.setVolume(volume);

        // 将设置保存到偏好设置中
        saveToPreferences(isBGMEnabled, selectedBGMIndex, volume);

        // 关闭设置面板
        dispose();
    }

    /**
     * 将设置保存到Java偏好设置API中。
     *
     * @param isBGMEnabled 是否启用背景音乐。
     * @param selectedBGMIndex 选中的背景音乐索引。
     * @param volume 音量大小。
     */
    private void saveToPreferences(boolean isBGMEnabled, int selectedBGMIndex, float volume) {
        // 获取当前类的偏好设置节点
        Preferences prefs = Preferences.userNodeForPackage(SettingsPanel.class);
        // 将是否启用背景音乐的设置保存到偏好设置中
        prefs.putBoolean("isBGMEnabled", isBGMEnabled);
        // 将选中的背景音乐索引保存到偏好设置中
        prefs.putInt("selectedBGMIndex", selectedBGMIndex);
        // 将音量大小保存到偏好设置中
        prefs.putFloat("volume", volume);

    }


    /**
     * 从偏好设置中加载设置，并更新UI组件的状态。
     */
    public void loadSettings() {
        // 获取当前类的偏好设置节点
        Preferences prefs = Preferences.userNodeForPackage(SettingsPanel.class);
        // 从偏好设置中获取是否启用背景音乐的设置，默认值为 true
        boolean isBGMEnabled = prefs.getBoolean("isBGMEnabled", true);
        // 从偏好设置中获取选中的背景音乐索引，默认值为 0
        int selectedBGMIndex = prefs.getInt("selectedBGMIndex", 0);
        // 从偏好设置中获取音量大小，默认值为 1.0f
        float volume = prefs.getFloat("volume", 1.0f);

        // 设置背景音乐开关复选框的选中状态
        bgmCheckBox.setSelected(isBGMEnabled);
        // 设置背景音乐选择下拉框的选中索引
        bgmComboBox.setSelectedIndex(selectedBGMIndex);
        // 设置音量滑块的值
        volumeSlider.setValue((int) (volume * 100));

        // 调用 saveSettings 方法保存当前设置
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
