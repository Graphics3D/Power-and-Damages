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
                (int)((Math.sin(Math.PI * f / 32)+0.5)*256),
                (int)((Math.sin(Math.PI * f / 64)+0.5)*256),
                (int)((Math.sin(Math.PI * f / 128)+0.5)*256));
    }
    /*
     red(i) = 0;
     green(i) = cos(π * i / 128);
     blue(i) = sin(π * i / 128);

     red(i) = cos(π * i / 128);
     green(i) = sin(π * i / 128);
     blue(i) = 0;
     */
}
