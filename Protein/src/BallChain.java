import java.util.ArrayList;
import java.util.List;


public class BallChain {
	
	List <Ball> bollList = new ArrayList<Ball>();

	public BallChain(ArrayList<Ball> bollList){
		this.bollList = bollList;
	}
	
	public BallChain() {
	}
	
	public void addBall(Ball ball) {
		bollList.add(ball);
	}
}
