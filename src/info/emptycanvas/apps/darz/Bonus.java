package info.emptycanvas.apps.darz;

import info.emptycanvas.apps.darz.help.BonusClass;
import info.emptycanvas.apps.darz.help.Cheval_Licorne;
import info.emptycanvas.apps.darz.help.Escargot;
import info.emptycanvas.apps.darz.help.MouvementDirectionnel;
import info.emptycanvas.library.object.ColorTexture;
import java.awt.Color;
import java.util.Random;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.tribase.TRISphere;
import info.emptycanvas.library.object.RepresentableConteneur;

public class Bonus extends RepresentableConteneur {

    private final Terrain terrain;
    private static final int SIZE;
    private static final int licorne;
    private static final int escargot;
    private static final int fuite;

    static {
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("info/emptycanvas/apps/darz/Bundle"); // NOI18N
        SIZE = Integer.parseInt(bundle.getString("bonus.size"));
        licorne = Integer.parseInt(bundle.getString("licorne.size"));
        escargot = Integer.parseInt(bundle.getString("escargot.size"));
        fuite = Integer.parseInt(bundle.getString("fuite.size"));
    }

    class TRISphere2<T extends BonusClass> extends TRISphere {

        double u;
        double v;
        BonusClass clazz;
        public TRISphere2(Point3D c, double r) {
            super(c, r);
            clazz = new BonusClass();
        }

        {
            this.v = (r.nextFloat());
        }

        {
            this.u = (r.nextFloat());
        }

        @Override
        public Point3D coordPoint3D(int x, int y) {
            return super.coordPoint3D(x, y).plus(terrain.calcCposition(u, v));
        }

        @Override
        public synchronized Point3D getCentre() {
            return super.getCentre().plus(terrain.calcCposition(u, v));
        }
        public BonusClass getBonusClass()
        {
            return clazz;
        }
    }

    Random r = new Random();
    private boolean locked = false;

    public Bonus(Terrain t) {
        this.terrain = t;

        for (int i = 0; i < SIZE; i++) {
            TRISphere2<BonusClass> s = new TRISphere2<>(Point3D.O0, 0.01);
            s.texture(new ColorTexture(Color.RED));

            s.setMaxX(4);

            s.setMaxY(4);

            add(s);

        }
        for (int i = 0; i < licorne; i++) {
            TRISphere2<Cheval_Licorne> s;
            s = new TRISphere2<>(Point3D.O0, 0.01);
            s.texture(new ColorTexture(Color.BLUE));

            s.setMaxX(4);

            s.setMaxY(4);

            add(s);

        }
        for (int i = 0; i < escargot; i++) {
            TRISphere2<Escargot> s = new TRISphere2<>(Point3D.O0, 0.01);
            s.texture(new ColorTexture(Color.GRAY));

            s.setMaxX(4);

            s.setMaxY(4);

            add(s);

        }
        for (int i = 0; i < fuite; i++) {
            TRISphere2<MouvementDirectionnel> s = new TRISphere2<>(Point3D.O0, 0.01);
            s.texture(new ColorTexture(Color.GREEN));

            s.setMaxX(4);

            s.setMaxY(4);

            add(s);

        }
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
