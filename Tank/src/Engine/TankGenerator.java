package Engine;

import Object.TankObject.Tank;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Random;

// 定义一个名为 TankGenerator 的公共类
public class TankGenerator {
    // 声明一个名为 gamePanel 的公共字段，用于存储游戏面板的引用
    public GamePanel gamePanel;

    /**
     * 构造一个新的 TankGenerator 对象
     */
    public TankGenerator() {}
        /**
     * 在游戏面板上生成指定数量的坦克
     *
     * @param mp 一个 HashMap，包含了游戏面板上所有可以生成坦克的位置
     * @param cnt 要生成的坦克数量
     */
    public void generateTank(HashMap<SimpleEntry<Integer, Integer>,Boolean> mp,int cnt) {
        Random r=new Random();
        // 生成指定数量的坦克
        while(cnt-->0){
            while(true){
                // 随机生成行和列
                int row= r.nextInt(21);
                int col= r.nextInt(21);
                // 如果该位置可以生成坦克
                if(mp.containsKey((new SimpleEntry<>(row,col)))){
                    // 在该位置生成坦克
                    gamePanel.tanks.add(new Tank(col*60, row*60, false, Direction.D, gamePanel,50));
                    // 从 mp 中移除该位置
                    mp.remove((new SimpleEntry<>(row,col)));
                    // 跳出内层循环
                    break;
                }
            }
        }
    }

    /**
     * 设置坦克生成器关联的游戏面板
     *
     * @param gamePanel 要关联的游戏面板
     */
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
