package org.spilth.astrosmash.game.actors;

import org.spilth.astrosmash.engine.VisibleActor;

public class Missile extends VisibleActor {
	private static int SPEED = 16;

    public String getImageName() {
    	return "missile.png";
    }
	
	public void update() {
	    y -= SPEED;
        if (y < -8) {
        	world.removeActor(this);
        }
	}
}
