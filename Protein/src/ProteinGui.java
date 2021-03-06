import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The class containing the main function, it starts up the program.
 * 
 * @author kandidat group 35
 * 
 */
public class ProteinGui {
	private static String TITLE = "Protein";
	static JFrame guiFrame;
	static Container contentPane;

	private static int WIDTH = 1280;
	private static int HEIGHT = 640;

	private static String inputFile = "src/Exempel.txt";
	private static String inputFile3 = "src/Exempel3.txt";

	public static void main(String[] args) {
		ProteinGui pg = new ProteinGui();
		pg.drawGui();
		// pg.readChainText(inputFile3);
		// pg.readChainText(inputFile);
		//new BallFrame();
		new Conformations();
	}

	/**
	 * Method that draws the frame of the program.
	 */
	private void drawGui() {
		// Run the GUI codes in the event-dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				guiFrame = new JFrame();
				guiFrame.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;
				
				guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				guiFrame.setTitle("Protein folding");
				guiFrame.setSize(WIDTH, HEIGHT);
				guiFrame.setLocationRelativeTo(null);

				contentPane = guiFrame.getContentPane();
				GLCanvas canvas = BallFrame.createBallFrame();
				// Create the top-level container

				c.weightx = 1;
				c.weighty = 1;
				contentPane.add(canvas, c);

				JPanel optFrame = createOptionPanel.createOpt();

				c.weightx = 0;
				guiFrame.add(optFrame);
				guiFrame.add(new JLabel(""), c);
				guiFrame.add(new JLabel(""), c);
				guiFrame.add(new JLabel(""), c);

				guiFrame.setTitle(TITLE);
				guiFrame.setResizable(true);
				guiFrame.pack();
				guiFrame.setVisible(true);
			}
		});
	}

	/**
	 * Used to read txt files (the parameter chainText) containing coordinates.
	 * 
	 * @param chainText
	 */
	private void readChainText(String chainText) {
		try {
			Scanner sc = new Scanner(new File(chainText));
			BallChain chain = new BallChain();
			Ball newBall;

			while (sc.hasNextLine()) {
				String ball = sc.nextLine();

				if (ball.equals("")) {
					Conformations.addConf(chain);
					chain = new BallChain();
					ball = sc.nextLine();
				}

				String[] ballSplit = ball.split(" ");
				float xCoord = Float.parseFloat(ballSplit[0]);
				float yCoord = Float.parseFloat(ballSplit[1]);
				float zCoord = Float.parseFloat(ballSplit[2]);
				String type = ballSplit[3];

				newBall = new Ball(xCoord, yCoord, zCoord, type);
				chain.addBall(newBall);
			}
			Conformations.addConf(chain);
			sc.close();
		} catch (IOException e) {
			System.err.println("could not read file, error: " + e);
		}
	}

}
