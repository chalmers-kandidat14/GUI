

import java.awt.event.*;
import java.util.Iterator;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;


import static javax.media.opengl.GL.GL_LINES;
import static javax.media.opengl.GL2.*; // GL2 constants


public class Camera implements MouseWheelListener, MouseMotionListener, MouseListener, KeyListener {

	private double phi, theta, rho;
	private double posX, posY, posZ;
	private double ballX, ballY;
	private int leftButton, rightButton;
	private float aspect;

	public Camera(double posX, double posY, double posZ) {
		phi = 60;
		theta = 20;
		rho = 70;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		ballX = 1;
		ballY = 1;
		leftButton = 0;
		rightButton = 0;
		aspect = 1;
	}
	
	public void focus(GL2 gl, GLU glu, double distance) {
		double xc = posX + rho * Math.cos(phi * Math.PI / 180) * Math.cos(theta * Math.PI / 180);
		double yc = posY + rho * Math.sin(phi * Math.PI / 180) * Math.cos(theta * Math.PI / 180);
		double zc = posZ + rho * Math.sin(theta * Math.PI / 180);
		
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(40, aspect, 1, 1000);		
		glu.gluLookAt(xc, yc, zc, posX, posY, posZ, 0, 0, 1);
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public void reshape(GLU glu, GL2 gl, int width, int height) {
		if (height == 0)
			height = 1; // prevent divide by zero
		aspect = (float) width / height;

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL_PROJECTION); // choose projection matrix
		gl.glLoadIdentity(); // reset projection matrix
		glu.gluPerspective(40.0, aspect, 0.01, 1000.0); // fovy, aspect, zNear,
														// zFar

		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}
	
	public void drawCameraCursor(GL2 gl){
		gl.glLoadIdentity();
		gl.glLineWidth(2f);
		gl.glBegin(GL_LINES);
		
		float x = (float) posX, y = (float) posY, z = (float) posZ;
		float d = (float) rho * 0.009f;

		
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
		gl.glVertex3f(x - d, y, z);
		gl.glVertex3f(x + d, y, z);
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(x, y - d, z);
		gl.glVertex3f(x, y + d, z);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(x, y, z - d);
		gl.glVertex3f(x, y, z + d);
		
		gl.glEnd();

	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double amount = (double) e.getWheelRotation();
		if (amount > 0)
			rho *= Math.pow(0.9, amount);
		else if (amount < 0)
			rho *= Math.pow(1.1, -amount);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		double x = e.getPoint().getX();
		double y = e.getPoint().getY();
		
		double deltaX = 0, deltaY = 0;
		if (ballX != -1 || true) {
			deltaX = -x + ballX;
		}
		if (ballY != -1 || true) {
			deltaY = y - ballY;
		}
		
		
		if (leftButton == 1) {
			phi += deltaX;

			if (deltaY + theta > 80)
				theta = 80;
			else if (deltaY + theta < -80)
				theta = -80;
			else
				theta += deltaY;
		}
		
		if (rightButton == 1) {
			posX += 0.003 * rho * ((-Math.sin(phi * Math.PI / 180)) * deltaX -
					  Math.cos(phi * Math.PI / 180) * deltaY);

			posY += 0.003 * rho * ((Math.cos(phi * Math.PI / 180)) * deltaX -
					  Math.sin(phi * Math.PI / 180) * deltaY);	
		}
		
		ballX = x;
		ballY = y;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftButton = 1;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			rightButton = 1;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftButton = 0;
		
		if (e.getButton() == MouseEvent.BUTTON3)
			rightButton = 0;
	}

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
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
			case KeyEvent.VK_Z: posZ += (float) rho * 0.01f; break;
			case KeyEvent.VK_X: posZ -= (float) rho * 0.01f; break;
			case KeyEvent.VK_A: posZ += (float) rho * 0.1f; break;
			case KeyEvent.VK_S: posZ -= (float) rho * 0.1f; break;
		}
	}

}
