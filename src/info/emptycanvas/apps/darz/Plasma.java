package info.emptycanvas.apps.darz;

import info.emptycanvas.library.object.Point2D;
import java.awt.Color;

public class Plasma {
    public static double scale;
    public static double t_factor = 0.001;
    public static double f(double x, double y, double t) {
        return Math.sin(
                new Point2D(x, y).
                distance(
                        new Point2D(
                                (Math.sin(-t*t_factor)), 
                                (Math.cos(-t*t_factor)))
                ) / scale
        );

    }

    public static Color color(double x, double y, double t) {
        return new Color(
                (float)((Math.cos(Math.PI * f(x,y,t) + 0.5 + t)+1)/2),
                (float)((Math.cos(Math.PI * f(x,y,t) + 1.0 + t)+1)/2),
                (float)((Math.cos(Math.PI * f(x,y,t) + 1.5 + t)+1)/2)
        );
    }
}
