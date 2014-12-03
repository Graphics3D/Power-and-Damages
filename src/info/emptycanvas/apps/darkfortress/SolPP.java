/**
 * *
 * Global license :  *
 * Microsoft Public Licence
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
    Creation time 05-nov.-2014
 *
 **
 */
package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.RepresentableConteneur;
import info.emptycanvas.library.object.SegmentDroite;
import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class SolPP {

    protected double INCR = 0.005;
    private ParametrizedSurface sol;

    public SolPP() {
    }

    public ParametrizedSurface getObject() {
        return sol;
    }

    public SolPP(ParametrizedSurface ps) {
        this.sol = ps;
    }

    public RepresentableConteneur generateWire() {
        RepresentableConteneur rc = new RepresentableConteneur();


        for (double i = 0; i < 1; i += INCR) {
            for (double j = 0; j < 1; j += INCR) {
                Point3D p1 = sol.calculerPoint3D(j, i);
                Point3D p2 = sol.calculerPoint3D(j + INCR, i);

                rc.add(new SegmentDroite(p1, p2, Color.WHITE));

                p1 = sol.calculerPoint3D(j, i);
                p2 = sol.calculerPoint3D(j, i + INCR);

                rc.add(new SegmentDroite(p1, p2, Color.WHITE));
            }
        }

        
        return rc;
    }
}
