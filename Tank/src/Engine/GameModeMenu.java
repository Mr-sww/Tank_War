package Engine;

import javax.swing.*;
import java.awt.*;

public class GameModeMenu extends JDialog {

    private String selectedMode = null; // �������ѡ��Ķ�սģʽ
    private Image backgroundImage; // ����ͼƬ

    public GameModeMenu(Frame parent) {
        super(parent, "ѡ���սģʽ", true); // ����Ϊģ̬����
        setSize(800, 600); // ���ô��ڴ�С��ȷ����ť�㹻��ʾ
        setLocationRelativeTo(parent);

        // ���ر���ͼƬ
        backgroundImage = new ImageIcon("src/Images/2.png").getImage(); // �滻Ϊʵ��·��

        // �Զ������
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // ����
        JLabel title = new JLabel("ѡ���սģʽ", JLabel.CENTER);
        title.setFont(new Font("����", Font.BOLD, 32)); // �������������С
        title.setForeground(Color.WHITE); // ������ɫ
        backgroundPanel.add(title, BorderLayout.NORTH);

        // ģʽ��ť���
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // ����͸��
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // ���Ӱ�ť��࣬���ⰴť����һ��
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ��ťλ����ߴ����
        addModeButton(buttonPanel, "���˶�ս", "single", gbc, 0);
        addModeButton(buttonPanel, "˫�˺���", "cooperative", gbc, 1);
        addModeButton(buttonPanel, "˫�˶�ս", "versus", gbc, 2);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        // �˳���ť
        JButton exitButton = createTransparentButton("�˳���Ϸ");
        exitButton.setFont(new Font("����", Font.BOLD, 22)); // �����˳���ť�Ĵ�С
        exitButton.addActionListener(e -> System.exit(0));
        backgroundPanel.add(exitButton, BorderLayout.SOUTH);
    }

    private void addModeButton(JPanel panel, String label, String mode, GridBagConstraints gbc, int yPos) {
        JButton button = createTransparentButton(label);
        button.setFont(new Font("����", Font.BOLD, 24)); // ������ť�������С
        button.addActionListener(e -> {
            selectedMode = mode; // ����ѡ���ģʽ
            dispose(); // �رղ˵�
        });
        gbc.gridy = yPos;
        gbc.weightx = 1; // ���Ӱ�ť��ˮƽ��չ
        panel.add(button, gbc);
    }

    private JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("����", Font.BOLD, 20)); // ��������
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
