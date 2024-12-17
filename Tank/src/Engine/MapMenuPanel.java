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
    public static boolean[] mapCleared = new boolean[5];
    // ���ڴ洢��ͼͨ��״̬

    // ������������
    Font chineseFont = new Font("����", Font.BOLD, 48);
    // ������ͼ1��ť
    JButton map1Button = ButtonFactory.createButton("��ͼ1", chineseFont, Color.YELLOW);
    // ������ͼ2��ť
    JButton map2Button = ButtonFactory.createButton("��ͼ2", chineseFont, Color.YELLOW);
    // ������ͼ2��ť
    JButton map3Button = ButtonFactory.createButton("��ͼ3", chineseFont, Color.YELLOW);
    // ������ͼ4��ť
    JButton map4Button = ButtonFactory.createButton("��ͼ4", chineseFont, Color.YELLOW);

    public MapMenuPanel() {;}//���췽��1,������GamePanel�и��µ�ͼ����
    // ���췽��2
    public MapMenuPanel(CardLayout cardLayout, JPanel cardPanel, int width, int height) {
        // ���ر���ͼƬ
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // ��ʼ�� CardLayout ����
        this.cardLayout = cardLayout;
        // ��ʼ�� cardPanel ����
        this.cardPanel = cardPanel;
        // �������Ĵ�С
        this.setPreferredSize(new Dimension(width, height));

        // ���ò���Ϊ���������
        this.setLayout(new GridBagLayout());
        // ������������ֵ�Լ������
        GridBagConstraints gbc = new GridBagConstraints();
        // ���ð�ť֮��ļ��
        gbc.insets = new Insets(10, 0, 10, 0);
        // ���õ�ͼ1�ı���
        setButtonBackground(map1Button, "Images/map1.png");
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
        this.add(map1Button, gbc);
        setButtonBackground(map2Button, "Images/map1.png"); // ���õ�ͼ2�ı���
        // Ϊ��ͼ2��ť��Ӷ���������
        map2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapCleared[1]) {
                    GameFrame.gameMap = "Map2";
                    cardLayout.show(cardPanel, "LevelMenuPanel");
                } else {
                    JOptionPane.showMessageDialog(null, "����ͨ��ǰ��ĵ�ͼ��");
                }
            }
        });
        // ���õ�ͼ2��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 1;
        // ����ͼ2��ť��ӵ������
        this.add(map2Button, gbc);


        // ���õ�ͼ3�ı���
        setButtonBackground(map3Button, "Images/map1.png");
        // Ϊ��ͼ3��ť��Ӷ���������
        map3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapCleared[2]) {
                    GameFrame.gameMap = "Map3";
                    cardLayout.show(cardPanel, "LevelMenuPanel");
                } else {
                    JOptionPane.showMessageDialog(null, "����ͨ��ǰ��ĵ�ͼ��");
                }
            }
        });
        // ���õ�ͼ3��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 2;
        // ����ͼ3��ť��ӵ������
        this.add(map3Button, gbc);

        // ���õ�ͼ4�ı���
        setButtonBackground(map4Button, "Images/map1.png");
        // Ϊ��ͼ4��ť��Ӷ���������
        map4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapCleared[3]) {
                    GameFrame.gameMap = "Map4";
                    cardLayout.show(cardPanel, "LevelMenuPanel");
                } else {
                    JOptionPane.showMessageDialog(null, "����ͨ��ǰ��ĵ�ͼ��");
                }
            }
        });
        // ���õ�ͼ4��ť������������е�λ��
        gbc.gridx = 0;
        gbc.gridy = 3;
        // ����ͼ4��ť��ӵ������
        this.add(map4Button, gbc);

        // �������ذ�ť
        JButton backButton = ButtonFactory.createButton("����", chineseFont, Color.YELLOW);
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
        this.add(backButton, gbc);
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

    // �������ð�ť�ı���ͼƬ
     public void setButtonBackground(JButton button, String imagePath) {
        try {
            // ʹ��ImageIcon����ͼƬ
            ImageIcon icon = new ImageIcon(imagePath);
            // ���ð�ť��ͼ��
            button.setIcon(icon);
            button.setHorizontalTextPosition(SwingConstants.CENTER); // �����ı�ˮƽ����
            button.setVerticalTextPosition(SwingConstants.BOTTOM); // �����ı���ͼ���·�
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

