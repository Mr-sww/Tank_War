package Engine;

import Object.StaticObject.BrickWall;
import Object.StaticObject.Home;
import Object.StaticObject.MetalWall;
import Object.StaticObject.River;
import Object.StaticObject.Tree;
import Object.TankObject.Tank;
import Object.UseObject.Blood;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;

/**
 * 地图生成器类，用于生成游戏地图
 */
public class MapGenerator {
    // 游戏面板对象，用于绘制地图
    public GamePanel gamePanel;

    /**
     * 默认构造函数，初始化 MapGenerator 对象
     */
    public MapGenerator() {}

    // 0表示空地，
    // 1表示普通墙，
    // 2表示铁墙，
    // 3表示树，
    // 4表示河流，
    // 5表示家周围的墙，
    // 6表示家，
    // 7表示玩家坦克
    int[][] map1 = {
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 3, 3, 0, 0, 3, 3, 0, 4, 4, 0, 3, 3, 0, 0, 3, 3, 1, 0, 0},
            {1, 0, 3, 3, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 3, 3, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 4, 4, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 1, 2, 1, 0, 0, 1, 2, 1, 0, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 1, 2, 1, 0, 0, 1, 2, 1, 0, 0, 0, 1, 1, 0, 0},
            {1, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 1, 0},
            {1, 0, 3, 3, 0, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 0, 3, 3, 0, 1, 0},
            {0, 0, 0, 0, 0, 4, 4, 4, 0, 2, 2, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0},
            {1, 0, 3, 3, 0, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 0, 3, 3, 0, 1, 0},
            {1, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 0, 1, 2, 1, 0, 0, 1, 2, 1, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 0, 1, 2, 1, 0, 0, 1, 2, 1, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 4, 4, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 3, 3, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 3, 3, 1, 0, 0},
            {1, 0, 3, 3, 0, 0, 3, 3, 0, 4, 4, 0, 3, 3, 0, 0, 3, 3, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 7, 5, 6, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    int[][] map2 = {
            {1, 1, 0, 0, 3, 3, 0, 0, 1, 1, 0, 1, 1, 0, 0, 3, 3, 0, 0, 1, 1},
            {1, 0, 0, 4, 4, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 4, 4, 0},
            {0, 0, 0, 4, 4, 0, 0, 3, 3, 0, 2, 2, 0, 3, 3, 0, 0, 4, 4, 0, 0},
            {0, 4, 4, 0, 0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 4, 4, 0},
            {3, 4, 4, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 4, 4, 3},
            {3, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 3},
            {0, 0, 0, 3, 3, 1, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 1, 3, 3, 0, 0},
            {0, 3, 3, 3, 3, 0, 0, 4, 4, 0, 0, 0, 0, 0, 4, 4, 0, 3, 3, 3, 3},
            {0, 3, 3, 0, 0, 0, 4, 4, 0, 0, 1, 1, 0, 0, 4, 4, 0, 0, 3, 3, 0},
            {1, 0, 0, 0, 0, 4, 4, 0, 1, 1, 0, 0, 1, 1, 0, 4, 4, 0, 0, 0, 1},
            {1, 1, 2, 0, 0, 0, 0, 0, 1, 1, 4, 4, 1, 1, 0, 0, 0, 0, 2, 1, 1},
            {1, 0, 0, 0, 0, 4, 4, 0, 1, 1, 0, 0, 1, 1, 0, 4, 4, 0, 0, 0, 1},
            {0, 3, 3, 0, 0, 0, 4, 4, 0, 0, 1, 1, 0, 0, 4, 4, 0, 0, 3, 3, 0},
            {0, 3, 3, 3, 3, 0, 0, 4, 4, 0, 0, 0, 0, 0, 4, 4, 0, 3, 3, 3, 3},
            {0, 0, 0, 3, 3, 1, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 1, 3, 3, 0, 0},
            {3, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 3},
            {3, 4, 4, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 4, 4, 3},
            {0, 4, 4, 0, 0, 0, 0, 0, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 4, 4, 0},
            {0, 0, 0, 4, 4, 0, 0, 3, 3, 0, 2, 2, 0, 3, 3, 0, 0, 4, 4, 0, 0},
            {1, 0, 0, 4, 4, 0, 0, 0, 0, 5, 5, 5, 0, 0, 1, 1, 0, 0, 4, 4, 0},
            {1, 1, 0, 0, 3, 3, 0, 0, 7, 5, 6, 5, 0, 0, 0, 3, 3, 0, 0, 1, 1}
    };
    int[][] map3 = {
            {1, 1, 1, 0, 0, 3, 3, 0, 1, 1, 0, 1, 1, 0, 3, 3, 0, 0, 1, 1, 1},
            {1, 0, 0, 0, 4, 4, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 4, 4, 0, 0, 1},
            {1, 0, 3, 3, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 3, 3, 1},
            {0, 0, 3, 3, 0, 1, 1, 0, 4, 4, 0, 0, 4, 4, 0, 1, 1, 0, 3, 3, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 4, 4, 0, 0, 4, 4, 0, 0, 1, 0, 0, 0, 0},
            {3, 3, 0, 0, 1, 1, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 1, 1, 0, 0, 3},
            {3, 3, 0, 1, 0, 0, 4, 4, 0, 3, 3, 3, 3, 0, 4, 4, 0, 0, 1, 0, 3},
            {0, 0, 0, 1, 0, 0, 4, 4, 0, 3, 0, 0, 3, 0, 4, 4, 0, 0, 1, 0, 0},
            {0, 4, 4, 0, 3, 3, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 3, 3, 0, 4, 4},
            {0, 4, 4, 0, 3, 0, 0, 1, 2, 2, 0, 0, 2, 2, 1, 0, 0, 3, 0, 4, 4},
            {1, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 1},
            {0, 4, 4, 0, 3, 0, 0, 1, 2, 2, 0, 0, 2, 2, 1, 0, 0, 3, 0, 4, 4},
            {0, 4, 4, 0, 3, 3, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 3, 3, 0, 4, 4},
            {0, 0, 0, 1, 0, 0, 4, 4, 0, 3, 0, 0, 3, 0, 4, 4, 0, 0, 1, 0, 0},
            {3, 3, 0, 1, 0, 0, 4, 4, 0, 3, 3, 3, 3, 0, 4, 4, 0, 0, 1, 0, 3},
            {3, 3, 0, 0, 1, 1, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 1, 1, 0, 0, 3},
            {0, 0, 0, 0, 0, 1, 0, 0, 4, 4, 0, 0, 4, 4, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 3, 3, 0, 1, 1, 0, 4, 4, 0, 0, 4, 4, 0, 1, 1, 0, 3, 3, 0},
            {1, 0, 3, 3, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 3, 3, 1},
            {1, 0, 0, 0, 4, 4, 0, 0, 0, 5, 5, 5, 0, 1, 0, 0, 4, 4, 0, 0, 1},
            {1, 1, 1, 0, 0, 3, 3, 0, 7, 5, 6, 5, 0, 0, 3, 3, 0, 0, 1, 1, 1}
    };
    int[][] map4 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 3, 4, 4, 2, 4, 4, 4, 4, 4, 4, 3, 3, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 0},
            {0, 0, 0, 0, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},
            {0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 7, 5, 6, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };


    // 初始化一个 HashMap 对象 mp，用于存储地图名称和对应的地图数组
    HashMap<String,int [][]> mp = new HashMap<>();
    {
        // 将名为 Map1 的地图数组 map1 存入 mp 中
        mp.put("Map1",map1);
        // 将名为 Map2 的地图数组 map2 存入 mp 中
        mp.put("Map2",map2);
        // 将名为 Map3 的地图数组 map3 存入 mp 中
        mp.put("Map3",map3);
        // 将名为 Map4 的地图数组 map4 存入 mp 中
        mp.put("Map4",map4);
    }

        /**
     * 设置游戏面板
     *
     * @param gamePanel 游戏面板对象
     */
    public void setGamePanel(GamePanel gamePanel) {
        // 将传入的 gamePanel 对象赋值给当前实例的 gamePanel 属性
        this.gamePanel = gamePanel;
    }

            /**
     * 根据指定的游戏地图名称生成地图
     *
     * @param gameMap 游戏地图名称
     * @return 包含地图元素信息的 HashMap
     */
    public HashMap<SimpleEntry<Integer, Integer>, Boolean> generateMap(String gameMap) {
        // 创建一个新的 HashMap 对象 res，用于存储生成的地图元素信息
        HashMap<SimpleEntry<Integer, Integer>, Boolean> res = new HashMap<>();
        // 从 mp 中获取指定名称的地图数组
        int[][] map = mp.get(gameMap);
        gamePanel.blood=new Blood();
        // 遍历地图数组的每一行
        for (int i = 0; i < map.length; i++) {
            // 遍历当前行的每一列
            for (int j = 0; j < map[i].length; j++) {
                // 如果当前位置的元素值为 1，则在游戏面板的 otherWall 列表中添加一个新的 BrickWall 对象
                if (map[i][j] == 1)
                    gamePanel.otherWall.add(new BrickWall(j * 60, i * 60, gamePanel));
                // 如果当前位置的元素值为 2，则在游戏面板的 metalWall 列表中添加一个新的 MetalWall 对象
                if (map[i][j] == 2)
                    gamePanel.metalWall.add(new MetalWall(j * 60, i * 60, gamePanel));
                // 如果当前位置的元素值为 3，则在游戏面板的 trees 列表中添加一个新的 Tree 对象
                if (map[i][j] == 3)
                    gamePanel.trees.add(new Tree(j * 60, i * 60, gamePanel));
                // 如果当前位置的元素值为 4，则在游戏面板的 theRiver 列表中添加一个新的 River 对象
                if (map[i][j] == 4)
                    gamePanel.theRiver.add(new River(j * 60, i * 60, gamePanel));
                if(map[i][j]==5)
                    gamePanel.homeWall.add(new BrickWall(j * 60, i * 60, gamePanel));
                if(map[i][j]==6)
                    gamePanel.home=new Home(j * 60, i * 60, gamePanel);
                if(map[i][j]==7)
                    gamePanel.homeTank=new Tank(j * 60, i * 60, true, Direction.STOP, gamePanel,50);
                // 如果当前位置的元素值为 0，则将当前位置的坐标作为键，true 作为值添加到 res 中
                if (map[i][j] == 0)
                    res.put(new SimpleEntry<>(i, j), true);
            }
        }
        // 返回生成的地图元素信息 HashMap
        return res;
    }
}


