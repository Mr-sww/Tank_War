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
    public static boolean[] mapCleared = new boolean[5];
    // 用于存储地图通关状态

    // 设置中文字体
    Font chineseFont = new Font("宋体", Font.BOLD, 48);
    // 创建地图1按钮
    JButton map1Button = ButtonFactory.createButton("地图1", chineseFont, Color.YELLOW);
    // 创建地图2按钮
    JButton map2Button = ButtonFactory.createButton("地图2", chineseFont, Color.YELLOW);
    // 创建地图2按钮
    JButton map3Button = ButtonFactory.createButton("地图3", chineseFont, Color.YELLOW);
    // 创建地图4按钮
    JButton map4Button = ButtonFactory.createButton("地图4", chineseFont, Color.YELLOW);

    public MapMenuPanel() {;}//构造方法1,用于在GamePanel中更新地图背景
    // 构造方法2
    public MapMenuPanel(CardLayout cardLayout, JPanel cardPanel, int width, int height) {
        // 加载背景图片
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // 初始化 CardLayout 对象
        this.cardLayout = cardLayout;
        // 初始化 cardPanel 对象
        this.cardPanel = cardPanel;
        // 设置面板的大小
        this.setPreferredSize(new Dimension(width, height));

        // 设置布局为网格袋布局
        this.setLayout(new GridBagLayout());
        // 创建网格袋布局的约束对象
        GridBagConstraints gbc = new GridBagConstraints();
        // 设置按钮之间的间距
        gbc.insets = new Insets(10, 0, 10, 0);
        // 设置地图1的背景
        setButtonBackground(map1Button, "Images/map1.png");
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
        this.add(map1Button, gbc);
        setButtonBackground(map2Button, "Images/map1.png"); // 设置地图2的背景
        // 为地图2按钮添加动作监听器
        map2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapCleared[1]) {
                    GameFrame.gameMap = "Map2";
                    cardLayout.show(cardPanel, "LevelMenuPanel");
                } else {
                    JOptionPane.showMessageDialog(null, "请先通关前面的地图！");
                }
            }
        });
        // 设置地图2按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 1;
        // 将地图2按钮添加到面板中
        this.add(map2Button, gbc);


        // 设置地图3的背景
        setButtonBackground(map3Button, "Images/map1.png");
        // 为地图3按钮添加动作监听器
        map3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapCleared[2]) {
                    GameFrame.gameMap = "Map3";
                    cardLayout.show(cardPanel, "LevelMenuPanel");
                } else {
                    JOptionPane.showMessageDialog(null, "请先通关前面的地图！");
                }
            }
        });
        // 设置地图3按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 2;
        // 将地图3按钮添加到面板中
        this.add(map3Button, gbc);

        // 设置地图4的背景
        setButtonBackground(map4Button, "Images/map1.png");
        // 为地图4按钮添加动作监听器
        map4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapCleared[3]) {
                    GameFrame.gameMap = "Map4";
                    cardLayout.show(cardPanel, "LevelMenuPanel");
                } else {
                    JOptionPane.showMessageDialog(null, "请先通关前面的地图！");
                }
            }
        });
        // 设置地图4按钮在网格袋布局中的位置
        gbc.gridx = 0;
        gbc.gridy = 3;
        // 将地图4按钮添加到面板中
        this.add(map4Button, gbc);

        // 创建返回按钮
        JButton backButton = ButtonFactory.createButton("返回", chineseFont, Color.YELLOW);
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
        this.add(backButton, gbc);
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

    // 用于设置按钮的背景图片
     public void setButtonBackground(JButton button, String imagePath) {
        try {
            // 使用ImageIcon加载图片
            ImageIcon icon = new ImageIcon(imagePath);
            // 设置按钮的图标
            button.setIcon(icon);
            button.setHorizontalTextPosition(SwingConstants.CENTER); // 设置文本水平居中
            button.setVerticalTextPosition(SwingConstants.BOTTOM); // 设置文本在图标下方
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void resetMapBackground(){
        if(mapCleared[1] ){
            setButtonBackground(map1Button, "Images/map2.png");
        }
        else if(mapCleared [2]){
            setButtonBackground(map2Button, "Images/map2.png");
        }
        else if(mapCleared [3]){
            setButtonBackground(map3Button, "Images/map2.png");
        }
        else if(mapCleared [4]){
            setButtonBackground(map4Button, "Images/map2.png");
        }
    }

}

