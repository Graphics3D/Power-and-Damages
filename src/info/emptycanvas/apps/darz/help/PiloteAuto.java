package info.emptycanvas.apps.darz.help;

import info.emptycanvas.apps.darz.Timer;
import info.emptycanvas.apps.darz.help.BonusClass;
import info.emptycanvas.library.gdximports.gdx_BSplineCurve;
import info.emptycanvas.library.object.Point3D;

/**
 *
 * @author Se7en
 */
public class PiloteAuto extends BonusClass {
    public Timer t;
    gdx_BSplineCurve bspline ;
    private final double duration;
    private final double timeStart;
    
    public PiloteAuto(double timeStart, double duration) {
        this.timeStart = timeStart;
        this.duration = duration;
    }
    public void createBSpline(Point3D[] points)
    {
    }
    public double timeStart()
    {
        return timeStart;
    }
    public double duration()
    {
        return duration;
    }
    public double tempsEcoule()
    {
        return System.nanoTime()-timeStart();
    }
    public boolean begins()
    {
        return System.nanoTime()>timeStart();
    }
    public boolean ends()
    {
        return System.nanoTime()>timeStart()+duration();
    }
    public Point3D getDirectionAtTimeT(double t)
    {
        return bspline.calculerPoint3D(t);
    }
}
