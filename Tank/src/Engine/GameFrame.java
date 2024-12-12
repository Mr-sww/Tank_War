package Engine;

import Object.TankObject.Tank;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * ̹�˴�ս������
 */

public class GameFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5972735870004738773L;

	// TODO ���ڵĴ�С��Ҫȷ��
	// ������Ϸ���ڵĿ�ȣ�800 ���ؿ��������� 50 ���صĿ��
	public static int Frame_width = 800 + 50;
	// ������Ϸ���ڵĳ��ȣ�600 ���س����������� 100 ���صĳ��ȣ�����ȥ 2 ����
	public static int Frame_length = 600 + 100 - 2;


	public static boolean printable = true; // ��¼��ͣ״̬����ʱ�̲߳�ˢ�½���
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
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ��ʼ����Ϸ��", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {

				gamePanel.printable = true;
				this.dispose();
				new GameFrame();
			} else {
				gamePanel.printable = true;
				gamePanel.GameStart();
			}

		} else if (e.getActionCommand().endsWith("Stop")) {// ��ͣ
			gamePanel.printable = false;
		} else if (e.getActionCommand().equals("Continue")) {// ����
			if (!gamePanel.printable) {
				gamePanel.printable = true;
				gamePanel.GameStart();//����������Ϸ
			}
		} else if (e.getActionCommand().equals("Exit")) {// �˳�
			gamePanel.printable = false;
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ�˳���", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				System.out.println("�˳�");
				System.exit(0);
			} else {
				gamePanel.printable = true;
				gamePanel.GameStart();//����������Ϸ
			}
		} else if (e.getActionCommand().equals("Help")) {
			gamePanel.printable = false;
			JOptionPane.showMessageDialog(null, "�á� �� �� �����Ʒ���F���̷��䣬R���¿�ʼ��", "��ʾ��", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			gamePanel.printable = true;
			gamePanel.GameStart();//����������Ϸ
		} else if (e.getActionCommand().startsWith("Level")) {
			setLevel(e.getActionCommand());
			gamePanel.tanks.clear(); // ����
			gamePanel.bullets.clear();
			gamePanel.trees.clear();
			gamePanel.otherWall.clear();
			gamePanel.homeWall.clear();
			gamePanel.metalWall.clear();
			gamePanel.homeTank.setLive(false);

			gamePanel.homeTank = new Tank(300, 560, true, Direction.STOP,gamePanel);// �����Լ����ֵ�λ��

			if (!gamePanel.home.isLive()) // ��home��������
				gamePanel.home.setLive(true);
			gamePanel.init();
		}else if (e.getActionCommand().startsWith("Map")) {
			String selectedLevel = e.getActionCommand();
			setMap(e.getActionCommand());
			gamePanel.tanks.clear(); // ����
			gamePanel.bullets.clear();
			gamePanel.trees.clear();
			gamePanel.otherWall.clear();
			gamePanel.homeWall.clear();
			gamePanel.metalWall.clear();
			gamePanel.homeTank.setLive(false);

			gamePanel.homeTank = new Tank(300, 560, true, Direction.STOP,gamePanel);// �����Լ����ֵ�λ��

			if (!gamePanel.home.isLive()) // ��home��������
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
		// �����˵����˵�ѡ��
		menubar = new JMenuBar();

		// ����һ�������������Ϊ΢���źڣ���ʽΪ��ͨ����СΪ 15 ��
		Font chineseFont = new Font("΢���ź�", Font.PLAIN, 15);

		// ��������Ӹ����˵��Ͳ˵���
		{
			menu1 = MenuFactory.createMenu("��Ϸ", chineseFont);
			item1 = MenuFactory.createItem("��ʼ����Ϸ", chineseFont, this, "NewGame");
			item2 = MenuFactory.createItem("�˳�", chineseFont, this, "Exit");
			menu1.add(item1);
			menu1.add(item2);
		}

		{
			menu2 = MenuFactory.createMenu("��ͣ/����", chineseFont);
			item3 = MenuFactory.createItem("��ͣ", chineseFont, this, "Stop");
			item4 = MenuFactory.createItem("����", chineseFont, this, "Continue");
			menu2.add(item3);
			menu2.add(item4);
		}

		{
			menu3 = MenuFactory.createMenu("��Ϸ����", chineseFont);
			item5 = MenuFactory.createItem("����1", chineseFont, this, "Level1");
			item6 = MenuFactory.createItem("����2", chineseFont, this, "Level2");
			item7 = MenuFactory.createItem("����3", chineseFont, this, "Level3");
			item8 = MenuFactory.createItem("����4", chineseFont, this, "Level4");
			menu3.add(item5);
			menu3.add(item6);
			menu3.add(item7);
			menu3.add(item8);
		}

		{
			menu4 = MenuFactory.createMenu("��Ϸ�ؿ�", chineseFont);
			item9 = MenuFactory.createItem("�ؿ�1", chineseFont, this, "Map1");
			item10 = MenuFactory.createItem("�ؿ�2", chineseFont, this, "Map2");
			item11 = MenuFactory.createItem("�ؿ�3", chineseFont, this, "Map3");
			item12 = MenuFactory.createItem("�ؿ�4", chineseFont, this, "Map4");
			menu4.add(item9);
			menu4.add(item10);
			menu4.add(item11);
			menu4.add(item12);
		}

		{
			menu5 = MenuFactory.createMenu("����", chineseFont);
			item13 = MenuFactory.createItem("��Ϸ˵��", chineseFont, this, "Help");
			menu5.add(item13);
		}

		{
			menubar.add(menu1);
			menubar.add(menu2);
			menubar.add(menu3);
			menubar.add(menu4);
			menubar.add(menu5); // ȷ�����в˵��������
		}

		// �˵�Bar�ŵ�JFrame��
		this.setJMenuBar(menubar);

		// ���ô��ڵ�ͼ��Ϊָ����ͼƬ
		this.setIconImage(ResourceManager.loadImage("/Images/frameIcon.jpg"));
		// ���ô��ڵı���
		this.setTitle("̹�˴�ս����(���¿�ʼ��R��  ����F��)");

		// ���ô��ڵ���ѡ��С
		cardPanel = new JPanel(cardLayout);
		cardPanel.setPreferredSize(new Dimension(Frame_width, Frame_length));

		// ��ʼ��Ƭ����
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
		// ��ʾ��Ϸģʽ�˵�
		cardLayout.show(cardPanel, "ModeMenuPanel");

		// �� cardPanel ��ӵ� JFrame
		this.getContentPane().add(cardPanel);

		// �������ڴ�С����Ӧ����
		this.setSize(Frame_width, Frame_length);

		// ���ô��������ָ�������λ�á�null ������ʾ����λ����Ļ������
		this.setLocationRelativeTo(null);
		// ���ô��ڹر�ʱ��Ĭ�ϲ�����EXIT_ON_CLOSE ��ʾ�˳�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô����Ƿ�ɼ���true ��ʾ���ڿɼ�
		this.setVisible(true);
		// ���ô����Ƿ�ɵ�����С��true ��ʾ���ڿɵ�����С
		this.setResizable(true);

	}

}

