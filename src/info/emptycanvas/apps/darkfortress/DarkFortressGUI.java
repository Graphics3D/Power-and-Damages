package info.emptycanvas.apps.darkfortress;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public final class DarkFortressGUI extends JFrame implements KeyListener, Runnable {

    private PositionUpdate mover;
    private Drawer drawer;
    private boolean release_up = true;
    private boolean release_down = true;
    private boolean release_left = true;
    private boolean release_right = true;
    private final Class drawerType;

    public DarkFortressGUI(Class clazz) {
        String Title = "Dark Fortress ";
        
        mover = new PositionUpdateImpl(new SolPlan());
        
        this.drawerType = clazz;
        
          Logger.getLogger(DarkFortressGUI.class.getName()).log(Level.INFO, drawerType.getSimpleName());
        
        if(drawerType.equals(JoglDrawer.class))
        {            
            Title += "with OpenGL bindings";
            drawer = new JoglDrawer(this);
        }
        else if(drawerType.equals(EcDrawer.class))
        {            
            Title += "with Empty Canvas rendering";
            drawer = new EcDrawer(this);
        }
        setTitle(Title);
        
        drawer.setLogic(mover);
        
        
        addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_up = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_right = false;
        }
        mover.testCollision();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_up = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_right = true;
        }
        mover.testCollision();

        if(mover.estGagnant())
            gagne();

    }

    private void cont(long timeKeyPress) {
        if (!release_up) {
            mover.acc(timeKeyPress);
            //System.out.println("Acc");
        }
        if (!release_down) {
            mover.dec(timeKeyPress);
            //System.out.println("Dec");
        }
        if (!release_left) {
            mover.rotationGauche(timeKeyPress);
            //System.out.println("Left");
        }
        if (!release_right) {
            mover.rotationDroite(timeKeyPress);
            //System.out.println("Right");
        }
    }

    public void start() {
        requestFocus();
    }

    @Override
    public void run() {
            long timeBefore = System.currentTimeMillis();
            long timeAfter = timeBefore;
        while (true) {
            timeBefore = System.currentTimeMillis();
            cont(timeAfter-timeBefore);
            timeAfter = System.currentTimeMillis();
        }
        
    }

    private void gagne() {
    }

    public void setLevel(Class sol) {
        try {
            Terrain t = null ;
            t = (Terrain) sol.newInstance();
            mover = new PositionUpdateImpl(t);
            drawer.setLogic(mover);
        } catch (InstantiationException ex) {
            Logger.getLogger(DarkFortressGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DarkFortressGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
