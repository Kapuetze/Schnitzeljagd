/**
 * 
 */
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import window.Game;
import window.Handler;

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

 

	/**
	 * @param x
	 * @param y
	 * @param velX
	 * @param velY
	 * @param life
	 * @param size
	 * @param color
	 * @param id
	 */
	public Particle(float x, float y, float velX, float velY, float gravity, float life, float size, Color color, ObjectID id) {
		super(x, y, 0, id);
		
		//gameobject variables
		this.velX = velX;
		this.velY = velY;
		
		//class specific variables
		this.life = life;
		this.size = size;
		this.color = color;
		
		//setters
		setGravity(gravity);
	}

	
	@Override
	public void update(LinkedList<GameObject> object) {
		
		//handle velocity
		x += velX;
		y += velY;
		
		//add gravity
		if(falling){
			velY += gravity;
			
			//max velocity
			if(velY > 5){
				velY = 5;
			}
		}
		
		//decrease lifetime and destroy, if dead
		life--;
		if(life == 0){
			this.destroy();
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setColor(color);
		g2d.fillRect((int)x-((int)size/2), (int)y-((int)size/2), (int)size, (int)size);
		
		g2d.dispose();
		
	}


	/**
	 * @return the life
	 */
	public float getLife() {
		return life;
	}


	/**
	 * @param life the life to set
	 */
	public void setLife(float life) {
		this.life = life;
	}

}