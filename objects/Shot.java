package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import framework.GameObject;
import framework.HitBox;
import framework.ObjectID;
import framework.TargetListener;
import framework.Texture;
import window.Animation;
import window.Game;
import window.Handler;

public class Shot extends GameObject  {
	
	/**
	 * list of all the listeners added to this Object
	 */
	private ArrayList<TargetListener> listeners = new ArrayList<TargetListener>();
	
	Texture texture = Game.getTextureInstance();
	private boolean flying = true;
	
	private boolean hit;
		
	private Animation forkfly;
	
	public Shot(float x, float y, float z, ObjectID id) {
		super(x, y, z, id);
		this.setWidth(10);
		this.setHeight(10);
		this.setDepth(30);
		this.setVelZ(-10);
		
		this.setHitbox(new HitBox((int)x, (int)y, (int)z, (int)width, (int)height, (int)depth));
		this.setLifetime(5);
		
		addListener(Game.getDashboardInstance());
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
		else if (!hit){
			//destroy shot after delay
			scheduler.schedule(this::destroy, lifetime, TimeUnit.SECONDS); 
		}
		
		
		//System.out.println("Z position of projectile: " + z);
	}
	
	private void Collision(LinkedList<GameObject> object){
		
		//test if the shot has hit something yet
		if(!hit){
			for (int i = 0; i < handler.objects.size(); i++){
				GameObject tempobject = handler.objects.get(i);
				
				if(tempobject.getID() == ObjectID.Schnitzel || tempobject.getID() == ObjectID.Ketchup){
					if(getHitbox().intersects(tempobject.getHitbox())){
						
						//stop fork from flying and set y greater than Schnitzel.y to render it in front of Schnitzel
						velZ = 0;
						z = -299;
						
						hit = true;
						
						//notify listeners
						for (TargetListener listener : listeners)
				            listener.targetHit(tempobject);
												
						//call destroy() after delay
						scheduler.schedule(this::destroy, tempobject.getLifetime(), TimeUnit.SECONDS); 
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		forkfly.drawAnimation(g, (int)x - 64, (int)y -32);
	}
	
	 /**
     * add a TargetListener to the Object
     * @param toAdd
     */
    public void addListener(TargetListener listener) {
        listeners.add(listener);
    }


}
