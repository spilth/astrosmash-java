package org.spilth.astrosmash.actors;

public class Asteroid extends VisibleActor {
	private static int SPEED = 6;
	
	public String getImageName() {
    	return "asteroid.png";
    }
	
	public void update() {
		y += SPEED;
	}
}