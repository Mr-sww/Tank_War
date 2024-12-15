package Engine;

import Object.StaticObject.BrickWall;
import Object.StaticObject.Home;
import Object.StaticObject.MetalWall;
import Object.StaticObject.River;
import Object.StaticObject.Tree;
import Object.TankObject.BombTank;
import Object.TankObject.Tank;
import Object.UseObject.Blood;
import Object.UseObject.Bullets;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;

import static java.lang.Thread.sleep;

public class GamePanel extends JPanel {
    public int panel_width = 800 + 50; // ��̬ȫ�ִ��ڴ�С
    public int panel_length = 600 + 100 - 2;
    public static int MapLevel = 1; // ��ͼ�ȼ�
    public static int level = 1; //�Ѷȵȼ�
    private static boolean isEnd = false; // ��Ϸ�Ƿ����
    public static boolean dialogShown = false; // �Ƿ����ɵ���
    public boolean printable = true; // ��¼��ͣ״̬����ʱ�̲߳�ˢ�½���
    // ����һ����Ϊ cardLayout �� CardLayout ���͵ı��������ڹ���Ƭ�Ĳ���
    CardLayout cardLayout;
    // ����һ����Ϊ cardPanel �� JPanel ���͵ı��������ڴ洢��Ƭ
    JPanel cardPanel;

    // ����һ�� MapGenerator ���͵ı���������������Ϸ��ͼ
    MapGenerator mapGenerator;
    // ����һ�� TankGenerator ���͵ı���������������Ϸ�з�̹��
    TankGenerator tankGenerator;


    // ����һ����Ϊ homeTank �� Tank ���͵ı��������ڴ洢���̹��
    public Tank homeTank;
    // ����һ����Ϊ home �� Home ���͵ı��������ڴ洢��Ӫ
    public Home home;
    // ����һ����Ϊ blood �� Blood ���͵ı��������ڴ洢Ѫ��
    public Blood blood;


    // ���¼��ϱ����ڹ��췽���н����˳�ʼ��
    // ����һ����Ϊ theRiver �� List ���͵ı��������ڴ洢 River ����
    public List<River> theRiver = new ArrayList<River>();
    // ����һ����Ϊ tanks �� List ���͵ı��������ڴ洢 Tank ����
    public List<Tank> tanks = new ArrayList<Tank>();
    // ����һ����Ϊ bombTanks �� List ���͵ı��������ڴ洢 BombTank ����
    public List<BombTank> bombTanks = new ArrayList<BombTank>();
    // ����һ����Ϊ bullets �� List ���͵ı��������ڴ洢 Bullets ����
    public List<Bullets> bullets = new ArrayList<Bullets>();
    // ����һ����Ϊ trees �� List ���͵ı��������ڴ洢 Tree ����
    public List<Tree> trees = new ArrayList<Tree>();
    // ����һ����Ϊ homeWall �� List ���͵ı��������ڴ洢 BrickWall ������Щ���������ҵĴ�Ӫǽ��
    public List<BrickWall> homeWall = new ArrayList<BrickWall>();
    // ����һ����Ϊ otherWall �� List ���͵ı��������ڴ洢 BrickWall ������Щ�����������ǽ��
    public List<BrickWall> otherWall = new ArrayList<BrickWall>();
    // ����һ����Ϊ metalWall �� List ���͵ı��������ڴ洢 MetalWall ����
    public List<MetalWall> metalWall = new ArrayList<MetalWall>();


    /**
     * ���췽�������ڳ�ʼ����Ϸ��塣
     *
     * @param cardLayout ��Ƭ���ֹ����������ڹ����������л���
     * @param cardPanel  ��Ƭ��壬���������Ϸ��塣
     */
    public GamePanel(CardLayout cardLayout, JPanel cardPanel,int width, int height) {
        // ��ʼ�� cardLayout ���������ڹ���Ƭ����
        this.cardLayout = cardLayout;
        // ��ʼ�� cardPanel ���������ڴ洢��Ƭ
        this.cardPanel = cardPanel;
        //��������С
        this.setPreferredSize(new Dimension(width,height));
        // ����һ�� MapGenerator ���ʵ��������������Ϸ��ͼ
        mapGenerator = new MapGenerator();
        // ����ǰ�� GamePanel ʵ�����ø� mapGenerator���Ա� mapGenerator ���Է��ʺ��޸� GamePanel �����Ժͷ���
        mapGenerator.setGamePanel(this);
        // ����һ�� TankGenerator ���ʵ��������������Ϸ�з�̹��
        tankGenerator = new TankGenerator();
        // ����ǰ�� GamePanel ʵ�����ø� tankGenerator���Ա� tankGenerator ���Է��ʺ��޸� GamePanel �����Ժͷ���
        tankGenerator.setGamePanel(this);
    }

    /**
     * ��ʼ����Ϸ�ķ�����
     * ������Ϸ�ѶȺ͵�ͼ����̹���������ٶȡ��ӵ��ٶȵȲ�������ʵ����������Ϸ����
     */
    public void init() {
        if(tanks.size() != 0)
        {
            tanks.clear(); // ����
            bullets.clear();
            trees.clear();
            otherWall.clear();
            homeWall.clear();
            metalWall.clear();
            homeTank.setLive(false);
            homeTank = new Tank(300, 560, true, Direction.STOP, this, 50);// �����Լ����ֵ�λ��
            if (!home.isLive()) // ��home��������
                home.setLive(true);
        }


        dialogShown = false;
        switch (GameFrame.gameLevel) {
            case "Level1":
                Tank.count = 12;
                Tank.speedX = 6;
                Tank.speedY = 6;
                Bullets.speedX = 10;
                Bullets.speedY = 10;
                break;
            case "Level2":
                Tank.count = 12;
                Tank.speedX = 10;
                Tank.speedY = 10;
                Bullets.speedX = 12;
                Bullets.speedY = 12;
                break;
            case "Level3":
                Tank.count = 20;
                Tank.speedX = 14;
                Tank.speedY = 14;
                Bullets.speedX = 16;
                Bullets.speedY = 16;
                break;
            case "Level4":
                Tank.count = 20;
                Tank.speedX = 16;
                Tank.speedY = 16;
                Bullets.speedX = 18;
                Bullets.speedY = 18;
                break;
        }

        homeTank = new Tank(8*60, 20*60, true, Direction.STOP, this,50);// ʵ����̹��
        blood = new Blood(); // ʵ����Ѫ��
        home = new Home(10*60, 20*60, this);// ʵ����home
        homeWall.add(new BrickWall(9*60,20*60,this));
        homeWall.add(new BrickWall(9*60,19*60,this));
        homeWall.add(new BrickWall(10*60,19*60,this));
        homeWall.add(new BrickWall(11*60,19*60,this));
        homeWall.add(new BrickWall(11*60,20*60,this));

        HashMap<SimpleEntry<Integer, Integer>, Boolean> mp = mapGenerator.generateMap(GameFrame.gameMap);
        tankGenerator.generateTank(mp,Tank.count); // ���ɵ�ͼ�͵з�̹��

        switch (GameFrame.gameMap){
            case "Map1":
                MapLevel = 1;
                break;
            case "Map2":
                MapLevel = 2;
                break;
            case "Map3":
                MapLevel = 3;
                break;
            case "Map4":
                MapLevel = 4;
                break;
        }


//        switch (GameFrame.gameMap)
//        {
//            case "Map1":
//                MapLevel = 1;
//                homeTank = new Tank(300, 560, true, Direction.STOP, this,50);// ʵ����̹��
//                blood = new Blood(); // ʵ����Ѫ��
//                home = new Home(373, 545, this);// ʵ����home
//
//                for (int i = 0; i < 10; i++) { // �ҵĸ��
//                    if (i < 4)
//                        homeWall.add(new BrickWall(350, 580 - 21 * i , this));
//                    else if (i < 7)
//                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 517 , this));
//                    else
//                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21 , this));
//                }
//
//                for (int i = 0; i < 32; i++) { // שǽ
//                    if (i < 16) {
//                        otherWall.add(new BrickWall(220 + 20 * i, 300 , this)); // שǽ����
//                        otherWall.add(new BrickWall(500 + 20 * i, 180 , this));
//                        otherWall.add(new BrickWall(200, 400 + 20 * i , this));
//                        otherWall.add(new BrickWall(500, 400 + 20 * i , this));
//                    } else if (i < 32) {
//                        otherWall.add(new BrickWall(220 + 20 * (i - 16), 320 , this));
//                        otherWall.add(new BrickWall(500 + 20 * (i - 16), 220 , this));
//                        otherWall.add(new BrickWall(220, 400 + 20 * (i - 16) , this));
//                        otherWall.add(new BrickWall(520, 400 + 20 * (i - 16) , this));
//                    }
//                }
//
//                for (int i = 0; i < 20; i++) { // ����ǽ����
//                    if (i < 10) {
//                        metalWall.add(new MetalWall(140 + 30 * i, 150 , this));
//                        metalWall.add(new MetalWall(600, 400 + 20 * (i), this));
//                    } else if (i < 20)
//                        metalWall.add(new MetalWall(140 + 30 * (i - 10), 180 , this));
//                    else
//                        metalWall.add(new MetalWall(500 + 30 * (i - 10), 160 , this));
//                }
//
//                for (int i = 0; i < 4; i++) { // ���Ĳ���
//                    if (i < 4) {
//                        trees.add(new Tree(0 + 30 * i, 360 , this));
//                        trees.add(new Tree(220 + 30 * i, 360 , this));
//                        trees.add(new Tree(440 + 30 * i, 360 , this));
//                        trees.add(new Tree(660 + 30 * i, 360 , this));
//                    }
//                }
//
//                theRiver.add(new River(85, 100, this));
//
//                for (int i = 0; i < 20; i++) { // ��ʼ��20��̹��
//                    if (i < 9) // ����̹�˳��ֵ�λ��
//                        tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,50));
//                    else if (i < 15)
//                        tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D, this,50));
//                    else
//                        tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D, this,50));
//                }
//                break;
//
//            case "Map2":
//                MapLevel = 2;
//                homeTank = new Tank(300, 560, true, Direction.STOP, this,50);// ʵ����̹��
//                blood = new Blood(); // ʵ����Ѫ��
//                home = new Home(373, 545, this);// ʵ����home
//
//                for (int i = 0; i < 10; i++) { // �ҵĸ��
//                    if (i < 4)
//                        homeWall.add(new BrickWall(350, 580 - 21 * i , this));
//                    else if (i < 7)
//                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 517 , this));
//                    else
//                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21 , this));
//                }
//
//                // ��������״���֣��м���ͨ����������שǽ
//                // ��������ǽ��
//                //̹�˵ĳ���Ϊ40��1��שǽ�Ŀ����20
//                for (int i = 0; i < 10; i++) {  //���ŵ�ͼ�Ĵ��β���
//                    for(int j=0;j<30;j++)
//                    {
//                        otherWall.add(new BrickWall(40+100*i, 0 + j * 20, this));  // �����ǽ
//                    }
//                }
//                for (int i = 0; i < 20; i++) { // ��ʼ��20��̹��
//                    if (i < 9) // ����̹�˳��ֵ�λ��
//                        tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,50));
//                    else if (i < 15)
//                        tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D, this,50));
//                    else
//                        tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D, this,50));
//                }
//                break;
//            case "Map3":
//                MapLevel = 3;
//                homeTank = new Tank(300, 560, true, Direction.STOP, this,50);// ʵ����̹��
//                blood = new Blood(); // ʵ����Ѫ��
//                home = new Home(373, 545, this);// ʵ����home
//
//                for (int i = 0; i < 10; i++) { // �ҵĸ��
//                    if (i < 4)
//                        homeWall.add(new BrickWall(350, 400 - 21 * i , this));
//                    else if (i < 7)
//                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 372 , this));
//                    else
//                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21 , this));
//                }
//                theRiver.add(new River(85, 100, this));
//
//                for (int i = 0; i < 20; i++) { // ��ʼ��20��̹��
//                    if (i < 9) // ����̹�˳��ֵ�λ��
//                        tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,50));
//                    else if (i < 15)
//                        tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D, this,50));
//                    else
//                        tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D, this,50));
//                }
//                break;
//            case "Map4":
//                MapLevel = 4;
//                homeTank = new Tank(300, 560, true, Direction.STOP, this,50);// ʵ����̹��
//                blood = new Blood(); // ʵ����Ѫ��
//                home = new Home(373, 545, this);// ʵ����home
//
//                for (int i = 0; i < 10; i++) { // �ҵĸ��
//                    if (i < 4)
//                        homeWall.add(new BrickWall(350, 580 - 21 * i , this));
//                    else if (i < 7)
//                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 517 , this));
//                    else
//                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21 , this));
//                }
//
//                for (int i = 0; i < 32; i++) { // שǽ
//                    if (i < 16) {
//                        otherWall.add(new BrickWall(220 + 20 * i, 300 , this)); // שǽ����
//                        otherWall.add(new BrickWall(500 + 20 * i, 180 , this));
//                        otherWall.add(new BrickWall(200, 400 + 20 * i , this));
//                        otherWall.add(new BrickWall(500, 400 + 20 * i , this));
//                    } else if (i < 32) {
//                        otherWall.add(new BrickWall(220 + 20 * (i - 16), 320 , this));
//                        otherWall.add(new BrickWall(500 + 20 * (i - 16), 220 , this));
//                        otherWall.add(new BrickWall(220, 400 + 20 * (i - 16) , this));
//                        otherWall.add(new BrickWall(520, 400 + 20 * (i - 16) , this));
//                    }
//                }
//
//                for (int i = 0; i < 20; i++) { // ����ǽ����
//                    if (i < 10) {
//                        metalWall.add(new MetalWall(140 + 30 * i, 150 , this));
//                        metalWall.add(new MetalWall(600, 400 + 20 * (i), this));
//                    } else if (i < 20)
//                        metalWall.add(new MetalWall(140 + 30 * (i - 10), 180 , this));
//                    else
//                        metalWall.add(new MetalWall(500 + 30 * (i - 10), 160 , this));
//                }
//
//                for (int i = 0; i < 4; i++) { // ���Ĳ���
//                    if (i < 4) {
//                        trees.add(new Tree(0 + 30 * i, 360 , this));
//                        trees.add(new Tree(220 + 30 * i, 360 , this));
//                        trees.add(new Tree(440 + 30 * i, 360 , this));
//                        trees.add(new Tree(660 + 30 * i, 360 , this));
//                    }
//                }
//
//                theRiver.add(new River(85, 100, this));
//
//                for (int i = 0; i < 20; i++) { // ��ʼ��20��̹��
//                    if (i < 9) // ����̹�˳��ֵ�λ��
//                        tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,50));
//                    else if (i < 15)
//                        tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D, this,50));
//                    else
//                        tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D, this,50));
//                }
//                break;
//
//        }

    }

    /**
     * ������Ϸ�ķ�����
     * �÷�����������Ϸ���Ľ��㣬���󽹵㣬������˼��̼������ͻ����̡߳�
     * ͬʱ�����������һ������������������ڴ��ڵ�����Сʱ���»�����Ϸ��塣
     */
    public void GameStart() {

        // �������ɾ۽�
        this.setFocusable(true);
        // ���󴰿ھ۽�
        this.requestFocusInWindow();
        // ��Ӽ��̼����������ڴ�������¼�
        this.addKeyListener(new KeyMonitor());
        // ���������̣߳����ڲ����ػ���Ϸ���
        new Thread(new PaintThread()).start();

        // ��Ӵ��ڵ����������������ڴ�С�ı�ʱ�����»�����Ϸ���
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(GameFrame.isResizable){
                    GameFrame.ratio=(getWidth()*getHeight())/(1260*1260);
                }
                repaint();
            }
        });
    }

    /**
     * ˽���ڲ��� KeyMonitor���̳��� KeyAdapter�����ڼ��������¼���
     * ������д�� keyReleased �� keyPressed �������ֱ�������ͷźͰ����¼���
     */
    private class KeyMonitor extends KeyAdapter {
        /**
         * ��������ͷ��¼���
         * �������ͷ�ʱ������ homeTank ����� keyReleased ������
         *
         * @param e �����¼�����
         */
        public void keyReleased(KeyEvent e) { // ���������ͷ�
            homeTank.keyReleased(e);
        }

        /**
         * ������̰����¼���
         * �����̰���ʱ������ homeTank ����� keyPressed ������
         *
         * @param e �����¼�����
         */
        public void keyPressed(KeyEvent e) { // �������̰���
            homeTank.keyPressed(e);
        }
    }

    /**
     * ˽���ڲ��� PaintThread��ʵ���� Runnable �ӿڣ���������Ϸ���Թ̶���ʱ�����ػ���Ϸ��塣
     */
    private class PaintThread implements Runnable {
        /**
         * �̵߳���Ҫִ�з�����
         * �� printable Ϊ true ʱ�����ϵ��� repaint() �����ػ���Ϸ��壬����ÿ���ػ������ 50 ���롣
         * ����̱߳��жϣ����ӡ���쳣��ջ������Ϣ��
         */
        public void run() {
            while (printable) {
                repaint();
                try {
                    sleep(35);
                } catch (InterruptedException e) {
                    // ���� InterruptedException ʱ����ӡ�쳣��ջ������Ϣ
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * ��д�� paintComponent ��������������Ϸ����ϻ���ͼ�Ρ�
     * �÷������ȵ��ø���� paintComponent ���������Ʊ�����Ȼ�����ñ�����ɫΪ��ɫ�������������塣
     * ���ŵ��� gamePanelPaint ������������Ϸ�е�����Ԫ�أ��������̹�ˡ��ӵ��ȡ�
     *
     * @param g ͼ�ζ�������������ϻ���ͼ�Ρ�
     */
    @Override
    protected void paintComponent(Graphics g) {
        // ���ø���� paintComponent ������ȷ����ȷ���Ʊ���
        super.paintComponent(g);

        // ���ñ�����ɫΪ��ɫ
        g.setColor(Color.GRAY);
        // ��������������
        g.fillRect(0, 0, getWidth(), getHeight());

        // ���� gamePanelPaint ������������Ϸ����ϵ�����Ԫ��
        gamePanelPaint(g);
    }


    /**
     * ��Ϸ�����Ʒ�����
     * �÷�����������Ϸ����ϻ��Ƹ���ͼ��Ԫ�أ�����������̹�ˡ��ӵ���ǽ�ڵȡ�
     *
     * @paramg ͼ�ζ�������������ϻ���ͼ�Ρ�
     */
    public void loadNextStage() {
        // ���ݹؿ����¹ؿ���ͼ
        GameFrame.gameMap = "Map" + ++MapLevel;
        init();
    }
    // ���ڼ�����һ�� level
    public void loadNextLevel() {
        // ������Ϸ״̬
        // ���� level �ͳ�ʼ״̬
        GameFrame.gameLevel="Level"+ ++level;
        init();
    }
    public void gamePanelPaint(Graphics g) {


        Color c = g.getColor();
        g.setColor(Color.green); // ����������ʾ����

        Font f1 = g.getFont();
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("�����ڻ��ез�̹��: ", 200, 70);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
        g.drawString("" + tanks.size(), 400, 70);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("ʣ������ֵ: ", 500, 70);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
        g.drawString("" + homeTank.getLife(), 650, 70);
        g.setFont(f1);

        // ������Ӯ�ˣ������ǵз�̹��ȫ�𡢴�Ӫ���ڡ����̹������Ѫ����
        if(tanks.size()==0 && home.isLive() && homeTank.isLive() && isEnd == false)
        {
            Tank t= new Tank(400, 300, false, Direction.STOP, this,500);
            tanks.add(t);
            isEnd=true;
        }
        if (tanks.size() == 0 && home.isLive() && homeTank.isLive() && isEnd == true) {
            Font f = g.getFont();
            g.setFont(new Font("TimesRoman", Font.BOLD, 60));
            this.otherWall.clear();
            g.drawString("��Ӯ�ˣ� ", 310, 300);
            g.setFont(f);
            if (!(MapLevel == 4 && level == 4)) {
                // ȷ������ֻ��һ�Σ�����ͨ������һ����Ǳ���������
                if (!dialogShown) {
                    dialogShown = true; // ���õ�������ʾ
                    // �����Ի���
                    int option = JOptionPane.showOptionDialog(null,
                            "��Ҫ��ս�����Ѷ���",   // ��������
                            "��ϲ����Ӯ�ˣ�",  // ������Ϣ
                            JOptionPane.DEFAULT_OPTION, // Ĭ�ϰ�ť
                            JOptionPane.INFORMATION_MESSAGE,
                            null, // ͼ��
                            new Object[]{"��һ��", "��һ��level", "�˳���Ϸ"}, // ��ť
                            "��һ��"  // Ĭ��ѡ��
                    );

                    // �����û�ѡ����в�ͬ�Ĵ���
                    switch (option) {
                        case 0: // ��һ��
                            loadNextStage();  // ������һ��
                            break;
                        case 1: // ��һ��level
                            loadNextLevel();  // ������һ��level
                            break;
                        case 2: // �˳���Ϸ
                            System.exit(0);  // �˳���Ϸ
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        if (homeTank.isLive() == false) {
            Font f = g.getFont();
            g.setFont(new Font("TimesRoman", Font.BOLD, 40));
            tanks.clear();
            bullets.clear();
            g.setFont(f);
        }
        g.setColor(c);

        for (int i = 0; i < theRiver.size(); i++) { // ��������
            River r = theRiver.get(i);
            r.draw(g);
        }

        for (int i = 0; i < theRiver.size(); i++) {
            River r = theRiver.get(i);
            homeTank.collideRiver(r);

            r.draw(g);
        }

        home.draw(g); // ����home
        homeTank.draw(g); // �����Լ��ҵ�̹��
        homeTank.eat(blood);// ��Ѫ--����ֵ

        for (int i = 0; i < bullets.size(); i++) { // ��ÿһ���ӵ�
            Bullets m = bullets.get(i);
            m.hitTanks(tanks); // ÿһ���ӵ���̹����
            m.hitTank(homeTank); // ÿһ���ӵ����Լ��ҵ�̹����ʱ
            m.hitHome(); // ÿһ���ӵ��򵽼���ʱ

            for (int j = 0; j < metalWall.size(); j++) { // ÿһ���ӵ��򵽽���ǽ��
                MetalWall mw = metalWall.get(j);
                m.hitWall(mw);
            }

            for (int j = 0; j < otherWall.size(); j++) {// ÿһ���ӵ�������ǽ��
                BrickWall w = otherWall.get(j);
                m.hitWall(w);
            }

            for (int j = 0; j < homeWall.size(); j++) {// ÿһ���ӵ��򵽼ҵ�ǽ��
                BrickWall cw = homeWall.get(j);
                m.hitWall(cw);
            }
            m.draw(g); // ����Ч��ͼ
        }

        // ����ÿһ���з�̹��
        for (int i = 0; i < tanks.size(); i++) {
            Tank t = tanks.get(i); // ��ü�ֵ�Եļ�

            for (int j = 0; j < homeWall.size(); j++) {
                BrickWall cw = homeWall.get(j);
                t.collideWithWall(cw); // ÿһ��̹��ײ�������ǽʱ
                cw.draw(g);
            }
            for (int j = 0; j < otherWall.size(); j++) { // ÿһ��̹��ײ���������ǽ
                BrickWall cw = otherWall.get(j);
                t.collideWithWall(cw);
                cw.draw(g);
            }
            for (int j = 0; j < metalWall.size(); j++) { // ÿһ��̹��ײ������ǽ
                MetalWall mw = metalWall.get(j);
                t.collideWithWall(mw);
                mw.draw(g);
            }
            for (int j = 0; j < theRiver.size(); j++) {
                River r = theRiver.get(j); // ÿһ��̹��ײ������ʱ
                t.collideRiver(r);
                r.draw(g);
                // t.draw(g);
            }

            t.collideWithTanks(tanks); // ײ���Լ�����
            t.collideHome(home);

            t.draw(g);
        }

        blood.draw(g);// ������Ѫ��

        for (int i = 0; i < trees.size(); i++) { // ����trees
            Tree tr = trees.get(i);
            tr.draw(g);
        }

        for (int i = 0; i < bombTanks.size(); i++) { // ������ըЧ��
            BombTank bt = bombTanks.get(i);
            bt.draw(g);
        }

        for (int i = 0; i < otherWall.size(); i++) { // ����otherWall
            BrickWall cw = otherWall.get(i);
            cw.draw(g);
        }

        for (int i = 0; i < metalWall.size(); i++) { // ����metalWall
            MetalWall mw = metalWall.get(i);
            mw.draw(g);
        }

        homeTank.collideWithTanks(tanks);
        homeTank.collideHome(home);

        for (int i = 0; i < metalWall.size(); i++) {// ײ������ǽ
            MetalWall w = metalWall.get(i);
            homeTank.collideWithWall(w);
            w.draw(g);
        }

        for (int i = 0; i < otherWall.size(); i++) {
            BrickWall cw = otherWall.get(i);
            homeTank.collideWithWall(cw);
            cw.draw(g);
        }

        for (int i = 0; i < homeWall.size(); i++) { // �����̹��ײ���Լ���
            BrickWall w = homeWall.get(i);
            homeTank.collideWithWall(w);
            w.draw(g);
        }

    }

}
