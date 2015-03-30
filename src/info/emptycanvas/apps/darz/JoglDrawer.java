package info.emptycanvas.apps.darz;

import info.emptycanvas.apps.darz.help.*;
import com.jogamp.newt.event.KeyEvent;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JApplet;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;

import info.emptycanvas.library.object.*;
import info.emptycanvas.library.tribase.TRIObjetGenerateur;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.sun.scenario.effect.impl.BufferUtil;
import java.awt.*;
import java.nio.IntBuffer;
import java.util.*;
import java.util.function.Consumer;

public class JoglDrawer extends Drawer implements GLEventListener {

    private final GLU glu = new GLU();
    private final Object component;
    private PositionUpdate mover;
    private Terrain terrain;
    private Bonus bonus;
    private TextRenderer renderer;
    private Vaisseau vaisseau;
    private boolean locked;
    private Circuit circuit;
    Timer timer;
    private final GLCanvas glcanvas;
    private int BUFSIZE;
    private Point2D pickPoint;
    private PiloteAuto piloteAuto;

    private PiloteAuto piloteAuto() {
        return piloteAuto;
    }

    private void piloteAuto(PiloteAuto pa) {
        piloteAuto = pa;
    }

    public JoglDrawer(DarkFortressGUI darkFortressGUI) {
        this.component = darkFortressGUI;

        GLProfile.initSingleton();
        GLProfile.initProfiles(GLProfile.getDefaultDevice());
        GLProfile profile = GLProfile.getDefault();
        System.out.println("Profil GL4 : " + GLProfile.isAvailable("GL4"));

        GLCapabilities capabilities = new GLCapabilities(profile);
        capabilities.setDoubleBuffered(true);

        glcanvas = new GLCanvas(capabilities);

        glcanvas.addGLEventListener(this);

        glcanvas.addKeyListener(darkFortressGUI);

        glcanvas.setSize(640, 480);

        initFrame((JFrame) component);

        ((JFrame) component).add(glcanvas);

        timer = new Timer();
        timer.init();
    }

    public void color(GL2 gl, Color c) {
        gl.glColor3f(
                c.getRed() / 255f,
                c.getGreen() / 255f, c.getBlue() / 255f);
    }

    public void draw(SegmentDroite segd, GLU glu, GL2 gl) {
        gl.glBegin(GL2.GL_LINES);
        color(gl, new Color(segd.texture().getColorAt(0.5, 0.5)));
        gl.glVertex3f((float) segd.getOrigine().get(0), (float) segd
                .getOrigine().get(1), (float) segd.getOrigine().get(2));
        gl.glVertex3f((float) segd.getExtremite().get(0), (float) segd
                .getExtremite().get(1), (float) segd.getExtremite().get(2));
        gl.glEnd();
        //System.out.print("SD");
        // System.out.println("L");
    }
    /*
     public void draw(Representable rep, GLU glu, GL2 gl)
     {
     throw new UnsupportedOperationException("Objet non supporte par "+getClass().getCanonicalName());
     }*/

    public void draw(TRI tri, GLU glu, GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        color(gl, new Color(tri.texture().getColorAt(0.5, 0.5)));
        for (int t = 0; t < 3; t++) {
            gl.glVertex3f((float) tri.getSommet()[t].get(0),
                    (float) tri.getSommet()[t].get(1),
                    (float) tri.getSommet()[t].get(2));
        }
        gl.glEnd();
		//System.out.print("T"+tri.getCouleur());

        // System.out.println("T");
    }

    public void draw(TRIObjetGenerateur s, GLU glu, GL2 gl) {
        for (int i = 0; i < s.getMaxX(); i++) {
            for (int j = 0; j < s.getMaxY(); j++) {
                TRI[] tris = new TRI[2];
                Point3D INFINI = new Point3D(0, 0, 100000);
                tris[0] = new TRI(INFINI, INFINI, INFINI);
                tris[1] = new TRI(INFINI, INFINI, INFINI);
                s.getTris(i, j, tris);
                draw(tris[0], glu, gl);
                draw(tris[1], glu, gl);
            }
        }
    }

    public void draw(TRIGenerable gen, GLU glu, GL2 gl) {
        draw(gen.generate(), glu, gl);
    }

    public void draw(TRIObject gen, GLU glu, GL2 gl) {
        gen.getTriangles().forEach((TRI t) -> {
            draw(t, glu, gl);
        });

    }

    public void draw(RepresentableConteneur rc, GLU glu, GL2 gl) {
        Iterator<Representable> it = rc.iterator();
        while (it.hasNext()) {
            Representable r = null;
            try {
                r = it.next();
            } catch (ConcurrentModificationException ex) {
                break;
            }
            if (r instanceof TRI) {
                draw((TRI) r, glu, gl);
            } else if (r instanceof SegmentDroite) {
                draw((SegmentDroite) r, glu, gl);
            } else if (r instanceof TRIObjetGenerateur) {
                TRIObjetGenerateur s = (TRIObjetGenerateur) r;
                draw(s, glu, gl);
            }

        }
    }

    public void draw(TRIConteneur con, GLU glu, GL2 gl) {
        /*if(con.getObj()==null && con instanceof TRIGenerable)
         {
         ((TRIGenerable)con).generate();
         }*/
        Iterable<TRI> iterable = con.iterable();
        iterable.forEach((TRI t) -> {
            draw(t, glu, gl);
        });

    }

    public void draw(Cube c, GLU glu, GL2 gl) {
        TRIObject generate = c.generate();
        draw(generate, glu, gl);
    }

    public void draw(String text, Color textColor, GLU glu, GL2 gl) {
        Dimension d = new Dimension(1, 1);
        if (component instanceof JFrame) {
            d = ((JFrame) component).getSize();
        }
        renderer.beginRendering((int) d.getWidth(), (int) d.getHeight());
        renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        renderer.draw(text, 10, 10);
        renderer.endRendering();
    }
    /*public void drawCard(Card c, GLU glu, GL2 gl)
     {
     //Buffer buffer;
     Dimension d = new Dimension(1,1);
     if(component instanceof JFrame)
     {
     d = ((JFrame)component).getSize();
     }
     //gl.glDrawPixels(0, 0, d.getWidth(), d.getHeight(),  buffer);
            
     }*/

    @Override
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();

        // Change to projection matrix.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        //glu.gluPerspective(60, 1.33, 0.001, 1.0);
        gl.glLoadIdentity();

        Point3D pos = mover.calcCposition();
        Point3D dir = mover.calcDirection();
        Point3D del = dir.moins(pos);
        glu.gluLookAt(pos.get(0), pos.get(1),
                pos.get(2), dir
                .get(0), dir.get(1),
                dir.get(2),
                del.prodVect(Point3D.Y.prodVect(del))
                .norme1().get(0),
                del.prodVect(Point3D.Y.prodVect(del))
                .norme1().get(1),
                del.prodVect(Point3D.Y.prodVect(del))
                .norme1().get(2));
        /*if(circuit==null)
         circuit = mover.getCircuit();
         if(circuit!=null)
         draw((TRIConteneur)circuit, glu, gl);
         /**/
        draw(bonus, glu, gl);
        draw(new Ciel().getBleu(), glu, gl);
        draw(terrain, glu, gl);
        int x = 0;
        int y = 0;
        double INCR_AA = 0.005;
        Plasma.scale = 50;
        for (double i = 0; i < 1; i += INCR_AA) {
            x = 0;

            for (double j = 0; j < 1; j += INCR_AA) {
                Point3D p1 = terrain.ps.calculerPoint3D(j, i);
                Point3D p2 = terrain.ps.calculerPoint3D(j + INCR_AA, i);
                Point3D p3 = terrain.ps.calculerPoint3D(j + INCR_AA, i + INCR_AA);

                draw(new TRI(p1, p2, p3, Plasma.color(x, y, time())), glu, gl);

                p1 = terrain.ps.calculerPoint3D(j, i);
                p2 = terrain.ps.calculerPoint3D(j, i + INCR_AA);
                p3 = terrain.ps.calculerPoint3D(j + INCR_AA, i + INCR_AA);

                draw(new TRI(p1, p2, p3, Plasma.color(x, y, time())), glu, gl);
                x++;
            }
            y++;
        }

        draw(vaisseau.getObject(), glu, gl);
        draw("Score :  " + mover.score(), Color.WHITE, glu, gl);

        Graphics g = null;
        if (component instanceof JApplet) {
            g = ((JApplet) component).getGraphics();
        }
        if (component instanceof JFrame) {
            g = ((JFrame) component).getGraphics();
        }

        if (g == null) {
            throw new NullPointerException("Problem initialising JFrame graphics");
        }
    }

    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged,
            boolean deviceChanged) {
        System.out.println("displayChanged called");
    }

    @Override
    public void init(GLAutoDrawable gLDrawable) {
        /*
         * System.out.println("init() called"); GL2 gl =
         * gLDrawable.getGL().getGL2(); gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
         * gl.glShadeModel(GL2.GL_FLAT);
         */

        GL2 gl = gLDrawable.getGL().getGL2();
        gLDrawable.setGL(new DebugGL2(gl));

        // Global settings.
        gl.glEnable(GL2.GL_DEPTH_TEST);

        /*
         gl.glDepthFunc(GL2.GL_LEQUAL);
         gl.glShadeModel(GL2.GL_SMOOTH);
         gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
		
         */
        gl.glClearColor(0f, 0f, 0f, 1f);

        // Start animator (which should be a field).
        FPSAnimator animator = new FPSAnimator(gLDrawable, 60);
        animator.start();
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 36));
    }

    @Override
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
            int height) {
        System.out.println("reshape() called: x = " + x + ", y = " + y
                + ", width = " + width + ", height = " + height);
        final GL2 gl = gLDrawable.getGL().getGL2();

        if (height <= 0) // avoid a divide by zero error!
        {
            height = 1;
        }

        final float h = (float) width / (float) height;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(60f, h, 0.001f, 2f);

        gl.glMatrixMode(GL2.GL_MODELVIEW);

    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        System.out.println("dispose() called");
    }

    @Override
    public void setLogic(PositionUpdate m) {
        this.mover = m;

        vaisseau = new Vaisseau(mover);
        terrain = ((PositionUpdateImpl) mover).getTerrain();
        bonus = new Bonus(terrain);
        mover.ennemi(bonus);
    }

    private boolean locked() {
        return locked;
    }

    private void setLocked(boolean l) {
        locked = l;
    }

    private double time() {
        return timer.getTimeEllapsed();
    }

    @Override
    public SegmentDroite click(Point2D p) {
        GLU glul = this.glu;

        /*
         double aspect = double(glcanvas.getWidth())/double(glcanvas.getHeight()); 
         glu.glMatrixMode( GL_PROJECTION ); 
         glLoadIdentity(); 
         glFrustum(-near_height * aspect,    
         near_height * aspect,    
         -near_height,    
         near_height, 
         zNear, 
         zFar ); 
         int window_y = (window_height - mouse_y) - window_height/2; 
         double norm_y = double(window_y)/double(window_height/2); 
         int window_x = mouse_x - window_width/2; 
         double norm_x = double(window_x)/double(window_width/2); 
         float y = near_height * norm_y; float x = near_height * aspect * norm_x; 
         float ray_pnt[4] = {0.f, 0.f, 0.f, 1.f}; float ray_vec[4] = {x, y, -near_distance, 0.f}; 

         GLuint buffer[BUF_SIZE]; glSelectBuffer (BUF_SIZE, buffer); 
         GLint hits; glRenderMode(GL_SELECT);
         glRenderMode(GL_RENDER); 
         */
        return null;
    }

    /*
     * prints out the contents of the selection array.
     */
    private void processHits(int hits, int buffer[]) {
        int names, ptr = 0;

        System.out.println("hits = " + hits);
        // ptr = (GLuint *) buffer;
        for (int i = 0; i < hits; i++) { /* for each hit */

            names = buffer[ptr];
            System.out.println(" number of names for hit = " + names);
            ptr++;
            System.out.println("  z1 is " + buffer[ptr]);
            ptr++;
            System.out.println(" z2 is " + buffer[ptr]);
            ptr++;
            System.out.print("\n   the name is ");
            for (int j = 0; j < names; j++) { /* for each name */

                System.out.println("" + buffer[ptr]);
                ptr++;
            }
            System.out.println();
        }
    }

    private void drawRects(GL2 gl, int mode) {
        if (mode == GL2.GL_SELECT) {
            gl.glLoadName(1);
        }
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3i(2, 0, 0);
        gl.glVertex3i(2, 6, 0);
        gl.glVertex3i(6, 6, 0);
        gl.glVertex3i(6, 0, 0);
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3i(3, 2, -1);
        gl.glVertex3i(3, 8, -1);
        gl.glVertex3i(8, 8, -1);
        gl.glVertex3i(8, 2, -1);
        gl.glColor3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3i(0, 2, -2);
        gl.glVertex3i(0, 7, -2);
        gl.glVertex3i(5, 7, -2);
        gl.glVertex3i(5, 2, -2);
        gl.glEnd();
    }
    /*
     * sets up selection mode, name stack, and projection matrix for picking. Then
     * the objects are drawn.
     */

    private void pickRects(GL2 gl) {
        int[] selectBuf = new int[BUFSIZE];
        IntBuffer selectBuffer = BufferUtil.newIntBuffer(BUFSIZE);
        int hits;
        int viewport[] = new int[4];
        // int x, y;

        gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);

        gl.glSelectBuffer(BUFSIZE, selectBuffer);
        gl.glRenderMode(GL2.GL_SELECT);

        gl.glInitNames();
        gl.glPushName(-1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        /* create 5x5 pixel picking region near cursor location */
        glu.gluPickMatrix((double) pickPoint.getX(),
                (double) (viewport[3] - pickPoint.getY()), //
                5.0, 5.0, viewport, 0);
        gl.glOrtho(0.0, 8.0, 0.0, 8.0, -0.5, 2.5);
        drawRects(gl, GL2.GL_SELECT);
        gl.glPopMatrix();
        gl.glFlush();

        hits = gl.glRenderMode(GL2.GL_RENDER);
        selectBuffer.get(selectBuf);
        processHits(hits, selectBuf);
    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        switch (key.getKeyChar()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

            default:
                break;
        }
    }
}
