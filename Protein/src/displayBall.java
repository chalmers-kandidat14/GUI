import java.util.Iterator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 * Draws the balls for the molecule
 * 
 * @author kandiatgrupp
 * 
 */

public class displayBall {
	int currConf;
	GL2 gl;
	GLU glu;
	float radius;
	int slices;
	int stacks;
	/**
	 * Handles all the parameters
	 * @param gl
	 * @param currConf
	 * @param glu
	 * @param radiusf
	 * @param slices
	 * @param stacks
	 */
	public displayBall(GL2 gl, int currConf, GLU glu, float radiusf,
			int slices, int stacks) {
		this.gl = gl;
		this.currConf = currConf;
		this.glu = glu;
		radius = radiusf;
		this.slices = slices;
		this.stacks = stacks;
		drawBall();
	}
	/**
	 * Method that draws the current current confirmation
	 * using Open GL. The method creates a Iterator on a 
	 * BallChain and prints every Ball.
	 */
	private void drawBall() {
		BallChain current = Conformations.retConf(currConf);
		Iterator<Ball> it = current.bollList.iterator();
		float currX, currY, currZ;
		String colorB;
//lade till % 10
		while (it.hasNext()) {
			Ball nextBall = it.next();
			currX = nextBall.getX();
			currY = nextBall.getY();
			currZ = nextBall.getZ();
			colorB = nextBall.getc();

			gl.glLoadIdentity();
			gl.glTranslatef(currX, currY, currZ);
			if (colorB.equals("H")) {
				float[] rgba = { 0.8f, 0.1f, 0.0f };
				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
				gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 0.5f);
			} else if (colorB.equals("P")) {
				float[] rgba = { 0.0f, 0.2f, 1.0f };
				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
				gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 0.5f);
			} else {
				float[] rgba = { 1.0f, 1.0f, 1.0f };
				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
				gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
				gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 0.5f);

			}

			GLUquadric sphere = glu.gluNewQuadric();
			glu.gluQuadricDrawStyle(sphere, GLU.GLU_FILL);
			glu.gluQuadricNormals(sphere, GLU.GLU_FLAT);
			glu.gluQuadricOrientation(sphere, GLU.GLU_OUTSIDE);
			glu.gluSphere(sphere, radius, slices, stacks);
		}
	}
}
