package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import framework.Texture;
import window.Game;
import window.Handler;

public class Shot extends GameObject  {
	
	private Color color;
	Texture texture = Game.getTextureInstance();
	private Handler handler;
	
	public Shot(float x, float y, float z, Handler handler, ObjectID id) {
		super(x, y, z, id);
		this.setWidth(10);
		this.setHeight(10);
		this.setVelZ(-10);
		this.handler = handler;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		x += velX;
		y += velY;
		z += velZ;
		
		if(z < -255){
			velZ = 0;
			z = -255;
		}
		
		int alpha =  (int)z*(-1);
		
		color = new Color(255, 0, 0, alpha);
		if(z == -250){
			color = new Color(172, 92, 100, 255);
		}
		
		Collision(object);
		
		//System.out.println("Z position of projectile: " + z);
	}
	
	private void Collision(LinkedList<GameObject> object){
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Schnitzel){
				if(getBounds().intersects(tempObject.getBounds()) && tempObject.getZ() == -255){
					handler.removeObject(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, (int)width, (int)height);
	}

}
