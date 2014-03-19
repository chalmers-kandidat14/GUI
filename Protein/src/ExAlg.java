import java.io.*;
import java.util.ArrayList;

/**
 * This class execute the algorithm
 * 
 * @author gao
 * 
 */
public class ExAlg {
	String line = "";
	ArrayList<String> algChain = new ArrayList<String>();

	/**
	 * Empty constructor,
	 */
	ExAlg() {

	}

	/**
	 * Method that returns the result of the algorithm as an arrayList
	 * 
	 * @return
	 */
	public ArrayList<String> getAlgRes() {
		return algChain;
	}

	/**
	 * Method executing the haskell code managing the algorithm
	 */
	public void runAlg() {
		try {
			Process p = Runtime.getRuntime().exec(
					"runhaskell  Main.hs HHHPHPPHHPH 100");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			while (!(line = in.readLine()).equals("")) {
				algChain.add(line);

				// check --remove later
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
