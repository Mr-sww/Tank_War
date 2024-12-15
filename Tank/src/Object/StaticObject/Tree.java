package Object.StaticObject;

import Engine.GameFrame;

import Engine.GamePanel;
import java.awt.*;

/**
 * 树（丛林）类
 */

public class Tree {
	public static final int width = 60;
	public static final int length = 60;

	int x, y;
	GamePanel tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] treeImags = null;
	static {
		treeImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/tree.gif")), };
	}

	public Tree(int x, int y, GamePanel tc) { // Tree的构造方法，传递x，y和tc对象
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) { // 画出树
		g.drawImage(treeImags[0], x*GameFrame.ratio, y*GameFrame.ratio,60*GameFrame.ratio,60*GameFrame.ratio, null);
	}


}
