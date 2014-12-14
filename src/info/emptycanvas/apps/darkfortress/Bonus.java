package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.object.ITexture;
import java.awt.Color;
import java.util.Random;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.tribase.TRISphere;
import info.emptycanvas.library.object.RepresentableConteneur;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bonus extends RepresentableConteneur {

    Random r = new Random();
    private boolean locked = false;

    public Bonus(Terrain t) {
        for (int i = 0; i < SIZE; i++) {
            TRISphere s = new TRISphere(Point3D.O0, 0.01) {
                double u;
                double v;
                {
                    this.v = (r.nextFloat());
                }
                {
                    this.u = (r.nextFloat());
                }

                @Override
                public Point3D coordPoint3D(int x, int y) {
                    return super.coordPoint3D(x, y).plus(t.calcCposition(u, v));
                }

                @Override
                public synchronized Point3D getCentre() {
                    return super.getCentre().plus(t.calcCposition(u, v));
                }
                
            };
            s.texture((ITexture) new TColor(Color.RED));

            s.setMaxX(4);

            s.setMaxY(4);

            add(s);

        }
    }
    private static int SIZE;
    {
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("info/emptycanvas/apps/darkfortress/Bundle"); // NOI18N
        SIZE = Integer.parseInt(bundle.getString("Bonus_Size"));
        
    }
    public boolean removeBonus(Representable r2) {
        boolean success = false;
        while (!success && this.getListRepresentable().contains(r2)) {
            try {
                super.remove(r2);
                success = true;
                if (this.getListRepresentable().isEmpty()) {
                    return true;
                }
            } catch (Exception ex) {
                success = false;
            }
        }
        return false;
    }
//
//    public boolean isLocked()
//    {
//        return locked;
//    }
//    public void setLocked(boolean locked)
//    {
//        this.locked = locked;
//    }
//    public boolean getLock()
//    {
//        if(!locked)
//        {
//            locked = true;
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//
//    public void waitForLock() {
//        while(!getLock())
//        {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Bonus.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
}
