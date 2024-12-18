package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

// ����һ����Ϊ ModeMenuPanel �Ĺ����࣬�̳��� JPanel ��
public class ModeMenuPanel extends JPanel {

    // ����һ��˽�е� Image ���͵ı��� backgroundImage�����ڴ洢����ͼƬ
    private Image backgroundImage;
    // ����һ��˽�е� CardLayout ���͵ı��� cardLayout�����ڴ洢��Ƭ���ֹ�����
    private CardLayout cardLayout;
    // ����һ��˽�е� JPanel ���͵ı��� cardPanel�����ڴ洢��Ƭ���
    private JPanel cardPanel;

    MapMenuPanel mapMenuPanel;


    public ModeMenuPanel(CardLayout cardLayout, JPanel cardPanel,int width,int height) {
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        // �������Ĵ�С
        this.setPreferredSize(new Dimension(width, height));

        this.setLayout(null);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 0, 10, 0); // ���ð�ť֮��ļ��
        // ������������
        Font chineseFont = new Font("mplus_hzk_12", Font.PLAIN, 48);

        int ysapce = 100;

        //���˶�ս
        JButton singleButton = ButtonFactory.createButton("���˶�ս", chineseFont, Color.YELLOW);
        singleButton.setBounds(8 * 60, 7 * 60, 5 * 60, 1 * 60);
        ImageLabel label1 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 - 5, 1 * 60, 1 * 60);
        //JLabel label1=LabelFactory.createLabel(ResourceManager.loadImage("/Images/Label1.png"),8*60-2*60-30, 7*60-5, 5*60, 1*60);
        label1.setVisible(false);
        // Ϊ���˶�ս��ť��Ӷ���������
        singleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������ϷģʽΪ����ģʽ
                GameFrame.gameMode = "Single";
                // ��ʾ��ͼѡ�����
                cardLayout.show(cardPanel, "MapMenuPanel");
                mapMenuPanel.repaint();
            }
        });
        // ���ð�ť����������
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

        //˫�˶�ս
        JButton doublePlayerButton = ButtonFactory.createButton("˫�˶�ս", chineseFont, Color.YELLOW);
        doublePlayerButton.setBounds(8 * 60, 7 * 60 + 1 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label2 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 + 1 * ysapce - 5, 1 * 60, 1 * 60);
        //JLabel label2=LabelFactory.createLabel(ResourceManager.loadImage("/Images/Label1.png"),8*60-2*60-30, 7*60+1*ysapce-5, 5*60, 1*60);
        label2.setVisible(false);
        // Ϊ˫�˶�ս��ť��Ӷ���������
        doublePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������ϷģʽΪ˫�˶�սģʽ
                GameFrame.gameMode = "Versus";
                // ��ʾ��ͼѡ�����
                cardLayout.show(cardPanel, "MapMenuPanel");
                mapMenuPanel.repaint();
            }
        });
        // ���ð�ť����������
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

        //����
        JButton settingsButton = ButtonFactory.createButton("����", chineseFont, Color.YELLOW);
        settingsButton.setBounds(8 * 60, 7 * 60 + 2 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label3 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 + 2 * ysapce - 5, 1 * 60, 1 * 60);
        label3.setVisible(false);
        // Ϊ˫�˺�����ť��Ӷ���������
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsPanel settingsPanel = new SettingsPanel(GameFrame.soundManager);
                settingsPanel.setVisible(true);
                mapMenuPanel.repaint();
            }
        });
        // ���ð�ť����������
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

//        //˫�˺���
//        JButton cooperateButton = ButtonFactory.createButton("˫�˺���", chineseFont, Color.YELLOW);
//        cooperateButton.setBounds(8 * 60, 7 * 60 + 2 * ysapce, 5 * 60, 1 * 60);
//        ImageLabel label3 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 + 2 * ysapce - 5, 1 * 60, 1 * 60);
//        label3.setVisible(false);
//        // Ϊ˫�˺�����ť��Ӷ���������
//        cooperateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // ������ϷģʽΪ˫�˺���ģʽ
//                GameFrame.gameMode = "Cooperative";
//                // ��ʾ��ͼѡ�����
//                cardLayout.show(cardPanel, "MapMenuPanel");
//                mapMenuPanel.repaint();
//            }
//        });
//        // ���ð�ť����������
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

        //�˳���Ϸ
        JButton exitButton = ButtonFactory.createButton("�˳���Ϸ", chineseFont, Color.YELLOW);
        exitButton.setBounds(8 * 60, 7 * 60 + 3 * ysapce, 5 * 60, 1 * 60);
        ImageLabel label4 = new ImageLabel((ResourceManager.loadImage("/Images/Label1.png")), 8 * 60 - 1 * 60 + 30, 7 * 60 + 3 * ysapce - 5, 1 * 60, 1 * 60);
//        JLabel label4=LabelFactory.createLabel(ResourceManager.loadImage("/Images/Label1.png"),8*60-2*60-30, 7*60+3*ysapce-5, 5*60, 1*60);
        label4.setVisible(false);
        // Ϊ�˳���Ϸ��ť��Ӷ���������
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // �˳�����
                System.exit(0);
            }
        });
        // ���ð�ť����������
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
     * ��д paintComponent ���������ڻ��Ʊ���ͼƬ
     *
     * @param g ͼ�ζ���
     */
    @Override
    protected void paintComponent(Graphics g) {
        // ���ø���� paintComponent ��������ȷ����ȷ�Ļ���˳����������
        super.paintComponent(g);
        // ������ϻ��Ʊ���ͼƬ���� (0,0) ���꿪ʼ������������Ŀ�Ⱥ͸߶�
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


