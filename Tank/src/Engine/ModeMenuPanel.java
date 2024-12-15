package Engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// ����һ����Ϊ ModeMenuPanel �Ĺ����࣬�̳��� JPanel ��
public class ModeMenuPanel extends JPanel {

    // ����һ��˽�е� Image ���͵ı��� backgroundImage�����ڴ洢����ͼƬ
    private Image backgroundImage;
    // ����һ��˽�е� CardLayout ���͵ı��� cardLayout�����ڴ洢��Ƭ���ֹ�����
    private CardLayout cardLayout;
    // ����һ��˽�е� JPanel ���͵ı��� cardPanel�����ڴ洢��Ƭ���
    private JPanel cardPanel;


    public ModeMenuPanel(CardLayout cardLayout, JPanel cardPanel,int width,int height) {
        this.backgroundImage = ResourceManager.loadImage("/Images/StartMenu.png");
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        // �������Ĵ�С
        this.setPreferredSize(new Dimension(width,height));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // ���ð�ť֮��ļ��
        // ������������
        Font chineseFont = new Font("����", Font.BOLD, 48);

        //���˶�ս
        JButton singleButton = ButtonFactory.createButton("���˶�ս", chineseFont, Color.YELLOW);
        // Ϊ���˶�ս��ť��Ӷ���������
        singleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������ϷģʽΪ����ģʽ
                GameFrame.gameMode = "Single";
                // ��ʾ��ͼѡ�����
                cardLayout.show(cardPanel, "MapMenuPanel");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(singleButton, gbc);

        //˫�˶�ս
        JButton doublePlayerButton = ButtonFactory.createButton("˫�˶�ս", chineseFont, Color.YELLOW);
        // Ϊ˫�˶�ս��ť��Ӷ���������
        doublePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������ϷģʽΪ˫�˶�սģʽ
                GameFrame.gameMode = "Versus";
                // ��ʾ��ͼѡ�����
                cardLayout.show(cardPanel, "MapMenuPanel");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(doublePlayerButton, gbc);

        //˫�˺���
        JButton cooperateButton = ButtonFactory.createButton("˫�˺���", chineseFont, Color.YELLOW);
        // Ϊ˫�˺�����ť��Ӷ���������
        cooperateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������ϷģʽΪ˫�˺���ģʽ
                GameFrame.gameMode = "Cooperative";
                // ��ʾ��ͼѡ�����
                cardLayout.show(cardPanel, "MapMenuPanel");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(cooperateButton, gbc);

        //�˳���Ϸ
        JButton exitButton = ButtonFactory.createButton("�˳���Ϸ", chineseFont, Color.YELLOW);
        // Ϊ�˳���Ϸ��ť��Ӷ���������
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // �˳�����
                System.exit(0);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(exitButton, gbc);
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


