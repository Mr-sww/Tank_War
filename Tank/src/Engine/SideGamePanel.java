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

        gameTimeLabel = new JLabel("游戏耗时: " + gameStatus.getGameTime() + "秒");
        gameTimeLabel.setFont(chineseFont);
        gameTimeLabel.setForeground(Color.GREEN);

        leftTimeLabel = new JLabel("剩余时间: " + gameStatus.getLeftTime() + "秒");
        leftTimeLabel.setFont(chineseFont);
        leftTimeLabel.setForeground(Color.GREEN);

        enemyTankCountLabel = new JLabel("敌方坦克数量: " + gameStatus.getEnemyTankCount());
        enemyTankCountLabel.setFont(chineseFont);
        enemyTankCountLabel.setForeground(Color.GREEN);

        playerTankLifeLabel = new JLabel("玩家生命值: " + gameStatus.getPlayerTankLife());
        playerTankLifeLabel.setFont(chineseFont);
        playerTankLifeLabel.setForeground(Color.GREEN);

        bulletCountLabel = new JLabel("剩余子弹个数: " + gameStatus.getBulletCount());
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

        //setOpaque(false); // 使标签背景透明
    }
    public void update() {
        gameTimeLabel.setText("游戏耗时: " + gameStatus.getGameTime() + "秒");
        leftTimeLabel.setText("剩余时间: " + gameStatus.getLeftTime() + "秒");
        enemyTankCountLabel.setText("敌方坦克数量: " + gameStatus.getEnemyTankCount());
        playerTankLifeLabel.setText("玩家生命值: " + gameStatus.getPlayerTankLife());
        bulletCountLabel.setText("剩余子弹个数: " + gameStatus.getBulletCount());

        repaint();
    }
    public void clear(){
        // 清空 JPanel
        this.removeAll();
        // 重新验证和重新绘制面板
        this.revalidate();
        this.repaint();
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
