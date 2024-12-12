package Engine;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * �˵������࣬���ڴ����˵��Ͳ˵���
 */
public class MenuFactory {
    /**
     * ����һ������ָ���ı������塢�����������Ͷ�������Ĳ˵���
     *
     * @param text �˵�����ı�
     * @param font �˵��������
     * @param listener �˵���Ķ���������
     * @param command �˵���Ķ�������
     * @return �����Ĳ˵���
     */
    public static JMenuItem createItem(String text, Font font, ActionListener listener, String command) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(font);
        item.addActionListener(listener);
        item.setActionCommand(command);
        return item;
    }

    /**
     * ����һ������ָ���ı�������Ĳ˵�
     *
     * @param s �˵����ı�
     * @param font �˵�������
     * @return �����Ĳ˵�
     */
    public static JMenu createMenu(String s, Font font) {
        JMenu menu = new JMenu(s);
        menu.setFont(font);
        return menu;
    }
}

