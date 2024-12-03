package Engine;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ResourceManager {
    public static Image loadImage(String path) {
        URL url = ResourceManager.class.getResource(path);
        if (url == null) {
            System.out.println("×ÊÔ´Î´ÕÒµ½£º" + path);
            return null;
        }
        return new ImageIcon(url).getImage();
    }
}
