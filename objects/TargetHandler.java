package objects;

import java.util.LinkedList;
import java.util.Random;

import framework.GameObject;
import framework.ObjectID;
import window.Game;
import window.Handler;

/**
 * @author Jonas
 * Handles target behaviour
 */
public class TargetHandler {
	
	/**
	 * 
	 */
	private LinkedList<GameObject> targets;
	
	/**
	 * 
	 */
	private int maxtargets;
	
	/**
	 * 
	 */
	private Handler handler;
    
	
	public TargetHandler(Handler handler){
		targets = new LinkedList<GameObject>();
		this.maxtargets = 10;
		this.handler = handler;
	}
	
	/**
	 * check to delete targets every update
	 */
	public void update(){
		deleteOldTargets();
	}
	
	/**
	 * spawn random targets at random positions outside the window
	 */
	public void spawnTarget(){
		if(targets.size() < maxtargets){
			Random random = new Random();
			int randX = random.nextInt(Game.WIDTH + 64) - 32;
			int randY = random.nextInt(Game.WIDTH + 64) - 32;
			int randVelX = random.nextInt(Game.WIDTH + 64) - 32;
			int randVelY = random.nextInt(Game.WIDTH + 64) - 32;
			
			if(randX < 0 || randX > Game.WIDTH){
				randY = random.nextInt(Game.HEIGHT);
				randVelX = random.nextInt(2);
				randVelY = random.nextInt(2);
			}
			if(randY )
			
			targets.add(new Schnitzel(-32, random.nextInt(Game.HEIGHT), -255, 2, random.nextInt(2), 0, handler, ObjectID.Schnitzel));
			handler.addObject(targets.getLast());
		}
	}
	
	/**
	 * checks if TargetHandler objects are still being handled by Handler and removes them if not
	 */
	private void deleteOldTargets(){
		for(int i = 0; i < targets.size(); i++){
			GameObject tempobject = targets.get(i);
			
			if(!handler.contains(tempobject)){
				targets.remove(tempobject);
			}
			
		}
	}
}
