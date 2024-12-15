package Object.StaticObject;

import Engine.GameFrame;

import Engine.GamePanel;
import java.awt.*;

/**
 * 砖墙类（子弹可打穿）
 */
public class BrickWall {
	public static final int width = 60; // 设置墙的固定参数
	public static final int length = 60;


	int x, y;

	GamePanel tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { // 储存commonWall的图片
				tk.getImage(BrickWall.class.getResource("/Images/commonWall.gif")), };
	}

	public BrickWall(int x, int y, GamePanel tc) { // 构造函数
		this.x = x;
		this.y = y;
		this.tc = tc; // 获得界面控制
	}

	public void draw(Graphics g) {// 画commonWall
		g.drawImage(wallImags[0], x*GameFrame.ratio, y*GameFrame.ratio,60*GameFrame.ratio,60*GameFrame.ratio, null);
	}


	public Rectangle getRect() { // 构造指定参数的长方形实例
		return new Rectangle(x, y, width, length);
	}


}
