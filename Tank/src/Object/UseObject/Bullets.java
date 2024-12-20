package Object.UseObject;

import Engine.Direction;
import Engine.GameConfig;
import Engine.GameFrame;
import Engine.GamePanel;
import Object.StaticObject.BrickWall;
import Object.StaticObject.MetalWall;
import Object.TankObject.BombTank;
import Object.TankObject.Tank;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Engine.Direction.*;

/**
 * 子弹类
 */

public class Bullets {
	public static int speedX = 10;
	public static int speedY = 10; // 子弹的全局静态速度

	public static final int width = 10;
	public static final int length = 10;

	private int x, y;
	Direction diretion;

	private boolean good;
	private boolean live = true;
	private boolean isgood=false; // 是否是玩家射出的子弹
	private boolean isFire=false; // 是否已经发射过

	private GamePanel tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bulletImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>(); // 定义Map键值对，是不同方向对应不同的弹头

	static {
		bulletImages = new Image[] { // 不同方向的子弹
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletL.gif")),//向左子弹

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletU.gif")),//向上

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletR.gif")),//向右

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletD.gif")),//向下

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletLD.gif")),//左下

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletRD.gif")),//右下

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletLU.gif")),//左上

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletRU.gif")),//右上

		};

		imgs.put("L", bulletImages[0]); // 加入Map容器

		imgs.put("U", bulletImages[1]);

		imgs.put("R", bulletImages[2]);

		imgs.put("D", bulletImages[3]);

		imgs.put("LD", bulletImages[4]);

		imgs.put("RD", bulletImages[5]);

		imgs.put("LU", bulletImages[6]);

		imgs.put("RU", bulletImages[7]);
	}

	public Bullets(int x, int y, Direction dir,boolean isgood,boolean isFire) { // 构造函数1，传递位置和方向
		this.x = x;
		this.y = y;
		this.diretion = dir;
		this.isgood=isgood;
		this.isFire=isFire;
	}

	// 构造函数2，接受另外两个参数
	public Bullets(int x, int y, boolean good, Direction dir, GamePanel tc,boolean isFire) {
		this(x, y,dir,good,isFire);
		this.good = good;
		this.tc = tc;
	}

	private void move() {

		switch (diretion) {
			case L:
				x -= speedX; // 子弹不断向左进攻
				break;

			case U: // 子弹不断向上进攻
				y -= speedY;
				break;

			case R:
				x += speedX; // 子弹不断向右进攻
				break;

			case D: // 子弹不断向下进攻
				y += speedY;
				break;

			case STOP: // 游戏暂停
				break;
			case LU:
				x -= speedX;
				y -= speedY;
				break;
			case RU:
				x += speedX;
				y -= speedY;
				break;
			case RD:
				x += speedX;
				y += speedY;
				break;
			case LD:
				x -= speedX;
				y += speedY;
				break;
		}

		if (x < 0 || y < 0 || x > GameConfig.MAIN_PANEL_WIDTH || y > GameConfig.MAIN_PANEL_HEIGHT) {
			live = false;
		}
	}

	public void draw(Graphics g) {
		if (!live) {
			tc.bullets.remove(this); // 移除已死亡的子弹
			return;
		}

		switch (diretion) { // 选择不同方向的子弹
			case L:
				g.drawImage(imgs.get("L"), x, y, null);
				break;

			case U:
				g.drawImage(imgs.get("U"), x, y, null);
				break;

			case R:
				g.drawImage(imgs.get("R"), x, y, null);
				break;

			case D:
				g.drawImage(imgs.get("D"), x, y, null);
				break;

			case LD:
				g.drawImage(imgs.get("LD"),x, y, null);
				break;

			case LU:
				g.drawImage(imgs.get("LU"),x, y, null);
				break;

			case RD:
				g.drawImage(imgs.get("RD"), x, y, null);
				break;

			case RU:
				g.drawImage(imgs.get("RU"), x, y, null);
				break;
		}
		move(); // 调用子弹move()函数
		if(Gun.flag==true && this.isgood==true && this.isFire==false)
		{
			Bullets m;
			switch (diretion) { // 选择不同方向的子弹
				case L:
					m = new Bullets(x, y + 2, good, LU, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, LD, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					break;

				case U:
					m = new Bullets(x, y + 2, good, LU, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, RU, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					break;

				case R:
					m = new Bullets(x, y + 2, good, RU, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, RD, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					break;

				case D:
					m = new Bullets(x, y + 2, good, LD, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, RD, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					break;

				case LD:
					m = new Bullets(x, y + 2, good, L, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, D, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					break;

				case STOP:
					break;
				case LU:
					m = new Bullets(x, y + 2, good, U, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, L, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					break;

				case RD:
					m = new Bullets(x, y + 2, good, R, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, D, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					break;

				case RU:
					m = new Bullets(x, y + 2, good, R, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, U, this.tc,true); // 没有给定方向时，向原来的方向发火
					tc.bullets.add(m);
					break;
			}
			this.isFire=true; // 子弹发射完毕
		}
	}


	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}


	public boolean hitTanks(List<Tank> tanks) {// 当子弹打到坦克时
		for (int i = 0; i < tanks.size(); i++) {
			if (hitTank(tanks.get(i))) { // 对每一辆坦克，调用hitTank
				return true;
			}
		}
		return false;
	}

	public boolean hitTank(Tank t) { // 当子弹打到坦克上

		if (this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()) {
			BombTank e = new BombTank(t.getX(), t.getY(), tc);
			tc.bombTanks.add(e);
			GameFrame.soundManager.playSound("explode");
			t.setLife(t.getLife() - 50); // 中一颗子弹寿命减少50，中4枪就死，总生命值200
			if (t.getLife() <= 0)
				t.setLive(false); // 当寿命为0时，设置寿命为死亡状态
			this.live = false;
			return true; // 射击成功，返回true
		}
		return false; // 否则返回false
	}

	public boolean hitWall(BrickWall w) { // 子弹打到CommonWall上
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			this.tc.otherWall.remove(w); // 子弹打到CommonWall墙上时则移除此击中墙
			this.tc.homeWall.remove(w);
			if(this.isgood){
				GameFrame.soundManager.playSound("wall");
			}
			return true;
		}
		return false;
	}


	public boolean hitWall(MetalWall w) { // 子弹打到金属墙上
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			if(Laser.flag==true)
			{
				this.tc.metalWall.remove(w); // 子弹打到金属墙上时则移除此击中墙
			}
			return true;
		}
		return false;
	}


	public boolean hitHome() { // 当子弹打到大本营时
		if (this.live && this.getRect().intersects(tc.home.getRect())) {
			this.live = false;
			this.tc.home.setLive(false); // 当大本营接受一枪时就毁灭
			return true;
		}
		return false;
	}


}
