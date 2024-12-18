package Object.UseObject;

import Engine.Direction;
import Engine.GameConfig;
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
 * �ӵ���
 */

public class Bullets {
	public static int speedX = 10;
	public static int speedY = 10; // �ӵ���ȫ�־�̬�ٶ�

	public static final int width = 10;
	public static final int length = 10;

	private int x, y;
	Direction diretion;

	private boolean good;
	private boolean live = true;
	private boolean isgood=false; // �Ƿ������������ӵ�
	private boolean isFire=false; // �Ƿ��Ѿ������

	private GamePanel tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bulletImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>(); // ����Map��ֵ�ԣ��ǲ�ͬ�����Ӧ��ͬ�ĵ�ͷ

	static {
		bulletImages = new Image[] { // ��ͬ������ӵ�
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletL.gif")),//�����ӵ�

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletU.gif")),//����

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletR.gif")),//����

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletD.gif")),//����

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletLD.gif")),//����

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletRD.gif")),//����

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletLU.gif")),//����

				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletRU.gif")),//����

		};

		imgs.put("L", bulletImages[0]); // ����Map����

		imgs.put("U", bulletImages[1]);

		imgs.put("R", bulletImages[2]);

		imgs.put("D", bulletImages[3]);

		imgs.put("LD", bulletImages[4]);

		imgs.put("RD", bulletImages[5]);

		imgs.put("LU", bulletImages[6]);

		imgs.put("RU", bulletImages[7]);
	}

	public Bullets(int x, int y, Direction dir,boolean isgood,boolean isFire) { // ���캯��1������λ�úͷ���
		this.x = x;
		this.y = y;
		this.diretion = dir;
		this.isgood=isgood;
		this.isFire=isFire;
	}

	// ���캯��2������������������
	public Bullets(int x, int y, boolean good, Direction dir, GamePanel tc,boolean isFire) {
		this(x, y,dir,good,isFire);
		this.good = good;
		this.tc = tc;
	}

	private void move() {

		switch (diretion) {
			case L:
				x -= speedX; // �ӵ������������
				break;

			case U: // �ӵ��������Ͻ���
				y -= speedY;
				break;

			case R:
				x += speedX; // �ӵ��������ҽ���
				break;

			case D: // �ӵ��������½���
				y += speedY;
				break;

			case STOP: // ��Ϸ��ͣ
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
			tc.bullets.remove(this); // �Ƴ����������ӵ�
			return;
		}

		switch (diretion) { // ѡ��ͬ������ӵ�
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
		move(); // �����ӵ�move()����
		if(Gun.flag==true && this.isgood==true && this.isFire==false)
		{
			Bullets m;
			switch (diretion) { // ѡ��ͬ������ӵ�
				case L:
					m = new Bullets(x, y + 2, good, LU, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, LD, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					break;

				case U:
					m = new Bullets(x, y + 2, good, LU, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, RU, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					break;

				case R:
					m = new Bullets(x, y + 2, good, RU, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, RD, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					break;

				case D:
					m = new Bullets(x, y + 2, good, LD, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, RD, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					break;

				case LD:
					m = new Bullets(x, y + 2, good, L, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, D, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					break;

				case STOP:
					break;
				case LU:
					m = new Bullets(x, y + 2, good, U, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, L, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					break;

				case RD:
					m = new Bullets(x, y + 2, good, R, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, D, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					break;

				case RU:
					m = new Bullets(x, y + 2, good, R, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					m = new Bullets(x, y + 2, good, U, this.tc,true); // û�и�������ʱ����ԭ���ķ��򷢻�
					tc.bullets.add(m);
					break;
			}
			this.isFire=true; // �ӵ��������
		}
	}


	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}


	public boolean hitTanks(List<Tank> tanks) {// ���ӵ���̹��ʱ
		for (int i = 0; i < tanks.size(); i++) {
			if (hitTank(tanks.get(i))) { // ��ÿһ��̹�ˣ�����hitTank
				return true;
			}
		}
		return false;
	}

	public boolean hitTank(Tank t) { // ���ӵ���̹����

		if (this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()) {
			BombTank e = new BombTank(t.getX(), t.getY(), tc);
			tc.bombTanks.add(e);
			t.setLife(t.getLife() - 50); // ��һ���ӵ���������50����4ǹ������������ֵ200
			if (t.getLife() <= 0)
				t.setLive(false); // ������Ϊ0ʱ����������Ϊ����״̬
			this.live = false;
			return true; // ����ɹ�������true
		}
		return false; // ���򷵻�false
	}

	public boolean hitWall(BrickWall w) { // �ӵ���CommonWall��
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			this.tc.otherWall.remove(w); // �ӵ���CommonWallǽ��ʱ���Ƴ��˻���ǽ
			this.tc.homeWall.remove(w);
			return true;
		}
		return false;
	}


	public boolean hitWall(MetalWall w) { // �ӵ��򵽽���ǽ��
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			if(Laser.flag==true)
			{
				this.tc.metalWall.remove(w); // �ӵ��򵽽���ǽ��ʱ���Ƴ��˻���ǽ
			}
			return true;
		}
		return false;
	}


	public boolean hitHome() { // ���ӵ��򵽴�Ӫʱ
		if (this.live && this.getRect().intersects(tc.home.getRect())) {
			this.live = false;
			this.tc.home.setLive(false); // ����Ӫ����һǹʱ�ͻ���
			return true;
		}
		return false;
	}


}
