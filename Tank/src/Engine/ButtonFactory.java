package Engine;

import java.awt.*;
import javax.swing.*;

public class ButtonFactory {
    /**
     * ����һ������ָ���ı��������ǰ��ɫ�İ�ť
     *
     * @param text ��ť���ı�
     * @param font ��ť������
     * @param foregroundColor ��ť��ǰ��ɫ
     * @return �����İ�ť
     */
    static JButton createButton(String text, Font font, Color foregroundColor) {
        // ����һ���µ� JButton ʵ�����ı�Ϊ���� text ָ��������
        JButton button = new JButton(text);
        // ���ð�ť������Ϊ���� font ָ��������
        button.setFont(font);
        // ���ð�ť���ı���ɫΪ���� foregroundColor ָ������ɫ
        button.setForeground(foregroundColor);
        // ���ð�ť�ڻ�ý���ʱ�����ƽ����
        button.setFocusPainted(false);
        // ���ð�ťΪ͸�����������ư�ť�ı���
        button.setOpaque(false);
        // ���ð�ť���������������ɫ������������͸��
        button.setContentAreaFilled(false);
        // ���ð�ť�ı߿򲻻���
        button.setBorderPainted(false);
        // ���ش����İ�ťʵ��
        return button;
    }
}

