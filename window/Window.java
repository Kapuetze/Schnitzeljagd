package window;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JFrame;

import framework.GameState;

public class Window {

	@SuppressWarnings("deprecation")
	public Window(int w, int h, String title, Game game){
		/** create Window here **/
		
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
		frame.setCursor(Cursor.CROSSHAIR_CURSOR);
		
		game.start();
		
	}
}
