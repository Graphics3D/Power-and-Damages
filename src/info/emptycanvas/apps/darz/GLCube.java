package info.emptycanvas.apps.darz;


import javax.media.opengl.GL2;


public class GLCube {


    public static void draw(GL2 gl){

        final float DEMI_TAILLE_CUBE = 50f;

        

        // On s'apprete a dessiner des quadrilateres

        // Chaque groupe de 4 vertex definit 1 quadrilatere

        gl.glBegin(GL2.GL_QUADS);

        

        // les prochains vertex seront bleu

        gl.glColor3f(0f, 0f, 1f);

        

        // le quadrilatere de devant

        gl.glVertex3f(-DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        gl.glVertex3f(-DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        

        // les prochains vertex seront verts

        gl.glColor3f(0f, 1f, 0f);

                

        // le quadrilatere de derriere

        gl.glVertex3f(-DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(-DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        

        

        // les prochains vertex seront rouges

        gl.glColor3f(1f, 0f, 0f);

                

        // le quadrilatere de gauche

        gl.glVertex3f(-DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        gl.glVertex3f(-DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(-DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(-DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        

        // les prochains vertex seront oranges

        gl.glColor3f(1f, 0.5f, 0f);

                        

        // le quadrilatere de droite

        gl.glVertex3f(+DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        

        // les prochains vertex seront blancs

        gl.glColor3f(1f, 1f, 1f);

                                

        // le quadrilatere du haut

        gl.glVertex3f(-DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        gl.glVertex3f(-DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        

        

        // les prochains vertex seront jaunes

        gl.glColor3f(1f, 1f, 0f);

                                        

        // le quadrilatere de bas

        gl.glVertex3f(-DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

        gl.glVertex3f(-DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE);

        gl.glVertex3f(+DEMI_TAILLE_CUBE, -DEMI_TAILLE_CUBE, +DEMI_TAILLE_CUBE);

                

        // On a fini

        gl.glEnd();

    }

    

}