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
    public int panel_width = 800 + 50; // 静态全局窗口大小
    public int panel_length = 600 + 100 - 2;
    public static int MapLevel = 1; // 地图等级
    public static int level = 1; //难度等级
    private static boolean isEnd = false; // 游戏是否结束
    public static boolean dialogShown = false; // 是否生成弹窗
    public boolean printable = true; // 记录暂停状态，此时线程不刷新界面
    // 定义一个名为 cardLayout 的 CardLayout 类型的变量，用于管理卡片的布局
    CardLayout cardLayout;
    // 定义一个名为 cardPanel 的 JPanel 类型的变量，用于存储卡片
    JPanel cardPanel;

    // 声明一个 MapGenerator 类型的变量，用于生成游戏地图
    MapGenerator mapGenerator;
    // 声明一个 TankGenerator 类型的变量，用于生成游戏敌方坦克
    TankGenerator tankGenerator;


    // 定义一个名为 homeTank 的 Tank 类型的变量，用于存储玩家坦克
    public Tank homeTank;
    // 定义一个名为 home 的 Home 类型的变量，用于存储大本营
    public Home home;
    // 定义一个名为 blood 的 Blood 类型的变量，用于存储血包
    public Blood blood;


    // 以下集合变量在构造方法中进行了初始化
    // 定义一个名为 theRiver 的 List 类型的变量，用于存储 River 对象
    public List<River> theRiver = new ArrayList<River>();
    // 定义一个名为 tanks 的 List 类型的变量，用于存储 Tank 对象
    public List<Tank> tanks = new ArrayList<Tank>();
    // 定义一个名为 bombTanks 的 List 类型的变量，用于存储 BombTank 对象
    public List<BombTank> bombTanks = new ArrayList<BombTank>();
    // 定义一个名为 bullets 的 List 类型的变量，用于存储 Bullets 对象
    public List<Bullets> bullets = new ArrayList<Bullets>();
    // 定义一个名为 trees 的 List 类型的变量，用于存储 Tree 对象
    public List<Tree> trees = new ArrayList<Tree>();
    // 定义一个名为 homeWall 的 List 类型的变量，用于存储 BrickWall 对象，这些对象代表玩家的大本营墙壁
    public List<BrickWall> homeWall = new ArrayList<BrickWall>();
    // 定义一个名为 otherWall 的 List 类型的变量，用于存储 BrickWall 对象，这些对象代表其他墙壁
    public List<BrickWall> otherWall = new ArrayList<BrickWall>();
    // 定义一个名为 metalWall 的 List 类型的变量，用于存储 MetalWall 对象
    public List<MetalWall> metalWall = new ArrayList<MetalWall>();


    /**
     * 构造方法，用于初始化游戏面板。
     *
     * @param cardLayout 卡片布局管理器，用于管理多个面板的切换。
     * @param cardPanel  卡片面板，包含多个游戏面板。
     */
    public GamePanel(CardLayout cardLayout, JPanel cardPanel,int width, int height) {
        // 初始化 cardLayout 变量，用于管理卡片布局
        this.cardLayout = cardLayout;
        // 初始化 cardPanel 变量，用于存储卡片
        this.cardPanel = cardPanel;
        //设置面板大小
        this.setPreferredSize(new Dimension(width,height));
        // 创建一个 MapGenerator 类的实例，用于生成游戏地图
        mapGenerator = new MapGenerator();
        // 将当前的 GamePanel 实例设置给 mapGenerator，以便 mapGenerator 可以访问和修改 GamePanel 的属性和方法
        mapGenerator.setGamePanel(this);
        // 创建一个 TankGenerator 类的实例，用于生成游戏敌方坦克
        tankGenerator = new TankGenerator();
        // 将当前的 GamePanel 实例设置给 tankGenerator，以便 tankGenerator 可以访问和修改 GamePanel 的属性和方法
        tankGenerator.setGamePanel(this);
    }

    /**
     * 初始化游戏的方法。
     * 根据游戏难度和地图设置坦克数量、速度、子弹速度等参数，并实例化各种游戏对象。
     */
    public void init() {
        if(tanks.size() != 0)
        {
            tanks.clear(); // 清理
            bullets.clear();
            trees.clear();
            otherWall.clear();
            homeWall.clear();
            metalWall.clear();
            homeTank.setLive(false);
            homeTank = new Tank(300, 560, true, Direction.STOP, this, 50);// 设置自己出现的位置
            if (!home.isLive()) // 将home重置生命
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

        homeTank = new Tank(8*60, 20*60, true, Direction.STOP, this,50);// 实例化坦克
        blood = new Blood(); // 实例化血包
        home = new Home(10*60, 20*60, this);// 实例化home
        homeWall.add(new BrickWall(9*60,20*60,this));
        homeWall.add(new BrickWall(9*60,19*60,this));
        homeWall.add(new BrickWall(10*60,19*60,this));
        homeWall.add(new BrickWall(11*60,19*60,this));
        homeWall.add(new BrickWall(11*60,20*60,this));

        HashMap<SimpleEntry<Integer, Integer>, Boolean> mp = mapGenerator.generateMap(GameFrame.gameMap);
        tankGenerator.generateTank(mp,Tank.count); // 生成地图和敌方坦克

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
//                homeTank = new Tank(300, 560, true, Direction.STOP, this,50);// 实例化坦克
//                blood = new Blood(); // 实例化血包
//                home = new Home(373, 545, this);// 实例化home
//
//                for (int i = 0; i < 10; i++) { // 家的格局
//                    if (i < 4)
//                        homeWall.add(new BrickWall(350, 580 - 21 * i , this));
//                    else if (i < 7)
//                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 517 , this));
//                    else
//                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21 , this));
//                }
//
//                for (int i = 0; i < 32; i++) { // 砖墙
//                    if (i < 16) {
//                        otherWall.add(new BrickWall(220 + 20 * i, 300 , this)); // 砖墙布局
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
//                for (int i = 0; i < 20; i++) { // 金属墙布局
//                    if (i < 10) {
//                        metalWall.add(new MetalWall(140 + 30 * i, 150 , this));
//                        metalWall.add(new MetalWall(600, 400 + 20 * (i), this));
//                    } else if (i < 20)
//                        metalWall.add(new MetalWall(140 + 30 * (i - 10), 180 , this));
//                    else
//                        metalWall.add(new MetalWall(500 + 30 * (i - 10), 160 , this));
//                }
//
//                for (int i = 0; i < 4; i++) { // 树的布局
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
//                for (int i = 0; i < 20; i++) { // 初始化20辆坦克
//                    if (i < 9) // 设置坦克出现的位置
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
//                homeTank = new Tank(300, 560, true, Direction.STOP, this,50);// 实例化坦克
//                blood = new Blood(); // 实例化血包
//                home = new Home(373, 545, this);// 实例化home
//
//                for (int i = 0; i < 10; i++) { // 家的格局
//                    if (i < 4)
//                        homeWall.add(new BrickWall(350, 580 - 21 * i , this));
//                    else if (i < 7)
//                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 517 , this));
//                    else
//                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21 , this));
//                }
//
//                // 创建川形状布局：中间是通道，四周有砖墙
//                // 纵向两侧墙体
//                //坦克的长度为40，1个砖墙的宽度是20
//                for (int i = 0; i < 10; i++) {  //整张地图的川形布局
//                    for(int j=0;j<30;j++)
//                    {
//                        otherWall.add(new BrickWall(40+100*i, 0 + j * 20, this));  // 左侧竖墙
//                    }
//                }
//                for (int i = 0; i < 20; i++) { // 初始化20辆坦克
//                    if (i < 9) // 设置坦克出现的位置
//                        tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,50));
//                    else if (i < 15)
//                        tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D, this,50));
//                    else
//                        tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D, this,50));
//                }
//                break;
//            case "Map3":
//                MapLevel = 3;
//                homeTank = new Tank(300, 560, true, Direction.STOP, this,50);// 实例化坦克
//                blood = new Blood(); // 实例化血包
//                home = new Home(373, 545, this);// 实例化home
//
//                for (int i = 0; i < 10; i++) { // 家的格局
//                    if (i < 4)
//                        homeWall.add(new BrickWall(350, 400 - 21 * i , this));
//                    else if (i < 7)
//                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 372 , this));
//                    else
//                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21 , this));
//                }
//                theRiver.add(new River(85, 100, this));
//
//                for (int i = 0; i < 20; i++) { // 初始化20辆坦克
//                    if (i < 9) // 设置坦克出现的位置
//                        tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,50));
//                    else if (i < 15)
//                        tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D, this,50));
//                    else
//                        tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D, this,50));
//                }
//                break;
//            case "Map4":
//                MapLevel = 4;
//                homeTank = new Tank(300, 560, true, Direction.STOP, this,50);// 实例化坦克
//                blood = new Blood(); // 实例化血包
//                home = new Home(373, 545, this);// 实例化home
//
//                for (int i = 0; i < 10; i++) { // 家的格局
//                    if (i < 4)
//                        homeWall.add(new BrickWall(350, 580 - 21 * i , this));
//                    else if (i < 7)
//                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 517 , this));
//                    else
//                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21 , this));
//                }
//
//                for (int i = 0; i < 32; i++) { // 砖墙
//                    if (i < 16) {
//                        otherWall.add(new BrickWall(220 + 20 * i, 300 , this)); // 砖墙布局
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
//                for (int i = 0; i < 20; i++) { // 金属墙布局
//                    if (i < 10) {
//                        metalWall.add(new MetalWall(140 + 30 * i, 150 , this));
//                        metalWall.add(new MetalWall(600, 400 + 20 * (i), this));
//                    } else if (i < 20)
//                        metalWall.add(new MetalWall(140 + 30 * (i - 10), 180 , this));
//                    else
//                        metalWall.add(new MetalWall(500 + 30 * (i - 10), 160 , this));
//                }
//
//                for (int i = 0; i < 4; i++) { // 树的布局
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
//                for (int i = 0; i < 20; i++) { // 初始化20辆坦克
//                    if (i < 9) // 设置坦克出现的位置
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
     * 启动游戏的方法。
     * 该方法设置了游戏面板的焦点，请求焦点，并添加了键盘监听器和绘制线程。
     * 同时，它还添加了一个组件监听器，用于在窗口调整大小时重新绘制游戏面板。
     */
    public void GameStart() {

        // 设置面板可聚焦
        this.setFocusable(true);
        // 请求窗口聚焦
        this.requestFocusInWindow();
        // 添加键盘监听器，用于处理键盘事件
        this.addKeyListener(new KeyMonitor());
        // 启动绘制线程，用于不断重绘游戏面板
        new Thread(new PaintThread()).start();

        // 添加窗口调整监听器，当窗口大小改变时，重新绘制游戏面板
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
     * 私有内部类 KeyMonitor，继承自 KeyAdapter，用于监听键盘事件。
     * 该类重写了 keyReleased 和 keyPressed 方法，分别处理键盘释放和按下事件。
     */
    private class KeyMonitor extends KeyAdapter {
        /**
         * 处理键盘释放事件。
         * 当键盘释放时，调用 homeTank 对象的 keyReleased 方法。
         *
         * @param e 键盘事件对象。
         */
        public void keyReleased(KeyEvent e) { // 监听键盘释放
            homeTank.keyReleased(e);
        }

        /**
         * 处理键盘按下事件。
         * 当键盘按下时，调用 homeTank 对象的 keyPressed 方法。
         *
         * @param e 键盘事件对象。
         */
        public void keyPressed(KeyEvent e) { // 监听键盘按下
            homeTank.keyPressed(e);
        }
    }

    /**
     * 私有内部类 PaintThread，实现了 Runnable 接口，用于在游戏中以固定的时间间隔重绘游戏面板。
     */
    private class PaintThread implements Runnable {
        /**
         * 线程的主要执行方法。
         * 当 printable 为 true 时，不断调用 repaint() 方法重绘游戏面板，并在每次重绘后休眠 50 毫秒。
         * 如果线程被中断，会打印出异常堆栈跟踪信息。
         */
        public void run() {
            while (printable) {
                repaint();
                try {
                    sleep(35);
                } catch (InterruptedException e) {
                    // 捕获到 InterruptedException 时，打印异常堆栈跟踪信息
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 重写的 paintComponent 方法，用于在游戏面板上绘制图形。
     * 该方法首先调用父类的 paintComponent 方法来绘制背景，然后设置背景颜色为灰色，并填充整个面板。
     * 接着调用 gamePanelPaint 方法来绘制游戏中的其他元素，如河流、坦克、子弹等。
     *
     * @param g 图形对象，用于在面板上绘制图形。
     */
    @Override
    protected void paintComponent(Graphics g) {
        // 调用父类的 paintComponent 方法，确保正确绘制背景
        super.paintComponent(g);

        // 设置背景颜色为灰色
        g.setColor(Color.GRAY);
        // 填充整个面板区域
        g.fillRect(0, 0, getWidth(), getHeight());

        // 调用 gamePanelPaint 方法，绘制游戏面板上的其他元素
        gamePanelPaint(g);
    }


    /**
     * 游戏面板绘制方法。
     * 该方法用于在游戏面板上绘制各种图形元素，包括河流、坦克、子弹、墙壁等。
     *
     * @paramg 图形对象，用于在面板上绘制图形。
     */
    public void loadNextStage() {
        // 根据关卡更新关卡地图
        GameFrame.gameMap = "Map" + ++MapLevel;
        init();
    }
    // 用于加载下一个 level
    public void loadNextLevel() {
        // 重置游戏状态
        // 更新 level 和初始状态
        GameFrame.gameLevel="Level"+ ++level;
        init();
    }
    public void gamePanelPaint(Graphics g) {


        Color c = g.getColor();
        g.setColor(Color.green); // 设置字体显示属性

        Font f1 = g.getFont();
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("区域内还有敌方坦克: ", 200, 70);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
        g.drawString("" + tanks.size(), 400, 70);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("剩余生命值: ", 500, 70);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
        g.drawString("" + homeTank.getLife(), 650, 70);
        g.setFont(f1);

        // 如果玩家赢了（条件是敌方坦克全灭、大本营健在、玩家坦克仍有血量）
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
            g.drawString("你赢了！ ", 310, 300);
            g.setFont(f);
            if (!(MapLevel == 4 && level == 4)) {
                // 确保弹窗只弹一次，可以通过设置一个标记变量来控制
                if (!dialogShown) {
                    dialogShown = true; // 设置弹窗已显示
                    // 弹出对话框
                    int option = JOptionPane.showOptionDialog(null,
                            "想要挑战更高难度吗？",   // 弹窗标题
                            "恭喜！你赢了！",  // 弹窗信息
                            JOptionPane.DEFAULT_OPTION, // 默认按钮
                            JOptionPane.INFORMATION_MESSAGE,
                            null, // 图标
                            new Object[]{"下一关", "下一个level", "退出游戏"}, // 按钮
                            "下一关"  // 默认选择
                    );

                    // 根据用户选择进行不同的处理
                    switch (option) {
                        case 0: // 下一关
                            loadNextStage();  // 加载下一关
                            break;
                        case 1: // 下一个level
                            loadNextLevel();  // 加载下一个level
                            break;
                        case 2: // 退出游戏
                            System.exit(0);  // 退出游戏
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

        for (int i = 0; i < theRiver.size(); i++) { // 画出河流
            River r = theRiver.get(i);
            r.draw(g);
        }

        for (int i = 0; i < theRiver.size(); i++) {
            River r = theRiver.get(i);
            homeTank.collideRiver(r);

            r.draw(g);
        }

        home.draw(g); // 画出home
        homeTank.draw(g); // 画出自己家的坦克
        homeTank.eat(blood);// 加血--生命值

        for (int i = 0; i < bullets.size(); i++) { // 对每一个子弹
            Bullets m = bullets.get(i);
            m.hitTanks(tanks); // 每一个子弹打到坦克上
            m.hitTank(homeTank); // 每一个子弹打到自己家的坦克上时
            m.hitHome(); // 每一个子弹打到家里时

            for (int j = 0; j < metalWall.size(); j++) { // 每一个子弹打到金属墙上
                MetalWall mw = metalWall.get(j);
                m.hitWall(mw);
            }

            for (int j = 0; j < otherWall.size(); j++) {// 每一个子弹打到其他墙上
                BrickWall w = otherWall.get(j);
                m.hitWall(w);
            }

            for (int j = 0; j < homeWall.size(); j++) {// 每一个子弹打到家的墙上
                BrickWall cw = homeWall.get(j);
                m.hitWall(cw);
            }
            m.draw(g); // 画出效果图
        }

        // 画出每一辆敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            Tank t = tanks.get(i); // 获得键值对的键

            for (int j = 0; j < homeWall.size(); j++) {
                BrickWall cw = homeWall.get(j);
                t.collideWithWall(cw); // 每一个坦克撞到家里的墙时
                cw.draw(g);
            }
            for (int j = 0; j < otherWall.size(); j++) { // 每一个坦克撞到家以外的墙
                BrickWall cw = otherWall.get(j);
                t.collideWithWall(cw);
                cw.draw(g);
            }
            for (int j = 0; j < metalWall.size(); j++) { // 每一个坦克撞到金属墙
                MetalWall mw = metalWall.get(j);
                t.collideWithWall(mw);
                mw.draw(g);
            }
            for (int j = 0; j < theRiver.size(); j++) {
                River r = theRiver.get(j); // 每一个坦克撞到河流时
                t.collideRiver(r);
                r.draw(g);
                // t.draw(g);
            }

            t.collideWithTanks(tanks); // 撞到自己的人
            t.collideHome(home);

            t.draw(g);
        }

        blood.draw(g);// 画出加血包

        for (int i = 0; i < trees.size(); i++) { // 画出trees
            Tree tr = trees.get(i);
            tr.draw(g);
        }

        for (int i = 0; i < bombTanks.size(); i++) { // 画出爆炸效果
            BombTank bt = bombTanks.get(i);
            bt.draw(g);
        }

        for (int i = 0; i < otherWall.size(); i++) { // 画出otherWall
            BrickWall cw = otherWall.get(i);
            cw.draw(g);
        }

        for (int i = 0; i < metalWall.size(); i++) { // 画出metalWall
            MetalWall mw = metalWall.get(i);
            mw.draw(g);
        }

        homeTank.collideWithTanks(tanks);
        homeTank.collideHome(home);

        for (int i = 0; i < metalWall.size(); i++) {// 撞到金属墙
            MetalWall w = metalWall.get(i);
            homeTank.collideWithWall(w);
            w.draw(g);
        }

        for (int i = 0; i < otherWall.size(); i++) {
            BrickWall cw = otherWall.get(i);
            homeTank.collideWithWall(cw);
            cw.draw(g);
        }

        for (int i = 0; i < homeWall.size(); i++) { // 家里的坦克撞到自己家
            BrickWall w = homeWall.get(i);
            homeTank.collideWithWall(w);
            w.draw(g);
        }

    }

}
