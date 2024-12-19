package Engine;

import java.awt.*;
import javax.swing.*;

public class SideGamePanel extends JPanel {
    CardLayout cardLayout;
    JPanel cardPanel;
    public JLabel gameTimeLabel;
    public JLabel leftTimeLabel;
    public JLabel enemyTankCountLabel;
    public JLabel playerTankLifeLabel;
    public JLabel bulletCountLabel;

    GameStatus gameStatus;
    public SideGamePanel(CardLayout cardLayout,JPanel cardPanel,int width, int height) {
        setPreferredSize(new Dimension(width, height));
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.setBackground(Color.GRAY);
    }
    public void init(){
        Font chineseFont=new Font("mplus_hzk_12", Font.ITALIC, 40);

        gameTimeLabel = new JLabel("��Ϸ��ʱ: " + gameStatus.getGameTime() + "��");
        gameTimeLabel.setFont(chineseFont);
        gameTimeLabel.setForeground(Color.GREEN);

        leftTimeLabel = new JLabel("ʣ��ʱ��: " + gameStatus.getLeftTime() + "��");
        leftTimeLabel.setFont(chineseFont);
        leftTimeLabel.setForeground(Color.GREEN);

        enemyTankCountLabel = new JLabel("�з�̹������: " + gameStatus.getEnemyTankCount());
        enemyTankCountLabel.setFont(chineseFont);
        enemyTankCountLabel.setForeground(Color.GREEN);

        playerTankLifeLabel = new JLabel("�������ֵ: " + gameStatus.getPlayerTankLife());
        playerTankLifeLabel.setFont(chineseFont);
        playerTankLifeLabel.setForeground(Color.GREEN);

        bulletCountLabel = new JLabel("ʣ���ӵ�����: " + gameStatus.getBulletCount());
        bulletCountLabel.setFont(chineseFont);
        bulletCountLabel.setForeground(Color.GREEN);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(gameTimeLabel);
        add(leftTimeLabel);
        add(enemyTankCountLabel);
        add(playerTankLifeLabel);
        add(bulletCountLabel);
        add(GamePanel.countdownLabel1);
        add(GamePanel.countdownLabel3);

        //setOpaque(false); // ʹ��ǩ����͸��
    }
    public void update() {
        gameTimeLabel.setText("��Ϸ��ʱ: " + gameStatus.getGameTime() + "��");
        leftTimeLabel.setText("ʣ��ʱ��: " + gameStatus.getLeftTime() + "��");
        enemyTankCountLabel.setText("�з�̹������: " + gameStatus.getEnemyTankCount());
        playerTankLifeLabel.setText("�������ֵ: " + gameStatus.getPlayerTankLife());
        bulletCountLabel.setText("ʣ���ӵ�����: " + gameStatus.getBulletCount());

        repaint();
    }
    public void clear(){
        // ��� JPanel
        this.removeAll();
        // ������֤�����»������
        this.revalidate();
        this.repaint();
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
