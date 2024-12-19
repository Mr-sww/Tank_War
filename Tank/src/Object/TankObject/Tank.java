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
 * ̹���ࣨ���õз�̹�˺����̹�ˣ�
 */

public class Tank {
	public static int speedX = 6, speedY = 6; // ��̬ȫ�ֱ����ٶ�
	private int oldSpeedX = speedX;
	private int oldSpeedY = speedY; // ��¼��һ�ε��ٶȣ����ڻָ��ٶ�
	public static int sspeedx = speedX; // ̹�˵��ٶȣ������仯���̹�˵��ٶ�
	public static int sspeedy = speedY;
	public static int count = 0;
	public static final int width = 50, length = 50; // ̹�˵�ȫ�ִ�С�����в��ɸı���
	private Direction direction = Direction.STOP; // ��ʼ��״̬Ϊ��ֹ
	private Direction Kdirection = Direction.U; // ��ʼ������Ϊ����
	public static int Tankblood=50;
	public int blood;
	GamePanel tc;
	private volatile Thread cooldownThread1;
	private volatile Thread cooldownThread2;
	private volatile Thread cooldownThread3; // ����volatile ���߳����������̵߳�ͬ������

	private boolean good;
	private int x, y;
	private int oldX, oldY;
	private boolean live = true; // ��ʼ��Ϊ����
	public int liveCount = 50; //�з�̹������ֵĬ��Ϊ50
	private int life = 200; // ��ҳ�ʼ����ֵ
	public int no; // ̹�˱��

	private static Random r = new Random();
	private int step = r.nextInt(10) + 5; // ����һ�������,���ģ��̹�˵��ƶ�·��


	public static int BulletsNumber=30;   //��ʼ��̹�˵Ŀ����ӵ�����
	private  int BulletsNNumber=BulletsNumber;
	public  int waittime =45;      //�ȴ�ʱ��
	private boolean flag =false;    //�ж������Ƿ��ӵ�����Ϊ0

	private boolean bL = false, bU = false, bR = false, bD = false;

	private static Toolkit tk = Toolkit.getDefaultToolkit();// �������
	private static Image[] tankImags = null; // �洢ȫ�־�̬
	private static Image[] tankImagsSingle = null; // �洢ȫ�־�̬
	private static Image[] tankImags1 = null; // �洢ȫ�־�̬
	private static Image[] tankImags2 = null; // �洢ȫ�־�̬
	private static Image[] tankImagsEnemy = null; // �洢ȫ�־�̬
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

	public Tank(int x, int y, boolean good, Direction dir, GamePanel tc,int blood,int no) {// Tank�Ĺ��캯��2
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
				tc.tanks.remove(this); // ɾ����Ч��
			}
			return;
		}
			new DrawBloodbBar().draw(g); // ���̹�˵�Ѫ����

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
			// ���ݷ���ѡ��̹�˵�ͼƬ
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

		move(); // ����move����
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
		if (y < 0) // ��ֹ�߳��涨����
			y = 0;
		if (x + Tank.width > GameConfig.MAIN_PANEL_WIDTH) // ����������ָ����߽�
			x = GameConfig.MAIN_PANEL_WIDTH - Tank.width;
		if (y + Tank.length > GameConfig.MAIN_PANEL_HEIGHT)
			y = GameConfig.MAIN_PANEL_HEIGHT - Tank.length;

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
		if(this.isGood() && this.no==1)
		{
			switch (key) {
				case KeyEvent.VK_R: // ������Rʱ�����¿�ʼ��Ϸ
					tc.tanks.clear(); // ����
					tc.bullets.clear();
					tc.trees.clear();
					tc.otherWall.clear();
					tc.homeWall.clear();
					tc.metalWall.clear();
					tc.homeTank.setLive(false);
					tc.homeTank = new Tank(300, 560, true, Direction.STOP, tc,200,1);// �����Լ����ֵ�λ��

					if (!tc.home.isLive()) // ��home��������
						tc.home.setLive(true);
					tc.init();
					break;
				case KeyEvent.VK_D: // �������Ҽ�
					bR = true;
					break;

				case KeyEvent.VK_A:// ���������
					bL = true;
					break;

				case KeyEvent.VK_W: // �������ϼ�
					bU = true;
					break;

				case KeyEvent.VK_S:// �������¼�
					bD = true;
					break;
				case KeyEvent.VK_G: // ���ټ�
					if (good==true&&live) { // �������̹��ʱ����
						sspeedx = 20; // ����
						sspeedy = 20; // ����
					}
					break;
			}
		}
		else if(this.isGood() && this.no==2){
			switch (key) {
				case KeyEvent.VK_R: // ������Rʱ�����¿�ʼ��Ϸ
					tc.tanks.clear(); // ����
					tc.bullets.clear();
					tc.trees.clear();
					tc.otherWall.clear();
					tc.homeWall.clear();
					tc.metalWall.clear();
					tc.homeTank.setLive(false);
					tc.homeTank = new Tank(300, 560, true, Direction.STOP, tc,200,1);// �����Լ����ֵ�λ��

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
				case KeyEvent.VK_L: // ���ټ�
					if (good==true&&live) { // �������̹��ʱ����
						sspeedx = 20; // ����
						sspeedy = 20; // ����
					}
					break;
			}
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

				case KeyEvent.VK_G: // �ͷż��ټ�
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

				case KeyEvent.VK_L: // �ͷż��ټ�
					if(good==true&&live){
						sspeedx = oldSpeedX;
						sspeedy = oldSpeedY;
					}
					break;
			}
		}

		decideDirection(); // �ͷż��̺�ȷ���ƶ�����
	}

	public Bullets fire() { // ���𷽷�
		if (!live)
			return null;
		if(live&&BulletsNumber==0){
			flag=true;
		}
		else{
			int x = this.x + Tank.width / 2 - Bullets.width / 2; // ����λ��
			int y = this.y + Tank.length / 2 - Bullets.length / 2;
			Bullets m = new Bullets(x, y + 2, good, Kdirection, this.tc,false); // û�и�������ʱ����ԭ���ķ��򷢻�
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

			// Ѫ����������̹���Ϸ�
			int barWidth = width; // Ѫ�������̹�˿��һ��
			int barHeight = 10;   // Ѫ���߶�
			int offsetY = -15;    // Ѫ����̹���Ϸ���ƫ����

			int barX = x;         // Ѫ����X������̹��һ��
			int barY = y + offsetY; // Ѫ����Y����

			// Ѫ������
			int currentWidth;
			if(good)
				currentWidth = barWidth * life / 200;
			else
				currentWidth = barWidth*liveCount/Tankblood;


			// ����Ѫ������
			g.drawRect(barX, barY, barWidth, barHeight);

			// ���Ѫ����
			g.fillRect(barX, barY, currentWidth, barHeight);

			// ����Ѫ����ֵ (xx/��Ѫ��)
			g.setColor(Color.WHITE); // ����������ɫ
			if(good)
				g.drawString(life + "/200", barX + barWidth / 4, barY + barHeight - 2);
			else
				g.drawString(liveCount + "/"+Tankblood, barX + barWidth / 4, barY + barHeight - 2);

			g.setColor(c); // �ָ�ԭʼ��ɫ
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
			g.setColor(c); // �ָ�ԭʼ��ɫ
		}
	}


	public boolean eat(Blood b) {//Ѫ��
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

	public boolean eat(Gun g) {//����ǹ
		if (this.live && g.isLive() && this.getRect().intersects(g.getRect())) {
			g.setLive(false); // ����ǹΪ������

			// ��鵱ǰ�Ƿ��е���ʱ�߳�������
			if (cooldownThread1 != null && cooldownThread1.isAlive()) {
				// ����������У����жϾ��߳�
				cooldownThread1.interrupt();
			}

			// �����µĵ���ʱ�߳�
			cooldownThread1 = new Thread(() -> {
				int countdown = 10; // ����ʱ
				for (int i = countdown; i > 0; i--) {
					// ����Ҫ����߳��Ƿ��ж�
					if (Thread.currentThread().isInterrupted()) {
						return; // ������жϣ��˳��߳�
					}
					// ���±�ǩ
					int finalI = i;
					SwingUtilities.invokeLater(() -> {
						if (GamePanel.countdownLabel1!= null) {
							GamePanel.countdownLabel1.setText("������ǹ��" + finalI + "��");
						}
					});
					try {
						Thread.sleep(1000); // ÿ������
					} catch (InterruptedException e) {
						// ������߱��жϣ�������߳�
						return;
					}
				}
				Gun.flag = false; // ����ʱ�������� flag ����Ϊ false
				SwingUtilities.invokeLater(() -> {
					if (GamePanel.countdownLabel1 != null) {
						GamePanel.countdownLabel1.setText("������ǹ�ѽ�����");
					}
				});
			});
			cooldownThread1.start(); // �����µĵ���ʱ�߳�
			Gun.flag = true; // ����ǹ���
			return true;
		}
		return false;
	}


	public boolean eat(Laser g) {//����
		if (this.live && g.isLive() && this.getRect().intersects(g.getRect())) {
			g.setLive(false); // ����ǹΪ������

			// ��鵱ǰ�Ƿ��е���ʱ�߳�������
			if (cooldownThread3 != null && cooldownThread3.isAlive()) {
				// ����������У����жϾ��߳�
				cooldownThread3.interrupt();
			}

			// �����µĵ���ʱ�߳�
			cooldownThread3 = new Thread(() -> {
				int countdown = 10; // ����ʱ
				for (int i = countdown; i > 0; i--) {
					// ����Ҫ����߳��Ƿ��ж�
					if (Thread.currentThread().isInterrupted()) {
						return; // ������жϣ��˳��߳�
					}
					// ���±�ǩ
					int finalI = i;
					SwingUtilities.invokeLater(() -> {
						if (GamePanel.countdownLabel3 != null) {
							GamePanel.countdownLabel3.setText("������" + finalI + "��");
						}
					});
					try {
						Thread.sleep(1000); // ÿ������
					} catch (InterruptedException e) {
						// ������߱��жϣ�������߳�
						return;
					}
				}
				Laser.flag = false; // ����ʱ�������� flag ����Ϊ false
				SwingUtilities.invokeLater(() -> {
					if (GamePanel.countdownLabel3 != null) {
						GamePanel.countdownLabel3.setText("�����ѽ�����");
					}
				});
			});
			cooldownThread3.start(); // �����µĵ���ʱ�߳�
			Laser.flag = true; // ����ǹ���
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