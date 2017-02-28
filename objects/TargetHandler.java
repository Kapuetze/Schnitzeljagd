package objects;

import java.util.LinkedList;
import java.util.Random;

import framework.GameObject;
import window.Game;
import window.Handler;

public class TargetHandler {
	private LinkedList<GameObject> targets;
	private int maxtargets;
	private Handler handler;
    
	
	public TargetHandler(Handler handler){
		targets = new LinkedList<GameObject>();
		this.maxtargets = 10;
		this.handler = handler;
	}
	
	public void update(){
		deleteOldTargets();
	}
	
	public void spawnTarget(){
		if(targets.size() < maxtargets){
			Random random = new Random();
			targets.add(new Schnitzel(-32, random.nextInt(Game.HEIGHT), -255, 2, random.nextInt(2), 0, handler, null));
			handler.addObject(targets.getLast());
		}
	}
	
	private void deleteOldTargets(){
		for(int i = 0; i < targets.size(); i++){
			GameObject tempobject = targets.get(i);
			
			if((tempobject.getX() > -Game.getMainCamera().getX() + Game.WIDTH || 
				(tempobject.getX() < -Game.getMainCamera().getX() -32 || 
				(tempobject.getY() > -Game.getMainCamera().getY() + Game.HEIGHT || 
				tempobject.getY() < -Game.getMainCamera().getY() -32)))){
					targets.remove(tempobject);
			}
		}
	}
}
