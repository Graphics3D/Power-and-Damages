package info.emptycanvas.apps.darkfortress;

import info.emptycanvas.library.object.Point2D;
import java.util.Iterator;

import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.tribase.TRISphere;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PositionUpdateImpl implements PositionUpdate, Runnable {

    private double unitPerMillis = 1.0 / 10000;
    private double rotationPerMillis = 1.0 / 3000;
    private Point2D position2D = new Point2D(0.5, 0.5);
    private Point2D direction2D = new Point2D(0, 1);
    private final double hauteur = 0.006;
    protected Point3D positionOrigine = Point3D.O0.plus(hauteur);
    protected Point3D position = positionOrigine;
    protected double positionIncrement = .01;
    protected double directionNorme = 1;
    protected Point3D directionOrigine = Point3D.Z;
    protected Point3D direction = directionOrigine;
    protected double angle = 0.0;
    private int score;
    private Bonus bonus;
    protected static final int STATE_GAME_IN_PROGRESS = 1;
    private boolean gagne = false;
    private Terrain terrain;
    private double collision_distance = 0.1;

    public PositionUpdateImpl(Terrain t) {
        this.terrain = t;
        bonus = new Bonus(terrain);
    }

    @Override
    public void update() {
    }

    @Override
    public void acc(long timeMillis) {

        position2D = position2D.plus(direction2D.mult(-timeMillis * unitPerMillis));
    }

    @Override
    public void dec(long timeMillis) {
        position2D = position2D.moins(direction2D.mult(-timeMillis * unitPerMillis));
    }

    @Override
    public int state() {
        return STATE_GAME_IN_PROGRESS;
    }

    @Override
    public GameObject[] getObjects() {
        return null;
    }

    @Override
    public Point2D position() {
        return position2D;
    }

    @Override
    public Point2D direction() {
        return direction2D;
    }

    @Override
    public void rotationGauche(long timeMillis) {
        angle = angle - Math.PI * 2 * rotationPerMillis * timeMillis;
        direction2D
                = new Point2D(
                        directionNorme * Math.sin(angle),
                        directionNorme * Math.cos(angle));
    }

    @Override
    public void rotationDroite(long timeMillis) {
        angle = angle + Math.PI * 2 * rotationPerMillis * timeMillis;
        direction2D
                = new Point2D(
                        directionNorme * Math.sin(angle),
                        directionNorme * Math.cos(angle));

    }

    @Override
    public void testCollision() {
        if (bonus == null) {
            return;
        }

        Point3D pos = calcCposition();

        //bonus.waitForLock();
                
        List<Representable> listRepresentable = bonus.getListRepresentable();
        
        try
        {
        for(Representable r : listRepresentable) {
            boolean catched = false;
            if (r instanceof TRISphere) {
                if (Point3D.distance(((TRISphere) r).getCentre(), pos) < collision_distance) {
                    int points = 10;
                    //System.out.println("POINTS" + points);

                    score += points;

                    System.out.println(score);

                    boolean removed = false;
                    while (!removed) {
                        try {
                            if (bonus.getListRepresentable().contains(r)) {
                                bonus.getListRepresentable().remove(r);
                                removed = true;
                            } else {
                                removed = false;
                            }

                        } catch (ConcurrentModificationException ex) {

                        }
                    }
                }
            }
        }
        } catch (ConcurrentModificationException ex) {
            
        }
    }

    @Override
    public void ennemi(Bonus e) {
        this.bonus = e;
    }

    @Override
    public int score() {
        return score;
    }

    private void win() {
        gagne = true;
    }

    @Override
    public boolean estGagnant() {
        return bonus.getListRepresentable().isEmpty();
    }

    @Override
    public Point3D calcCposition() {
        return terrain.calcCposition(position2D.getX(), position2D.getY()).plus(terrain.calcNormale(position2D.getX(), position2D.getY()).norme1().mult(hauteur));
    }

    @Override
    public Point3D calcDirection() {
        return terrain.calcCposition(position2D.getX() + direction2D.getX() * positionIncrement, position2D.getY() + direction2D.getY() * positionIncrement);
    }

    @Override
    public int STATE_GAME_IN_PROGRESS() {
        return STATE_GAME_IN_PROGRESS;
    }

    @Override
    public void setTerrain(Terrain t) {
        this.terrain = t;
    }

    @Override
    public Point3D calculerPositionAuSol(Point2D position2D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point3D calculerPositionModule(Point2D position2D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point3D calculerDirectionAuSol(Point2D position2D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Terrain getTerrain() {
        return terrain;
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                Thread.sleep(20);
                testCollision();
            } catch (InterruptedException ex) {
                Logger.getLogger(PositionUpdateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
