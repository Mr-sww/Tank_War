package Engine;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 菜单工厂类，用于创建菜单和菜单项
 */
public class MenuFactory {
    /**
     * 创建一个带有指定文本、字体、动作监听器和动作命令的菜单项
     *
     * @param text 菜单项的文本
     * @param font 菜单项的字体
     * @param listener 菜单项的动作监听器
     * @param command 菜单项的动作命令
     * @return 创建的菜单项
     */
    public static JMenuItem createItem(String text, Font font, ActionListener listener, String command) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(font);
        item.addActionListener(listener);
        item.setActionCommand(command);
        return item;
    }

    /**
     * 创建一个带有指定文本和字体的菜单
     *
     * @param s 菜单的文本
     * @param font 菜单的字体
     * @return 创建的菜单
     */
    public static JMenu createMenu(String s, Font font) {
        JMenu menu = new JMenu(s);
        menu.setFont(font);
        return menu;
    }
}

