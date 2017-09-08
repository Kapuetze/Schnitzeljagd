package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import framework.Dashboard;
import framework.GameState;
import framework.KeyInput;
import framework.MouseInput;
import framework.ObjectID;
import framework.Texture;
import objects.Block;
import objects.BlockID;
import objects.Ketchup;
import objects.Schnitzel;
import objects.TargetHandler;


/**
 * Main Game class
 * @author Jonas
 *
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -7516887150698980623L;
	
	/**
	 * Is the Game running
	 */
	private boolean running = false;
	
	/**
	 * Main Thread
	 */
	private Thread thread;
	
	/**
	 * level Texture
	 */
	private BufferedImage level = null;
	
	/**
	 * Width and Height of the window
	 */
	public static int WIDTH, HEIGHT;
	
	/**
	 * Gameoject Handler
	 */
	static Handler handler;
	
	/**
	 * Handler for targets
	 */
	static TargetHandler targethandler;
	
	/**
	 * Holds textures
	 */
	static Texture texture;
	
	/**
	 * Main camera
	 */
	static Camera camera;
	
	/**
	 * Dashboard
	 */
	static Dashboard dashboard;
	
	/**
	 * Main menu
	 */
	static Menu menu;
	
	/**
	 * global number of frames
	 */
	static int FRAMES;
	
	
	final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(); //used to spawn targets every second

	
	/**
	 * initialize all static variables
	 * add Listener controllers
	 */
	private void init(){
		handler = new Handler();
		targethandler = new TargetHandler(5);
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		FRAMES = 0;
				
		texture = new Texture();
		camera = new Camera(0,0);
		dashboard = new Dashboard(1500, 25, 1, ObjectID.Dashboard);
		menu = new Menu();
		
		executorService.scheduleAtFixedRate(targethandler::spawnTarget, 0, 500, TimeUnit.MILLISECONDS); //call spawnTarget() every half a second

		
		
		this.addKeyListener(new KeyInput());
		this.addMouseListener(new MouseInput());
		
		//Load Level
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png");
		
		loadLevel(level); //load level from level texture
	}
	
	/**
	 * Start the game thread
	 */
	public synchronized void start(){
		
		if(running){
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
		
		//actual game loop
		while(running){
			
			switch (GameState.getState()){
				case PAUSED: 
					pause();
					break;
					
				case RUNNING: 
					resume();
					break;
		
				default:
					break;
			}
			
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
			FRAMES++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	
	/**
	 * pause the Game
	 */
	private void pause(){
		try {
			synchronized(thread){
				thread.wait();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * resume the Game
	 */
	private void resume(){
		synchronized(thread){
			thread.notify();
		}
	}
	
	/**
	 * stop the Game
	 */
	private void stop(){
		
	}
	
	/**
	 * The Render function
	 */
	private void render() {
		
		

			// TODO Auto-generated method stub
			BufferStrategy bs = this.getBufferStrategy();
			if(bs == null){
				this.createBufferStrategy(3); //set max buffer to 3
				return;
			}

			Graphics g = bs.getDrawGraphics();

			// We draw our contents here

			//default BG color is white
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH, HEIGHT);

			//pause game if menu
			if(GameState.getState() == GameState.RUNNING){
				//tell the handler to render all gameobjects
				handler.render(g);
			}
			else if (GameState.getState() == GameState.MENU){
				//render menu
				menu.render(g);
			}

			g.dispose();
			bs.show();
		
	}

	/**
	 * The Update function
	 */
	private void update() {
		//pause game if menu
		if(GameState.getState() == GameState.RUNNING){
			//update all targets
			targethandler.update();
			
			//update all gameobjects
			handler.update();
		}
		else if (GameState.getState() == GameState.MENU){
			
		}
	}
	
	/**
	 * Load level out of image
	 * @param image
	 */
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
					new Block(xi *32, yi *32, 0, BlockID.KitchenTileGray, ObjectID.Block);
				}
				if(red == 0 && green == 100 & blue == 100){
					new Block(xi *32, yi *32, 0, BlockID.KitchenTileWhite, ObjectID.Block);
				}
				if(red == 255 && green == 200 & blue == 0){
					new Schnitzel(xi *32, yi *32, 0, ObjectID.Block);
				}
			}
		}
		
	}
	
	/**
	 * @return the Textures
	 */
	public static Texture getTextureInstance(){
		return texture;
	}
	
	/**
	 * @return the Camera (not used)
	 */
	public static Camera getMainCamera(){
		return camera;
	}
	
	/**
	 * @return the TargetHandler
	 */
	public static TargetHandler getTargetHandlerInstance(){
		return targethandler;
	}
	
	/**
	 * @return the Handler
	 */
	public static Handler getHandlerInstance(){
		return handler;
	}
	
	/**
	 * @return the Dashboard
	 */
	public static Dashboard getDashboardInstance(){
		return dashboard;
	}
	
	/**
	 * @return the Menu
	 */
	public static Menu getMenuInstance(){
		return menu;
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * @return the frames
	 */
	public static int getFrames() {
		return FRAMES;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	public static void main(String[] args){
		new Window(1600, 800, "Schnitzeljagd", new Game());
		
	}
}
