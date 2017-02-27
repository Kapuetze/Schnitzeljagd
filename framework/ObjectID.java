package framework;

import java.util.Random;

public enum ObjectID {

	Player(),
	Schnitzel(),
	Shot(),
	Block();

	public static ObjectID getRandomObject(){
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
