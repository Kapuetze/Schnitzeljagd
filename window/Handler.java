package window;

import java.awt.Graphics;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;

public class Handler {

	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	private GameObject tempobject;
	
	public void update(){
		for(int i = 0; i < objects.size(); i++){
			tempobject = objects.get(i);
			tempobject.update(objects);
		}
		
		
	}
	
	public void render(Graphics g){
		
		 //render objects in z-position order
		 Collections.sort(objects, new Comparator<GameObject>() {
		      @Override
		      public int compare(final GameObject o1, final GameObject o2) {
		          return Integer.compare((int)o1.getZ(), (int)o2.getZ());
		      }
	
		  });
		
		for(int i = 0; i < objects.size(); i++){
			tempobject = objects.get(i);
			
			//cull objects outside the screen
			if(!(tempobject.getX() > -Game.getMainCamera().getX() + Game.WIDTH + 64|| 
				(tempobject.getX() < -Game.getMainCamera().getX() -64 || 
				(tempobject.getY() > -Game.getMainCamera().getY() + Game.HEIGHT +64 || 
				tempobject.getY() < -Game.getMainCamera().getY() -64)))){
					tempobject.render(g);
			}
			else{
				if(tempobject.getID() == ObjectID.Schnitzel){
					removeObject(tempobject);
				}
				
			}
			
			
		}
	}
	
	/**
	 * adds a GameObject to the handler
	 * @param object	the GameObject
	 */
	public void addObject(GameObject object){
		objects.add(object);
	}
	
	/**
	 * removes a GameObject from the handler
	 * @param object	the GameObject
	 */
	public void removeObject(GameObject object){
		objects.remove(object);
	}
	
	/**
	 * Returns if an Object is being handled by the Handler
	 * @param object The GameObject
	 * @return true if the object is inside the list, false if not
	 */
	public boolean contains(GameObject object){
		return objects.contains(object);
	}
	
}
