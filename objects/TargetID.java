package objects;

import java.util.Random;

/**
 * @author Jonas
 * Holds all IDs for targets
 */
public enum TargetID {
	
	Schnitzel(),
	Ketchup(),
	Steak();
	
	/**
	 * Returns a random TargetID based on the specified chance
	 * @return A random TargetID
	 */
	public static TargetID getRandomObject(){
		
		//percentages
		int ketchupchance = 10;			//10
		int schnitzelchance = 60;		//60 arbitrary, because the rest is specified
		int steakchance = 30;			//30
		
		//calculate a random number
		Random random = new Random();
		int chance = random.nextInt(100);
		
		//return object based on the chance it has
		if (chance < ketchupchance){
			return Ketchup;
		}
		else if(chance < steakchance){
			return Steak;
		}
		else{
			return Schnitzel;
		}

	}

	
}

