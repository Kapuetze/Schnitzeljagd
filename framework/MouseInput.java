package framework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import objects.Shot;
import window.Game;
import window.Handler;

public class MouseInput implements MouseListener {
	
	/**
	 * Time of the last shot in MS
	 */
	private long shootTime = 0;
	
	/**
	 * Delay between shots
	 */
	private long shootDelay = 700;
	
	Handler handler = Game.getHandlerInstance();
	
	/**
	 * initialise mouse input handler
	 */
	public MouseInput(){

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		switch(GameState.getState()){
		case RUNNING:
			
			//add shot object
			if(System.currentTimeMillis() - shootTime > shootDelay){
				new Shot((int)mx - (int)10, (int)my - (int)10, 0, ObjectID.Shot);
				shootTime = System.currentTimeMillis();
			}
			
			
			break;
			
		case MENU:
			//menu button handling
			if (Game.getMenuInstance().getBtnresume().contains(mx, my)){
				//turn start -> resume
				Game.getMenuInstance().setFirststart(false);
				
				//start/resume the game
				GameState.setState(GameState.RUNNING);
			}
			else if (Game.getMenuInstance().getBtnquit().contains(mx, my)){
				//quit the game
				System.exit(1);
			}
			break;
			
		default:
			break;
		

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
