package org.spilth.astrosmash.game.actors;

import org.spilth.astrosmash.engine.VisibleActor;

public class Asteroid extends VisibleActor {
	private static int SPEED = 6;
	
	public String getImageName() {
    	return "asteroid.png";
    }
	
	public void update() {
		y += SPEED;
	}
}