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
	public String arg = "runhaskell  Main.hs HHHPHPPHHPH 100";
	ArrayList<String> algChain = new ArrayList<String>();

	/**
	 * Empty constructor,
	 */
	ExAlg() {

	}
	ExAlg(String hp, int iter){
		this.arg = "runhaskell Main.hs " + hp + " " + iter;
		System.out.println(hp + "   " + iter);
		System.out.println(arg);
		runAlg();
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
					arg);

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
