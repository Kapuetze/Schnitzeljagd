/**
 * 
 */
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import framework.GameObject;
import framework.ObjectID;
import objects.Particle;
import window.Game;
import window.Handler;

/**
 * @author Jonas
 *
 */
public class ParticleSystem extends GameObject {
	
	
	/**
	 * Holds all the Particles of a ParticleSystem
	 */
	private ArrayList<Particle> particles = new ArrayList<Particle>(500);
	
	/**
	 * maximum number of particles in the system
	 */
	private int maxparticles;
	
	/**
	 * indicates, if the system is running
	 */
	private boolean running = false;
	

	public ParticleSystem(float x, float y, float z, int maxparticles, ObjectID id) {
		super(x, y, z, id);
		
		this.maxparticles = maxparticles;
		// TODO Auto-generated constructor stub
	}


	@Override
	public void update(LinkedList<GameObject> object) {
		
		if(running){
			//add new particles
			if(particles.size() < maxparticles){
				addParticle();
			}
			
			deleteDeadParticles();
		}
	}

	@Override
	public void render(Graphics g) {
		//Rendering is taking place in the Particle class
		
	}
	
	/**
	 * add a particle to the system
	 */
	private void addParticle(){
		
		//random generator
		Random random = new Random();
		
		float randVelX = random.nextFloat() * 2 - 1;
		float randVelY = -(float)random.nextInt(3) - 2;
		particles.add(new Particle(x, y, randVelX, randVelY, 0.1f, 50, 5, Color.red, ObjectID.Particle));
	}
	
	/**
	 * remove a particle from the system
	 */
	private void removeParticle(Particle particle){
		particles.remove(particle);
	}
	
	/**
	 * checks if TargetHandler objects are still being handled by Handler and removes them if not
	 */
	private void deleteDeadParticles(){
		//loops through all the Particles of the list and deletes the ones that are not being handled by the handler anymore
		//works, because particles destroy themselves 
		for(int i = 0; i < particles.size(); i++){
			GameObject tempobject = particles.get(i);
			
			if(!handler.contains(tempobject)){
				removeParticle(particles.get(i));
			}
			
		}
	}
	
	/**
	 * starts the particle system
	 */
	public void start(){
		if(running == false){
			running = true;
		}
	}
	
	/**
	 * stops the particle system
	 */
	public void stop(){
		if(running == true){
			running = false;
			particles.clear();
		}
	}


	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}


	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
}
