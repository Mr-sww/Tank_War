package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

// ����һ����Ϊ MapMenuPanel �Ĺ����࣬�̳��� JPanel ��
public class MapMenuPanel extends JPanel {
    public int button_width=(4*60),button_height=(4*60);

    // ����һ��˽�е� Image ���͵ı��������ڴ洢����ͼƬ
    private Image backgroundImage;
    // ����һ��˽�е� Image ���͵ı��� backImage�����ڴ洢backͼ��
    private Image backImage;
    // ����һ��˽�е� CardLayout ���͵ı��������ڹ���Ƭ����
    private CardLayout cardLayout;
    // ����һ��˽�е� JPanel ���͵ı��������ڴ洢��Ƭ���
    private JPanel cardPanel;
    public static boolean[] mapCleared = new boolean[5];
    // ���ڴ洢��ͼͨ��״̬

    // ������������
    Font chineseFont = new Font("����", Font.BOLD, 48);
    // ������ͼ1��ť
    ImageButton map1Button = new ImageButton(ResourceManager.loadImage("/Images/yellow1.jpg"), button_width, button_height);
    // ������ͼ2��ť
    ImageButton map2Button = new ImageButton(ResourceManager.loadImage("/Images/grey2.jpg"), button_width, button_height);
    // ������ͼ2��ť
    ImageButton map3Button = new ImageButton(ResourceManager.loadImage("/Images/grey3.jpg"), button_width, button_height);
    // ������ͼ4��ť
    ImageButton map4Button = new ImageButton(ResourceManager.loadImage("/Images/grey4.jpg"), button_width, button_height);
    // �������ذ�ť
    ImageButton backButton = new ImageButton(ResourceManager.loadImage("/Images/back.png"), button_width, button_height);

    // ���췽��1
    public MapMenuPanel(CardLayout cardLayout, JPanel cardPanel, int width, int height) {
        // ���ر���ͼƬ
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        // ���ط��ذ�ť�ı���ͼƬ
        this.backImage = ResourceManager.loadImage("/Images/back.png");
        // ��ʼ�� CardLayout ����
        this.cardLayout = cardLayout;
        // ��ʼ�� cardPanel ����
        this.cardPanel = cardPanel;
        // �������Ĵ�С
        this.setPreferredSize(new Dimension(width, height));

//        // ���ò���Ϊ���������
//        this.setLayout(new GridBagLayout());
//        // ������������ֵ�Լ������
//        GridBagConstraints gbc = new GridBagConstraints();
//        // ���ð�ť֮��ļ��
//        gbc.insets = new Insets(10, 0, 10, 0);
//        // ���õ�ͼ1�ı���
//        setButtonBackground(map1Button, "src/Images/yellow1.jpg");
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
//        // ���õ�ͼ1��ť������������е�λ��
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        // ����ͼ1��ť��ӵ������
//        this.add(map1Button, gbc);
//        map1Button.setBounds(8*60,);


//        setButtonBackground(map2Button, "src/Images/grey2.jpg"); // ���õ�ͼ2�ı���
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
//        // ���õ�ͼ2��ť������������е�λ��
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        // ����ͼ2��ť��ӵ������
//        this.add(map2Button, gbc);



//        // ���õ�ͼ3�ı���
//        setButtonBackground(map3Button, "src/Images/grey3.jpg");
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
//        // ���õ�ͼ3��ť������������е�λ��
//        gbc.gridx = 2;
//        gbc.gridy = 0;
//        // ����ͼ3��ť��ӵ������
//        this.add(map3Button, gbc);

//        // ���õ�ͼ4�ı���
//        setButtonBackground(map4Button, "src/Images/grey4.jpg");
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
//        // ���õ�ͼ4��ť������������е�λ��
//        gbc.gridx = 3;
//        gbc.gridy = 0;
//        // ����ͼ4��ť��ӵ������
//        this.add(map4Button, gbc);


        // Ϊ���ذ�ť��Ӷ���������
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ��ʾ ModeMenuPanel ��Ƭ
                cardLayout.show(cardPanel, "ModeMenuPanel");
            }
        });
//        // ���÷��ذ�ť������������е�λ��
//        gbc.gridx = 2;
//        gbc.gridy = 1;
//        // �����ذ�ť��ӵ������
//        this.add(backButton, gbc);

//        setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        // ���÷��ذ�ť��λ�úʹ�С
//        gbc.gridx = 0; // ������
//        gbc.gridy = 0; // ������
//        gbc.gridwidth = 1; // ռ�ݵ�����
//        gbc.gridheight = 1; // ռ�ݵ�����
//        gbc.weightx = 0.0; // ˮƽȨ��
//        gbc.weighty = 0.0; // ��ֱȨ��
//        gbc.anchor = GridBagConstraints.NORTHWEST; // ê��λ��
//        add(backButton, gbc);
//
//        // ����һ��������������в���4����ť
//        JPanel centerPanel = new JPanel();
//        centerPanel.setLayout(new GridLayout(1, 4, 10, 10)); // 1��4�У�ˮƽ�ʹ�ֱ���Ϊ10
//        centerPanel.add(map1Button);
//        centerPanel.add(map2Button);
//        centerPanel.add(map3Button);
//        centerPanel.add(map4Button);
//
//        // ����������λ�úʹ�С
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.gridwidth = GridBagConstraints.REMAINDER; // ռ��ʣ���������
//        gbc.gridheight = 1;
//        gbc.weightx = 1.0; // ˮƽȨ��
//        gbc.weighty = 1.0; // ��ֱȨ��
//        gbc.fill = GridBagConstraints.HORIZONTAL; // ˮƽ���
//        gbc.anchor = GridBagConstraints.CENTER; // ê��λ��
//        add(centerPanel, gbc);

        setLayout(null);
        backButton.setBounds(1*60+30,30,120,80);

        // ����һ��������������в���4����ť
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
        // ���ð�ť����������
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
        // ���ð�ť����������
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
        // ���ð�ť����������
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
        // ���ð�ť����������
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
        // ���ð�ť����������
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
        resetMapBackground();
    }

    // �������ð�ť�ı���ͼƬ
    public void setButtonBackground(JButton button, String imagePath) {
        try {
            // ʹ��ImageIcon����ͼƬ
            ImageIcon icon = new ImageIcon(imagePath);
            // ���ð�ť��ͼ��
            button.setIcon(icon);
            button.setText(""); // ���ð�ť�ı�Ϊ�գ�ȷ������ʾ����
            button.setHorizontalTextPosition(SwingConstants.CENTER); // �����ı�ˮƽ����
            button.setVerticalTextPosition(SwingConstants.BOTTOM); // �����ı���ͼ���·�
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
        // ����ͼƬ
        this.image = image;
        // ���ð�ť�ڻ�ý���ʱ�����ƽ����
        this.setFocusPainted(false);
        // ���ð�ťΪ͸�����������ư�ť�ı���
        this.setOpaque(false);
        // ���ð�ť���������������ɫ������������͸��
        this.setContentAreaFilled(false);
        // ���ð�ť�ı߿򲻻���
        this.setBorderPainted(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) { // ���Ӽ�飬ȷ�� image ��Ϊ null
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
}

