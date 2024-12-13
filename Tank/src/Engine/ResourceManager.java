package Engine;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ResourceManager {
    /**
     * ����ָ��·����ͼƬ��Դ
     *
     * @param path ͼƬ��Դ��·��
     * @return ���سɹ���ͼƬ��������Դδ�ҵ��򷵻� null
     */
    public static Image loadImage(String path) {
        // ��ȡ��Դ�� URL
        URL url = ResourceManager.class.getResource(path);
        // ��� URL �Ƿ�Ϊ�գ�����Դ�Ƿ��ҵ�
        if (url == null) {
            // ��ӡ��Դδ�ҵ�����Ϣ
            System.out.println("��Դδ�ҵ���" + path);
            // ���� null
            return null;
        }
        // ���ؼ��ص�ͼƬ
        return new ImageIcon(url).getImage();
    }
}

