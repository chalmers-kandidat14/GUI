import java.awt.*;
import java.awt.event.*;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAnimatorControl;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.FPSAnimator;

import static javax.media.opengl.GL.*; // GL constants
import static javax.media.opengl.GL2.*; // GL2 constants

@SuppressWarnings("serial")
public class BallFrame extends GLCanvas implements GLEventListener, KeyListener {
	// Define constants for the top-level container

	private static int CANVAS_WIDTH = 720; // width of the drawable
	private static int CANVAS_HEIGHT = 640; // height of the drawable
	private static final int FPS = 60; // animator's target frames per second
	private static float xLookAt, yLookAt = 0.0f;
	private static float zLookAt = 30.0f;

	public static GLCanvas createBallFrame() {
		// Create the OpenGL rendering canvas
		GLCanvas canvas = new BallFrame();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

		final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
		animator.start(); // start the animation loop

		canvas.addMouseMotionListener(new MouseMotionListener() {
			@SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
				xLookAt = e.getX() - xLookAt;
				yLookAt = e.getY() - yLookAt;
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				xLookAt = (float) ((e.getPoint().getX() - xLookAt) / 10.0f) - 30f;
				yLookAt = (float) ((e.getPoint().getY() - xLookAt) / 10.0f) - 30f;
				translater(xLookAt, yLookAt);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		return canvas;
	}
	
	final static float rad = 30f;
	private static void translater(float x, float y) {
		zLookAt = (float) Math.sqrt(Math.pow(rad, 2) - Math.pow(x, 2) - Math.pow(y, 2));
	}
	

	// Setup OpenGL Graphics Renderer

	private GLU glu; // for the GL Utility
	private static float dist = 10f;
	final float radius = 0.33f;
	final int slices = 16;
	final int stacks = 16;
	private static int balls = 0;

	/** Constructor to setup the GUI for this Component */
	public BallFrame() {
		this.addGLEventListener(this); // for handling GLEvents
		this.addKeyListener(this); // for Handling KeyEvents
		this.setFocusable(true);
		this.requestFocus();
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL graphics context
		glu = new GLU(); // get GL Utilities
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
		gl.glClearDepth(1.0f); // set clear depth value to farthest
		gl.glEnable(GL_DEPTH_TEST); // enables depth testing
		gl.glDepthFunc(GL_LEQUAL);
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		gl.glShadeModel(GL_SMOOTH);
	}

	/**
	 * Call-back handler for window re-size event. Also called when the drawable
	 * is first set to visible.
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context

		if (height == 0)
			height = 1; // prevent divide by zero
		float aspect = (float) width / height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL_PROJECTION); // choose projection matrix
		gl.glLoadIdentity(); // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear,
														// zFar

		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}

	@Override
	public void display(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		// Set camera.
		setCamera(gl, glu, dist);

		// Prepare light parameters.
		float SHINE_ALL_DIRECTIONS = 1;
		float[] lightPos = { -30, 0, 0, SHINE_ALL_DIRECTIONS };
		float[] lightColorAmbient = { 0.2f, 0.2f, 0.2f, 1f };
		float[] lightColorSpecular = { 0.8f, 0.8f, 0.8f, 1f };

		// Set light parameters.
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular, 0);

		// Enable lighting in GL.
		gl.glEnable(GL2.GL_LIGHT1);
		gl.glEnable(GL2.GL_LIGHTING);
	

		for (int i = 0; i < balls; i++) {
			display(gl, i);
		}

		// disable lightning
		gl.glDisable(GL2.GL_LIGHT1);
		gl.glDisable(GL2.GL_LIGHTING);

		for (int i = 0; i < balls; i++) {
			drawLines(gl, i);
		}
	}
	
	public static void incI() {
		balls++;
	}
	
	public static int retI() {
		return balls;
	}

	private void drawLines(GL2 gl, int i) {
		if (i > 0) {
			float currX = CreateBall.getX(i);
			float currY = CreateBall.getY(i);
			float currZ = CreateBall.getZ(i);
			float prevX = CreateBall.getX(i - 1);
			float prevY = CreateBall.getY(i - 1);
			float prevZ = CreateBall.getZ(i - 1);

			gl.glLoadIdentity();
			gl.glLineWidth(3f);
			gl.glBegin(GL_LINES);
			gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
			gl.glVertex3f(currX, currY, currZ);
			gl.glVertex3f(prevX, prevY, prevZ);
			gl.glEnd();
		}
	}

	private void display(GL2 gl, int i) {
		gl.glLoadIdentity();
		float currX = CreateBall.getX(i);
		float currY = CreateBall.getY(i);
		float currZ = CreateBall.getZ(i);
		String colorB = CreateBall.getC(i);
		gl.glTranslatef(currX, currY, currZ);
		
		if(colorB.equals("P")){

			float[] rgba = { 0.8f, 0.1f, 0.0f };
			gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
			gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
			gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 0.5f);
		}
		else if(colorB.equals("H")){
			float[] rgba = { 0.0f, 0.2f, 1.0f };
			gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
			gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
			gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 0.5f);
		}
		else{
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

	public static void incrBall() {
		balls++;
	}

	public static int getBalls() {
		return balls;
	}

	private void setCamera(GL2 gl, GLU glu, float distance) {
		// Change to projection matrix.
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		// Perspective.
		float widthHeightRatio = (float) getWidth() / (float) getHeight();
		glu.gluPerspective(30, widthHeightRatio, 1, 1000);
		glu.gluLookAt(-xLookAt, yLookAt, zLookAt, 0, 0, 0, 0, 1, 0);

		// Change back to model view matrix.
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	/**
	 * ; Called back before the OpenGL context is destroyed. Release resource
	 * such as buffers.
	 */
	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_ESCAPE: // quit
			// Use a dedicate thread to run the stop() to ensure that the
			// animator stops before program exits.
			new Thread() {
				@Override
				public void run() {
					GLAnimatorControl animator = getAnimator();
					if (animator.isStarted())
						animator.stop();
					System.exit(0);
				}
			}.start();
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
	}
}