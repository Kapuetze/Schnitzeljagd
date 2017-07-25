package window;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Jonas
 * Handles the loading of an image from the file system into the application
 */
public class BufferedImageLoader {

	private BufferedImage image;
	
	/**
	 * Load on image from a file path
	 * @param path
	 * @return The loaded image
	 */
	public BufferedImage loadImage(String path){
		
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Width:" + image.getWidth() + "Height" + image.getHeight());
		return image;
	}
}
