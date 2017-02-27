package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import framework.KeyInput;
import framework.MouseInput;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -7516887150698980623L;
	
	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	
	Handler handler;
	
	private void init(){
		handler = new Handler();
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		handler.createLevel();
		handler.createTargets();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
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
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	
		handler.render(g);
		
		g.dispose();
		bs.show();
	}

	private void update() {
		handler.update();
	}

	public static void main(String[] args){
		new Window(1600, 800, "Schnitzeljagd", new Game());
	}
}
