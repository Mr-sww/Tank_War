package Object.StaticObject;

import Engine.Direction;
import Engine.GameFrame;
import Engine.GamePanel;
import Object.TankObject.Tank;

import java.awt.*;

/**
 * ��Ӫ��
 */

public class Home {
	private int x, y;
	private GamePanel tc;
	public static final int width = 60, length = 60; // ȫ�־�̬��������
	public static  int flag = 0;

	private boolean live = true;

	private static Toolkit tk = Toolkit.getDefaultToolkit(); // ȫ�־�̬����
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/home.jpg")), };
	}

	public Home(int x, int y, GamePanel tc) {// ���캯��������Home�Ĳ�������ֵ
		this.x = x;
		this.y = y;
		this.tc = tc; // ��ÿ���
		flag = 0;
	}

	public void gameOver(Graphics g) {

		tc.tanks.clear();// ������ҳ�湤��
		tc.metalWall.clear();
		tc.otherWall.clear();
		tc.bombTanks.clear();
		tc.theRiver.clear();
		tc.trees.clear();
		tc.bullets.clear();
		tc.homeTank.setLive(false);
		Color c = g.getColor(); // ���ò���
		g.setColor(Color.green);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.drawString("�����ˣ�", 220, 250);
		g.drawString("  ��Ϸ������ ", 220, 300);
		g.setFont(f);
		g.setColor(c);

	}

	public void draw(Graphics g) {

		if (live) { // ������ţ��򻭳�home
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
			gameOver(g); // ������Ϸ����

		}
	}

	public boolean isLive() { // �ж��Ƿ񻹻���
		return live;
	}

	public void setLive(boolean live) { // ��������
		this.live = live;
	}

	public Rectangle getRect() { // ���س�����ʵ��
		return new Rectangle(x, y, width, length);
	}

}
