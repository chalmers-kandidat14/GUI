import static javax.media.opengl.GL.GL_LINES;

import javax.media.opengl.GL2;

/**
 * Class for printing the grid.
 * 
 * @author kandidatgrupp
 *
 */

public class Grid {
	float x = 12.0f;
	float y = 12.0f;
	float z = 12.0f;
	int currConf = 0;
	

	
	/**
	 * Using Open GL this constructor draws the grid
	 * @param gl
	 */
	public Grid(GL2 gl, int currConf){
		this.currConf = currConf;
		if(currConf >0){
			BallChain current = Conformations.retConf(currConf);
			Ball b = current.getLastBall();
		float xx = b.getX();
		float yy = b.getY();
		float zz = b.getZ();
		
		// x-axis
		gl.glLoadIdentity();
		gl.glLineWidth(1f);
		gl.glBegin(GL_LINES);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f( xx + x, yy, zz);
		gl.glVertex3f(xx, yy, zz);
		gl.glEnd();
		
		// y-axis
		gl.glLoadIdentity();
		gl.glLineWidth(1f);
		gl.glBegin(GL_LINES);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(xx, yy + y, zz);
		gl.glVertex3f(xx, yy, zz);
		gl.glEnd();
		
		// z-axis
		gl.glLoadIdentity();
		gl.glLineWidth(1f);
		gl.glBegin(GL_LINES);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(xx, yy, zz + z);
		gl.glVertex3f(xx, yy, zz);
		gl.glEnd();
		}
		else{
			// x-axis
			gl.glLoadIdentity();
			gl.glLineWidth(1f);
			gl.glBegin(GL_LINES);
			gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
			gl.glVertex3f( x, 0, 0);
			gl.glVertex3f(0, 0, 0);
			gl.glEnd();
			
			// y-axis
			gl.glLoadIdentity();
			gl.glLineWidth(1f);
			gl.glBegin(GL_LINES);
			gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
			gl.glVertex3f(0, y, 0);
			gl.glVertex3f(0, 0, 0);
			gl.glEnd();
			
			// z-axis
			gl.glLoadIdentity();
			gl.glLineWidth(1f);
			gl.glBegin(GL_LINES);
			gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
			gl.glVertex3f(0, 0, z);
			gl.glVertex3f(0, 0, 0);
			gl.glEnd();
		}
	}

	
	

}
