/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.object.Point3D;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public abstract class Map {
    public final int CARD_TYPE_COLORS = 0;
    public final int CARD_TYPE_POLYGONS = 1;
    protected int width;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    protected int height;
    
    public abstract Point3D getGamePosition();
    public abstract void setGamePosition(Point3D coordonnees);
    public abstract BufferedImage genererImage();
    public abstract void initCard(int width, int height, Color [][] objet);
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
