/**
 * 
 */
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;

/**
 * @author Jonas
 *
 */
public class Particle extends GameObject {
	
	
	/**
	 * lifetime of the Particle
	 */
	private float life;
	
	/**
	 * size of the Particle
	 */
	private float size;
	
	/**
	 * Color of the Particle
	 */
	private Color color;

 

	public Particle(float x, float y, float velX, float velY, float life, float size, Color color, ObjectID id) {
		super(x, y, 0, id);
		
		this.velX = velX;
		this.velY = velY;
		this.life = life;
		this.size = size;
		this.color = color;
	}

	
	@Override
	public void update(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}