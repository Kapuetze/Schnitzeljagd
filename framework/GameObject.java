package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y, z;
	protected float velX, velY, velZ;
	protected float width, height;
	protected float gravity;
	protected ObjectID id;
	protected boolean falling = true;
	
	public GameObject(float x, float y, float z, ObjectID id){
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
	}
	

	public abstract void update(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getX(){
		return x;
	}
	

	public float getY(){
		return y;
	}
	
	
	public float getZ(){
		return z;
	}
	
	
	public void setX(float x){
		this.x = x;
	}
	
	
	public void setY(float y){
		this.y = y;
	}
	
	
	public void setZ(float z){
		this.z = z;
	}
	
	
	public float getVelX(){
		return velX;
	}
	
	
	public float getVelY(){
		return velY;
	}
	
	
	public float getVelZ(){
		return velZ;
	}
	
	
	public void setVelX(float x){
		this.velX = x;
	}
	
	
	public void setVelY(float y){
		this.velY = y;
	}
	
	
	public void setVelZ(float z){
		this.velZ = z;
	}
	
	
	public boolean isFalling() {
		return falling;
	}
	

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	
	public float getGravity() {
		return gravity;
	}
	

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	
	
	public float getWidth() {
		return width;
	}


	public void setWidth(float width) {
		this.width = width;
	}


	public float getHeight() {
		return height;
	}


	public void setHeight(float height) {
		this.height = height;
	}


	public ObjectID getID(){
		return id;
	}
}
