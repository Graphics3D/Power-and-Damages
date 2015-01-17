/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <manuel.dahmen@gmail.com>

***/


package info.emptycanvas.apps.darz;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.move.SimpleTrajectory;
import java.util.Collection;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class Weird {
    Terrain t;
    Player p;
    SimpleTrajectory simpleTrajectory;
    private double longpc, latpc;
    public Weird(Terrain t, Player p) {
        this.t = t;
        this.p = p;
        
        simpleTrajectory = new SimpleTrajectory();
        for(int i=0; i<100; i++)
        {
            //simpleTrajectory.addPoints(null);
        }
    }

    
    public void update()
    {
        
    }
    
    
}
