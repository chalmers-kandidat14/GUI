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
	
	/**
	 * Using Open GL this constructor draws the grid
	 * @param gl
	 */
	public Grid(GL2 gl, int currConf){
		// x-axis
		gl.glLoadIdentity();
		gl.glLineWidth(1f);
		gl.glBegin(GL_LINES);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(x, 0, 0);

		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, y, 0);

		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 0, z);
		
		gl.glEnd();	
	}
}
