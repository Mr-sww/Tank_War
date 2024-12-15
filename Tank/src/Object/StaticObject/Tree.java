package Object.StaticObject;

import Engine.GameFrame;

import Engine.GamePanel;
import java.awt.*;

/**
 * �������֣���
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

	public Tree(int x, int y, GamePanel tc) { // Tree�Ĺ��췽��������x��y��tc����
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) { // ������
		g.drawImage(treeImags[0], x*GameFrame.ratio, y*GameFrame.ratio,60*GameFrame.ratio,60*GameFrame.ratio, null);
	}


}
