/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.object.Point2D;
import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public interface PositionUpdate {

    public void update();

    public void acc(long timeMillis);

    public void dec(long timeMillis);

    public int state();

    public GameObject[] getObjects();

    public Point2D position();

    public Point2D direction();

    public void rotationGauche(long timeMillis);

    public void rotationDroite(long timeMillis);

    public void testCollision();

    public void ennemi(Bonus e);

    public int score();

    public boolean estGagnant();
    public Point3D calcCposition(); 
    public Point3D calcDirection();

    public Point3D calculerPositionAuSol(Point2D position2D);
    public Point3D calculerPositionModule(Point2D position2D);
    public Point3D calculerDirectionAuSol(Point2D position2D);
    
    
    public int STATE_GAME_IN_PROGRESS();
    
    public void setTerrain(Terrain t);
}
