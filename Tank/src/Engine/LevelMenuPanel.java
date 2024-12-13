package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// 定义一个名为 LevelMenuPanel 的公共类，继承自 JPanel 类
public class LevelMenuPanel extends JPanel {

    // 定义一个私有的 Image 类型的变量，用于存储背景图片
    private Image backgroundImage;
    // 定义一个私有的 CardLayout 类型的变量，用于管理卡片布局
    private CardLayout cardLayout;
    // 定义一个私有的 JPanel 类型的变量，用于存储卡片面板
    private JPanel cardPanel;
    // 定义一个私有的 GamePanel 类型的变量，用于存储游戏面板
    private GamePanel gamePanel;


        /**
     * 构造函数，初始化级别菜单面板
     *
     * @param cardLayout 卡片布局管理器，用于在不同面板之间切换
     * @param cardPanel 卡片面板，包含所有需要切换的面板
     */
    public LevelMenuPanel(CardLayout cardLayout, JPanel cardPanel) {
        // 加载背景图片
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // 初始化卡片布局管理器
        this.cardLayout = cardLayout;
        // 初始化卡片面板
        this.cardPanel = cardPanel;

        // 设置布局为网格袋布局
        this.setLayout(new GridBagLayout());
        // 创建网格袋布局的约束对象
        GridBagConstraints gbc = new GridBagConstraints();
        // 设置按钮之间的间距
        gbc.insets = new Insets(10, 0, 10, 0);
        // 设置中文字体
        Font chineseFont = new Font("宋体", Font.BOLD, 48);

        // 创建级别1按钮
        JButton level1Button = ButtonFactory.createButton("级别1",chineseFont,Color.YELLOW);
        // 为级别1按钮添加动作监听器
        level1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏级别为级别1
                GameFrame.gameLevel = "Level1";
                // 在卡片面板中显示游戏面板
                cardLayout.show(cardPanel, "GamePanel");
                // 初始化游戏面板
                gamePanel.init();
                // 开始游戏
                gamePanel.GameStart();
            }
        });
        // 设置级别1按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 0;
        // 将级别1按钮添加到当前面板
        this.add(level1Button,gbc);

        // 创建级别2按钮
        JButton level2Button = ButtonFactory.createButton("级别2",chineseFont,Color.YELLOW);
        // 为级别2按钮添加动作监听器
        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏级别为级别2
                GameFrame.gameLevel = "Level2";
                // 在卡片面板中显示游戏面板
                cardLayout.show(cardPanel, "GamePanel");
                // 初始化游戏面板
                gamePanel.init();
                // 开始游戏
                gamePanel.GameStart();
            }
        });
        // 设置级别2按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 1;
        // 将级别2按钮添加到当前面板
        this.add(level2Button,gbc);

        // 创建级别3按钮
        JButton level3Button = ButtonFactory.createButton("级别3",chineseFont,Color.YELLOW);
        // 为级别3按钮添加动作监听器
        level3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏级别为级别3
                GameFrame.gameLevel = "Level3";
                // 在卡片面板中显示游戏面板
                cardLayout.show(cardPanel, "GamePanel");
                // 初始化游戏面板
                gamePanel.init();
                // 开始游戏
                gamePanel.GameStart();
            }
        });
        // 设置级别3按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 2;
        // 将级别3按钮添加到当前面板
        this.add(level3Button,gbc);

        // 创建级别4按钮
        JButton level4Button = ButtonFactory.createButton("级别4",chineseFont,Color.YELLOW);
        // 为级别4按钮添加动作监听器
        level4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏级别为级别4
                GameFrame.gameLevel = "Level4";
                // 在卡片面板中显示游戏面板
                cardLayout.show(cardPanel, "GamePanel");
                // 初始化游戏面板
                gamePanel.init();
                // 开始游戏
                gamePanel.GameStart();
            }
        });
        // 设置级别4按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 3;
        // 将级别4按钮添加到当前面板
        this.add(level4Button,gbc);

        // 创建返回按钮
        JButton backButton = ButtonFactory.createButton("返回",chineseFont,Color.YELLOW);
        // 为返回按钮添加动作监听器
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在卡片面板中显示地图菜单面板
                cardLayout.show(cardPanel, "MapMenuPanel");
            }
        });
        // 设置返回按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 4;
        // 将返回按钮添加到当前面板
        this.add(backButton,gbc);
    }


        /**
     * 重写paintComponent方法，用于绘制背景图片
     *
     * @param g 图形对象
     */
    @Override
    protected void paintComponent(Graphics g) {
        // 调用父类的paintComponent方法，以确保正确的绘制顺序和清除背景
        super.paintComponent(g);
        // 在面板上绘制背景图片，从(0,0)开始，填充整个面板的宽度和高度
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }


        /**
     * 设置游戏面板
     *
     * @param gamePanel 要设置的游戏面板
     */
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}

