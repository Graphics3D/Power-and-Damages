package info.emptycanvas.apps.darz;

import info.emptycanvas.library.object.Point2D;
import info.emptycanvas.library.object.SegmentDroite;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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

    /**
     *
     * @param p Point 2D in the window (mouse cordinates)
     * @return Segment Near Far direction of click
     */
    public abstract SegmentDroite click(Point2D p);
}
