package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;

public class Block extends GameObject{

	public Block(float x, float y, float z, ObjectID id) {
		super(x, y, z, id);
	}

	public void update(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 32, 32);
	}
	

}
