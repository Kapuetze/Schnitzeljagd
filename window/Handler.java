package window;

import java.awt.Graphics;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;
import objects.*;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void update(){
		for(int i = 0; i < object.size(); i++){
			tempObject = object.get(i);
			tempObject.update(object);
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public void createTargets(){
		
		Targets targets = new Targets();
		float tx = 300;
		float ty = 300;
		
		if(targets.size() < 2){
			targets.add(new Schnitzel(tx, ty, -254, this, ObjectID.Schnitzel));
			addObject(targets.getLast());
			System.out.println("Target created.");
		}
		
		targets.getLast().setVelX(5);
	}
}
