package objects;

import java.util.Random;

public enum TargetID {
	
	Schnitzel(),
	Ketchup(),
	Steak();
	
	public static TargetID getRandomObject(){
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

	
}

