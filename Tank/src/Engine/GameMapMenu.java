package Engine;

import javax.swing.*;
import java.awt.*;

public class GameMapMenu extends JDialog {

    private Image backgroundImage; // 背景图片

    public GameMapMenu(Frame parent) {
        super(parent, "选择游戏地图", true); // 设置为模态窗口
        setSize(800, 600); // 调整窗口大小
        setLocationRelativeTo(parent);
        setResizable(false); // 禁止调整窗口大小

        // 加载背景图片
        backgroundImage = new ImageIcon("src/Images/StartMenu.png").getImage(); // 替换为实际路径

        // 自定义面板
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null); // 使用绝对布局
        setContentPane(backgroundPanel);

        // 标题
        JLabel title = new JLabel("选择游戏地图", JLabel.CENTER);
        title.setFont(new Font("宋体", Font.BOLD, 32)); // 调整字体大小
        title.setForeground(Color.WHITE); // 确保文字在背景上清晰可见
        title.setBounds(0, 30, 800, 40); // 设置标题位置和大小
        backgroundPanel.add(title);

        // 地图按钮
        String[] maps = {"关卡 1", "关卡 2", "关卡 3", "关卡 4"};
        int buttonWidth = 200;
        int buttonHeight = 50;
        int totalButtons = maps.length;
        int spacing = 20; // 按钮之间的垂直间距
        int totalHeight = totalButtons * buttonHeight + (totalButtons - 1) * spacing;
        int startX = (800 - buttonWidth) / 2; // 水平居中

        // 调整 startY 以将按钮组向上移动
        int desiredCenterY = 250; // 期望按钮组的中心Y坐标，根据需要调整
        int startY = desiredCenterY - (totalHeight / 2); // 计算按钮组的起始Y坐标

        for (int i = 0; i < totalButtons; i++) {
            JButton button = createTransparentButton(maps[i]);
            button.setFont(new Font("宋体", Font.BOLD, 24)); // 调整按钮字体大小
            int x = startX;
            int y = startY + i * (buttonHeight + spacing);
            button.setBounds(x, y, buttonWidth, buttonHeight);
            final int mapNumber = i + 1; // 地图编号
            button.addActionListener(e -> {
                GameFrame.MapLevel = mapNumber; // 设置静态变量 MapLevel 为选中的地图编号
                dispose(); // 关闭菜单
            });
            backgroundPanel.add(button);
        }

        // 退出按钮
        JButton exitButton = createTransparentButton("退出游戏");
        exitButton.setFont(new Font("宋体", Font.BOLD, 20)); // 增加退出按钮字体大小
        // 根据按钮组的新位置，调整退出按钮的Y坐标
        exitButton.setBounds((800 - 200) / 2, startY + totalHeight + 30, 200, 50); // 设置退出按钮的位置和大小
        exitButton.addActionListener(e -> System.exit(0));
        backgroundPanel.add(exitButton);
    }

    private JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("宋体", Font.BOLD, 22)); // 增大字体
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
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // 拉伸背景图
            } else {
                g.setColor(Color.BLACK); // 默认黑色背景
                g.fillRect(0, 0, getWidth(), getHeight());
                System.out.println("背景图片加载失败！");
            }
        }
    }
}
