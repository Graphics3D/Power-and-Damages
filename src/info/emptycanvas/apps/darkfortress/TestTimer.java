/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.apps.darkfortress;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestTimer {
    public static void main(String [] args)
    {
        Timer t = new Timer();
        t.init();
        int i=0;
        while(i<100)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(t.getTimeEllapsed());
        }
    }
}
