package framework;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import window.Game;
import window.Handler;

/**
 * The GameObject class provides a superclass from which other GameObjects can inherit
 * to share a common design pattern.
 * @author Jonas
 *
 */
public abstract class GameObject{

	/**
	 * GameObject position
	 */
	protected float x, y, z;
	
	/**
	 * GameObject velocity
	 */
	protected float velX, velY, velZ;
	
	/**
	 * GameObject dimensions
	 */
	protected float width, height, depth;
	
	/**
	 * GameObject gravity
	 */
	protected float gravity;
	
	/**
	 * ID of the GameObject, used to identify the type
	 */
	protected ObjectID id;
	
	/**
	 * Is the GameObject falling
	 */
	protected boolean falling = true;
	
	/**
	 * Is the GameObject hit
	 */
	protected boolean hit = false;
	
	/**
	 * delay to destroy
	 */
	protected int lifetime;
	
	/**
	 * Is the GameObject alive
	 */
	protected boolean alive = true;
	
	/**
	 * Hitbox of the GameObject (used for collision detection)
	 */
	protected HitBox hitbox;
	
	/**
	 * The Handler object
	 */
	protected Handler handler = Game.getHandlerInstance();
	
	/**
	 * list of all the listeners added to this Object
	 */
	protected ArrayList<TargetListener> listeners = new ArrayList<TargetListener>();
	
	/**
	 * Scheduler for destryoing
	 */
	protected ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param z The z coordinate
	 * @param id The ID coordinate
	 */
	public GameObject(float x, float y, float z, ObjectID id){
		//populate position coordinates
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
		
		//add object to handler
		handler.addObject(this);
		
		//initiate Hitbox at (0,0,0) with size 0
		this.hitbox = new HitBox(0,0,0,0,0,0);
		
		//add TargetListener
		addListener(Game.getDashboardInstance());
	}
	

	/**
	 * Update the properties of the GameObject
	 * @param object List of all GameObjects
	 */
	public abstract void update(LinkedList<GameObject> object);
	
	/**
	 * Renders the GameObject to the screen
	 * @param g Graphics object
	 */
	public abstract void render(Graphics g);
	
	/**
	 * mark the GameObject as destroyed
	 */
	public void destroy(){
		alive = false;
	}
	

    /**
     * Adds the TargetListener to the GameObject
     * @param listener The TargetListener to be added
     */
    public void addListener(TargetListener listener) {
        listeners.add(listener);
    }

	/**
	 * Turns the object into a readable String format
	 */
	@Override
	public String toString() {
		return "GameObject [id=" + id + ", x=" + x + ", y=" + y + ", z=" + z + ", velX=" + velX + ", velY=" + velY
				+ ", velZ=" + velZ + ", width=" + width + ", height=" + height + ", depth=" + depth + ", gravity="
				+ gravity + ", falling=" + falling + ", hitbox=" + hitbox + "]";
	}

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


	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}


	/**
	 * @return the lifetime
	 */
	public int getLifetime() {
		return lifetime;
	}


	/**
	 * @param lifetime the lifetime to set
	 */
	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	
	

}
