package framework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import objects.Shot;
import window.Game;
import window.Handler;

public class MouseInput implements MouseListener {
	
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
		/*
		long timeNow = System.currentTimeMillis();
		long timeOfLastProjectile = 0;
		long time = timeNow - timeOfLastProjectile ;
		
		if (time < 0 || time > 1000) {
		*/
			//timeOfLastProjectile = timeNow;
			new Shot((int)mx - (int)10, (int)my - (int)10, 0, ObjectID.Shot);
		//}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
