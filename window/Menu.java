/**
 * 
 */
package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author Jonas
 *
 */
public class Menu{
	
	private int btnwidth = 300;
	
	private int btnheight = 100;

	private Rectangle btnresume = new Rectangle((int)(Game.WIDTH/2 - btnwidth * 1.5f), Game.HEIGHT/2 - btnheight/2, btnwidth, btnheight);
	
	private Rectangle btnquit = new Rectangle(Game.WIDTH/2, Game.HEIGHT/2 - btnheight/2, btnwidth, btnheight);
	
	private boolean firststart = true;



	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		
		//draw menu buttons
		g.setColor(Color.BLACK);
		g2d.draw(btnresume);
		g2d.draw(btnquit);
		
		//add caption
		if (firststart){
			g.drawString("START", btnresume.x + btnwidth/5, btnresume.y + 65);
		}
		else{
			g.drawString("RESUME", btnresume.x + btnwidth/8, btnresume.y + 65);
		}
		g.drawString("QUIT", btnquit.x + btnwidth/4, btnquit.y + 65);
	}
	

	/**
	 * @return the btnresume
	 */
	public Rectangle getBtnresume() {
		return btnresume;
	}

	/**
	 * @param btnresume the btnresume to set
	 */
	public void setBtnresume(Rectangle btnresume) {
		this.btnresume = btnresume;
	}


	/**
	 * @return the btnquit
	 */
	public Rectangle getBtnquit() {
		return btnquit;
	}


	/**
	 * @param btnquit the btnquit to set
	 */
	public void setBtnquit(Rectangle btnquit) {
		this.btnquit = btnquit;
	}
	
	/**
	 * @return the firststart
	 */
	public boolean isFirststart() {
		return firststart;
	}


	/**
	 * @param firststart the firststart to set
	 */
	public void setFirststart(boolean firststart) {
		this.firststart = firststart;
	}


}
