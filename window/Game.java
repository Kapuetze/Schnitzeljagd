package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import framework.KeyInput;
import framework.MouseInput;
import framework.ObjectID;
import framework.Texture;
import objects.Block;
import objects.BlockID;
import objects.Schnitzel;
import objects.TargetHandler;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -7516887150698980623L;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage level = null;
	
	public static int WIDTH, HEIGHT;
	
	Handler handler;
	TargetHandler targethandler;
	static Texture texture;
	static Camera camera;
	
	
	final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(); //used to spawn targets every second

	
	private void init(){
		handler = new Handler();
		targethandler = new TargetHandler(handler);
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
				
		texture = new Texture();
		camera = new Camera(0,0);
		
		executorService.scheduleAtFixedRate(targethandler::spawnTarget, 0, 500, TimeUnit.MILLISECONDS); //call spawnTarget() every second

		
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
		
		//Load Level
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png");
		
		loadLevel(level); //load level from level texture
	}
	
	public synchronized void start(){
		
		if(running){
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run(){
		System.out.println("Main thread has been started!");
		
		/** Gameloop **/
		
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; //fps
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running){
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			
			render(); //render all frames
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	
	private void render() {
		// TODO Auto-generated method stub
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3); //set max buffer to 3
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		// We draw our contents here
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	
		handler.render(g);
		
		g.dispose();
		bs.show();
	}

	private void update() {
		targethandler.update();
		handler.update();
	}
	
	private void loadLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		//loop through every single pixel of the level texture
		for(int xi = 0; xi < h; xi++){
			for(int yi = 0; yi < w; yi++){
				int pixel = image.getRGB(xi,  yi);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				//white pixel -> creates level structure
				if(red == 255 && green == 255 & blue == 255){
					handler.addObject(new Block(xi *32, yi *32, 0, BlockID.KitchenTileGray, ObjectID.Block));
				}
				if(red == 0 && green == 100 & blue == 100){
					handler.addObject(new Block(xi *32, yi *32, 0, BlockID.KitchenTileWhite, ObjectID.Block));
				}
				if(red == 255 && green == 200 & blue == 0){
					handler.addObject(new Schnitzel(xi *32, yi *32, 0, handler, ObjectID.Block));
				}
			}
		}
	}
	
	public static Texture getTextureInstance(){
		return texture;
	}
	
	public static Camera getMainCamera(){
		return camera;
	}

	public static void main(String[] args){
		new Window(1600, 800, "Schnitzeljagd", new Game());
	}
}
