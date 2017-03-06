package framework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import objects.Shot;
import window.Handler;

public class MouseInput implements MouseListener {
	
	Handler handler;
	
	public MouseInput(Handler handler){
		this.handler = handler;
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
		
		handler.addObject(new Shot((int)mx - (int)10, (int)my - (int)10, 0, handler, ObjectID.Shot));
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
