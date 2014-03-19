import java.util.ArrayList;
import java.util.List;

/**
 * A class that keep tracks on the chains
 * @author kandidat
 *
 */
public class Conformations {
	static List <BallChain> conformation;
	/**
	 * Constructor that initiates a ArrayList
	 */
	public Conformations() {
		conformation = new ArrayList<BallChain>();
	}
	/*
	 * adds a chain as a confirmation
	 */
	public static void addConf(BallChain chain) {
		conformation.add(chain);
	}
	/**
	 * Method that returns the BallChain at
	 * index i (parameter)
	 * @param i
	 * @return BallChain
	 */
	public static BallChain retConf(int i) {
		return conformation.get(i);
	}
	/**
	 * Returns the size of the confirmation
	 * @return
	 */
	public static int confSize() {
		return conformation.size();
	}
}
