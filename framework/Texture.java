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
	public BufferedImage[] schnitzel = new BufferedImage[31];
	public BufferedImage[] fork = new BufferedImage[8];
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			blocksheet = loader.loadImage("/blocksheet.png");
			schnitzelsheet = loader.loadImage("/schnitzelsheet_new.png");
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
		/*
		schnitzel[0] = schnitzels.getImage(1, 1, 64, 64);
		schnitzel[1] = schnitzels.getImage(2, 1, 64, 64);
		schnitzel[2] = schnitzels.getImage(3, 1, 64, 64);
		schnitzel[3] = schnitzels.getImage(4, 1, 64, 64);
		*/
		/*
		fork[0] = forks.getImage(1, 1, 128, 128);
		fork[1] = forks.getImage(2, 1, 128, 128);
		fork[2] = forks.getImage(3, 1, 128, 128);
		fork[3] = forks.getImage(4, 1, 128, 128);
		fork[4] = forks.getImage(6, 1, 128, 128);
		fork[5] = forks.getImage(7, 1, 128, 128);
		fork[6] = forks.getImage(8, 1, 128, 128);
		fork[7] = forks.getImage(1, 2, 128, 128);
		*/
		
		loadTexturesFromSheet(schnitzel, 161, 161, 9, 4, 31, schnitzels);
		loadTexturesFromSheet(fork, 128, 128, 8, 2, 8, forks);
		
	}
	
	private void loadTexturesFromSheet(BufferedImage[] images, int spritewidth, int spriteheight, int maxcol, int maxrow, int spritecount, SpriteSheet sheet){
		int counter = 0;
		
		for(int row = 1; row <= maxrow; row++){
			for(int col = 1; col <= maxcol; col++){
				if(counter < spritecount){
					images[counter] = sheet.getImage(col, row, spritewidth, spriteheight);
					counter++;
				}
			}
		}
	}
}
