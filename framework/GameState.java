package framework;

/**
 * GameState handles the current state the game is in.
 * @author Jonas
 *
 */
public enum GameState {
	PAUSED,
	RUNNING,
	MENU;
	
	private static GameState gamestate = GameState.MENU;
	
	public static GameState getState(){
		return gamestate;
	}
	
	public static void setState(GameState state)
	{
	    gamestate = state;
	}
	
	
}


