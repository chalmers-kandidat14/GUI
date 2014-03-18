import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class readChain {
	String line = null;
	ArrayList<String> chainText = new ArrayList<String>();
	private static int i;
	readChain(ArrayList<String> chainText){
		i = 0;
		this.chainText = chainText;
		//this.line = line;
		readChainText(chainText);
	}
	private void readChainText(ArrayList<String> chainText) {
		//try {
			//Scanner sc = new Scanner(new File(chainText));
			BallChain chain = new BallChain();
			Ball newBall;

			while (i < chainText.size() + 1) {
				String ball = chainText.get(i);

				if (ball.equals("")) {
					Conformations.addConf(chain);
					chain = new BallChain();
					//ball = sc.nextLine();
					i++;
					ball = chainText.get(i);
				}

				String[] ballSplit = ball.split(" ");
				float xCoord = Float.parseFloat(ballSplit[0]);
				float yCoord = Float.parseFloat(ballSplit[1]);
				float zCoord = Float.parseFloat(ballSplit[2]);
				String type = ballSplit[3];

				newBall = new Ball(xCoord, yCoord, zCoord, type);
				chain.addBall(newBall);
				i++;
			}
			Conformations.addConf(chain);
			//sc.close();
		//}// catch (IOException e) {
			//System.err.println("could not read file, error: " + e);
		//}
	}

}
