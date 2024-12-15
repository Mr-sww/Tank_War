package Engine;

import Object.TankObject.Tank;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Random;

// ����һ����Ϊ TankGenerator �Ĺ�����
public class TankGenerator {
    // ����һ����Ϊ gamePanel �Ĺ����ֶΣ����ڴ洢��Ϸ��������
    public GamePanel gamePanel;

    /**
     * ����һ���µ� TankGenerator ����
     */
    public TankGenerator() {}
        /**
     * ����Ϸ���������ָ��������̹��
     *
     * @param mp һ�� HashMap����������Ϸ��������п�������̹�˵�λ��
     * @param cnt Ҫ���ɵ�̹������
     */
    public void generateTank(HashMap<SimpleEntry<Integer, Integer>,Boolean> mp,int cnt) {
        Random r=new Random();
        // ����ָ��������̹��
        while(cnt-->0){
            while(true){
                // ��������к���
                int row= r.nextInt(21);
                int col= r.nextInt(21);
                // �����λ�ÿ�������̹��
                if(mp.containsKey((new SimpleEntry<>(row,col)))){
                    // �ڸ�λ������̹��
                    gamePanel.tanks.add(new Tank(col*60, row*60, false, Direction.D, gamePanel,50));
                    // �� mp ���Ƴ���λ��
                    mp.remove((new SimpleEntry<>(row,col)));
                    // �����ڲ�ѭ��
                    break;
                }
            }
        }
    }

    /**
     * ����̹����������������Ϸ���
     *
     * @param gamePanel Ҫ��������Ϸ���
     */
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
