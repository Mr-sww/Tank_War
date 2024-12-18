package Engine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class GameLauncher {
    /**
     * 游戏启动器的主方法，用于创建并显示游戏窗口
     *
     * @param args 命令行参数
     */
    public static void main(String[] args){
        try {
            // 字体文件路径
            File fontFile = new File("src/Font/font.ttf");
            // 创建字体对象
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            // 注册字体，使它可以被系统使用
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            // 捕获字体加载过程中的异常
            System.out.println("加载字体时出错：" + e.getMessage());
        }



        // 使用 SwingUtilities.invokeLater 方法在事件分发线程中创建并显示游戏窗口
        SwingUtilities.invokeLater(GameFrame::new);
    }
}

