package info.emptycanvas.apps.darz;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

public abstract class Drawer {

    public abstract void setLogic(PositionUpdate l);

    public void initFrame(JFrame component) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame.setDefaultLookAndFeelDecorated(true);

        JMenuBar jMenuBar = new JMenuBar();

        jMenuBar.add(new JMenu("Game"));

        component.setJMenuBar(jMenuBar);

        screenSize.setSize(screenSize.getWidth() - 100, screenSize.getHeight() - 100);

        component.setSize(screenSize);
        Image image = Toolkit.getDefaultToolkit().getImage("favicon.ico");
        component.setIconImage(image);
        ArrayList<Image> imageList = new ArrayList<Image>();
        imageList.add(image);
        component.setIconImages(imageList);

        

        
    }
}
