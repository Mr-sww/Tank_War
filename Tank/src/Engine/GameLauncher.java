package Engine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class GameLauncher {
    /**
     * ��Ϸ�������������������ڴ�������ʾ��Ϸ����
     *
     * @param args �����в���
     */
    public static void main(String[] args){
        try {
            // �����ļ�·��
            File fontFile = new File("src/Font/font.ttf");
            // �����������
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            // ע�����壬ʹ�����Ա�ϵͳʹ��
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            // ����������ع����е��쳣
            System.out.println("��������ʱ����" + e.getMessage());
        }



        // ʹ�� SwingUtilities.invokeLater �������¼��ַ��߳��д�������ʾ��Ϸ����
        SwingUtilities.invokeLater(GameFrame::new);
    }
}

