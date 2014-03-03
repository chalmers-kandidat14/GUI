import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
	private static String TITLE = "Protein";
	static JFrame guiFrame;
	static Container contentPane;

	private static int WIDTH = 1240;
	private static int HEIGHT = 640;

	public static void main(String[] args) throws FileNotFoundException {

		// Run the GUI codes in the event-dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				guiFrame = new JFrame();
				guiFrame.setLayout(new BorderLayout(10, 10));
				guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				guiFrame.setTitle("Protein folding");
				guiFrame.setSize(WIDTH, HEIGHT);
				guiFrame.setLocationRelativeTo(null);

				contentPane = guiFrame.getContentPane();
				GLCanvas canvas = BallFrame.createBallFrame();
				// Create the top-level container

				contentPane.add(canvas, BorderLayout.EAST);

				JPanel optFrame = createOptionPanel.createOpt();

				guiFrame.add(optFrame, BorderLayout.CENTER);
				guiFrame.add(new JLabel(""), BorderLayout.PAGE_END);
				guiFrame.add(new JLabel(""), BorderLayout.PAGE_START);
				guiFrame.add(new JLabel(""), BorderLayout.WEST);

				guiFrame.setTitle(TITLE);
				guiFrame.pack();
				guiFrame.setVisible(true);
			}
		});

		Scanner sc = new Scanner(new File("src/Exempel.txt"));
		while (sc.hasNextLine()) {
			String ball = sc.nextLine();
			String[] ballSplit = ball.split(" ");
			float xCoord = Float.parseFloat(ballSplit[0]);
			float yCoord = Float.parseFloat(ballSplit[1]);
			float zCoord = Float.parseFloat(ballSplit[2]);
			String type = ballSplit[3];
			
			CreateBall.addBall(BallFrame.retI(), xCoord, yCoord, zCoord, type);
			BallFrame.incI();
		}
		sc.close();
	}
}
