package Engine;

import java.awt.*;
import java.io.File;
import java.net.URL;
import javax.swing.*;

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
    /**
     * 加载指定路径的文件资源
     *
     * @param path 文件资源的路径
     * @return 加载成功的文件对象，若资源未找到则返回 null
     */
    public static File loadFile(String path){
        // 获取资源的 URL
        URL url = ResourceManager.class.getResource(path);
        // 检查 URL 是否为空，即资源是否找到
        if (url == null) {
            // 打印资源未找到的信息
            System.out.println("资源未找到：" + path);
            // 返回 null
            return null;
        }
        // 返回加载的文件
        return new File(url.getPath());
    }
}

