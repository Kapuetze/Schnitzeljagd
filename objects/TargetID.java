package objects;

import java.util.Random;

public enum TargetID {
	
	Schnitzel(),
	Ketchup(),
	Steak();
	
	public static TargetID getRandomObject(){
		
		//percentages
		int ketchupchance = 10;
		int schnitzelchance = 60;
		int steakchance = 30;
		
		Random random = new Random();
		int chance = random.nextInt(100);
		
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

