package Object.UseObject;

import Engine.GameFrame;
import Object.StaticObject.BrickWall;

import java.awt.*;
import java.util.Random;

/**
 * 导弹类
 */
public class Laser {

    public static final int width = 60;
    public static final int length = 60;
    public static  boolean flag = false;
    private int x, y;
    GameFrame tc;
    private static Random r = new Random();

    int step = 0;
    private boolean live = false;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private Image[] bloodImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/Laster.jpg")), };

    private int[][] poition = { { 155, 196 }, { 500, 58 }, { 80, 340 }, { 99, 199 }, { 345, 456 }, { 123, 321 },
            { 258, 413 } };

    public void draw(Graphics g) {
        if (r.nextInt(100) > 98) {
            this.live = true;
            move();
        }
        if (!live)
            return;
        g.drawImage(bloodImags[0], x, y,60,60, null);

    }

    private void move() {
        step++;
        if (step == poition.length) {
            step = 0;
        }
        x = poition[step][0];
        y = poition[step][1];

    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, length);
    }

    public boolean isLive() {// 判断是否还活着
        return live;
    }

    public void setLive(boolean live) { // 设置生命
        this.live = live;
    }
}