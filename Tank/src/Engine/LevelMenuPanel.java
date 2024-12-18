package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

// ����һ����Ϊ LevelMenuPanel �Ĺ����࣬�̳��� JPanel ��
public class LevelMenuPanel extends JPanel {
    public int button_width=(4*60),button_height=(4*60);

    // ����һ��˽�е� Image ���͵ı��������ڴ洢����ͼƬ
    private Image backgroundImage;
    // ����һ��˽�е� CardLayout ���͵ı��������ڹ���Ƭ����
    private CardLayout cardLayout;
    // ����һ��˽�е� JPanel ���͵ı��������ڴ洢��Ƭ���
    private JPanel cardPanel;
    // ����һ��˽�е� GamePanel ���͵ı��������ڴ洢��Ϸ���
    private GamePanel gamePanel;


        /**
     * ���캯������ʼ������˵����
     *
     * @param cardLayout ��Ƭ���ֹ������������ڲ�ͬ���֮���л�
     * @param cardPanel ��Ƭ��壬����������Ҫ�л������
     */
    public LevelMenuPanel(CardLayout cardLayout, JPanel cardPanel,int width,int height) {
        // ���ر���ͼƬ
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // ��ʼ����Ƭ���ֹ�����
        this.cardLayout = cardLayout;
        // ��ʼ����Ƭ���
        this.cardPanel = cardPanel;
        // �������Ĵ�С
        this.setPreferredSize(new Dimension(width,height));

        this.setLayout(null);
        // ������������
        Font chineseFont = new Font("mplus_hzk_12", Font.PLAIN, 48);

        int ysapce = 100;

        //��������1��ť
        JButton level1Button = ButtonFactory.createButton("����1",chineseFont,Color.YELLOW);
        level1Button.setBounds(8 * 60, 7 * 60, 5 * 60, 1 * 60);
        ImageLabel label1 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30+30, 7 * 60 - 5, 1 * 60, 1 * 60);
        label1.setVisible(false);
        // Ϊ����1��ť��Ӷ���������
        level1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������Ϸ����Ϊ����1
                GameFrame.gameLevel = "Level1";
                // �ڿ�Ƭ�������ʾ��Ϸ���
                cardLayout.show(cardPanel, "GamePanel");
                // ��ʼ����Ϸ���
                gamePanel.init();
                // ��ʼ��Ϸ
                gamePanel.GameStart();
            }
        });
        // ���ð�ť����������
        level1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label1.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label1.setVisible(false);
            }
        });
        this.add(level1Button);
        this.add(label1);


        // ��������2��ť
        JButton level2Button = ButtonFactory.createButton("����2",chineseFont,Color.YELLOW);
        level2Button.setBounds(8 * 60, 7 * 60 + 1 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label2 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30+30, 7 * 60 + 1 * ysapce - 5, 1 * 60, 1 * 60);
        label2.setVisible(false);
        // Ϊ����2��ť��Ӷ���������
        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������Ϸ����Ϊ����2
                GameFrame.gameLevel = "Level2";
                // �ڿ�Ƭ�������ʾ��Ϸ���
                cardLayout.show(cardPanel, "GamePanel");
                // ��ʼ����Ϸ���
                gamePanel.init();
                // ��ʼ��Ϸ
                gamePanel.GameStart();
            }
        });
        // ���ð�ť����������
        level2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label2.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label2.setVisible(false);
            }
        });
        this.add(level2Button);
        this.add(label2);



        //��������3��ť
        JButton level3Button = ButtonFactory.createButton("����3",chineseFont,Color.YELLOW);
        level3Button.setBounds(8 * 60, 7 * 60 + 2 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label3 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30+30, 7 * 60 + 2 * ysapce - 5, 1 * 60, 1 * 60);
        label3.setVisible(false);
        // Ϊ����3��ť��Ӷ���������
        level3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������Ϸ����Ϊ����3
                GameFrame.gameLevel = "Level3";
                // �ڿ�Ƭ�������ʾ��Ϸ���
                cardLayout.show(cardPanel, "GamePanel");
                // ��ʼ����Ϸ���
                gamePanel.init();
                // ��ʼ��Ϸ
                gamePanel.GameStart();
            }
        });
        // ���ð�ť����������
        level3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label3.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label3.setVisible(false);
            }
        });
        this.add(level3Button);
        this.add(label3);

        //��������4��ť
        JButton level4Button = ButtonFactory.createButton("����4",chineseFont,Color.YELLOW);
        level4Button.setBounds(8 * 60, 7 * 60 + 3 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label4 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30+30, 7 * 60 + 3 * ysapce - 5, 1 * 60, 1 * 60);
        label4.setVisible(false);
        // Ϊ����4��ť��Ӷ���������
        level4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������Ϸ����Ϊ����4
                GameFrame.gameLevel = "Level4";
                // �ڿ�Ƭ�������ʾ��Ϸ���
                cardLayout.show(cardPanel, "GamePanel");
                // ��ʼ����Ϸ���
                gamePanel.init();
                // ��ʼ��Ϸ
                gamePanel.GameStart();
            }
        });
        // ���ð�ť����������
        level4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label4.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label4.setVisible(false);
            }
        });
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        this.add(exitButton, gbc);
        this.add(level4Button);
        this.add(label4);

        //�������ذ�ť
        ImageButton backButton = new ImageButton(ResourceManager.loadImage("/Images/back.png"),button_width,button_height);
        backButton.setBounds(1*60+30,30,120,80);
        ImageLabel backLabel=new ImageLabel(ResourceManager.loadImage("/Images/Label1.png"),1*60-40,30+10,1*60,1*60);
        backLabel.setVisible(false);
        // Ϊ���ذ�ť��Ӷ���������
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // �ڿ�Ƭ�������ʾ��ͼ�˵����
                cardLayout.show(cardPanel, "MapMenuPanel");
            }
        });
        // ���ð�ť����������
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backLabel.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backLabel.setVisible(false);
            }
        });
        this.add(backButton);
        this.add(backLabel);





//        // ���ò���Ϊ���������
//        this.setLayout(new GridBagLayout());
//        // ������������ֵ�Լ������
//        GridBagConstraints gbc = new GridBagConstraints();
//        // ���ð�ť֮��ļ��
//        gbc.insets = new Insets(10, 0, 10, 0);
//        // ������������
//        Font chineseFont = new Font("����", Font.BOLD, 48);
//

//        // ���ü���1��ť������������е�λ��
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        // ������1��ť��ӵ���ǰ���
//        this.add(level1Button,gbc);
//
//
//        // ���ü���2��ť������������е�λ��
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        // ������2��ť��ӵ���ǰ���
//        this.add(level2Button,gbc);
//
//
//        // ���ü���3��ť������������е�λ��
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        // ������3��ť��ӵ���ǰ���
//        this.add(level3Button,gbc);
//
//
//        // ���ü���4��ť������������е�λ��
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        // ������4��ť��ӵ���ǰ���
//        this.add(level4Button,gbc);
//
//
//        // ���÷��ذ�ť������������е�λ��
//        gbc.gridx = 0;
//        gbc.gridy = 4;
//        // �����ذ�ť��ӵ���ǰ���
//        this.add(backButton,gbc);
    }


        /**
     * ��дpaintComponent���������ڻ��Ʊ���ͼƬ
     *
     * @param g ͼ�ζ���
     */
    @Override
    protected void paintComponent(Graphics g) {
        // ���ø����paintComponent��������ȷ����ȷ�Ļ���˳����������
        super.paintComponent(g);
        // ������ϻ��Ʊ���ͼƬ����(0,0)��ʼ������������Ŀ�Ⱥ͸߶�
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }


        /**
     * ������Ϸ���
     *
     * @param gamePanel Ҫ���õ���Ϸ���
     */
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}

