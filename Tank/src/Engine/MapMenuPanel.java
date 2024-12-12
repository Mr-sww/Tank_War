package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// 定义一个名为 MapMenuPanel 的公共类，继承自 JPanel 类
public class MapMenuPanel extends JPanel {

    // 定义一个私有的 Image 类型的变量，用于存储背景图片
    private Image backgroundImage;
    // 定义一个私有的 CardLayout 类型的变量，用于管理卡片布局
    private CardLayout cardLayout;
    // 定义一个私有的 JPanel 类型的变量，用于存储卡片面板
    private JPanel cardPanel;


        public MapMenuPanel(CardLayout cardLayout, JPanel cardPanel) {
        // 加载背景图片
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // 初始化 CardLayout 对象
        this.cardLayout = cardLayout;
        // 初始化 cardPanel 对象
        this.cardPanel = cardPanel;

        // 设置布局为网格袋布局
        this.setLayout(new GridBagLayout());
        // 创建网格袋布局的约束对象
        GridBagConstraints gbc = new GridBagConstraints();
        // 设置按钮之间的间距
        gbc.insets = new Insets(10, 0, 10, 0);
        // 设置中文字体
        Font chineseFont = new Font("宋体", Font.BOLD, 48);

        // 创建地图1按钮
        JButton map1Button = ButtonFactory.createButton("地图1",chineseFont,Color.YELLOW);
        // 为地图1按钮添加动作监听器
        map1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏地图为 Map1
                GameFrame.gameMap = "Map1";
                // 显示 LevelMenuPanel 卡片
                cardLayout.show(cardPanel, "LevelMenuPanel");
            }
        });
        // 设置地图1按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 0;
        // 将地图1按钮添加到面板中
        this.add(map1Button,gbc);

        // 创建地图2按钮
        JButton map2Button = ButtonFactory.createButton("地图2",chineseFont,Color.YELLOW);
        // 为地图2按钮添加动作监听器
        map2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏地图为 Map2
                GameFrame.gameMap = "Map2";
                // 显示 LevelMenuPanel 卡片
                cardLayout.show(cardPanel, "LevelMenuPanel");
            }
        });
        // 设置地图2按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 1;
        // 将地图2按钮添加到面板中
        this.add(map2Button,gbc);

        // 创建地图3按钮
        JButton map3Button = ButtonFactory.createButton("地图3",chineseFont,Color.YELLOW);
        // 为地图3按钮添加动作监听器
        map3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏地图为 Map3
                GameFrame.gameMap = "Map3";
                // 显示 LevelMenuPanel 卡片
                cardLayout.show(cardPanel, "LevelMenuPanel");
            }
        });
        // 设置地图3按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 2;
        // 将地图3按钮添加到面板中
        this.add(map3Button,gbc);

        // 创建地图4按钮
        JButton map4Button = ButtonFactory.createButton("地图4",chineseFont,Color.YELLOW);
        // 为地图4按钮添加动作监听器
        map4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏地图为 Map4
                GameFrame.gameMap = "Map4";
                // 显示 LevelMenuPanel 卡片
                cardLayout.show(cardPanel, "LevelMenuPanel");
            }
        });
        // 设置地图4按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 3;
        // 将地图4按钮添加到面板中
        this.add(map4Button,gbc);

        // 创建返回按钮
        JButton backButton = ButtonFactory.createButton("返回",chineseFont,Color.YELLOW);
        // 为返回按钮添加动作监听器
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示 ModeMenuPanel 卡片
                cardLayout.show(cardPanel, "ModeMenuPanel");
            }
        });
        // 设置返回按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 4;
        // 将返回按钮添加到面板中
        this.add(backButton,gbc);
    }

        /**
     * 绘制组件的内容
     *
     * @param g 图形对象
     */
    @Override
    protected void paintComponent(Graphics g) {
        // 调用父类的 paintComponent 方法，确保组件正确绘制
        super.paintComponent(g);
        // 在组件上绘制背景图片，图片覆盖整个组件区域
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}

