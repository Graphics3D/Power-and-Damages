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

    public Weird(Terrain t, Player p) {
        this.t = t;
        this.p = p;
        
        new SimpleTrajectory();
    }

    
    public void update()
    {
        
    }
    
    
}
