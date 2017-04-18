package framework;

import java.awt.image.BufferedImage;

import window.BufferedImageLoader;

public class Texture {

	//spritesheets
	SpriteSheet blocks;
	SpriteSheet schnitzels;
	SpriteSheet steaks;
	SpriteSheet forks;
	
	//initiate Bufferedimages
	private BufferedImage blocksheet = null;
	private BufferedImage schnitzelsheet = null;
	private BufferedImage steaksheet = null;
	private BufferedImage forksheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] schnitzel = new BufferedImage[31];
	public BufferedImage[] steak = new BufferedImage[25];
	public BufferedImage[] fork = new BufferedImage[8];
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			blocksheet = loader.loadImage("/blocksheet.png");
			schnitzelsheet = loader.loadImage("/schnitzelsheet_new.png");
			steaksheet = loader.loadImage("/steaksheet_small.png");
			forksheet = loader.loadImage("/forksheet.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		blocks = new SpriteSheet(blocksheet);
		steaks = new SpriteSheet(steaksheet);
		schnitzels = new SpriteSheet(schnitzelsheet);
		forks = new SpriteSheet(forksheet);
		
		getTextures();
	}
	
	private void getTextures(){
		block[0] = blocks.getImage(1, 1, 32, 32);
		block[1] = blocks.getImage(2, 1, 32, 32);
		
		loadTexturesFromSheet(schnitzel, 161, 161, 9, 4, 31, schnitzels);
		loadTexturesFromSheet(steak, 105, 105, 9, 3, 25, steaks);
		loadTexturesFromSheet(fork, 128, 128, 8, 2, 8, forks);
		
	}
	
	/**
	 * dynamically load texture from spritesheet
	 * @param images
	 * @param spritewidth
	 * @param spriteheight
	 * @param maxcol
	 * @param maxrow
	 * @param spritecount
	 * @param sheet
	 */
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
