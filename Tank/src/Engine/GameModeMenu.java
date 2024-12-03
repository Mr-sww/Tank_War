package Engine;

import javax.swing.*;
import java.awt.*;

public class GameModeMenu extends JDialog {

    private String selectedMode = null; // 保存玩家选择的对战模式
    private Image backgroundImage; // 背景图片

    public GameModeMenu(Frame parent) {
        super(parent, "选择对战模式", true); // 设置为模态窗口
        setSize(800, 600); // 设置窗口大小，确保按钮足够显示
        setLocationRelativeTo(parent);

        // 加载背景图片
        backgroundImage = new ImageIcon("src/Images/2.png").getImage(); // 替换为实际路径

        // 自定义面板
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // 标题
        JLabel title = new JLabel("选择对战模式", JLabel.CENTER);
        title.setFont(new Font("宋体", Font.BOLD, 32)); // 调整标题字体大小
        title.setForeground(Color.WHITE); // 文字颜色
        backgroundPanel.add(title, BorderLayout.NORTH);

        // 模式按钮面板
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // 设置透明
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // 增加按钮间距，避免按钮挤在一起
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 按钮位置与尺寸调整
        addModeButton(buttonPanel, "单人对战", "single", gbc, 0);
        addModeButton(buttonPanel, "双人合作", "cooperative", gbc, 1);
        addModeButton(buttonPanel, "双人对战", "versus", gbc, 2);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        // 退出按钮
        JButton exitButton = createTransparentButton("退出游戏");
        exitButton.setFont(new Font("宋体", Font.BOLD, 22)); // 调整退出按钮的大小
        exitButton.addActionListener(e -> System.exit(0));
        backgroundPanel.add(exitButton, BorderLayout.SOUTH);
    }

    private void addModeButton(JPanel panel, String label, String mode, GridBagConstraints gbc, int yPos) {
        JButton button = createTransparentButton(label);
        button.setFont(new Font("宋体", Font.BOLD, 24)); // 调整按钮的字体大小
        button.addActionListener(e -> {
            selectedMode = mode; // 保存选择的模式
            dispose(); // 关闭菜单
        });
        gbc.gridy = yPos;
        gbc.weightx = 1; // 增加按钮的水平扩展
        panel.add(button, gbc);
    }

    private JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("宋体", Font.BOLD, 20)); // 增大字体
        button.setContentAreaFilled(false); // 去掉按钮背景
        button.setFocusPainted(false); // 去掉焦点边框
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2)); // 黄色边框，增强对比
        button.setForeground(Color.YELLOW); // 黄色文字，清晰可见
        return button;
    }

    public String getSelectedMode() {
        return selectedMode;
    }

    // 自定义面板，用于绘制背景图片
    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK); // 默认黑色背景
            g.fillRect(0, 0, getWidth(), getHeight());
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // 拉伸背景图
            } else {
                System.out.println("背景图片加载失败！");
            }
        }
    }
}
