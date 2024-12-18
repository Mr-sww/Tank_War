package Engine;

public class GameStatus {
    public int playerID;
    public int gameTime;
    public int enemyTankCount;
    public int playerTankLife;
    public int bulletCount;

    public GamePanel gamePanel;

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void update(){
        setGameTime(gamePanel.elapsedTime);
        setEnemyTankCount(gamePanel.tanks.size());
        setPlayerTankLife(gamePanel.homeTank.getLife());
        setBulletCount(gamePanel.homeTank.BulletsNumber);
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getEnemyTankCount() {
        return enemyTankCount;
    }

    public void setEnemyTankCount(int enemyTankCount) {
        this.enemyTankCount = enemyTankCount;
    }

    public int getPlayerTankLife() {
        return playerTankLife;
    }

    public void setPlayerTankLife(int playerTankLife) {
        this.playerTankLife = playerTankLife;
    }


    public int getBulletCount() {
        return bulletCount;
    }

    public void setBulletCount(int bulletCount) {
        this.bulletCount = bulletCount;
    }

}
