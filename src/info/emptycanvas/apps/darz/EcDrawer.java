package info.emptycanvas.apps.darz;

import java.awt.Color;
import java.awt.Graphics;

import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.ECBufferedImage;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.ZBuffer;
import info.emptycanvas.library.object.ZBufferFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class EcDrawer extends Drawer implements Runnable {

    private DarkFortressGUI component;
    private Terrain terrain;
    private Bonus bonus;
    private ZBuffer z;
    private int w, h, aw, ah;
    private Vaisseau vaisseau;
    private PositionUpdate mover;

    public EcDrawer(DarkFortressGUI darkFortress) {
        this.component = darkFortress;

        z = ZBufferFactory.instance(100, 100);

        darkFortress.setSize(640, 480);
        
        darkFortress.pack();
        
        new Thread(this).start();
        
        initFrame(component);

    }

    public void resize() {
        z = ZBufferFactory.instance(w, h);
        z.couleurDeFond(new TColor(Color.black));

        ah = h;
        aw = w;
    }
    /* (non-Javadoc)
     * @see be.ibiiztera.darkfortress.Drawer#setLogic(be.ibiiztera.darkfortress.GameLogic)
     */

    @Override
    public void setLogic(PositionUpdate m) {
        this.mover = m;
        vaisseau = new Vaisseau(mover);
        terrain = ((PositionUpdateImpl) mover).getTerrain();
        bonus = new Bonus(terrain);
        mover.ennemi(bonus);
        }

    /* (non-Javadoc)
     * @see be.ibiiztera.darkfortress.Drawer#run()
     */
    @Override
    public void run() {

        while (true) {
            dessiner();

            w = component.getWidth();
            h = component.getHeight();

            if (ah != h || aw != w) {
                resize();
            }
            /*try {
             Thread.sleep(10);
             } catch (InterruptedException e) {
             e.printStackTrace();
             }*/
        }
    }

    /* (non-Javadoc)
     * @see be.ibiiztera.darkfortress.Drawer#dessiner()
     */
    public void dessiner() {
        Graphics g = component.getGraphics();

        //z.couleurDeFond(new TColor(Color.BLACK));
        if (g != null && component.getWidth() > 0 && component.getHeight() > 0) {

            z.suivante();

            z.scene(new Scene());

            if (mover != null) {
                z.scene().add(terrain);
                z.scene().add(bonus);
                z.scene().add(vaisseau.getObject());
                z.scene().cameraActive(new Camera(
                        mover.calcCposition(),
                        mover.calcDirection()
                ));
            }
            try {
                z.dessinerSilhouette3D();
            } catch (Exception ex) {
                System.err.println("Ex");
            }
            ECBufferedImage ri = z.image();

            Graphics g2 = ri.getGraphics();
            g2.setColor(Color.WHITE);
            g2.drawString("Score : " + mover.score(), 0, ri.getHeight() - 40);

            g.drawImage(ri, 0, 0, component.getWidth(), component.getHeight(), null);

        }
    }

    public boolean isLocked() {
        return z.isLocked();
    }

}
