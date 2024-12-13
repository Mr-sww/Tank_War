package Engine;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ResourceManager {
    /**
     * 加载指定路径的图片资源
     *
     * @param path 图片资源的路径
     * @return 加载成功的图片对象，若资源未找到则返回 null
     */
    public static Image loadImage(String path) {
        // 获取资源的 URL
        URL url = ResourceManager.class.getResource(path);
        // 检查 URL 是否为空，即资源是否找到
        if (url == null) {
            // 打印资源未找到的信息
            System.out.println("资源未找到：" + path);
            // 返回 null
            return null;
        }
        // 返回加载的图片
        return new ImageIcon(url).getImage();
    }
}

