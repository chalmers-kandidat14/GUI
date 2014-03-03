import java.util.ArrayList;
import java.util.List;


public class Conformations {
	static List <BallChain> conformation = new ArrayList<BallChain>();
	
	public Conformations() {
	}
	
	public static void addConf(BallChain chain) {
		conformation.add(chain);
	}
	
	public static BallChain retConf(int i) {
		return conformation.get(i);
	}
}
