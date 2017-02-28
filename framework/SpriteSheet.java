package framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage getImage(int col, int row, int width, int height){
		//Returns a part of an image 
		BufferedImage gimage = image.getSubimage((col*width) - width + 1, (row*height) - height, width, height);
		return gimage;
	}
}
