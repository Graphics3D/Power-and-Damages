package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.object.Point2D;
import java.awt.Color;

public class Plasma {
    public static double scale;
    public static double f(double x, double y, double t) {
        return Math.sin(
                new Point2D(x, y).
                distance(
                        new Point2D((128 * Math.sin(-t) + 128), (128 * Math.cos(-t) + 128))
                ) / scale
        );

    }

    public static Color color(double f) {
        return new Color(
                (float)((Math.cos(Math.PI * f + 0.5)+1)/2),
                (float)((Math.cos(Math.PI * f + 1.0)+1)/2),
                (float)((Math.cos(Math.PI * f + 1.5)+1)/2)
        );
    }
}
