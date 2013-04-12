package org.spilth.astrosmash.game.actors;

import org.spilth.astrosmash.engine.VisibleActor;

public class Explosion extends VisibleActor {	
	private int lifetime = 0;

	public Explosion() {
		super();
		scale = 2.0f;
	}
	
	public String getImageName() {
    	return "explosion.png";
    }
		
	public void update() {
		lifetime += 1;
		scale += 0.2;
		if (lifetime > 4) {
			world.removeActor(this);
		}
	}
}