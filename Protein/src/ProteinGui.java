import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ProteinGui {
	private static String TITLE = "Protein";
	static JFrame guiFrame;
	static Container contentPane;

	private static int WIDTH = 1240;
	private static int HEIGHT = 640;

	// List of ball chains containing balls
	// private static List<listOfBalls> chainList = new
	// ArrayList<listOfBalls>();
	private static String inputFile = "src/Exempel2.txt";
	private static String inputFile3 = "src/Exempel3.txt";

	public static void main(String[] args) {
		ProteinGui pg = new ProteinGui();
		pg.drawGui();
		pg.readChainText(inputFile3);
	}

	private void drawGui() {
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
	}

	/**
	 * Läser in kedjor, ska sedan skakpa en lista för listor av kedjor där
	 * alla kedjor läggs in. För att sedan kunna swappa mellan dessa i guit.
	 * en inre å yttre loop vore fördelaktigt.
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
