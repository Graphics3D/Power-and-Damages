/**
 * *
 * Global license :  *
 * CC Attribution
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.apps.darz;

import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.object.Cube;
import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
class Vaisseau {

    private final PositionUpdate gm;

    public Vaisseau(PositionUpdate gm) {
        this.gm = gm;
    }

    public Cube getObject() {
        return new Cube(0.001, gm.calcDirection(),new ColorTexture( Color.GREEN));
    }
}
