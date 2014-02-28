import java.util.HashMap;


public class CreateBall {
	private static HashMap<Integer, Float> positionsX = new HashMap<Integer, Float>();
	private static HashMap<Integer, Float> positionsY = new HashMap<Integer, Float>();
	private static HashMap<Integer, Float> positionsZ = new HashMap<Integer, Float>();
	private static HashMap<Integer, String> colorBall = new HashMap<Integer,String>();
	
	public static void addBall(int ID, float x, float y, float z, String c) {
		setX(ID, x);
		setY(ID, y);
		setZ(ID, z);
		setColorBall(ID, c);
	}
	
	public static void setX(int ID, float x) {
		positionsX.put(ID, x);
	}
	public static void setY(int ID, float y) {
		positionsY.put(ID, y);
	}
	public static void setZ(int ID, float z) {
		positionsZ.put(ID, z);
	}
	public static void setColorBall(int ID, String c) {
		colorBall.put(ID, c);
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
	public static String getC(int ID) {
		return colorBall.get(ID);
	}
}
