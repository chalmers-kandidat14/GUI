import static javax.media.opengl.GL.GL_LINES;

import java.util.Iterator;
import javax.media.opengl.GL2;

/**
 * Draws the lines for the molecules
 * 
 * @author kandidatgrupp
 *
 */


public class drawLines {
	
	int currConf;
	GL2 gl;
	
	public drawLines(GL2 gl,  int currConf) {
		this.currConf = currConf;
		this.gl = gl;
	}
	public void doDrawLines(){
		BallChain current = Conformations.retConf(currConf);
		Iterator<Ball> it = current.bollList.iterator();
		Ball prevBall = it.next();
		Ball nextBall;

		while (it.hasNext()) {
			nextBall = it.next();
			float currX = nextBall.getX();
			float currY = nextBall.getY();
			float currZ = nextBall.getZ();
			float prevX = prevBall.getX();
			float prevY = prevBall.getY();
			float prevZ = prevBall.getZ();

			gl.glLoadIdentity();
			gl.glLineWidth(3f);
			gl.glBegin(GL_LINES);
			gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
			gl.glVertex3f(currX, currY, currZ);
			gl.glVertex3f(prevX, prevY, prevZ);
			gl.glEnd();
			
			prevBall = nextBall;
		}
	}

}

