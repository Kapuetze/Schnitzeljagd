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

public class Schnitzel extends GameObject {
	
	private boolean hit;
	Texture texture = Game.getTextureInstance();
	private Handler handler;
	
	//Animations
	private Animation schnitzelrotation;
	
	public Schnitzel(float x, float y, float z, Handler handler, ObjectID id) {
		super(x, y, z, id);
		this.setGravity(0);
		this.setWidth(50);
		this.setHeight(50);
		this.setVelY(0);
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
