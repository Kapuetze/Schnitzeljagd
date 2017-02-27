package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import window.Handler;

public class Schnitzel extends GameObject {
	
	private Color color = Color.orange;
	private boolean hit;
	private Handler handler;
	
	public Schnitzel(float x, float y, float z, Handler handler, ObjectID id) {
		super(x, y, z, id);
		this.setGravity(0.1f);
		this.setWidth(50);
		this.setHeight(50);
		this.setVelY(1);
		this.handler = handler;
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
			color = Color.GRAY;
			velX = 0;
			velY = 2;
		}
		
		Collision(object);
	}
	
	private void Collision(LinkedList<GameObject> object){
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Shot){
				if(getBounds().intersects(tempObject.getBounds()) && tempObject.getZ() == -254){
					this.hit = true;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRoundRect((int)x, (int)y, (int)width, (int)height, 40, 40);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, (int)width, (int)height);
	}

}
