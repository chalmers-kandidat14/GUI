
public class Ball {
	
	private float xpos;
	private float ypos;
	private float zpos;
	private String col;
	public Ball(float x, float y, float z, String c){
		xpos = x;
		ypos = y;
		zpos = z;
		col = c;
		
	}
	public float getX(){
		return xpos;
	}
	public float gety(){
		return ypos;
	}
	public float getz(){
		return zpos;
	}
	public String getc(){
		return col;
	}
}
