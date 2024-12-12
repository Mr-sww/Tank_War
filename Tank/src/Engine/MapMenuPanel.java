package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// ����һ����Ϊ MapMenuPanel �Ĺ����࣬�̳��� JPanel ��
public class MapMenuPanel extends JPanel {

    // ����һ��˽�е� Image ���͵ı��������ڴ洢����ͼƬ
    private Image backgroundImage;
    // ����һ��˽�е� CardLayout ���͵ı��������ڹ���Ƭ����
    private CardLayout cardLayout;
    // ����һ��˽�е� JPanel ���͵ı��������ڴ洢��Ƭ���
    private JPanel cardPanel;


        public MapMenuPanel(CardLayout cardLayout, JPanel cardPanel) {
        // ���ر���ͼƬ
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // ��ʼ�� CardLayout ����
        this.cardLayout = cardLayout;
        // ��ʼ�� cardPanel ����
        this.cardPanel = cardPanel;

        // ���ò���Ϊ���������
        this.setLayout(new GridBagLayout());
        // ������������ֵ�Լ������
        GridBagConstraints gbc = new GridBagConstraints();
        // ���ð�ť֮��ļ��
        gbc.insets = new Insets(10, 0, 10, 0);
        // ������������
        Font chineseFont = new Font("����", Font.BOLD, 48);

        // ������ͼ1��ť
        JButton map1Button = ButtonFactory.createButton("��ͼ1",chineseFont,Color.YELLOW);
        // Ϊ��ͼ1��ť��Ӷ���������
        map1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������Ϸ��ͼΪ Map1
                GameFrame.gameMap = "Map1";
                // ��ʾ LevelMenuPanel ��Ƭ
                cardLayout.show(cardPanel, "LevelMenuPanel");
            }
        });
        // ���õ�ͼ1��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 0;
        // ����ͼ1��ť��ӵ������
        this.add(map1Button,gbc);

        // ������ͼ2��ť
        JButton map2Button = ButtonFactory.createButton("��ͼ2",chineseFont,Color.YELLOW);
        // Ϊ��ͼ2��ť��Ӷ���������
        map2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������Ϸ��ͼΪ Map2
                GameFrame.gameMap = "Map2";
                // ��ʾ LevelMenuPanel ��Ƭ
                cardLayout.show(cardPanel, "LevelMenuPanel");
            }
        });
        // ���õ�ͼ2��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 1;
        // ����ͼ2��ť��ӵ������
        this.add(map2Button,gbc);

        // ������ͼ3��ť
        JButton map3Button = ButtonFactory.createButton("��ͼ3",chineseFont,Color.YELLOW);
        // Ϊ��ͼ3��ť��Ӷ���������
        map3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������Ϸ��ͼΪ Map3
                GameFrame.gameMap = "Map3";
                // ��ʾ LevelMenuPanel ��Ƭ
                cardLayout.show(cardPanel, "LevelMenuPanel");
            }
        });
        // ���õ�ͼ3��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 2;
        // ����ͼ3��ť��ӵ������
        this.add(map3Button,gbc);

        // ������ͼ4��ť
        JButton map4Button = ButtonFactory.createButton("��ͼ4",chineseFont,Color.YELLOW);
        // Ϊ��ͼ4��ť��Ӷ���������
        map4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������Ϸ��ͼΪ Map4
                GameFrame.gameMap = "Map4";
                // ��ʾ LevelMenuPanel ��Ƭ
                cardLayout.show(cardPanel, "LevelMenuPanel");
            }
        });
        // ���õ�ͼ4��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 3;
        // ����ͼ4��ť��ӵ������
        this.add(map4Button,gbc);

        // �������ذ�ť
        JButton backButton = ButtonFactory.createButton("����",chineseFont,Color.YELLOW);
        // Ϊ���ذ�ť��Ӷ���������
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ��ʾ ModeMenuPanel ��Ƭ
                cardLayout.show(cardPanel, "ModeMenuPanel");
            }
        });
        // ���÷��ذ�ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 4;
        // �����ذ�ť��ӵ������
        this.add(backButton,gbc);
    }

        /**
     * �������������
     *
     * @param g ͼ�ζ���
     */
    @Override
    protected void paintComponent(Graphics g) {
        // ���ø���� paintComponent ������ȷ�������ȷ����
        super.paintComponent(g);
        // ������ϻ��Ʊ���ͼƬ��ͼƬ���������������
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}

