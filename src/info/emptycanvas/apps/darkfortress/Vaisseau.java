/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.object.Cube;
import info.emptycanvas.library.object.Polygone;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.SegmentDroite;
import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
class Vaisseau {
    private final PositionUpdate gm;
    public Vaisseau(PositionUpdate gm)
    {
        this.gm = gm;
    }
    public Cube getObject()
    {
        /*return new SegmentDroite(
            gm.calcCposition(), gm.calcCposition().plus(gm.calcDirection()),
            Color.GREEN
        );*/
        return new Cube(0.005,gm.calcDirection(), Color.GREEN);
        
    }
}
