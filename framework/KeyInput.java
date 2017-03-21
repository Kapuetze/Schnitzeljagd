package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import window.Game;
import window.Handler;

public class KeyInput extends KeyAdapter {

	Handler handler = Game.getHandlerInstance();
	
	public KeyInput(){
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e){
		
	}
	
	
}
