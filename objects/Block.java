package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import framework.Texture;
import window.Game;

public class Block extends GameObject{

	Texture texture = Game.getTextureInstance();
	private int blocktype;
	
	public Block(float x, float y, float z, int blocktype, ObjectID id) {
		super(x, y, z, id);
		this.blocktype = blocktype;
	}

	public void update(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		if(blocktype == 0){
			g.drawImage(texture.block[0], (int)x, (int)y, null);
		}
		if(blocktype == 1){
			g.drawImage(texture.block[1], (int)x, (int)y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 32, 32);
	}
	

}
