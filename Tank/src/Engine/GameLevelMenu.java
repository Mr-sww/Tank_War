package Engine;

import javax.swing.*;
import java.awt.*;

public class GameLevelMenu extends JDialog {

    private String selectedMode = null; // �������ѡ���ģʽ
    private Image backgroundImage; // ����ͼƬ

    public GameLevelMenu(Frame parent) {
        super(parent, "ѡ����Ϸģʽ", true); // ����Ϊģ̬����
        setSize(800, 600); // �������ڴ�С
        setLocationRelativeTo(parent);
        setResizable(false); // ��ֹ�������ڴ�С

        // ���ر���ͼƬ
        backgroundImage = new ImageIcon("src/Images/StartMenu.png").getImage(); // �滻Ϊʵ��·��

        // �Զ������
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null); // ʹ�þ��Բ���
        setContentPane(backgroundPanel);

        // ����
        JLabel title = new JLabel("ѡ����Ϸ�ؿ�", JLabel.CENTER);
        title.setFont(new Font("����", Font.BOLD, 32)); // ���������С
        title.setForeground(Color.WHITE); // ȷ�������ڱ����������ɼ�
        title.setBounds(0, 30, 800, 40); // ���ñ���λ�úʹ�С
        backgroundPanel.add(title);

        // ģʽ��ť
        String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4"};
        int buttonWidth = 200;
        int buttonHeight = 50;
        int totalButtons = levels.length;
        int spacing = 20; // ��ť֮��Ĵ�ֱ���
        int totalHeight = totalButtons * buttonHeight + (totalButtons - 1) * spacing;
        int startX = (800 - buttonWidth) / 2; // ˮƽ����

        // ���� startY �Խ���ť�������ƶ�
        int desiredCenterY = 300; // ������ť�������Y����
        int startY = desiredCenterY - (totalHeight / 2); // ���㰴ť�����ʼY����

        for (int i = 0; i < totalButtons; i++) {
            JButton button = createTransparentButton(levels[i]);
            button.setFont(new Font("����", Font.BOLD, 24)); // ������ť�����С
            int x = startX;
            int y = startY + i * (buttonHeight + spacing);
            button.setBounds(x, y, buttonWidth, buttonHeight);
            final String mode = "level" + (i + 1);
            button.addActionListener(e -> {
                selectedMode = mode; // ����ѡ���ģʽ
                dispose(); // �رղ˵�
            });
            backgroundPanel.add(button);
        }

        // �˳���ť
        JButton exitButton = createTransparentButton("�˳���Ϸ");
        exitButton.setFont(new Font("����", Font.BOLD, 20)); // �����˳���ť�����С
        // ���ݰ�ť�����λ�ã������˳���ť��Y����
        exitButton.setBounds((800 - 200) / 2, startY + totalHeight + 30, 200, 50); // �����˳���ť��λ�úʹ�С
        exitButton.addActionListener(e -> System.exit(0));
        backgroundPanel.add(exitButton);
    }

    private JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("����", Font.BOLD, 22)); // ���Ӱ�ť�����С
        button.setContentAreaFilled(false); // ȥ����ť����
        button.setFocusPainted(false); // ȥ������߿�
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2)); // ��ɫ�߿���ǿ�Ա�
        button.setForeground(Color.YELLOW); // ��ɫ���֣������ɼ�
        return button;
    }

    public String getSelectedMode() {
        return selectedMode;
    }

    // �Զ�����壬���ڻ��Ʊ���ͼƬ
    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // ���챳��ͼ
            } else {
                g.setColor(Color.BLACK); // Ĭ�Ϻ�ɫ����
                g.fillRect(0, 0, getWidth(), getHeight());
                System.out.println("����ͼƬ����ʧ�ܣ�");
            }
        }
    }
}
