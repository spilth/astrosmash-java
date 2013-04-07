package org.spilth.astrosmash.actors;

import org.spilth.astrosmash.World;

public class Actor {

	protected World world;

	public Actor() {
		super();
	}

	public void update() {}

	public void setWorld(World world) {
		this.world = world;
	}

}