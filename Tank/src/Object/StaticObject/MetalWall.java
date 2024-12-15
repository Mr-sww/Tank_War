package Object.StaticObject;

import Engine.GameFrame;

import Engine.GamePanel;
import java.awt.*;

/**
 * ����ǽ�ࣨ�ְ�,�ӵ����ɴ򴩣�
 */
public class MetalWall {
	public static final int width = 60; // ���ý���ǽ�ĳ���̬ȫ�ֲ���
	public static final int length = 60;

	private int x, y;
	GamePanel tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/metalWall.gif")), };
	}

	public MetalWall(int x, int y, GamePanel tc) {// ���캯��������Ҫ����ĳ�����ֵ
		this.x = x;
		this.y = y;
		this.tc = tc;
	}


	public void draw(Graphics g) { // ������ǽ
		g.drawImage(wallImags[0], x*GameFrame.ratio, y*GameFrame.ratio,60*GameFrame.ratio,60*GameFrame.ratio, null);
	}

	public Rectangle getRect() { // ����ָ�������ĳ�����ʵ��
		return new Rectangle(x, y, width, length);
	}

}
