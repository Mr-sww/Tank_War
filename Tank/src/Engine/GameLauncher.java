package Engine;

import javax.swing.*;

public class GameLauncher {
    /**
     * 游戏启动器的主方法，用于创建并显示游戏窗口
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 使用 SwingUtilities.invokeLater 方法在事件分发线程中创建并显示游戏窗口
        SwingUtilities.invokeLater(GameFrame::new);
    }
}

