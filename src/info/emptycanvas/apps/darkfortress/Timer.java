/***
Global license : 

    CC Attribution
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.apps.darkfortress;

/**
 *
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class Timer {
    double timeEllapsed = 0.0;
    long timeEllapsedSystem = 0;
    public Timer()
    {
    }
    public void init()
    {
        timeEllapsedSystem = System.currentTimeMillis();
        timeEllapsed = 0.0;
    }
    
    public double getTimeEllapsed()
    {
        long timeInter = System.currentTimeMillis();
        
        timeEllapsed = (timeInter-timeEllapsedSystem)/1000.0;
        
        return timeEllapsed;
    }
    public void reset(){
        init();
    }
    
}
