/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.apps.darz;

import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.tribase.TRISphere;
import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Ciel {
    private TRISphere bleu;

    public Ciel() {
        bleu = new TRISphere(Point3D.O0, 2);
        bleu.texture(new ColorTexture(Color.BLUE));
        
    }

    public TRISphere getBleu() {
        return bleu;
    }
    
    
    
}
