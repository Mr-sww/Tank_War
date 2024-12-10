package Engine;

import javax.swing.*;
import java.awt.*;

public class GameMapMenu extends JDialog {

    private Image backgroundImage; // ����ͼƬ

    public GameMapMenu(Frame parent) {
        super(parent, "ѡ����Ϸ��ͼ", true); // ����Ϊģ̬����
        setSize(800, 600); // �������ڴ�С
        setLocationRelativeTo(parent);

        // ���ر���ͼƬ
        backgroundImage = new ImageIcon("src/Images/StartMenu.png").getImage(); // �滻Ϊʵ��·��

        // �Զ������
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // ����
        JLabel title = new JLabel("ѡ����Ϸ��ͼ", JLabel.CENTER);
        title.setFont(new Font("����", Font.BOLD, 32)); // ���������С
        title.setForeground(Color.WHITE); // ȷ�������ڱ����������ɼ�
        backgroundPanel.add(title, BorderLayout.NORTH);

        // ��ͼ��ť���
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // ���ð�ť���͸��
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // ���Ӱ�ť���
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ��Ӹ�����ͼ��ť
        addMapButton(buttonPanel, "�ؿ� 1", 1, gbc, 0);
        addMapButton(buttonPanel, "�ؿ� 2", 2, gbc, 1);
        addMapButton(buttonPanel, "�ؿ� 3", 3, gbc, 2);
        addMapButton(buttonPanel, "�ؿ� 4", 4, gbc, 3);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // �˳���ť
        JButton exitButton = createTransparentButton("�˳���Ϸ");
        exitButton.setFont(new Font("����", Font.BOLD, 20)); // �����˳���ť�����С
        exitButton.addActionListener(e -> System.exit(0));
        backgroundPanel.add(exitButton, BorderLayout.SOUTH);
    }

    private void addMapButton(JPanel panel, String label, int map, GridBagConstraints gbc, int yPos) {
        JButton button = createTransparentButton(label);
        button.setFont(new Font("����", Font.BOLD, 24)); // ������ť�����С
        button.addActionListener(e -> {
            GameFrame.MapLevel = map; // ���þ�̬���� MapLevel Ϊѡ�еĵ�ͼ���
            dispose(); // �رղ˵�
        });
        gbc.gridy = yPos;
        gbc.weightx = 1; // ���ð�ťˮƽ���
        panel.add(button, gbc);
    }

    private JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("����", Font.BOLD, 20)); // ��������
        button.setContentAreaFilled(false); // ȥ����ť����
        button.setFocusPainted(false); // ȥ������߿�
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2)); // ��ɫ�߿���ǿ�Ա�
        button.setForeground(Color.YELLOW); // ��ɫ����
        return button;
    }

    // �Զ�����壬���ڻ��Ʊ���ͼƬ
    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK); // Ĭ�Ϻ�ɫ����
            g.fillRect(0, 0, getWidth(), getHeight());
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // ���챳��ͼ
            } else {
                System.out.println("����ͼƬ����ʧ�ܣ�");
            }
        }
    }
}
