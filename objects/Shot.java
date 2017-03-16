package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;
import framework.HitBox;
import framework.ObjectID;
import framework.Texture;
import window.Animation;
import window.Game;
import window.Handler;

public class Shot extends GameObject  {
	
	Texture texture = Game.getTextureInstance();
	private Handler handler;
	private boolean flying = true;
		
	private Animation forkfly;
	
	public Shot(float x, float y, float z, Handler handler, ObjectID id) {
		super(x, y, z, id);
		this.setWidth(10);
		this.setHeight(10);
		this.setDepth(30);
		this.setVelZ(-10);
		this.handler = handler;
		
		this.setHitbox(new HitBox((int)x, (int)y, (int)z, (int)width, (int)height, (int)depth));
		
		forkfly = new Animation(3, texture.fork);
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		hitbox.update(this);
		
		x += velX;
		y += velY;
		z += velZ;
		
		if(z < -300){
			velZ = 0;
			z = -300;
			flying = false;
		}
		
		if(velZ == 0){
			flying = false;
		}
		
		
		Collision(object);
		
		if(flying){
			forkfly.runAnimation();
		}
		else{
			//handler.removeObject(this);
		}
		
		
		//System.out.println("Z position of projectile: " + z);
	}
	
	private void Collision(LinkedList<GameObject> object){
		for (int i = 0; i < handler.objects.size(); i++){
			GameObject tempobject = handler.objects.get(i);
			
			if(tempobject.getID() == ObjectID.Schnitzel){
				if(getHitbox().intersects(tempobject.getHitbox())){
					
					//stop fork from flying and set y greater than Schnitzel.y to render it in front of Schnitzel
					velZ = 0;
					z = -299;
					
					//stop both the Shot and the Schnitzel from being rendered and used for collision detection
					//handler.removeObject(tempobject); 
					//handler.removeObject(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		forkfly.drawAnimation(g, (int)x - 64, (int)y -32);
		g.setColor(Color.red);
	}

}
