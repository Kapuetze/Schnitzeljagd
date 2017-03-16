package window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

public class Animation {

	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	public BufferedImage[] images;
	private BufferedImage currentImg;
	
	private float alpha = 1f;
	
	
	public Animation(int speed, BufferedImage[] args){
		this.speed = speed;
		images = new BufferedImage[args.length];
		
		for(int i = 0; i< args.length; i++){
			images[i] = args[i];
		}
		frames = args.length;
	}
	
	public void runAnimation(){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}
	}
	
	public void pauseAnimation(){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}
	}
	
	private void nextFrame(){
		
		currentImg = images[count%frames];
		count++;
	}
	
	public void drawAnimation(Graphics g, int x, int y){
		g.drawImage(currentImg, x, y, null);
	}
	
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
		g.drawImage(currentImg, x, y, scaleX, scaleY, null);
	}
	

}
