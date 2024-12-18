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

        Font chineseFont=new Font("mplus_hzk_12", Font.PLAIN, 40);
        this.setLayout(null);

        ImageLabel guideLabel = new ImageLabel(ResourceManager.loadImage("/Images/guide.jpg"),61,0,238,1024);
        this.add(guideLabel);

        int xspace=120,yspace=100;
        ImageLabel gunLabel = new ImageLabel(ResourceManager.loadImage("/Images/Gun.png"),30,17*60,60,60);
        this.add(gunLabel);
        JLabel label1=new JLabel();
        label1.setText("É¢µ¯");
        label1.setFont(chineseFont);
        label1.setForeground(Color.WHITE);
        label1.setBounds(30+1*xspace,17*60,120,60);
        label1.setOpaque(false);
        this.add(label1);


        ImageLabel missileLabel = new ImageLabel(ResourceManager.loadImage("/Images/Laster.jpg"),30,17*60+1*yspace,60,60);
        this.add(missileLabel);
        JLabel label2=new JLabel();
        label2.setText("»÷´©½ðÊôÇ½");
        label2.setFont(chineseFont);
        label2.setForeground(Color.WHITE);
        label2.setBounds(30+1*xspace-30-10-10,17*60+1*yspace,300,60);
        label2.setOpaque(false);
        this.add(label2);
    }

}


