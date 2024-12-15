package Object.StaticObject;

import Engine.GameFrame;

import Engine.GamePanel;
import java.awt.*;

/**
 * שǽ�ࣨ�ӵ��ɴ򴩣�
 */
public class BrickWall {
	public static final int width = 60; // ����ǽ�Ĺ̶�����
	public static final int length = 60;


	int x, y;

	GamePanel tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { // ����commonWall��ͼƬ
				tk.getImage(BrickWall.class.getResource("/Images/commonWall.gif")), };
	}

	public BrickWall(int x, int y, GamePanel tc) { // ���캯��
		this.x = x;
		this.y = y;
		this.tc = tc; // ��ý������
	}

	public void draw(Graphics g) {// ��commonWall
		g.drawImage(wallImags[0], x*GameFrame.ratio, y*GameFrame.ratio,60*GameFrame.ratio,60*GameFrame.ratio, null);
	}


	public Rectangle getRect() { // ����ָ�������ĳ�����ʵ��
		return new Rectangle(x, y, width, length);
	}


}
