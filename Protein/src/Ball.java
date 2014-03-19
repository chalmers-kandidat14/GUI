/**
 * A class that represents a blob in a protein
 * @author kandidat
 *
 */
public class Ball {
	
	private float xpos;
	private float ypos;
	private float zpos;
	private String col;
	/**
	 * Constructor with parameters that will
	 * make the size and color of a sphere
	 * @param x
	 * @param y
	 * @param z
	 * @param c
	 */
	public Ball(float x, float y, float z, String c){
		xpos = x;
		ypos = y;
		zpos = z;
		col = c;
		
	}
	/**
	 * Returns x-pos
	 * @return
	 */
	public float getX(){
		return xpos;
	}
	/**
	 * Returns y-pos
	 * @return
	 */
	public float getY(){
		return ypos;
	}
	/**
	 * Returns z-pos
	 * @return
	 */
	public float getZ(){
		return zpos;
	}
	/**
	 * Returns color of sphere
	 * @return
	 */
	public String getc(){
		return col;
	}
	/**
	 * Sets x-pos
	 * @param x
	 */
	public void setX(float x){
		xpos = x;
	}
	/**
	 * Sets y-pos
	 * @param y
	 */
	public void setY(float y){
		ypos = y;
	}
	/**
	 * Sets z-pos
	 * @param z
	 */
	public void setZ(float z){
		zpos =  z;
	}
}
