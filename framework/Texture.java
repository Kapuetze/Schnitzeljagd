package framework;

import java.awt.image.BufferedImage;

import window.BufferedImageLoader;

public class Texture {

	SpriteSheet blocks;
	SpriteSheet schnitzels;
	SpriteSheet forks;
	private BufferedImage blocksheet = null;
	private BufferedImage schnitzelsheet = null;
	private BufferedImage forksheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] schnitzel = new BufferedImage[4];
	public BufferedImage[] fork = new BufferedImage[5];
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			blocksheet = loader.loadImage("/blocksheet.png");
			schnitzelsheet = loader.loadImage("/schnitzelsheet.png");
			forksheet = loader.loadImage("/forksheet.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		blocks = new SpriteSheet(blocksheet);
		schnitzels = new SpriteSheet(schnitzelsheet);
		forks = new SpriteSheet(forksheet);
		
		getTextures();
	}
	
	private void getTextures(){
		block[0] = blocks.getImage(1, 1, 32, 32);
		block[1] = blocks.getImage(2, 1, 32, 32);
		
		schnitzel[0] = schnitzels.getImage(1, 1, 64, 64);
		schnitzel[1] = schnitzels.getImage(2, 1, 64, 64);
		schnitzel[2] = schnitzels.getImage(3, 1, 64, 64);
		schnitzel[3] = schnitzels.getImage(4, 1, 64, 64);
		
		fork[0] = forks.getImage(1, 1, 127, 128);
		fork[1] = forks.getImage(2, 1, 127, 128);
		fork[2] = forks.getImage(3, 1, 127, 128);
		fork[3] = forks.getImage(4, 1, 127, 128);
		fork[4] = forks.getImage(5, 1, 127, 128);
		
		
	}
}
