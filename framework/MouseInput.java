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
	
	private boolean shotready = true;
	
	Handler handler = Game.getHandlerInstance();
	
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

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		switch(GameState.getState()){
		case RUNNING:
			
			shotready = true;
			
			if(shotready){
				new Shot((int)mx - (int)10, (int)my - (int)10, 0, ObjectID.Shot);
				shotready = false;
			}
			
			//limit projectiles
			//int timer = 1000;
			//ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
			//scheduler.schedule(this::shotready, timer, TimeUnit.MILLISECONDS);
			
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
	
	private void shotready(){
		shotready = true;
	}

}
