import java.util.ArrayList;
import java.util.List;


public class BallChain {
	
	static List <Ball> bollList = new ArrayList<Ball>();

	@SuppressWarnings("static-access")
	public BallChain(ArrayList<Ball> bollList){
		this.bollList = bollList;
	}
	
	public BallChain() {
	}
	
	public static void addBall(Ball ball) {
		bollList.add(ball);
	}
}
