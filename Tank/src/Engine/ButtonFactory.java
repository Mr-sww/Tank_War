package Engine;

import java.awt.*;
import javax.swing.*;

public class ButtonFactory {
    /**
     * 创建一个带有指定文本、字体和前景色的按钮
     *
     * @param text 按钮的文本
     * @param font 按钮的字体
     * @param foregroundColor 按钮的前景色
     * @return 创建的按钮
     */
    static JButton createButton(String text, Font font, Color foregroundColor) {
        // 创建一个新的 JButton 实例，文本为参数 text 指定的内容
        JButton button = new JButton(text);
        // 设置按钮的字体为参数 font 指定的字体
        button.setFont(font);
        // 设置按钮的文本颜色为参数 foregroundColor 指定的颜色
        button.setForeground(foregroundColor);
        // 设置按钮在获得焦点时不绘制焦点框
        button.setFocusPainted(false);
        // 设置按钮为透明，即不绘制按钮的背景
        button.setOpaque(false);
        // 设置按钮的内容区域不填充颜色，即内容区域透明
        button.setContentAreaFilled(false);
        // 设置按钮的边框不绘制
        button.setBorderPainted(false);
        // 返回创建的按钮实例
        return button;
    }
}

