package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import framework.Texture;
import window.Animation;
import window.Game;
import window.Handler;

/**
* The Schnitzel class defines an object which can be shot at with the shot object.
*
* @author  Jonas Berger
* @version 1.0
* @since   28.02.2017
* @see Shot
*/

public class Schnitzel extends GameObject {
	
	private boolean hit;
	Texture texture = Game.getTextureInstance();
	private Handler handler;
	
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
		this.setWidth(50);
		this.setHeight(50);
		this.handler = handler;
		
		schnitzelrotation = new Animation(6, texture.schnitzel[0], texture.schnitzel[1], texture.schnitzel[2], texture.schnitzel[3]);
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
		this.setWidth(50);
		this.setHeight(50);

		this.setVelX(velX);
		this.setVelY(velY);
		this.setVelZ(velZ);
		this.handler = handler;
		
		schnitzelrotation = new Animation(6, texture.schnitzel[0], texture.schnitzel[1], texture.schnitzel[2], texture.schnitzel[3]);
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(falling){
			velY += gravity;
			
			if(velY > 5){
				velY = 5;
			}
		}
		
		if(hit){
			velX = 0;
			velY = 2;
		}
		
		Collision(object);
		
		schnitzelrotation.runAnimation();
	}
	
	/**
	 * Handles collision for Schnitzel objects.
	 */
	private void Collision(LinkedList<GameObject> object){
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Shot){
				if(getBounds().intersects(tempObject.getBounds()) && tempObject.getZ() == -255){
					this.hit = true;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		schnitzelrotation.drawAnimation(g, (int)x, (int)y);
		//g.drawImage(texture.schnitzel[0], (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, (int)width, (int)height);
	}

}
