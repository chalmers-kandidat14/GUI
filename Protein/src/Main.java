import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Main {
	private static String TITLE = "Protein";
	static JFrame guiFrame;
	static Container contentPane;
	
	private static int WIDTH = 1000;
	private static int HEIGHT = 640;
	
	public static void main(String[] args) {
		// Run the GUI codes in the event-dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				guiFrame = new JFrame();
				guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				guiFrame.setTitle("Protein folding");
				guiFrame.setSize(WIDTH, HEIGHT);
				guiFrame.setLocationRelativeTo(null);
				
				contentPane = guiFrame.getContentPane();
				GLCanvas canvas = BallFrame.createBallFrame();
				// Create the top-level container

				contentPane.add(canvas);
				
				JPanel optFrame = createOptionPanel.createOpt();
				
				guiFrame.add(optFrame, BorderLayout.WEST);
				
				guiFrame.setTitle(TITLE);
				guiFrame.pack();
				guiFrame.setVisible(true);
			}
		});
	}
}
