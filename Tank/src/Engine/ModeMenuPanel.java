package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// 定义一个名为 ModeMenuPanel 的公共类，继承自 JPanel 类
public class ModeMenuPanel extends JPanel {

    // 定义一个私有的 Image 类型的变量 backgroundImage，用于存储背景图片
    private Image backgroundImage;
    // 定义一个私有的 CardLayout 类型的变量 cardLayout，用于存储卡片布局管理器
    private CardLayout cardLayout;
    // 定义一个私有的 JPanel 类型的变量 cardPanel，用于存储卡片面板
    private JPanel cardPanel;


    public ModeMenuPanel(CardLayout cardLayout, JPanel cardPanel,int width,int height) {
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        // 设置面板的大小
        this.setPreferredSize(new Dimension(width,height));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // 设置按钮之间的间距
        // 设置中文字体
        Font chineseFont = new Font("宋体", Font.BOLD, 48);

        //单人对战
        JButton singleButton = ButtonFactory.createButton("单人对战", chineseFont, Color.YELLOW);
        // 为单人对战按钮添加动作监听器
        singleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏模式为单人模式
                GameFrame.gameMode = "Single";
                // 显示地图选择面板
                cardLayout.show(cardPanel, "MapMenuPanel");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(singleButton, gbc);

        //双人对战
        JButton doublePlayerButton = ButtonFactory.createButton("双人对战", chineseFont, Color.YELLOW);
        // 为双人对战按钮添加动作监听器
        doublePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏模式为双人对战模式
                GameFrame.gameMode = "Versus";
                // 显示地图选择面板
                cardLayout.show(cardPanel, "MapMenuPanel");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(doublePlayerButton, gbc);

        //双人合作
        JButton cooperateButton = ButtonFactory.createButton("双人合作", chineseFont, Color.YELLOW);
        // 为双人合作按钮添加动作监听器
        cooperateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏模式为双人合作模式
                GameFrame.gameMode = "Cooperative";
                // 显示地图选择面板
                cardLayout.show(cardPanel, "MapMenuPanel");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(cooperateButton, gbc);

        //退出游戏
        JButton exitButton = ButtonFactory.createButton("退出游戏", chineseFont, Color.YELLOW);
        // 为退出游戏按钮添加动作监听器
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 退出程序
                System.exit(0);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(exitButton, gbc);
    }


    /**
     * 重写 paintComponent 方法，用于绘制背景图片
     *
     * @param g 图形对象
     */
    @Override
    protected void paintComponent(Graphics g) {
        // 调用父类的 paintComponent 方法，以确保正确的绘制顺序和清除背景
        super.paintComponent(g);
        // 在面板上绘制背景图片，从 (0,0) 坐标开始，填充整个面板的宽度和高度
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}


