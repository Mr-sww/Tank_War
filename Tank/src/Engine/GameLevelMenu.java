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

        // ���ر���ͼƬ
        backgroundImage = new ImageIcon("src/Images/StartMenu.png").getImage(); // �滻Ϊʵ��·��

        // �Զ������
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // ����
        JLabel title = new JLabel("ѡ����Ϸģʽ", JLabel.CENTER);
        title.setFont(new Font("����", Font.BOLD, 32)); // ���������С
        title.setForeground(Color.WHITE); // ȷ�������ڱ����������ɼ�
        backgroundPanel.add(title, BorderLayout.NORTH);

        // ģʽ��ť���
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // ���ð�ť���͸��
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // ���Ӱ�ť���
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ��Ӹ���ģʽ�İ�ť
        addModeButton(buttonPanel, "ģʽ 1", "level1", gbc, 0);
        addModeButton(buttonPanel, "ģʽ 2", "level2", gbc, 1);
        addModeButton(buttonPanel, "ģʽ 3", "level3", gbc, 2);
        addModeButton(buttonPanel, "ģʽ 4", "level4", gbc, 3);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // �˳���ť
        JButton exitButton = createTransparentButton("�˳���Ϸ");
        exitButton.setFont(new Font("����", Font.BOLD, 20)); // �����˳���ť�����С
        exitButton.addActionListener(e -> System.exit(0));
        backgroundPanel.add(exitButton, BorderLayout.SOUTH);
    }

    private void addModeButton(JPanel panel, String label, String mode, GridBagConstraints gbc, int yPos) {
        JButton button = createTransparentButton(label);
        button.setFont(new Font("����", Font.BOLD, 24)); // ������ť�����С
        button.addActionListener(e -> {
            selectedMode = mode; // ����ѡ���ģʽ
            dispose(); // �رղ˵�
        });
        gbc.gridy = yPos;
        gbc.weightx = 1; // ���ð�ťˮƽ���
        panel.add(button, gbc);
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
