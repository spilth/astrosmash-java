package org.spilth.astrosmash.actors;

public class Explosion extends VisibleActor {	
	private int lifetime = 0;
	
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