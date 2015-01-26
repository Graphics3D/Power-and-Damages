/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

    Creation time 05-nov.-2014

***/


package info.emptycanvas.apps.darz;

import info.emptycanvas.apps.darz.Bonus.TRISphere2;
import info.emptycanvas.library.object.ColorTexture;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.tribase.TubulaireN;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Circuit extends TubulaireN
{
   
   public Circuit(ArrayList<Point3D> listPoint3d)
   {
       listPoint3d.forEach(this::addPoint);
       this.texture(new ColorTexture(Color.ORANGE));
   }

    public Circuit(Bonus bonus) {
       Iterator<Representable> iterator = bonus.getListRepresentable().iterator();
       while(iterator.hasNext())
       {
           Representable next = iterator.next();
           if(next != null && next instanceof TRISphere2)
           {
               addPoint(((TRISphere2)next).getCentre());
           }
           
           this.texture(new ColorTexture(Color.ORANGE));
       }
       
       
    }
   
   public TubulaireN baseCircuitRepresentable()
   {
       return this;
   }
    
    
}
