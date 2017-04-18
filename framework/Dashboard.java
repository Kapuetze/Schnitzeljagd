package framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import window.Game;

/**
 * Creates a new Score object
 * @author Jonas
 *
 */
public class Dashboard extends GameObject implements TargetListener{

	/**
	 * The overall Score
	 */
	private int score;
	
	
	/**
	 * indicates, if the multiplier is active
	 */
	private boolean isKetchup = false;
	
	/**
	 * multiplier amount for Ketchup
	 */
	private int ketchupMultiplier = 1;
	
	/**
	 * String output
	 */
	private String ketchupAlert = "KETCHUP BONUS ACTIVE!!!";
	
	/**
	 * String output
	 */
	private String ketchupAlertOriginal = "KETCHUP BONUS ACTIVE!!!";
	

	
	
	/**
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 * @param id ObjectID
	 */
	public Dashboard(float x, float y, float z, ObjectID id) {
		super(x, y, z, id);
		score = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void targetHit(GameObject object) {
		
		 switch(object.getID()){
		 	
			//add one point for schnitzel
			case Schnitzel:
				if(isKetchup){
					score = score + 1 * ketchupMultiplier;
				}
				else{
					score++;
				}
				break;
				
			//add 2 points for steak
			case Steak:
				if(isKetchup){
					score = score + 2 + 1 * ketchupMultiplier;
				}
				else{
					score = score + 2;
				}
				break;
			
			//multiply points for ketchup
			case Ketchup:
				
				isKetchup = true;
				
				ketchupMultiplier++;
				
				//change multiplier alert
				ketchupAlert = ketchupAlertOriginal + " x" + Integer.toString(ketchupMultiplier);
				break;
			
			default:
				
				break;
		 
		 }
		
	}

	@Override
	public void update(LinkedList<GameObject> object) {

		
	}

	@Override
	public void render(Graphics g) {
		
		//create graphics2D object
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		//draw score
		g2d.setColor(Color.BLACK);
		g2d.drawString("Score: " + Integer.toString(score), (int)x - 20, (int)y);
		
		//if Ketchup multiplier draw alert
		if(isKetchup){
			g2d.setColor(Color.RED);
			g2d.drawString(ketchupAlert, Game.WIDTH - Game.WIDTH/2 - ketchupAlert.length()*7, 25);
		}
		
	}

	@Override
	public void targetDestroyed(GameObject object) {
		
		switch(object.getID()){
		//multiply points for ketchup
		case Ketchup:
			if (ketchupMultiplier == 1) {
				isKetchup = false;
			}
			
			ketchupMultiplier--;
			
			//change multiplier alert
			ketchupAlert = ketchupAlertOriginal + " x" + Integer.toString(ketchupMultiplier);
			break;
		
		default:
			
			break;
	 
	 }
		
	}

	
}
