package Engine;

import javax.swing.*;

public class GameLauncher {
    /**
     * ��Ϸ�������������������ڴ�������ʾ��Ϸ����
     *
     * @param args �����в���
     */
    public static void main(String[] args) {
        // ʹ�� SwingUtilities.invokeLater �������¼��ַ��߳��д�������ʾ��Ϸ����
        SwingUtilities.invokeLater(GameFrame::new);
    }
}

