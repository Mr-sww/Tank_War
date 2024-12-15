package Object.TankObject;

import Engine.*;
import Engine.Direction;
import Object.StaticObject.BrickWall;
import Object.StaticObject.Home;
import Object.StaticObject.MetalWall;
import Object.StaticObject.River;
import Object.UseObject.Blood;
import Object.UseObject.Bullets;
import Object.UseObject.Gun;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * ̹���ࣨ���õз�̹�˺����̹�ˣ�
 */

public class Tank {
	public static int speedX = 6, speedY = 6; // ��̬ȫ�ֱ����ٶ�
	public static int count = 0;
	public static final int width = 35, length = 35; // ̹�˵�ȫ�ִ�С�����в��ɸı���
	private Direction direction = Direction.STOP; // ��ʼ��״̬Ϊ��ֹ
	private Direction Kdirection = Direction.U; // ��ʼ������Ϊ����
	public static int Tankblood=50;
	public int blood;
	GamePanel tc;

	private boolean good;
	private int x, y;
	private int oldX, oldY;
	private boolean live = true; // ��ʼ��Ϊ����
	private int liveCount = 50; //Ĭ��Ϊ50
	private int life = 200; // ��ʼ����ֵ

	private static Random r = new Random();
	private int step = r.nextInt(10) + 5; // ����һ�������,���ģ��̹�˵��ƶ�·��

	private boolean bL = false, bU = false, bR = false, bD = false;

	private static Toolkit tk = Toolkit.getDefaultToolkit();// �������
	private static Image[] tankImags = null; // �洢ȫ�־�̬
	static {
		tankImags = new Image[] {
				tk.getImage(BombTank.class.getResource("/Images/tankD.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankU.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankL.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankR.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankLD.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankLU.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankRD.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankRU.gif")),
		};
	}

	public Tank(int x, int y, boolean good) {// Tank�Ĺ��캯��1
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

	public Tank(int x, int y, boolean good, Direction dir, GamePanel tc,int blood) {// Tank�Ĺ��캯��2
		this(x, y, good);
		this.direction = dir;
		this.tc = tc;
		this.blood = blood;
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
				tc.tanks.remove(this); // ɾ����Ч��
			}
			return;
		}

		if (good)
			new DrawBloodbBar().draw(g); // ���̹�˵�Ѫ����

		switch (Kdirection) {
			// ���ݷ���ѡ��̹�˵�ͼƬ
			case D:
				g.drawImage(tankImags[0], x, y, null);
				break;

			case U:
				g.drawImage(tankImags[1], x, y, null);
				break;
			case L:
				g.drawImage(tankImags[2], x, y, null);
				break;

			case R:
				g.drawImage(tankImags[3], x, y, null);
				break;
			case LD:
				g.drawImage(tankImags[4], x, y, null);
				break;
			case LU:
				g.drawImage(tankImags[5], x, y, null);
				break;
			case RD:
				g.drawImage(tankImags[6], x, y, null);
				break;
			case RU:
				g.drawImage(tankImags[7], x, y, null);
				break;
		}

		move(); // ����move����
	}

	void move() {

		this.oldX = x;
		this.oldY = y;

		switch (direction) { // ѡ���ƶ�����
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
		if (y < 40) // ��ֹ�߳��涨����
			y = 40;
		if (x + Tank.width > GameFrame.Frame_width) // ����������ָ����߽�
			x = GameFrame.Frame_width - Tank.width;
		if (y + Tank.length > GameFrame.Frame_length)
			y = GameFrame.Frame_length - Tank.length;

		if (!good) {
			Direction[] directons = Direction.values();
			if (step == 0) {
				step = r.nextInt(12) + 3; // �������·��
				int rn = r.nextInt(directons.length);
				direction = directons[rn]; // �����������
			}
			step--;

			if (r.nextInt(40) > 38)// ��������������Ƶ��˿���
				this.fire();
		}
	}

	private void changToOldDir() {
		x = oldX;
		y = oldY;
	}

	public void keyPressed(KeyEvent e) { // ���ܼ����¼�
		int key = e.getKeyCode();
		GamePanel tc = this.tc;
		switch (key) {
			case KeyEvent.VK_R: // ������Rʱ�����¿�ʼ��Ϸ
				tc.tanks.clear(); // ����
				tc.bullets.clear();
				tc.trees.clear();
				tc.otherWall.clear();
				tc.homeWall.clear();
				tc.metalWall.clear();
				tc.homeTank.setLive(false);
				tc.homeTank = new Tank(300, 560, true, Direction.STOP, tc,200);// �����Լ����ֵ�λ��

				if (!tc.home.isLive()) // ��home��������
					tc.home.setLive(true);
				tc.init();
				break;
			case KeyEvent.VK_RIGHT: // �������Ҽ�
				bR = true;
				break;

			case KeyEvent.VK_LEFT:// ���������
				bL = true;
				break;

			case KeyEvent.VK_UP: // �������ϼ�
				bU = true;
				break;

			case KeyEvent.VK_DOWN:// �������¼�
				bD = true;
				break;
		}
		decideDirection();// ���ú���ȷ���ƶ�����
	}

	void decideDirection() {
		if (!bL && !bU && bR && !bD) // �����ƶ�
			direction = Direction.R;

		else if (bL && !bU && !bR && !bD) // ������
			direction = Direction.L;

		else if (!bL && bU && !bR && !bD) // �����ƶ�
			direction = Direction.U;

		else if (!bL && !bU && !bR && bD) // �����ƶ�
			direction = Direction.D;

		else if (!bL && !bU && !bR && !bD)
			direction = Direction.STOP; // û�а������ͱ��ֲ���
		else if(bL && bU )
			direction = Direction.LU;
		else if(bR && bU)
			direction = Direction.RU;
		else if(bL && bD)
			direction = Direction.LD;
		else if(bR && bD)
			direction = Direction.RD;
	}

	public void keyReleased(KeyEvent e) { // �����ͷż���
		int key = e.getKeyCode();
		switch (key) {

			case KeyEvent.VK_F:
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

		}
		decideDirection(); // �ͷż��̺�ȷ���ƶ�����
	}

	public Bullets fire() { // ���𷽷�
		if (!live)
			return null;
		int x = this.x + Tank.width / 2 - Bullets.width / 2; // ����λ��
		int y = this.y + Tank.length / 2 - Bullets.length / 2;
		Bullets m = new Bullets(x, y + 2, good, Kdirection, this.tc); // û�и�������ʱ����ԭ���ķ��򷢻�
		tc.bullets.add(m);
		return m;
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

	public boolean collideWithWall(BrickWall w) { // ��ײ����ͨǽʱ
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir(); // ת����ԭ���ķ�����ȥ
			return true;
		}
		return false;
	}

	public boolean collideWithWall(MetalWall w) { // ײ������ǽ
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}


	public boolean collideRiver(River r) { // ײ��������ʱ��
		if (this.live && this.getRect().intersects(r.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideHome(Home h) { // ײ���ҵ�ʱ��
		if (this.live && this.getRect().intersects(h.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideWithTanks(java.util.List<Tank> tanks) {// ײ��̹��ʱ
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
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	private class DrawBloodbBar {
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);

			// Ѫ����������̹���Ϸ�
			int barWidth = width; // Ѫ�������̹�˿��һ��
			int barHeight = 10;   // Ѫ���߶�
			int offsetY = -15;    // Ѫ����̹���Ϸ���ƫ����

			int barX = x;         // Ѫ����X������̹��һ��
			int barY = y + offsetY; // Ѫ����Y����

			// Ѫ������
			int currentWidth = barWidth * life / 200;

			// ����Ѫ������
			g.drawRect(barX, barY, barWidth, barHeight);

			// ���Ѫ����
			g.fillRect(barX, barY, currentWidth, barHeight);

			// ����Ѫ����ֵ (xx/��Ѫ��)
			g.setColor(Color.WHITE); // ����������ɫ
			g.drawString(life + "/200", barX + barWidth / 4, barY + barHeight - 2);

			g.setColor(c); // �ָ�ԭʼ��ɫ
		}
	}



	public boolean eat(Blood b) {
		if (this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			if (this.life <= 100)
				this.life = this.life + 100; // ÿ��һ��������100������
			else
				this.life = 200;
			b.setLive(false);
			return true;
		}
		return false;
	}

	public boolean eat(Gun g) {
		if (this.live && g.isLive() && this.getRect().intersects(g.getRect()) && Gun.flag==false) {
			Gun.flag=true;
			g.setLive(false);
			return true;
		}
		return false;
	}

//	public boolean eat(Gun g) {
//		if (this.live && g.isLive() && this.getRect().intersects(g.getRect()) && Gun.flag==false) {
//			Gun.flag=true;
//			g.setLive(false);
//			return true;
//		}
//		return false;
//	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}