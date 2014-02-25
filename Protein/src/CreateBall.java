import java.util.HashMap;


public class CreateBall {
	private static HashMap<Integer, Float> positionsX = new HashMap<Integer, Float>();
	private static HashMap<Integer, Float> positionsY = new HashMap<Integer, Float>();
	private static HashMap<Integer, Float> positionsZ = new HashMap<Integer, Float>();
	private int ID;
	
	public void addBall(int ID, float x, float y, float z) {
		this.ID = ID;
		setX(x);
		setY(y);
		setZ(z);
	}
	
	public void setX(float x) {
		positionsX.put(ID, x);
	}
	public void setY(float y) {
		positionsY.put(ID, y);
	}
	public void setZ(float z) {
		positionsZ.put(ID, z);
	}
	
	public static Float getX(int ID) {
		return positionsX.get(ID);
	}
	public static Float getY(int ID) {
		return positionsY.get(ID);
	}
	public static Float getZ(int ID) {
		return positionsZ.get(ID);
	}
}
