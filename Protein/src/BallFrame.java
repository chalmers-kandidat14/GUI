import java.awt.event.*;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAnimatorControl;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import java.awt.Dimension;
import java.awt.event.MouseWheelListener;

import com.jogamp.opengl.util.FPSAnimator;

import static javax.media.opengl.GL.*; // GL constants
import static javax.media.opengl.GL2.*; // GL2 constants

@SuppressWarnings("serial")
public class BallFrame extends GLCanvas implements GLEventListener{
	// Define constants for the top-level container

	private static int CANVAS_WIDTH = 720; // width of the drawable
	private static int CANVAS_HEIGHT = 640; // height of the drawable
	private static final int FPS = 60; // animator's target frames per second
	private static int currConf = 0;
	public static float zoomFactor = 1;
	
	private static Camera camera = new Camera(-1,-1,-1);

	public static GLCanvas createBallFrame() {
		// Create the OpenGL rendering canvas
		GLCanvas canvas = new BallFrame();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

		final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
		animator.start(); // start the animation loop

		
		canvas.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				camera.mouseWheelMoved(e);
			}
		});
		
		canvas.addMouseMotionListener(new MouseMotionListener() {
			@SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
				/*xLookAt = e.getX();
				yLookAt = e.getY();
				if (isClicked) {
					isClicked = false;
				} else {
					isClicked = true;
				}*/
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				//xLookAt = (float) ((e.getPoint().getX() - xLookAt) / 10.0f) - 30f;
				//yLookAt = (float) ((e.getPoint().getY() - yLookAt) / 10.0f) - 30f;
				//translater(xLookAt, yLookAt);
				
				camera.mouseDragged(e);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				camera.mouseMoved(e);
			}
		});

		canvas.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				camera.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				camera.mouseReleased(e);
			}
		
		});
		
		return canvas;
	}

	final static float rad = 30f;

	// Setup OpenGL Graphics Renderer
	private GLU glu; // for the GL Utility
	private static float dist = 10f;
	final float radius = 0.33f;
	final int slices = 30;
	final int stacks = 30;

	/** Constructor to setup the GUI for this Component */
	public BallFrame() {
		this.addGLEventListener(this); // for handling GLEvents
		this.setFocusable(true);
		this.requestFocus();
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
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
		});
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
		camera.focus(gl, glu, dist);
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

		// display(gl);
		if (Conformations.confSize() == 0) {
			gl.glLoadIdentity();
		} else {
			new displayBall(gl, currConf, glu, radius, slices, stacks);
			
		}
		
		
		// disable lightning
		gl.glDisable(GL2.GL_LIGHT1);
		gl.glDisable(GL2.GL_LIGHTING);

		// drawLines(gl);
		new Grid(gl, currConf);
		if (Conformations.confSize() == 0) {
			gl.glLoadIdentity();
		} else {
			drawLines dl = new drawLines(gl, currConf);
			dl.doDrawLines();
		}

	}

	
	public static int getConf() {
		return currConf;
	}

	public static void incrConf() {
		currConf++;
	}

	public static void decrConf() {
		currConf--;
	}

	public static void zeroConf() {
		currConf = 0;
	}

	/**
	 * ; Called back before the OpenGL context is destroyed. Release resource
	 * such as buffers.
	 */
	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	}