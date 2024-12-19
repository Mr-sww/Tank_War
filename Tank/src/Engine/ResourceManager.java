package Engine;

import java.awt.*;
import java.io.File;
import java.net.URL;
import javax.swing.*;

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
    /**
     * ����ָ��·�����ļ���Դ
     *
     * @param path �ļ���Դ��·��
     * @return ���سɹ����ļ���������Դδ�ҵ��򷵻� null
     */
    public static File loadFile(String path){
        // ��ȡ��Դ�� URL
        URL url = ResourceManager.class.getResource(path);
        // ��� URL �Ƿ�Ϊ�գ�����Դ�Ƿ��ҵ�
        if (url == null) {
            // ��ӡ��Դδ�ҵ�����Ϣ
            System.out.println("��Դδ�ҵ���" + path);
            // ���� null
            return null;
        }
        // ���ؼ��ص��ļ�
        return new File(url.getPath());
    }
}

