import java.util.ArrayList;
import java.util.List;

/**
 * A chain of balls
 * 
 * @author Kandidat
 * 
 */
public class BallChain {

	List<Ball> bollList = new ArrayList<Ball>();

	/**
	 * Constructor has a parameter that is a list
	 * of Balls. 
	 * @param bollList
	 */
	public BallChain(ArrayList<Ball> bollList) {
		this.bollList = bollList;
	}
	/**
	 * Empty constructor
	 */
	public BallChain() {
	}
	/**
	 * A ball as a parameter that adds to
	 * the BallChain
	 * @param ball
	 */
	public void addBall(Ball ball) {
		bollList.add(ball);
	}
	public Ball getLastBall(){
		return bollList.get(bollList.size() -1);
	}
}
