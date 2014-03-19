import java.util.ArrayList;

/**
 * Class that reads output from the list of coordinates created by the algorithm
 * 
 * @author Kandidatgrupp
 * 
 */

public class readChain {
	ArrayList<String> chainText = new ArrayList<String>();
	private static int i;

	/**
	 * The constructor takes an ArrayList of strings, then runs the method
	 * readChaintext.
	 * 
	 * @param chainText
	 */
	readChain(ArrayList<String> chainText) {
		i = 0;
		this.chainText = chainText;
		readText(chainText);
	}

	/**
	 * method that handles the list of coordinates and makes them into balls and
	 * chains.
	 * 
	 * @param chainText
	 */
	private void readText(ArrayList<String> chainText) {
		BallChain chain = new BallChain();
		Ball newBall;

		while (i < (chainText.size())) {
			String ball = chainText.get(i);

			if (ball.equals("")) {
				Conformations.addConf(chain);
				chain = new BallChain();
				ball = chainText.get(i + 1);
				// i++;
			}
			// if(i >= (chainText.size() +1)){
			String[] ballSplit = ball.split(" ");
			float xCoord = Float.parseFloat(ballSplit[0]);
			float yCoord = Float.parseFloat(ballSplit[1]);
			float zCoord = Float.parseFloat(ballSplit[2]);
			String type = ballSplit[3];

			newBall = new Ball(xCoord, yCoord, zCoord, type);
			chain.addBall(newBall);
			i++;
			// }
		}
		Conformations.addConf(chain);
	}

}
