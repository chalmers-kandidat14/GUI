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
		try {
			readText(chainText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * method that handles the list of coordinates and makes them into balls and
	 * chains.
	 * 
	 * @param chainText
	 */
	private void readText(ArrayList<String> chainText) throws Exception {
		BallChain chain = new BallChain();
		Ball newBall;
		
		float x0 = 0, y0 = 0, z0 = 0;
		if (chainText.size() > 0) {
			String ball = "";
			for (int j = 0; j < chainText.size() && ball.equals(""); j++) {
				ball = chainText.get(i);
			}
			if (ball.equals("")) {
				throw new Exception("The list of balls was empty.");
			}
			
			String[] ballSplit = ball.split(" ");
			float xCoord = Float.parseFloat(ballSplit[0]);
			float yCoord = Float.parseFloat(ballSplit[1]);
			float zCoord = Float.parseFloat(ballSplit[2]);
			
			x0 = xCoord;
			y0 = yCoord;
			z0 = zCoord;
			
		}
		

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
			float xCoord = Float.parseFloat(ballSplit[0]) - x0;
			float yCoord = Float.parseFloat(ballSplit[1]) - y0;
			float zCoord = Float.parseFloat(ballSplit[2]) - z0;
			String type = ballSplit[3];

			newBall = new Ball(xCoord, yCoord, zCoord, type);
			chain.addBall(newBall);
			i++;
			// }
		}
		Conformations.addConf(chain);
	}

}
