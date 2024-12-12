package Engine;

import Object.TankObject.Tank;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 坦克大战的主类
 */

public class GameFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5972735870004738773L;

	// TODO 窗口的大小需要确定
	// 定义游戏窗口的宽度，800 像素宽，额外增加 50 像素的宽度
	public static int Frame_width = 800 + 50;
	// 定义游戏窗口的长度，600 像素长，额外增加 100 像素的长度，并减去 2 像素
	public static int Frame_length = 600 + 100 - 2;


	public static boolean printable = true; // 记录暂停状态，此时线程不刷新界面
	public static int MapLevel=1;
	JMenuBar menubar;
	JMenu menu1,menu2,menu3,menu4,menu5;
	JMenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13;
	Image screenImage = null;
	static String gameMode=null;
	static String gameLevel =null;
	static String gameMap=null;

	CardLayout cardLayout;
	JPanel cardPanel;
	GamePanel gamePanel;


	public GameFrame() {
		initFrame();
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NewGame")) {
			gamePanel.printable = false;
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要开始新游戏！", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {

				gamePanel.printable = true;
				this.dispose();
				new GameFrame();
			} else {
				gamePanel.printable = true;
				gamePanel.GameStart();
			}

		} else if (e.getActionCommand().endsWith("Stop")) {// 暂停
			gamePanel.printable = false;
		} else if (e.getActionCommand().equals("Continue")) {// 继续
			if (!gamePanel.printable) {
				gamePanel.printable = true;
				gamePanel.GameStart();//重新启动游戏
			}
		} else if (e.getActionCommand().equals("Exit")) {// 退出
			gamePanel.printable = false;
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要退出吗", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				System.out.println("退出");
				System.exit(0);
			} else {
				gamePanel.printable = true;
				gamePanel.GameStart();//重新启动游戏
			}
		} else if (e.getActionCommand().equals("Help")) {
			gamePanel.printable = false;
			JOptionPane.showMessageDialog(null, "用→ ← ↑ ↓控制方向，F键盘发射，R重新开始！", "提示！", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			gamePanel.printable = true;
			gamePanel.GameStart();//重新启动游戏
		} else if (e.getActionCommand().startsWith("Level")) {
			setLevel(e.getActionCommand());
			gamePanel.tanks.clear(); // 清理
			gamePanel.bullets.clear();
			gamePanel.trees.clear();
			gamePanel.otherWall.clear();
			gamePanel.homeWall.clear();
			gamePanel.metalWall.clear();
			gamePanel.homeTank.setLive(false);

			gamePanel.homeTank = new Tank(300, 560, true, Direction.STOP,gamePanel);// 设置自己出现的位置

			if (!gamePanel.home.isLive()) // 将home重置生命
				gamePanel.home.setLive(true);
			gamePanel.init();
		}else if (e.getActionCommand().startsWith("Map")) {
			String selectedLevel = e.getActionCommand();
			setMap(e.getActionCommand());
			gamePanel.tanks.clear(); // 清理
			gamePanel.bullets.clear();
			gamePanel.trees.clear();
			gamePanel.otherWall.clear();
			gamePanel.homeWall.clear();
			gamePanel.metalWall.clear();
			gamePanel.homeTank.setLive(false);

			gamePanel.homeTank = new Tank(300, 560, true, Direction.STOP,gamePanel);// 设置自己出现的位置

			if (!gamePanel.home.isLive()) // 将home重置生命
				gamePanel.home.setLive(true);
			gamePanel.init();
		}

	}

	private void setMap(String map) {
		gameMap = map;
	}

	private void setLevel(String level) {
		gameLevel = level;
	}

	public void initFrame() {
		// 创建菜单及菜单选项
		menubar = new JMenuBar();

		// 创建一个字体对象，字体为微软雅黑，样式为普通，大小为 15 磅
		Font chineseFont = new Font("微软雅黑", Font.PLAIN, 15);

		// 创建并添加各个菜单和菜单项
		{
			menu1 = MenuFactory.createMenu("游戏", chineseFont);
			item1 = MenuFactory.createItem("开始新游戏", chineseFont, this, "NewGame");
			item2 = MenuFactory.createItem("退出", chineseFont, this, "Exit");
			menu1.add(item1);
			menu1.add(item2);
		}

		{
			menu2 = MenuFactory.createMenu("暂停/继续", chineseFont);
			item3 = MenuFactory.createItem("暂停", chineseFont, this, "Stop");
			item4 = MenuFactory.createItem("继续", chineseFont, this, "Continue");
			menu2.add(item3);
			menu2.add(item4);
		}

		{
			menu3 = MenuFactory.createMenu("游戏级别", chineseFont);
			item5 = MenuFactory.createItem("级别1", chineseFont, this, "Level1");
			item6 = MenuFactory.createItem("级别2", chineseFont, this, "Level2");
			item7 = MenuFactory.createItem("级别3", chineseFont, this, "Level3");
			item8 = MenuFactory.createItem("级别4", chineseFont, this, "Level4");
			menu3.add(item5);
			menu3.add(item6);
			menu3.add(item7);
			menu3.add(item8);
		}

		{
			menu4 = MenuFactory.createMenu("游戏关卡", chineseFont);
			item9 = MenuFactory.createItem("关卡1", chineseFont, this, "Map1");
			item10 = MenuFactory.createItem("关卡2", chineseFont, this, "Map2");
			item11 = MenuFactory.createItem("关卡3", chineseFont, this, "Map3");
			item12 = MenuFactory.createItem("关卡4", chineseFont, this, "Map4");
			menu4.add(item9);
			menu4.add(item10);
			menu4.add(item11);
			menu4.add(item12);
		}

		{
			menu5 = MenuFactory.createMenu("帮助", chineseFont);
			item13 = MenuFactory.createItem("游戏说明", chineseFont, this, "Help");
			menu5.add(item13);
		}

		{
			menubar.add(menu1);
			menubar.add(menu2);
			menubar.add(menu3);
			menubar.add(menu4);
			menubar.add(menu5); // 确保所有菜单都被添加
		}

		// 菜单Bar放到JFrame上
		this.setJMenuBar(menubar);

		// 设置窗口的图标为指定的图片
		this.setIconImage(ResourceManager.loadImage("/Images/frameIcon.jpg"));
		// 设置窗口的标题
		this.setTitle("坦克大战——(重新开始：R键  开火：F键)");

		// 设置窗口的首选大小
		cardPanel = new JPanel(cardLayout);
		cardPanel.setPreferredSize(new Dimension(Frame_width, Frame_length));

		// 开始卡片布局
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		ModeMenuPanel modeMenuPanel = new ModeMenuPanel(cardLayout, cardPanel);
		MapMenuPanel mapMenuPanel = new MapMenuPanel(cardLayout, cardPanel);
		LevelMenuPanel levelMenuPanel = new LevelMenuPanel(cardLayout, cardPanel);
		gamePanel = new GamePanel(cardLayout, cardPanel);

		levelMenuPanel.setGamePanel(gamePanel);

		cardPanel.add(modeMenuPanel, "ModeMenuPanel");
		cardPanel.add(mapMenuPanel, "MapMenuPanel");
		cardPanel.add(levelMenuPanel, "LevelMenuPanel");
		cardPanel.add(gamePanel, "GamePanel");
		// 显示游戏模式菜单
		cardLayout.show(cardPanel, "ModeMenuPanel");

		// 将 cardPanel 添加到 JFrame
		this.getContentPane().add(cardPanel);

		// 调整窗口大小以适应内容
		this.setSize(Frame_width, Frame_length);

		// 设置窗口相对于指定组件的位置。null 参数表示窗口位于屏幕的中央
		this.setLocationRelativeTo(null);
		// 设置窗口关闭时的默认操作。EXIT_ON_CLOSE 表示退出程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口是否可见。true 表示窗口可见
		this.setVisible(true);
		// 设置窗口是否可调整大小。true 表示窗口可调整大小
		this.setResizable(true);

	}

}

