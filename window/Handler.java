package window;

import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempobject;
	
	public void update(){
		for(int i = 0; i < object.size(); i++){
			tempobject = object.get(i);
			tempobject.update(object);
		}
	}
	
	public void render(Graphics g){
		
		for(int i = 0; i < object.size(); i++){
			tempobject = object.get(i);
			
			//cull objects outside the screen
			if(!(tempobject.getX() > -Game.getMainCamera().getX() + Game.WIDTH || 
				(tempobject.getX() < -Game.getMainCamera().getX() -32 || 
				(tempobject.getY() > -Game.getMainCamera().getY() + Game.HEIGHT || 
				tempobject.getY() < -Game.getMainCamera().getY() -32)))){
					tempobject.render(g);
			}
			else{
				
			}
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
}
