package objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import framework.*;
import window.Animation;
import window.Game;
import window.Handler;

/**
* The Schnitzel class defines an object which can be hit by the shot object.
*
* @author  Jonas Berger
* @version 1.0
* @since   28.02.2017
* @see Shot
*/

public class Schnitzel extends GameObject {
	
	/**
	 * indicates if Schnitzel was hit by a Shot
	 */
	private boolean hit;
	
	/**
	 * contains the Texture
	 */
	Texture texture = Game.getTextureInstance();
	
	/**
	 * Handler for Collision detection, updating and rendering
	 */
	private Handler handler;
	
	/**
	 * used for fading out the object, if it was hit
	 */
	private float alpha = 1f;

	
	
	//Animations
	private Animation schnitzelrotation;
	
	/**
	 * Creates a Schnitzel target object, width and height is set to 50.
	 * Defines an animation for the Schnitzel from texture.schnitzel[0]-[3]
	 *
	 * @param  x	The x coordinate
	 * @param  y	The y coordinate
	 * @param  z	The z coordinate
	 * @param  handler	The handler instance used to handle collision detection
	 * @param  id	ObjectID of the created object
	 * @see	ObjectID
	 * @see Handler
	 */
	public Schnitzel(float x, float y, float z, Handler handler, ObjectID id) {
		super(x, y, z, id);
		this.setGravity(0);
		this.setWidth(40);
		this.setHeight(40);
		this.setDepth(1);
		this.handler = handler;
		
		this.setHitbox(new HitBox((int)x, (int)y, (int)z, (int)30, (int)30, (int)depth));
		
		schnitzelrotation = new Animation(3, texture.schnitzel);
	}
	
	/**
	 * Creates a Schnitzel target object, width and height is set to 50.
	 * Defines an animation for the Schnitzel from texture.schnitzel[0]-[3]
	 *
	 * @param  x	The x coordinate
	 * @param  y	The y coordinate
	 * @param  z	The z coordinate
	 * @param  velX	The velocity in x direction
	 * @param  velY	The velocity in y direction
	 * @param  velZ	The velocity in z direction
	 * @param  handler	The handler instance used to handle collision detection
	 * @param  id	ObjectID of the created object
	 * @see	ObjectID
	 * @see Handler
	 */
	public Schnitzel(float x, float y, float z, float velX, float velY, float velZ, Handler handler, ObjectID id) {
		super(x, y, z, id);
		this.setGravity(0);
		this.setWidth(40);
		this.setHeight(40);
		this.setDepth(1);

		this.setVelX(velX);
		this.setVelY(velY);
		this.setVelZ(velZ);
		this.handler = handler;
		
		this.setHitbox(new HitBox((int)x, (int)y, (int)z, (int)30, (int)30, (int)depth));
		
		schnitzelrotation = new Animation(3, texture.schnitzel);
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		hitbox.update(this);
		
		//add velocity 
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
		
		//calculate Collision
		Collision(object);
		
		if(!hit){
			//run rotating animation as long as not hit
			schnitzelrotation.runAnimation();
		}
		else{
			//stop if hit
			velX = 0;
			velY = 0;
			z = -300;
		}
		
		/*
		
		float maxX = x + width;
		float maxY = y + height;
		float maxZ = z + depth;

		
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("z: " + z);
		System.out.println("width: " + width);
		System.out.println("height: " + height);
		System.out.println("depth: " + depth);
		System.out.println("maxX: " + maxX);
		System.out.println("maxY: " + maxY);
		System.out.println("maxZ: " + maxZ);
		*/
	}
	
	/**
	 * Handles collision for Schnitzel objects.
	 */
	private void Collision(LinkedList<GameObject> object){
		for (int i = 0; i < handler.objects.size(); i++){
			GameObject tempobject = handler.objects.get(i);
			
			if(tempobject.getID() == ObjectID.Shot){
				if(getHitbox().intersects(tempobject.getHitbox())){
					//set to true if the object was a shot
					this.hit = true;
					
										
					//stop both the Shot and the Schnitzel from being rendered and used for collision detection
					//handler.removeObject(tempobject); 
					//handler.removeObject(this);
					Game.getTargetHandlerInstance().removeTarget(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		//create graphics2D object
		Graphics2D g2d = (Graphics2D) g;
		//set alpha
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		//Draw the Schnitzel rotation
		schnitzelrotation.drawAnimation(g2d, (int)x - 8, (int)y - 8, (int)width + 16, (int)height + 16);
	}

}
