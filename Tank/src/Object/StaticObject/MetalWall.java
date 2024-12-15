package Object.StaticObject;

import Engine.GameFrame;

import Engine.GamePanel;
import java.awt.*;

/**
 * 金属墙类（钢板,子弹不可打穿）
 */
public class MetalWall {
	public static final int width = 60; // 设置金属墙的长宽静态全局参数
	public static final int length = 60;

	private int x, y;
	GamePanel tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/metalWall.gif")), };
	}

	public MetalWall(int x, int y, GamePanel tc) {// 构造函数，传递要构造的长宽并赋值
		this.x = x;
		this.y = y;
		this.tc = tc;
	}


	public void draw(Graphics g) { // 画金属墙
		g.drawImage(wallImags[0], x*GameFrame.ratio, y*GameFrame.ratio,60*GameFrame.ratio,60*GameFrame.ratio, null);
	}

	public Rectangle getRect() { // 构造指定参数的长方形实例
		return new Rectangle(x, y, width, length);
	}

}
