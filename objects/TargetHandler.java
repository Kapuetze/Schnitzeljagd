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
	 * target list
	 */
	private LinkedList<GameObject> targets;
	
	/**
	 * maximum target count
	 */
	private int maxtargets;
	
    
	
	/**
	 * initiate Targethandler 
	 * @param maxtargets maximum amount of targets that the TargetHandler should handle
	 */
	public TargetHandler(int maxtargets){
		targets = new LinkedList<GameObject>();
		this.maxtargets = maxtargets;
	}
	
	/**
	 * check to delete targets with every update
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
			
			int position = random.nextInt(4); // 0 = left, 1 = top, 2 = right, 3 = bot
			
			//get random object
			TargetID targettype = TargetID.getRandomObject();
			
			int randY, randX, randVelX, randVelY;
			float gravity;
			
			//Spawn targets with random values on a specific side of the screen
			switch(targettype){
			
				//same requirements for Schnitzel and Steak
				case Schnitzel:
				case Steak:
	
					switch (position){
						//LEFT
						case 0: randX = -32;
								randY = random.nextInt(Game.HEIGHT);
								randVelX = random.nextInt(2) + 1;
								randVelY = random.nextInt(2);
								gravity = 0;
								break;
							
						//TOP
						case 1: randX = random.nextInt(Game.WIDTH);
								randY = 0;
								randVelX = random.nextInt(2);
								randVelY = random.nextInt(1);
								gravity = 0.05f;
								break;
						
						//RIGHT
						case 2: randX = Game.WIDTH + 32;
								randY = random.nextInt(Game.HEIGHT);
								randVelX = -random.nextInt(2) - 1;
								randVelY = random.nextInt(2);
								gravity = 0;
								break;
						
						//BOT
						case 3: randX = random.nextInt(Game.WIDTH);
								randY = Game.HEIGHT - 32;
								randVelX = random.nextInt(2);
								randVelY = -random.nextInt(5) - 2;
								gravity = 0.05f;
								break;
								
						default: 	randX = 0;
									randY = 0;
									randVelX = 0;
									randVelY = 0;
									gravity = 0;
									break;
						
					}
					
					
//					System.out.println("randX: " + randX);
//					System.out.println("randY: " + randY);
//					System.out.println("randVelX: " + randVelX);
//					System.out.println("randVelY: " + randVelY);
//					System.out.println("gravity: " + gravity);
					
					//spawn Schnitzel
					if (targettype == TargetID.Schnitzel){
						targets.add(new Schnitzel(randX, randY, -255, randVelX, randVelY, 0, ObjectID.Schnitzel));
					}
					
					//spawn Steak
					if (targettype == TargetID.Steak){
						targets.add(new Steak(randX, randY, -255, randVelX, randVelY, 0, ObjectID.Steak));
					}
					
					//add gravity
					targets.getLast().setGravity(gravity);
					//targets.add(new Schnitzel(-32, random.nextInt(Game.HEIGHT), -255, 2, random.nextInt(2), 0, handler, ObjectID.Schnitzel));
				break;	
				case Ketchup:
					
					//create random position
					randX = random.nextInt(Game.WIDTH);
					randY = random.nextInt(Game.HEIGHT);
					
					//add ketchup
					targets.add(new Ketchup(randX, randY, -255, ObjectID.Ketchup));
				break;
			}
		}
	}
	
	/**
	 * checks if TargetHandler objects are still being handled by Handler and removes them if not
	 */
	private void deleteOldTargets(){
		
		for(int i = 0; i < targets.size(); i++){
			GameObject tempobject = targets.get(i);
			
			//cull object outside of the screen
			if((tempobject.getX() > -Game.getMainCamera().getX() + Game.WIDTH + 64|| 
				(tempobject.getX() < -Game.getMainCamera().getX() -64 || 
				(tempobject.getY() > -Game.getMainCamera().getY() + Game.HEIGHT +64 || 
				tempobject.getY() < -Game.getMainCamera().getY() -64)))){
					
					//remove object from Targethandler
					removeTarget(tempobject);
					
					//destroy Object
					tempobject.destroy();
				}
			
		}
		
	}
	
	/**
	 * remove Target from TargetHandler
	 * @param object The GameObject to be removed
	 */
	public void removeTarget(GameObject object){
		targets.remove(object);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public void printTargets() {
		for(int i = 0; i < targets.size(); i++) {
			System.out.println(targets.get(i).toString());
		}
	}
}
