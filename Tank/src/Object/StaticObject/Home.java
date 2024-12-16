package Object.StaticObject;

import Engine.Direction;
import Engine.GameFrame;
import Engine.GamePanel;
import Object.TankObject.Tank;

import java.awt.*;

/**
 * 大本营类
 */

public class Home {
	private int x, y;
	private GamePanel tc;
	public static final int width = 60, length = 60; // 全局静态变量长宽
	public static  int flag = 0;

	private boolean live = true;

	private static Toolkit tk = Toolkit.getDefaultToolkit(); // 全局静态变量
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/home.jpg")), };
	}

	public Home(int x, int y, GamePanel tc) {// 构造函数，传递Home的参数并赋值
		this.x = x;
		this.y = y;
		this.tc = tc; // 获得控制
		flag = 0;
	}

	public void gameOver(Graphics g) {

		tc.tanks.clear();// 作清理页面工作
		tc.metalWall.clear();
		tc.otherWall.clear();
		tc.bombTanks.clear();
		tc.theRiver.clear();
		tc.trees.clear();
		tc.bullets.clear();
		tc.homeTank.setLive(false);
		Color c = g.getColor(); // 设置参数
		g.setColor(Color.green);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.drawString("你输了！", 220, 250);
		g.drawString("  游戏结束！ ", 220, 300);
		g.setFont(f);
		g.setColor(c);

	}

	public void draw(Graphics g) {

		if (live) { // 如果活着，则画出home
			g.drawImage(homeImags[0], x, y,60,60, null);
			if(tc.tanks.size()==0 && flag == 0)
			{
				Tank t =new Tank(400, 300, false, Direction.D, tc,500);
				t.setLiveCount(-150);
				tc.tanks.add(t);
				flag=1;
			}
			for (int i = 0; i < tc.homeWall.size(); i++) {
				BrickWall w = tc.homeWall.get(i);
				w.draw(g);
			}
		} else {
			gameOver(g); // 调用游戏结束

		}
	}

	public boolean isLive() { // 判读是否还活着
		return live;
	}

	public void setLive(boolean live) { // 设置生命
		this.live = live;
	}

	public Rectangle getRect() { // 返回长方形实例
		return new Rectangle(x, y, width, length);
	}

}
