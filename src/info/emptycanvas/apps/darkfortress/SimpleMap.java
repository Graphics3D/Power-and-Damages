/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Polygone;
import info.emptycanvas.library.object.Scene;
import info.emptycanvas.library.object.ZBuffer;
import info.emptycanvas.library.object.ZBufferFactory;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class SimpleMap extends Map
{
    private Point3D gamePosition;
    private final Camera camera;
    private final ArrayList<Polygone> polygones;
    private ZBuffer z;

    
    public ArrayList<Polygone> getPolygones() {
        return polygones;
    }
    
    public SimpleMap()
    {
        polygones = new ArrayList<Polygone>();
        camera = new Camera(new Point3D(0.5,1,0.5), new Point3D(0.5,0,0.5));
    }
    
    public Camera camera()
    {
        return camera;
    }

    public void prepareImage()
    {
        z = ZBufferFactory.instance(width, height);
        z.scene(new Scene());
        Iterator<Polygone> itP;
        itP = polygones.iterator();
        while(itP.hasNext())
            z.scene().add(itP.next());
        z.scene().add(gamePosition);
    }
    
    @Override
    public Point3D getGamePosition() {
        return gamePosition;
    }

    @Override
    public void setGamePosition(Point3D coordonnees) {
        this.gamePosition = coordonnees;
    }
    
    @Override
    public BufferedImage genererImage() {
        prepareImage();
        return z.image();
    }

    @Override
    public void initCard(int width, int height, Color[][] objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
