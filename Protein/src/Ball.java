
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
	public float getY(){
		return ypos;
	}
	public float getZ(){
		return zpos;
	}
	public String getc(){
		return col;
	}
	public void setX(float x){
		xpos = x;
	}
	public void setY(float y){
		ypos = y;
	}
	public void setZ(float z){
		zpos =  z;
	}
}
