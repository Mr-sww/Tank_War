package Engine;

import javax.swing.*;
import java.awt.*;

public class GameMapMenu extends JDialog {

    private Image backgroundImage; // 背景图片

    public GameMapMenu(Frame parent) {
        super(parent, "选择游戏地图", true); // 设置为模态窗口
        setSize(800, 600); // 调整窗口大小
        setLocationRelativeTo(parent);

        // 加载背景图片
        backgroundImage = new ImageIcon("src/Images/StartMenu.png").getImage(); // 替换为实际路径

        // 自定义面板
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // 标题
        JLabel title = new JLabel("选择游戏地图", JLabel.CENTER);
        title.setFont(new Font("宋体", Font.BOLD, 32)); // 调整字体大小
        title.setForeground(Color.WHITE); // 确保文字在背景上清晰可见
        backgroundPanel.add(title, BorderLayout.NORTH);

        // 地图按钮面板
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // 设置按钮面板透明
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // 增加按钮间距
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 添加各个地图按钮
        addMapButton(buttonPanel, "关卡 1", 1, gbc, 0);
        addMapButton(buttonPanel, "关卡 2", 2, gbc, 1);
        addMapButton(buttonPanel, "关卡 3", 3, gbc, 2);
        addMapButton(buttonPanel, "关卡 4", 4, gbc, 3);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // 退出按钮
        JButton exitButton = createTransparentButton("退出游戏");
        exitButton.setFont(new Font("宋体", Font.BOLD, 20)); // 增加退出按钮字体大小
        exitButton.addActionListener(e -> System.exit(0));
        backgroundPanel.add(exitButton, BorderLayout.SOUTH);
    }

    private void addMapButton(JPanel panel, String label, int map, GridBagConstraints gbc, int yPos) {
        JButton button = createTransparentButton(label);
        button.setFont(new Font("宋体", Font.BOLD, 24)); // 调整按钮字体大小
        button.addActionListener(e -> {
            GameFrame.MapLevel = map; // 设置静态变量 MapLevel 为选中的地图编号
            dispose(); // 关闭菜单
        });
        gbc.gridy = yPos;
        gbc.weightx = 1; // 设置按钮水平填充
        panel.add(button, gbc);
    }

    private JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("宋体", Font.BOLD, 20)); // 增大字体
        button.setContentAreaFilled(false); // 去掉按钮背景
        button.setFocusPainted(false); // 去掉焦点边框
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2)); // 黄色边框，增强对比
        button.setForeground(Color.YELLOW); // 黄色文字
        return button;
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
