package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.RepresentableConteneur;
import java.util.Iterator;

@SuppressWarnings("serial")
public class SolPlan extends Terrain {

    

    public SolPlan() {
        ps = new ParametrizedSurface() {

            @Override
            public Point3D calculerPoint3D(double u, double v) {
                return new Point3D(u - 0.5, 0.05, v - 0.5);
            }

            @Override
            public Point3D calculerVitesse3D(double u, double v) {
                return new Point3D(0, 0, 1);
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
