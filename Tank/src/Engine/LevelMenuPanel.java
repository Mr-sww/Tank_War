package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// ����һ����Ϊ LevelMenuPanel �Ĺ����࣬�̳��� JPanel ��
public class LevelMenuPanel extends JPanel {

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
    public LevelMenuPanel(CardLayout cardLayout, JPanel cardPanel) {
        // ���ر���ͼƬ
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // ��ʼ����Ƭ���ֹ�����
        this.cardLayout = cardLayout;
        // ��ʼ����Ƭ���
        this.cardPanel = cardPanel;

        // ���ò���Ϊ���������
        this.setLayout(new GridBagLayout());
        // ������������ֵ�Լ������
        GridBagConstraints gbc = new GridBagConstraints();
        // ���ð�ť֮��ļ��
        gbc.insets = new Insets(10, 0, 10, 0);
        // ������������
        Font chineseFont = new Font("����", Font.BOLD, 48);

        // ��������1��ť
        JButton level1Button = ButtonFactory.createButton("����1",chineseFont,Color.YELLOW);
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
        // ���ü���1��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 0;
        // ������1��ť��ӵ���ǰ���
        this.add(level1Button,gbc);

        // ��������2��ť
        JButton level2Button = ButtonFactory.createButton("����2",chineseFont,Color.YELLOW);
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
        // ���ü���2��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 1;
        // ������2��ť��ӵ���ǰ���
        this.add(level2Button,gbc);

        // ��������3��ť
        JButton level3Button = ButtonFactory.createButton("����3",chineseFont,Color.YELLOW);
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
        // ���ü���3��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 2;
        // ������3��ť��ӵ���ǰ���
        this.add(level3Button,gbc);

        // ��������4��ť
        JButton level4Button = ButtonFactory.createButton("����4",chineseFont,Color.YELLOW);
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
        // ���ü���4��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 3;
        // ������4��ť��ӵ���ǰ���
        this.add(level4Button,gbc);

        // �������ذ�ť
        JButton backButton = ButtonFactory.createButton("����",chineseFont,Color.YELLOW);
        // Ϊ���ذ�ť��Ӷ���������
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // �ڿ�Ƭ�������ʾ��ͼ�˵����
                cardLayout.show(cardPanel, "MapMenuPanel");
            }
        });
        // ���÷��ذ�ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 4;
        // �����ذ�ť��ӵ���ǰ���
        this.add(backButton,gbc);
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

