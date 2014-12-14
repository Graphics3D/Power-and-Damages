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
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.RepresentableConteneur;
import java.util.Iterator;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class SolSphere extends Terrain {

    private double radius = 1.0;

    private Point3D coord(double u, double v) {
        double a = u * 2 * Math.PI ;//- Math.PI;
        double b = v * 2 * Math.PI ;
        Point3D p = new Point3D(Math.sin(a) * Math.sin(b)
                * radius, Math.sin(a) * Math.cos(b) * radius,
                Math.cos(a) * radius);
        return p;

    }

    public SolSphere() {
        ps = new ParametrizedSurface() {
            @Override
            public Point3D calculerPoint3D(double u, double v) {
                return coord(u, v);
                
            }

            @Override
            public Point3D calculerVitesse3D(double u, double v) {
                Point3D pU = calculerPoint3D(u + 0.001, v).moins(calculerPoint3D(u, v));
                Point3D pV = calculerPoint3D(u, v + 0.001).moins(calculerPoint3D(u, v));
                return pV.plus(pU).norme1();
            }
        };
        SolPP sol = new SolPP(ps);
        RepresentableConteneur generateWire = sol.generateWire();

        Iterator<Representable> it = generateWire.iterator();

        while (it.hasNext()) {
            add(it.next());
        }
    }

}
