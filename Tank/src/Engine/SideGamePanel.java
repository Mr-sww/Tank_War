package Engine;

import java.awt.*;
import javax.swing.*;

public class SideGamePanel extends JPanel {
    CardLayout cardLayout;
    JPanel cardPanel;
    public JLabel gameTimeLabel;
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
        Font chineseFont=new Font("TimesRoman", Font.ITALIC, 40);

        gameTimeLabel = new JLabel("��Ϸʱ��: " + gameStatus.getGameTime() + "��");
        gameTimeLabel.setFont(chineseFont);
        gameTimeLabel.setForeground(Color.GREEN);

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
        add(enemyTankCountLabel);
        add(playerTankLifeLabel);
        add(bulletCountLabel);

        //setOpaque(false); // ʹ��ǩ����͸��
    }
    public void update() {
        gameTimeLabel.setText("��Ϸʱ��: " + gameStatus.getGameTime() + "��");
        enemyTankCountLabel.setText("�з�̹������: " + gameStatus.getEnemyTankCount());
        playerTankLifeLabel.setText("�������ֵ: " + gameStatus.getPlayerTankLife());
        bulletCountLabel.setText("ʣ���ӵ�����: " + gameStatus.getBulletCount());

        repaint();
    }
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
