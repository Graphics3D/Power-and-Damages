package info.emptycanvas.apps.darz;

import info.emptycanvas.library.nurbs.ParametrizedSurface;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.RepresentableConteneur;
import java.util.Iterator;

@SuppressWarnings("serial")
public class SolReliefMouvant extends Terrain {

    protected Timer timer = new Timer();

    public SolReliefMouvant() {
        timer.init();
        ps = new ParametrizedSurface() {
            @Override
            public Point3D calculerPoint3D(double u, double v) {
                return new Point3D(u - 0.5, Math.sin(2 * Math.PI * (u + 2) + timer.getTimeEllapsed() / 100.0) * Math.cos(2 * Math.PI * (v + 2) + timer.getTimeEllapsed() / 100.0), v - 0.5);
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
