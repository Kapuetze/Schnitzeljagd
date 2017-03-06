package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Comparator;
import java.util.LinkedList;

public abstract class GameObject{

	protected float x, y, z;
	protected float velX, velY, velZ;
	protected float width, height, depth;
	protected float gravity;
	protected ObjectID id;
	protected boolean falling = true;
	protected HitBox hitbox;
	
	public GameObject(float x, float y, float z, ObjectID id){
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
		this.hitbox = new HitBox(0,0,0,0,0,0);
	}
	

	public abstract void update(LinkedList<GameObject> object);
	public abstract void render(Graphics g);

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}


	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}


	/**
	 * @return the z
	 */
	public float getZ() {
		return z;
	}


	/**
	 * @param z the z to set
	 */
	public void setZ(float z) {
		this.z = z;
	}


	/**
	 * @return the velX
	 */
	public float getVelX() {
		return velX;
	}


	/**
	 * @param velX the velX to set
	 */
	public void setVelX(float velX) {
		this.velX = velX;
	}


	/**
	 * @return the velY
	 */
	public float getVelY() {
		return velY;
	}


	/**
	 * @param velY the velY to set
	 */
	public void setVelY(float velY) {
		this.velY = velY;
	}


	/**
	 * @return the velZ
	 */
	public float getVelZ() {
		return velZ;
	}


	/**
	 * @param velZ the velZ to set
	 */
	public void setVelZ(float velZ) {
		this.velZ = velZ;
	}


	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}


	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}


	/**
	 * @return the depth
	 */
	public float getDepth() {
		return depth;
	}


	/**
	 * @param depth the depth to set
	 */
	public void setDepth(float depth) {
		this.depth = depth;
	}


	/**
	 * @return the gravity
	 */
	public float getGravity() {
		return gravity;
	}


	/**
	 * @param gravity the gravity to set
	 */
	public void setGravity(float gravity) {
		this.gravity = gravity;
	}


	/**
	 * @return the id
	 */
	public ObjectID getID() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setID(ObjectID id) {
		this.id = id;
	}


	/**
	 * @return the hitbox
	 */
	public HitBox getHitbox() {
		return hitbox;
	}


	/**
	 * @param hitbox the hitbox to set
	 */
	public void setHitbox(HitBox hitbox) {
		this.hitbox = hitbox;
	}
	

	
	

}
