package info.emptycanvas.apps.darz;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public abstract class Drawer {

    public abstract void setLogic(PositionUpdate l);

    public void init(JFrame component) {

        component.setSize(640, 480);
        try {
            component.setIconImage(ImageIO.read(getClass().getResource("/info/emptycanvas/apps/darkfortress/favicon.ico")));
        } catch (IOException ex) {
            Logger.getLogger(JoglDrawer.class.getName()).log(Level.SEVERE, null, ex);
        }

        component.pack();
    }
}
