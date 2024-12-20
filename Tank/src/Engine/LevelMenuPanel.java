package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

// 定义一个名为 LevelMenuPanel 的公共类，继承自 JPanel 类
public class LevelMenuPanel extends JPanel {
    public int button_width=(4*60),button_height=(4*60);

    // 定义一个私有的 Image 类型的变量，用于存储背景图片
    private Image backgroundImage;
    // 定义一个私有的 CardLayout 类型的变量，用于管理卡片布局
    private CardLayout mainCardLayout;
    // 定义一个私有的 JPanel 类型的变量，用于存储卡片面板
    private JPanel mainCardPanel;
    // 定义一个私有的 GamePanel 类型的变量，用于存储游戏面板
    private GamePanel gamePanel;

    private CardLayout sideCardLayout;
    // 定义一个私有的 JPanel 类型的变量，用于存储卡片面板
    private JPanel sideCardPanel;
    // 定义一个私有的 SideGamePanel 类型的变量，用于存储侧边游戏面板
    SideGamePanel sideGamePanel;


    /**
     * 构造函数，初始化级别菜单面板
     *
     * @param mainCardLayout 卡片布局管理器，用于在不同面板之间切换
     * @param mainCardPanel 卡片面板，包含所有需要切换的面板
     */
    public LevelMenuPanel(CardLayout mainCardLayout, JPanel mainCardPanel, CardLayout sideCardLayout, JPanel sideCardPanel,int width, int height) {
        // 加载背景图片
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // 初始化卡片布局管理器
        this.mainCardLayout = mainCardLayout;
        // 初始化卡片面板
        this.mainCardPanel = mainCardPanel;
        // 设置面板的大小
        this.setPreferredSize(new Dimension(width,height));

        this.setLayout(null);
        // 设置中文字体
        Font chineseFont = new Font("mplus_hzk_12", Font.PLAIN, 48);

        int ysapce = 100;

        //创建级别1按钮
        JButton level1Button = ButtonFactory.createButton("级别1",chineseFont,Color.YELLOW);
        level1Button.setBounds(8 * 60, 7 * 60, 5 * 60, 1 * 60);
        ImageLabel label1 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30+30, 7 * 60 - 5, 1 * 60, 1 * 60);
        label1.setVisible(false);
        // 为级别1按钮添加动作监听器
        level1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏级别为级别1
                GameFrame.gameLevel = "Level1";
                // 在卡片面板中显示游戏面板
                mainCardLayout.show(mainCardPanel, "GamePanel");
                // 初始化游戏面板
                gamePanel.init();
                sideGamePanel.init();
                // 开始游戏
                gamePanel.GameStart();

                sideCardLayout.show(sideCardPanel, "SideGamePanel");
            }
        });
        // 设置按钮的鼠标监听器
        level1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label1.setVisible(true);
                GameFrame.soundManager.playSound("select");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label1.setVisible(false);
            }
        });
        this.add(level1Button);
        this.add(label1);


        // 创建级别2按钮
        JButton level2Button = ButtonFactory.createButton("级别2",chineseFont,Color.YELLOW);
        level2Button.setBounds(8 * 60, 7 * 60 + 1 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label2 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30+30, 7 * 60 + 1 * ysapce - 5, 1 * 60, 1 * 60);
        label2.setVisible(false);
        // 为级别2按钮添加动作监听器
        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏级别为级别2
                GameFrame.gameLevel = "Level2";
                // 在卡片面板中显示游戏面板
                mainCardLayout.show(mainCardPanel, "GamePanel");
                // 初始化游戏面板
                gamePanel.init();
                sideGamePanel.init();
                // 开始游戏
                gamePanel.GameStart();

                sideCardLayout.show(sideCardPanel, "SideGamePanel");
            }
        });
        // 设置按钮的鼠标监听器
        level2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label2.setVisible(true);
                GameFrame.soundManager.playSound("select");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label2.setVisible(false);
            }
        });
        this.add(level2Button);
        this.add(label2);



        //创建级别3按钮
        JButton level3Button = ButtonFactory.createButton("级别3",chineseFont,Color.YELLOW);
        level3Button.setBounds(8 * 60, 7 * 60 + 2 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label3 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30+30, 7 * 60 + 2 * ysapce - 5, 1 * 60, 1 * 60);
        label3.setVisible(false);
        // 为级别3按钮添加动作监听器
        level3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏级别为级别3
                GameFrame.gameLevel = "Level3";
                // 在卡片面板中显示游戏面板
                mainCardLayout.show(mainCardPanel, "GamePanel");
                // 初始化游戏面板
                gamePanel.init();
                sideGamePanel.init();
                // 开始游戏
                gamePanel.GameStart();

                sideCardLayout.show(sideCardPanel, "SideGamePanel");
            }
        });
        // 设置按钮的鼠标监听器
        level3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label3.setVisible(true);
                GameFrame.soundManager.playSound("select");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label3.setVisible(false);
            }
        });
        this.add(level3Button);
        this.add(label3);

        //创建级别4按钮
        JButton level4Button = ButtonFactory.createButton("级别4",chineseFont,Color.YELLOW);
        level4Button.setBounds(8 * 60, 7 * 60 + 3 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label4 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30+30, 7 * 60 + 3 * ysapce - 5, 1 * 60, 1 * 60);
        label4.setVisible(false);
        // 为级别4按钮添加动作监听器
        level4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏级别为级别4
                GameFrame.gameLevel = "Level4";
                // 在卡片面板中显示游戏面板
                mainCardLayout.show(mainCardPanel, "GamePanel");
                // 初始化游戏面板
                gamePanel.init();
                sideGamePanel.init();
                // 开始游戏
                gamePanel.GameStart();

                sideCardLayout.show(sideCardPanel, "SideGamePanel");
            }
        });
        // 设置按钮的鼠标监听器
        level4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label4.setVisible(true);
                GameFrame.soundManager.playSound("select");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label4.setVisible(false);
            }
        });
        this.add(level4Button);
        this.add(label4);

        //创建返回按钮
        ImageButton backButton = new ImageButton(ResourceManager.loadImage("/Images/back.png"),button_width,button_height);
        backButton.setBounds(1*60+30,30,120,80);
        ImageLabel backLabel=new ImageLabel(ResourceManager.loadImage("/Images/Label1.png"),1*60-40,30+10,1*60,1*60);
        backLabel.setVisible(false);
        // 为返回按钮添加动作监听器
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在卡片面板中显示地图菜单面板
                mainCardLayout.show(mainCardPanel, "MapMenuPanel");
            }
        });
        // 设置按钮的鼠标监听器
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backLabel.setVisible(true);
                GameFrame.soundManager.playSound("select");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backLabel.setVisible(false);
            }
        });
        this.add(backButton);
        this.add(backLabel);

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

    public void setSideGamePanel(SideGamePanel sideGamePanel) {
        this.sideGamePanel = sideGamePanel;
    }
}

