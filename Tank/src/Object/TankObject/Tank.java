package Object.TankObject;

import Engine.*;
import Engine.Direction;
import Object.StaticObject.BrickWall;
import Object.StaticObject.Home;
import Object.StaticObject.MetalWall;
import Object.StaticObject.River;
import Object.UseObject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 坦克类（适用敌方坦克和玩家坦克）
 */

public class Tank {
	public static int speedX = 6, speedY = 6; // 静态全局变量速度
	private int oldSpeedX = speedX;
	private int oldSpeedY = speedY; // 记录上一次的速度，用于恢复速度
	public static int sspeedx = speedX; // 坦克的速度，用来变化玩家坦克的速度
	public static int sspeedy = speedY;
	public static int count = 0;
	public static final int width = 50, length = 50; // 坦克的全局大小，具有不可改变性
	private Direction direction = Direction.STOP; // 初始化状态为静止
	private Direction Kdirection = Direction.U; // 初始化方向为向上
	public static int Tankblood=50;
	public int blood;
	GamePanel tc;
	private volatile Thread cooldownThread1;
	private volatile Thread cooldownThread2;
	private volatile Thread cooldownThread3; // 定义volatile 的线程用来避免线程的同步问题

	private boolean good;
	private int x, y;
	private int oldX, oldY;
	private boolean live = true; // 初始化为活着
	public int liveCount = 50; //敌方坦克生命值默认为50
	private int life = 200; // 玩家初始生命值
	public int no; // 坦克编号

	private static Random r = new Random();
	private int step = r.nextInt(10) + 5; // 产生一个随机数,随机模拟坦克的移动路径


	public static int BulletsNumber=30;   //初始化坦克的可用子弹数量
	private  int BulletsNNumber=BulletsNumber;
	public  int waittime =45;      //等待时间
	private boolean flag =false;    //判断现在是否子弹数量为0

	private boolean bL = false, bU = false, bR = false, bD = false;

	private static Toolkit tk = Toolkit.getDefaultToolkit();// 控制面板
	private static Image[] tankImags = null; // 存储全局静态
	private static Image[] tankImagsSingle = null; // 存储全局静态
	private static Image[] tankImags1 = null; // 存储全局静态
	private static Image[] tankImags2 = null; // 存储全局静态
	private static Image[] tankImagsEnemy = null; // 存储全局静态
	static {
		tankImagsEnemy = new Image[] {
				tk.getImage(BombTank.class.getResource("/Images/tankD.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankU.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankL.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankR.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankLD.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankLU.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankRD.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankRU.gif")),
		};
		tankImagsSingle = new Image[] {
				tk.getImage(BombTank.class.getResource("/Images/singletankD.png")),
				tk.getImage(BombTank.class.getResource("/Images/singletankU.png")),
				tk.getImage(BombTank.class.getResource("/Images/singletankL.png")),
				tk.getImage(BombTank.class.getResource("/Images/singletankR.png")),
				tk.getImage(BombTank.class.getResource("/Images/singletankLD.png")),
				tk.getImage(BombTank.class.getResource("/Images/singletankLU.png")),
				tk.getImage(BombTank.class.getResource("/Images/singletankRD.png")),
				tk.getImage(BombTank.class.getResource("/Images/singletankRU.png")),
		};
		tankImags1 = new Image[] {
				tk.getImage(BombTank.class.getResource("/Images/1tankD.png")),
				tk.getImage(BombTank.class.getResource("/Images/1tankU.png")),
				tk.getImage(BombTank.class.getResource("/Images/1tankL.png")),
				tk.getImage(BombTank.class.getResource("/Images/1tankR.png")),
				tk.getImage(BombTank.class.getResource("/Images/1tankLD.png")),
				tk.getImage(BombTank.class.getResource("/Images/1tankLU.png")),
				tk.getImage(BombTank.class.getResource("/Images/1tankRD.png")),
				tk.getImage(BombTank.class.getResource("/Images/1tankRU.png")),
		};
		tankImags2 = new Image[] {
				tk.getImage(BombTank.class.getResource("/Images/2tankD.png")),
				tk.getImage(BombTank.class.getResource("/Images/2tankU.png")),
				tk.getImage(BombTank.class.getResource("/Images/2tankL.png")),
				tk.getImage(BombTank.class.getResource("/Images/2tankR.png")),
				tk.getImage(BombTank.class.getResource("/Images/2tankLD.png")),
				tk.getImage(BombTank.class.getResource("/Images/2tankLU.png")),
				tk.getImage(BombTank.class.getResource("/Images/2tankRD.png")),
				tk.getImage(BombTank.class.getResource("/Images/2tankRU.png")),
		};
	}

	public Tank(int x, int y, boolean good) {// Tank的构造函数1
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
		this.liveCount = Tankblood;
	}
	public void setBlood(int blood)
	{
		Tankblood-=blood;
	}

	public Tank(int x, int y, boolean good, Direction dir, GamePanel tc,int blood,int no) {// Tank的构造函数2
		this(x, y, good);
		this.direction = dir;
		this.tc = tc;
		this.blood = blood;
		this.no=no;
	}
	public void setLiveCount(int liveCount) { this.liveCount -= liveCount;}
	public boolean getLiveCount()
	{
		if(liveCount <= 0) return true;
		else return false;
	}
	public void draw(Graphics g) {
		if (!live) {
			if (!good) {
				tc.tanks.remove(this); // 删除无效的
			}
			return;
		}
			new DrawBloodbBar().draw(g); // 玩家坦克的血量条

		if(good&&live&&flag&& waittime >=0){
			new DrawLoadBullets().draw(g);
		}

		if(good){
			if("Single".equals(GameFrame.gameMode))tankImags= tankImagsSingle;
			else{
				if(no==1)tankImags= tankImags1;
				else tankImags=tankImags2;
			}
		}else{
			tankImags=tankImagsEnemy;
		}

		switch (Kdirection) {
			// 根据方向选择坦克的图片
			case D:
				g.drawImage(tankImags[0], x, y,width,length, null);
				break;

			case U:
				g.drawImage(tankImags[1], x, y,width,length, null);
				break;
			case L:
				g.drawImage(tankImags[2], x, y,width,length, null);
				break;

			case R:
				g.drawImage(tankImags[3], x, y,width,length, null);
				break;
			case LD:
				g.drawImage(tankImags[4], x, y,width,length, null);
				break;
			case LU:
				g.drawImage(tankImags[5], x, y,width,length, null);
				break;
			case RD:
				g.drawImage(tankImags[6], x, y,width,length, null);
				break;
			case RU:
				g.drawImage(tankImags[7], x, y,width,length, null);
				break;
		}

		move(); // 调用move函数
	}

	void move() {

		this.oldX = x;
		this.oldY = y;
		if(good==true){
			speedX = sspeedx;
			speedY = sspeedy;
		}else{
			speedX = oldSpeedX;
			speedY = oldSpeedY;
		}

		switch (direction) { // 选择移动方向
			case L:
				x -= speedX;
				break;
			case U:
				y -= speedY;
				break;
			case R:
				x += speedX;
				break;
			case D:
				y += speedY;
				break;
			case STOP:
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

		if (this.direction != Direction.STOP) {
			this.Kdirection = this.direction;
		}

		if (x < 0)
			x = 0;
		if (y < 0) // 防止走出规定区域
			y = 0;
		if (x + Tank.width > GameConfig.MAIN_PANEL_WIDTH) // 超过区域则恢复到边界
			x = GameConfig.MAIN_PANEL_WIDTH - Tank.width;
		if (y + Tank.length > GameConfig.MAIN_PANEL_HEIGHT)
			y = GameConfig.MAIN_PANEL_HEIGHT - Tank.length;

		if (!good) {
			Direction[] directons = Direction.values();
			if (step == 0) {
				step = r.nextInt(12) + 3; // 产生随机路径
				int rn = r.nextInt(directons.length);
				direction = directons[rn]; // 产生随机方向
			}
			step--;

			if (r.nextInt(40) > 38)// 产生随机数，控制敌人开火
				this.fire();
		}
	}

	private void changToOldDir() {
		x = oldX;
		y = oldY;
	}

	public void keyPressed(KeyEvent e) { // 接受键盘事件
		int key = e.getKeyCode();
		GamePanel tc = this.tc;
		if(this.isGood() && this.no==1)
		{
			switch (key) {
				case KeyEvent.VK_R: // 当按下R时，重新开始游戏
					tc.tanks.clear(); // 清理
					tc.bullets.clear();
					tc.trees.clear();
					tc.otherWall.clear();
					tc.homeWall.clear();
					tc.metalWall.clear();
					tc.homeTank.setLive(false);
					tc.homeTank = new Tank(300, 560, true, Direction.STOP, tc,200,1);// 设置自己出现的位置

					if (!tc.home.isLive()) // 将home重置生命
						tc.home.setLive(true);
					tc.init();
					break;
				case KeyEvent.VK_D: // 监听向右键
					bR = true;
					break;

				case KeyEvent.VK_A:// 监听向左键
					bL = true;
					break;

				case KeyEvent.VK_W: // 监听向上键
					bU = true;
					break;

				case KeyEvent.VK_S:// 监听向下键
					bD = true;
					break;
				case KeyEvent.VK_G: // 加速键
					if (good==true&&live) { // 仅在玩家坦克时加速
						sspeedx = 20; // 加速
						sspeedy = 20; // 加速
					}
					break;
			}
		}
		else if(this.isGood() && this.no==2){
			switch (key) {
				case KeyEvent.VK_R: // 当按下R时，重新开始游戏
					tc.tanks.clear(); // 清理
					tc.bullets.clear();
					tc.trees.clear();
					tc.otherWall.clear();
					tc.homeWall.clear();
					tc.metalWall.clear();
					tc.homeTank.setLive(false);
					tc.homeTank = new Tank(300, 560, true, Direction.STOP, tc,200,1);// 设置自己出现的位置

					if (!tc.home.isLive()) // 将home重置生命
						tc.home.setLive(true);
					tc.init();
					break;
				case KeyEvent.VK_RIGHT: // 监听向右键
					bR = true;
					break;

				case KeyEvent.VK_LEFT:// 监听向左键
					bL = true;
					break;

				case KeyEvent.VK_UP: // 监听向上键
					bU = true;
					break;

				case KeyEvent.VK_DOWN:// 监听向下键
					bD = true;
					break;
				case KeyEvent.VK_L: // 加速键
					if (good==true&&live) { // 仅在玩家坦克时加速
						sspeedx = 20; // 加速
						sspeedy = 20; // 加速
					}
					break;
			}
		}
		decideDirection();// 调用函数确定移动方向
	}

	void decideDirection() {
		if (!bL && !bU && bR && !bD) // 向右移动
			direction = Direction.R;

		else if (bL && !bU && !bR && !bD) // 向左移
			direction = Direction.L;

		else if (!bL && bU && !bR && !bD) // 向上移动
			direction = Direction.U;

		else if (!bL && !bU && !bR && bD) // 向下移动
			direction = Direction.D;

		else if (!bL && !bU && !bR && !bD)
			direction = Direction.STOP; // 没有按键，就保持不动
		else if(bL && bU )
			direction = Direction.LU;
		else if(bR && bU)
			direction = Direction.RU;
		else if(bL && bD)
			direction = Direction.LD;
		else if(bR && bD)
			direction = Direction.RD;
	}

	public void keyReleased(KeyEvent e) { // 键盘释放监听
		int key = e.getKeyCode();
		if(this.isGood() && this.no==1)
		{
			switch (key) {
				case KeyEvent.VK_F:
					fire();
					break;

				case KeyEvent.VK_D:
					bR = false;
					break;

				case KeyEvent.VK_A:
					bL = false;
					break;

				case KeyEvent.VK_W:
					bU = false;
					break;

				case KeyEvent.VK_S:
					bD = false;
					break;

				case KeyEvent.VK_G: // 释放加速键
					if(good==true&&live){
						sspeedx = oldSpeedX;
						sspeedy = oldSpeedY;
					}
					break;
			}
		} else if (this.isGood() && this.no==2) {
			switch (key) {
				case KeyEvent.VK_K:
					fire();
					break;

				case KeyEvent.VK_RIGHT:
					bR = false;
					break;

				case KeyEvent.VK_LEFT:
					bL = false;
					break;

				case KeyEvent.VK_UP:
					bU = false;
					break;

				case KeyEvent.VK_DOWN:
					bD = false;
					break;

				case KeyEvent.VK_L: // 释放加速键
					if(good==true&&live){
						sspeedx = oldSpeedX;
						sspeedy = oldSpeedY;
					}
					break;
			}
		}

		decideDirection(); // 释放键盘后确定移动方向
	}

	public Bullets fire() { // 开火方法
		if (!live)
			return null;
		if(live&&BulletsNumber==0){
			flag=true;
		}
		else{
			int x = this.x + Tank.width / 2 - Bullets.width / 2; // 开火位置
			int y = this.y + Tank.length / 2 - Bullets.length / 2;
			Bullets m = new Bullets(x, y + 2, good, Kdirection, this.tc,false); // 没有给定方向时，向原来的方向发火
			tc.bullets.add(m);
			if(good){
				BulletsNumber--;
				GameFrame.soundManager.playSound("shoot");
			}

			return m;
		}
		return  null;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}


	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isGood() {
		return good;
	}

	public boolean collideWithWall(BrickWall w) { // 碰撞到普通墙时
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir(); // 转换到原来的方向上去
			return true;
		}
		return false;
	}

	public boolean collideWithWall(MetalWall w) { // 撞到金属墙
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}


	public boolean collideRiver(River r) { // 撞到河流的时候
		if (this.live && this.getRect().intersects(r.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideHome(Home h) { // 撞到家的时候
		if (this.live && this.getRect().intersects(h.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideWithTanks(java.util.List<Tank> tanks) {// 撞到坦克时
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {
				if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
					this.changToOldDir();
					t.changToOldDir();
					return true;
				}
			}
		}
		return false;
	}

	public int getLife() {
		if(good)
			return life;
		else
			return  liveCount;
	}

	public void setLife(int life) {
		if(good)
			this.life = life;
		else
			this.liveCount= life;
	}

	private class DrawBloodbBar {
		public void draw(Graphics g) {
			Color c = g.getColor();
			if(good){
				g.setColor(Color.green);
				if(life<=50){
					g.setColor(Color.RED);
				}
			}
			else{
				g.setColor(Color.pink);
			}

			// 血量条绘制在坦克上方
			int barWidth = width; // 血条宽度与坦克宽度一致
			int barHeight = 10;   // 血条高度
			int offsetY = -15;    // 血条在坦克上方的偏移量

			int barX = x;         // 血条的X坐标与坦克一致
			int barY = y + offsetY; // 血条的Y坐标

			// 血量计算
			int currentWidth;
			if(good)
				currentWidth = barWidth * life / 200;
			else
				currentWidth = barWidth*liveCount/Tankblood;


			// 绘制血条背景
			g.drawRect(barX, barY, barWidth, barHeight);

			// 填充血量条
			g.fillRect(barX, barY, currentWidth, barHeight);

			// 绘制血量数值 (xx/总血量)
			g.setColor(Color.WHITE); // 设置文字颜色
			if(good)
				g.drawString(life + "/200", barX + barWidth / 4, barY + barHeight - 2);
			else
				g.drawString(liveCount + "/"+Tankblood, barX + barWidth / 4, barY + barHeight - 2);

			g.setColor(c); // 恢复原始颜色
		}
	}


	private  class  DrawLoadBullets{
		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.blue);
			int lbheight=5;
			int lbwidth=45;
			int offsetY=40;
			int offsetx=-5;
			int blx = x+offsetx;
			int bly = y+offsetY;
			int currentwidth = --waittime;
			if(waittime==0){
				flag=false;
				waittime =lbwidth;
				BulletsNumber=BulletsNNumber;
			}
			g.drawRect(blx, bly, lbwidth, lbheight);
			g.fillRect(blx, bly,currentwidth,lbheight);
			g.setColor(c); // 恢复原始颜色
		}
	}


	public boolean eat(Blood b) {//血包
		if (this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			if (this.life <= 100)
				this.life = this.life + 100; // 每吃一个，增加100生命点
			else
				this.life = 200;
			b.setLive(false);
			return true;
		}
		return false;
	}

	public boolean eat(Gun g) {//霰弹枪
		if (this.live && g.isLive() && this.getRect().intersects(g.getRect())) {
			g.setLive(false); // 设置枪为不可用

			// 检查当前是否有倒计时线程在运行
			if (cooldownThread1 != null && cooldownThread1.isAlive()) {
				// 如果正在运行，先中断旧线程
				cooldownThread1.interrupt();
			}

			// 启动新的倒计时线程
			cooldownThread1 = new Thread(() -> {
				int countdown = 10; // 倒计时
				for (int i = countdown; i > 0; i--) {
					// 这里要检查线程是否被中断
					if (Thread.currentThread().isInterrupted()) {
						return; // 如果被中断，退出线程
					}
					// 更新标签
					int finalI = i;
					SwingUtilities.invokeLater(() -> {
						if (GamePanel.countdownLabel1!= null) {
							GamePanel.countdownLabel1.setText("三连发枪：" + finalI + "秒");
						}
					});
					try {
						Thread.sleep(1000); // 每秒休眠
					} catch (InterruptedException e) {
						// 如果休眠被中断，则结束线程
						return;
					}
				}
				Gun.flag = false; // 倒计时结束，将 flag 设置为 false
				SwingUtilities.invokeLater(() -> {
					if (GamePanel.countdownLabel1 != null) {
						GamePanel.countdownLabel1.setText("三连发枪已结束！");
					}
				});
			});
			cooldownThread1.start(); // 启动新的倒计时线程
			Gun.flag = true; // 激活枪标记
			return true;
		}
		return false;
	}


	public boolean eat(Laser g) {//导弹
		if (this.live && g.isLive() && this.getRect().intersects(g.getRect())) {
			g.setLive(false); // 设置枪为不可用

			// 检查当前是否有倒计时线程在运行
			if (cooldownThread3 != null && cooldownThread3.isAlive()) {
				// 如果正在运行，先中断旧线程
				cooldownThread3.interrupt();
			}

			// 启动新的倒计时线程
			cooldownThread3 = new Thread(() -> {
				int countdown = 10; // 倒计时
				for (int i = countdown; i > 0; i--) {
					// 这里要检查线程是否被中断
					if (Thread.currentThread().isInterrupted()) {
						return; // 如果被中断，退出线程
					}
					// 更新标签
					int finalI = i;
					SwingUtilities.invokeLater(() -> {
						if (GamePanel.countdownLabel3 != null) {
							GamePanel.countdownLabel3.setText("导弹：" + finalI + "秒");
						}
					});
					try {
						Thread.sleep(1000); // 每秒休眠
					} catch (InterruptedException e) {
						// 如果休眠被中断，则结束线程
						return;
					}
				}
				Laser.flag = false; // 倒计时结束，将 flag 设置为 false
				SwingUtilities.invokeLater(() -> {
					if (GamePanel.countdownLabel3 != null) {
						GamePanel.countdownLabel3.setText("导弹已结束！");
					}
				});
			});
			cooldownThread3.start(); // 启动新的倒计时线程
			Laser.flag = true; // 激活枪标记
			return true;
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}