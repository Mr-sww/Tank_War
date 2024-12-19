package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

// 定义一个名为 MapMenuPanel 的公共类，继承自 JPanel 类
public class MapMenuPanel extends JPanel {
    public int button_width=(4*60),button_height=(4*60);

    // 定义一个私有的 Image 类型的变量，用于存储背景图片
    private Image backgroundImage;
    // 定义一个私有的 Image 类型的变量 backImage，用于存储back图标
    private Image backImage;
    // 定义一个私有的 CardLayout 类型的变量，用于管理卡片布局
    private CardLayout cardLayout;
    // 定义一个私有的 JPanel 类型的变量，用于存储卡片面板
    private JPanel cardPanel;
    public static boolean[] mapCleared = new boolean[5];
    // 用于存储地图通关状态

    // 设置中文字体
    Font chineseFont = new Font("宋体", Font.BOLD, 48);
    // 创建地图1按钮
    ImageButton map1Button = new ImageButton(ResourceManager.loadImage("/Images/yellow1.jpg"), button_width, button_height);
    // 创建地图2按钮
    ImageButton map2Button = new ImageButton(ResourceManager.loadImage("/Images/grey2.jpg"), button_width, button_height);
    // 创建地图2按钮
    ImageButton map3Button = new ImageButton(ResourceManager.loadImage("/Images/grey3.jpg"), button_width, button_height);
    // 创建地图4按钮
    ImageButton map4Button = new ImageButton(ResourceManager.loadImage("/Images/grey4.jpg"), button_width, button_height);
    // 创建返回按钮
    ImageButton backButton = new ImageButton(ResourceManager.loadImage("/Images/back.png"), button_width, button_height);

    // 构造方法1
    public MapMenuPanel(CardLayout cardLayout, JPanel cardPanel, int width, int height) {
        // 加载背景图片
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // 加载返回按钮的背景图片
        this.backImage = ResourceManager.loadImage("/Images/back.png");
        // 初始化 CardLayout 对象
        this.cardLayout = cardLayout;
        // 初始化 cardPanel 对象
        this.cardPanel = cardPanel;
        // 设置面板的大小
        this.setPreferredSize(new Dimension(width, height));
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


        // 为返回按钮添加动作监听器
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示 ModeMenuPanel 卡片
                cardLayout.show(cardPanel, "ModeMenuPanel");
            }
        });

        setLayout(null);
        backButton.setBounds(1*60+30,30,120,80);

        // 创建一个子面板来放置中部的4个按钮
        JPanel centerPanel = new JPanel(new FlowLayout()) ;
        centerPanel.add(map1Button);
        centerPanel.add(map2Button);
        centerPanel.add(map3Button);
        centerPanel.add(map4Button);
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setBounds(0,7*60,1260,4*60);

        this.add(backButton);
        this.add(centerPanel);

        ImageLabel backLabel=new ImageLabel(ResourceManager.loadImage("/Images/Label1.png"),1*60-40,30+10,1*60,1*60);
        backLabel.setVisible(false);
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
        this.add(backLabel);

        int xspace=4*60;

        ImageLabel label1=new ImageLabel(ResourceManager.loadImage("/Images/Label2.png"),4*60-30,5*60,1*60+30,1*60+30);
        label1.setVisible(false);
        // 设置按钮的鼠标监听器
        map1Button.addMouseListener(new MouseAdapter() {
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
        this.add(label1);

        ImageLabel label2=new ImageLabel(ResourceManager.loadImage("/Images/Label2.png"),4*60-30+1*xspace+10,5*60,1*60+30,1*60+30);
        label2.setVisible(false);
        // 设置按钮的鼠标监听器
        map2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(mapCleared[1]){
                    label2.setVisible(true);
                    GameFrame.soundManager.playSound("select");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label2.setVisible(false);
            }
        });
        this.add(label2);

        ImageLabel label3=new ImageLabel(ResourceManager.loadImage("/Images/Label2.png"),4*60-30+2*xspace+10,5*60,1*60+30,1*60+30);
        label3.setVisible(false);
        // 设置按钮的鼠标监听器
        map3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(mapCleared[2]){
                    label3.setVisible(true);
                    GameFrame.soundManager.playSound("select");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label3.setVisible(false);
            }
        });
        this.add(label3);

        ImageLabel label4=new ImageLabel(ResourceManager.loadImage("/Images/Label2.png"),4*60-30+3*xspace+10+10,5*60,1*60+30,1*60+30);
        label4.setVisible(false);
        // 设置按钮的鼠标监听器
        map4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(mapCleared[3]){
                    label4.setVisible(true);
                    GameFrame.soundManager.playSound("select");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label4.setVisible(false);
            }
        });
        this.add(label4);
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
        resetMapBackground();
    }

    // 用于设置按钮的背景图片
    public void setButtonBackground(JButton button, String imagePath) {
        try {
            // 使用ImageIcon加载图片
            ImageIcon icon = new ImageIcon(imagePath);
            // 设置按钮的图标
            button.setIcon(icon);
            button.setText(""); // 设置按钮文本为空，确保不显示文字
            button.setHorizontalTextPosition(SwingConstants.CENTER); // 设置文本水平居中
            button.setVerticalTextPosition(SwingConstants.BOTTOM); // 设置文本在图标下方
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void resetMapBackground(){
        if(mapCleared [1]){
            map2Button.setImage(ResourceManager.loadImage("/Images/yellow2.jpg"));
        }
        if(mapCleared [2]){
            map3Button.setImage(ResourceManager.loadImage("/Images/yellow3.jpg"));
        }
        if(mapCleared [3]){
            map4Button.setImage(ResourceManager.loadImage("/Images/yellow4.jpg"));
        }
    }

}
class ImageButton extends JButton {

    private Image image;
    public ImageButton(Image image,int width,int height)  {
        this.setPreferredSize(new Dimension(width, height));
        // 加载图片
        this.image = image;
        // 设置按钮在获得焦点时不绘制焦点框
        this.setFocusPainted(false);
        // 设置按钮为透明，即不绘制按钮的背景
        this.setOpaque(false);
        // 设置按钮的内容区域不填充颜色，即内容区域透明
        this.setContentAreaFilled(false);
        // 设置按钮的边框不绘制
        this.setBorderPainted(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) { // 增加检查，确保 image 不为 null
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
}

