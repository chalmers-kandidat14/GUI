import static javax.media.opengl.GL.GL_LINES;

import javax.media.opengl.GL2;

/**
 * Class for printing the grid.
 * should fix it
 * @author gao
 *
 */

public class Grid {
	float x = 8.0f;
	float y = 8.0f;
	float z = 8.0f;
	
	public Grid(GL2 gl){
		gl.glLoadIdentity();
		gl.glLineWidth(1f);
		gl.glBegin(GL_LINES);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(x, 0, 0);
		gl.glVertex3f(0, 0, 0);
		gl.glEnd();
		
		gl.glLoadIdentity();
		gl.glLineWidth(1f);
		gl.glBegin(GL_LINES);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(0, y, 0);
		gl.glVertex3f(0, 0, 0);
		gl.glEnd();
		
		gl.glLoadIdentity();
		gl.glLineWidth(1f);
		gl.glBegin(GL_LINES);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(0, 0, z);
		gl.glVertex3f(0, 0, 0);
		gl.glEnd();
		
	}

	
	

}
