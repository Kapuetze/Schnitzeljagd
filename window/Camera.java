package window;

/**
 * 2D Camera class (could be used to move the view, not implemented)
 * @author Jonas
 *
 */
public class Camera {

	/**
	 * position coordinates
	 */
	private float x, y;
	
	/**
	 * Creates an instance of Camera at the given position
	 * @param x	X coordinate of the camera
	 * @param y	Y coordinate of the camera
	 */
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * update camera position
	 */
	public void update(){
		
	}

	/**
	 * Returns the cameras position on the X-Axis
	 * @return The x coordinate
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets the cameras position on X-Axis
	 * @param x The x coordinate
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Returns the cameras position on the Y-Axis
	 * @return The y coordinate
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets the cameras position on Y-Axis
	 * @param y The y coordinate
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	
}
