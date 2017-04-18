package window;

import java.awt.Graphics;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectID;

public class Handler {

	/**
	 * holds all the GameObjects in the Game
	 */
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	/**
	 * temporary opbject
	 */
	private GameObject tempobject;
	
	/**
	 * update all gameobjects
	 */
	public synchronized void update(){

		//render objects in z-position order
		Collections.sort(objects, new Comparator<GameObject>() {
			@Override
			public int compare(final GameObject o1, final GameObject o2) {
				return Integer.compare((int)o1.getZ(), (int)o2.getZ());
			}
		});

		for(int i = 0; i < objects.size(); i++){
			tempobject = objects.get(i);
			try {
				tempobject.update(objects);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}
	
	/**
	 * render all gameobjects
	 * @param g the Graphics object
	 */
	public synchronized void render(Graphics g){	
		
		 //loop through all objects and render them, if they are inside of the screen
		for(int i = 0; i < objects.size(); i++){
			tempobject = objects.get(i);
			
			//cull objects outside the screen
			if(!(tempobject.getX() > -Game.getMainCamera().getX() + Game.WIDTH + 64|| 
				(tempobject.getX() < -Game.getMainCamera().getX() -64 || 
				(tempobject.getY() > -Game.getMainCamera().getY() + Game.HEIGHT +64 || 
				tempobject.getY() < -Game.getMainCamera().getY() -64)))){
					try {
						tempobject.render(g);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			//remove GameObject, if it is not alive
			if(!tempobject.isAlive()){
				removeObject(tempobject);
			}
		}
	}
	
	/**
	 * adds a GameObject to the handler
	 * @param object	the GameObject
	 */
	public synchronized void addObject(GameObject object){
		objects.add(object);
	}
	
	/**
	 * removes a GameObject from the handler
	 * @param object	the GameObject
	 */
	public synchronized void removeObject(GameObject object){
		try {
			objects.remove(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns if an Object is being handled by the Handler
	 * @param object The GameObject
	 * @return true if the object is inside the list, false if not
	 */
	public synchronized boolean contains(GameObject object){
		return objects.contains(object);
	}
	
}
