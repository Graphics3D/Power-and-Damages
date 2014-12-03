/**
 * *
 * Global license :  *
 * CC Attribution
 *
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 *
 **
 */
package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.RepresentableConteneur;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public abstract class Terrain extends RepresentableConteneur {
    protected ParametrizedSurface ps;
    public Point3D calcNormale(double u, double v)
    {
        Point3D v1 = ps.calculerPoint3D(u+ps.incr1, v).moins(ps.calculerPoint3D(u, v));
        Point3D v2 = ps.calculerPoint3D(u, v+ps.incr2).moins(ps.calculerPoint3D(u, v));
        return v1.prodVect(v2);
    }
    public Point3D calcCposition(double u, double v) {
        return ps.calculerPoint3D(u, v);
    }

    //public Point3D calcDirection(double u, double v) {
    //    return ps.calculerVitesse3D(u, v);
    //}

}
