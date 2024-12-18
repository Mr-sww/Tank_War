package Engine;

import java.awt.*;
import javax.swing.*;

public class SideNormalPanel extends JPanel{
    Image backgroundImage;
    CardLayout cardLayout;
    JPanel cardPanel;
    public SideNormalPanel(CardLayout cardLayout,JPanel cardPanel,int width, int height) {
        backgroundImage = ResourceManager.loadImage("/Images/guide.jpg");
        setPreferredSize(new Dimension(width, height));
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.setBackground(Color.BLACK);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage!= null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int imageWidth = backgroundImage.getWidth(this);
            int imageHeight = backgroundImage.getHeight(this);
            // 计算图片在面板中的x和y坐标，使其居中
            int x = (panelWidth - imageWidth)/2;
            int y = (panelHeight - imageHeight)/2;
            g.drawImage(backgroundImage, x, y, this);
            //g.drawImage(backgroundImage, 0, 0, getWidth(),getHeight(),this);
        }
    }
}


