package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * ̹�˴�ս������
 */

public class GameFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5972735870004738773L;
	private static List<Data> historyRecords=new ArrayList<Data>();//�����ʷ��¼

	public static boolean printable = true; // ��¼��ͣ״̬����ʱ�̲߳�ˢ�½���
	JMenuBar menubar;
	JMenu menu1, menu2, menu3, menu4, menu5,menu6;
	JMenuItem item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13,item14;

	static String gameMode = null;
	static String gameLevel = null;
	static String gameMap = null;

	CardLayout cardLayout;
	JPanel cardPanel;
	GamePanel gamePanel;
	MusicPlayer bgm;

	public GameFrame() {
		initFrame();
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NewGame")) {
			printable = false;
			Object[] options = {"ȷ��", "ȡ��"};
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
				gamePanel.GameStart();
			}
		} else if (e.getActionCommand().equals("Exit")) {// �˳�
			gamePanel.printable = false;
			Object[] options = {"ȷ��", "ȡ��"};
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ�˳���", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				System.exit(0);
			} else {
				gamePanel.printable = true;
				gamePanel.GameStart();
			}
		} else if (e.getActionCommand().equals("Help")) {
			gamePanel.printable = false;
			JOptionPane.showMessageDialog(null, "�á� �� �� �����Ʒ���F���̷��䣬R���¿�ʼ��", "��ʾ��", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			gamePanel.printable = true;
			gamePanel.GameStart();
		} else if (e.getActionCommand().startsWith("Level")) {
			setLevel(e.getActionCommand());
			gamePanel.init();
		} else if (e.getActionCommand().startsWith("Map")) {
			setMap(e.getActionCommand());
			gamePanel.init();
		}else if(e.getActionCommand().equals("history")) {
			showHistory(); // ��ʾ��ʷ��¼
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
			item9 = MenuFactory.createItem("��ͼ1", chineseFont, this, "Map1");
			item10 = MenuFactory.createItem("��ͼ2", chineseFont, this, "Map2");
			item11 = MenuFactory.createItem("��ͼ3", chineseFont, this, "Map3");
			item12 = MenuFactory.createItem("��ͼ4", chineseFont, this, "Map4");
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
			menu6 = MenuFactory.createMenu("��ʷ��¼", chineseFont);
			item14 = MenuFactory.createItem("�鿴��ʷ��¼", chineseFont, this, "history");
			menu6.add(item14);
		}
		{
			menubar.add(menu1);
			menubar.add(menu2);
			menubar.add(menu3);
			menubar.add(menu4);
			menubar.add(menu5);
			menubar.add(menu6); // ȷ�����в˵��������
		}

		// �˵�Bar�ŵ�JFrame��
		this.setJMenuBar(menubar);

		// ���ô��ڵ�ͼ��Ϊָ����ͼƬ
		this.setIconImage(ResourceManager.loadImage("/Images/frameIcon.jpg"));
		// ���ô��ڵı���
		this.setTitle("̹�˴�ս����(���¿�ʼ��R��  ����F��)");

		// ��ʼ��Ƭ����
        cardLayout = new CardLayout();
        // ����һ���µ�JPanel��ʹ�ÿ�Ƭ����
        cardPanel = new JPanel(cardLayout);
        // ���ÿ�Ƭ������ѡ��СΪ��Ϸ�����ж��������Ⱥ͸߶�
        cardPanel.setPreferredSize(new Dimension(GameConfig.PANEL_WIDTH, GameConfig.PANEL_HEIGHT));


		// ����һ�� ModeMenuPanel ʵ����������ʾ��Ϸģʽ�˵�
		ModeMenuPanel modeMenuPanel = new ModeMenuPanel(cardLayout, cardPanel,GameConfig.PANEL_WIDTH,GameConfig.PANEL_HEIGHT);
		// ����һ�� MapMenuPanel ʵ����������ʾ��Ϸ��ͼ�˵�
		MapMenuPanel mapMenuPanel = new MapMenuPanel(cardLayout, cardPanel,GameConfig.PANEL_WIDTH,GameConfig.PANEL_HEIGHT);
		// ����һ�� LevelMenuPanel ʵ����������ʾ��Ϸ����˵�
		LevelMenuPanel levelMenuPanel = new LevelMenuPanel(cardLayout, cardPanel,GameConfig.PANEL_WIDTH,GameConfig.PANEL_HEIGHT);
		// ����һ�� GamePanel ʵ����������ʾ��Ϸ����
		gamePanel = new GamePanel(cardLayout, cardPanel,GameConfig.PANEL_WIDTH,GameConfig.PANEL_HEIGHT);

		// ���� LevelMenuPanel �е� GamePanel ʵ�����Ա���ѡ�񼶱������л�����Ϸ����
		levelMenuPanel.setGamePanel(gamePanel);

		// �� modeMenuPanel ��ӵ� cardPanel �У���ָ��������Ϊ "ModeMenuPanel"
		cardPanel.add(modeMenuPanel, "ModeMenuPanel");
		// �� mapMenuPanel ��ӵ� cardPanel �У���ָ��������Ϊ "MapMenuPanel"
		cardPanel.add(mapMenuPanel, "MapMenuPanel");
		// �� levelMenuPanel ��ӵ� cardPanel �У���ָ��������Ϊ "LevelMenuPanel"
		cardPanel.add(levelMenuPanel, "LevelMenuPanel");
		// �� gamePanel ��ӵ� cardPanel �У���ָ��������Ϊ "GamePanel"
		cardPanel.add(gamePanel, "GamePanel");
		;
		// ��ʾ��Ϸģʽ�˵�
		cardLayout.show(cardPanel, "ModeMenuPanel");



		// ���ô��ڵĲ���Ϊ BorderLayout
		this.setLayout(new BorderLayout());
		// �� cardPanel ��ӵ� JFrame ������λ��
		this.getContentPane().add(cardPanel, BorderLayout.CENTER);
		// �Զ��������ڴ�С����Ӧ������
		this.pack();


		// ���ô��������ָ�������λ�á�null ������ʾ����λ����Ļ������
		this.setLocationRelativeTo(null);
		// ���ô��ڹر�ʱ��Ĭ�ϲ�����EXIT_ON_CLOSE ��ʾ�˳�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô����Ƿ�ɼ���true ��ʾ���ڿɼ�
		this.setVisible(true);
		// ���ô����Ƿ�ɵ�����С��false ��ʾ���ڲ��ɵ�����С
		this.setResizable(false);


		// ����һ����Ϊ bgm �� MusicPlayer �������ڲ��ű�������
		bgm = new MusicPlayer("src/Music/bgm.wav");

		// ���� bgm ����� playOnce ����������һ�α�������
		bgm.playOnce();


	}

	private void showHistory() {
		loadHistory(); // ���ļ�������ʷ��¼
		StringBuilder historyOutput = new StringBuilder();

		// ����Ƿ�����ʷ��¼
		if (historyRecords.isEmpty()) {
			historyOutput.append("û����ʷ��¼��\n");
		} else {
			for (Data record : historyRecords) {
				historyOutput.append(record.toString()).append("\n");
			}
		}

		// ��ʾ�Ի���
		JOptionPane.showMessageDialog(this, historyOutput.toString(), "��ʷ��¼", JOptionPane.INFORMATION_MESSAGE);
	}

	private void loadHistory() {
		historyRecords.clear(); // ������м�¼
		try (BufferedReader reader = new BufferedReader(new FileReader("history.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 4) {
					String map = parts[0];
					String level = parts[1];
					long duration = Long.parseLong(parts[2]);
					String date = parts[3];
					historyRecords.add(new Data(map, level, duration));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveToFile(Data data) {
		// ����ʷ��¼д���ļ�
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("history.txt", true))) {
			writer.write(data.getRecord());
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void recordWin(String map, String level, long duration) {
		// ��һ�ʤʱ��¼��Ϣ
		Data data = new Data(map, level, duration);
		historyRecords.add(data); // ��ӵ��ڴ��е���ʷ��¼
		saveToFile(data); // ���浽�ļ�
	}

}

