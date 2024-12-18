package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

// 定义一个名为 ModeMenuPanel 的公共类，继承自 JPanel 类
public class ModeMenuPanel extends JPanel {

    // 定义一个私有的 Image 类型的变量 backgroundImage，用于存储背景图片
    private Image backgroundImage;
    // 定义一个私有的 CardLayout 类型的变量 cardLayout，用于存储卡片布局管理器
    private CardLayout cardLayout;
    // 定义一个私有的 JPanel 类型的变量 cardPanel，用于存储卡片面板
    private JPanel cardPanel;

    MapMenuPanel mapMenuPanel;


    public ModeMenuPanel(CardLayout cardLayout, JPanel cardPanel,int width,int height) {
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        // 设置面板的大小
        this.setPreferredSize(new Dimension(width, height));

        this.setLayout(null);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 0, 10, 0); // 设置按钮之间的间距
        // 设置中文字体
        Font chineseFont = new Font("mplus_hzk_12", Font.PLAIN, 48);

        int ysapce = 100;

        //单人对战
        JButton singleButton = ButtonFactory.createButton("单人对战", chineseFont, Color.YELLOW);
        singleButton.setBounds(8 * 60, 7 * 60, 5 * 60, 1 * 60);
        ImageLabel label1 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 - 5, 1 * 60, 1 * 60);
        //JLabel label1=LabelFactory.createLabel(ResourceManager.loadImage("/Images/Label1.png"),8*60-2*60-30, 7*60-5, 5*60, 1*60);
        label1.setVisible(false);
        // 为单人对战按钮添加动作监听器
        singleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏模式为单人模式
                GameFrame.gameMode = "Single";
                // 显示地图选择面板
                cardLayout.show(cardPanel, "MapMenuPanel");
                mapMenuPanel.repaint();
            }
        });
        // 设置按钮的鼠标监听器
        singleButton.addMouseListener(new MouseAdapter() {
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
        this.add(singleButton);
        this.add(label1);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        this.add(singleButton, gbc);

        //双人对战
        JButton doublePlayerButton = ButtonFactory.createButton("双人对战", chineseFont, Color.YELLOW);
        doublePlayerButton.setBounds(8 * 60, 7 * 60 + 1 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label2 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 + 1 * ysapce - 5, 1 * 60, 1 * 60);
        //JLabel label2=LabelFactory.createLabel(ResourceManager.loadImage("/Images/Label1.png"),8*60-2*60-30, 7*60+1*ysapce-5, 5*60, 1*60);
        label2.setVisible(false);
        // 为双人对战按钮添加动作监听器
        doublePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置游戏模式为双人对战模式
                GameFrame.gameMode = "Versus";
                // 显示地图选择面板
                cardLayout.show(cardPanel, "MapMenuPanel");
                mapMenuPanel.repaint();
            }
        });
        // 设置按钮的鼠标监听器
        doublePlayerButton.addMouseListener(new MouseAdapter() {
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
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        this.add(doublePlayerButton, gbc);
        this.add(doublePlayerButton);
        this.add(label2);

        //设置
        JButton settingsButton = ButtonFactory.createButton("设置", chineseFont, Color.YELLOW);
        settingsButton.setBounds(8 * 60, 7 * 60 + 2 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label3 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 + 2 * ysapce - 5, 1 * 60, 1 * 60);
        label3.setVisible(false);
        // 为双人合作按钮添加动作监听器
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsPanel settingsPanel = new SettingsPanel(GameFrame.soundManager);
                settingsPanel.setVisible(true);
                mapMenuPanel.repaint();
            }
        });
        // 设置按钮的鼠标监听器
        settingsButton.addMouseListener(new MouseAdapter() {
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
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        this.add(cooperateButton, gbc);
        this.add(settingsButton);
        this.add(label3);

//        //双人合作
//        JButton cooperateButton = ButtonFactory.createButton("双人合作", chineseFont, Color.YELLOW);
//        cooperateButton.setBounds(8 * 60, 7 * 60 + 2 * ysapce, 5 * 60, 1 * 60);
//        ImageLabel label3 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 + 2 * ysapce - 5, 1 * 60, 1 * 60);
//        label3.setVisible(false);
//        // 为双人合作按钮添加动作监听器
//        cooperateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // 设置游戏模式为双人合作模式
//                GameFrame.gameMode = "Cooperative";
//                // 显示地图选择面板
//                cardLayout.show(cardPanel, "MapMenuPanel");
//                mapMenuPanel.repaint();
//            }
//        });
//        // 设置按钮的鼠标监听器
//        cooperateButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                label3.setVisible(true);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                label3.setVisible(false);
//            }
//        });
////        gbc.gridx = 0;
////        gbc.gridy = 2;
////        this.add(cooperateButton, gbc);
//        this.add(cooperateButton);
//        this.add(label3);

        //退出游戏
        JButton exitButton = ButtonFactory.createButton("退出游戏", chineseFont, Color.YELLOW);
        exitButton.setBounds(8 * 60, 7 * 60 + 3 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label4 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 + 3 * ysapce - 5, 1 * 60, 1 * 60);
//        JLabel label4=LabelFactory.createLabel(ResourceManager.loadImage("/Images/Label1.png"),8*60-2*60-30, 7*60+3*ysapce-5, 5*60, 1*60);
        label4.setVisible(false);
        // 为退出游戏按钮添加动作监听器
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 退出程序
                System.exit(0);
            }
        });
        // 设置按钮的鼠标监听器
        exitButton.addMouseListener(new MouseAdapter() {
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
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        this.add(exitButton, gbc);
        this.add(exitButton);
        this.add(label4);
    }


    public void setMapMenuPanel(MapMenuPanel mapMenuPanel) {
        this.mapMenuPanel = mapMenuPanel;
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

class ImageLabel extends JLabel {
    private Image image;
    public ImageLabel(Image image, int positionX, int positionY, int width, int height) {
        this.image = image;
        this.setPreferredSize(new Dimension(width, height));
        this.setBounds(positionX, positionY, width, height);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}


