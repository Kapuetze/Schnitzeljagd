package framework;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Jonas
 *	Creates a 3D box that is used for collision detection
 */
public class HitBox {
	
	/**
	 * x, y ,z - coordinates of the HitBox
	 */
	private int x, y, z;
	
	/**
	 * width, height, depth starting at origin coordinates
	 */
	private int width, height, depth;

	/**
	 * Creates standard hitbox
	 * @param x	coordinates of the HitBox
	 * @param y	coordinates of the HitBox
	 * @param z	coordinates of the HitBox
	 * @param width	width starting at origin coordinates
	 * @param height	height starting at origin coordinates
	 * @param depth	depth starting at origin coordinates
	 */
	public HitBox(int x, int y, int z, int width, int height, int depth) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	/**
	 * checks if the HitBox intersects with another
	 * @param c HitBox to check with
	 * @return true if Hitboxes intersect, false if not
	 */
	public boolean intersects(HitBox c){
		return (x <= (c.getX() + c.getWidth()) && (x + width) >= c.getX()) &&
		         (y <= (c.getY() + c.getHeight()) && (y + height) >= c.getY()) &&
		         (z <= (c.getZ() + c.getDepth()) && (z + depth) >= c.getZ());
		
	}
	
	/**
	 * updates the hitobx position to match the gameobject
	 * @param o	the GameObject
	 */
	public void update(GameObject o){
		x = (int)o.getX();
		y = (int)o.getY();
		z = (int)o.getZ();
	}
	
	/**
	 * draw the outline of the hitbox
	 * @param g	Graphics object
	 */
	public void draw(Graphics g){
		g.setColor(Color.PINK);
		g.drawRect(x, y, width, height);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
	
	
}
