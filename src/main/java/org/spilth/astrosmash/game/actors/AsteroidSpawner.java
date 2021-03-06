package org.spilth.astrosmash.game.actors;

import java.util.Random;

import org.spilth.astrosmash.engine.Actor;

public class AsteroidSpawner extends Actor {
	Random generator;
	
	public AsteroidSpawner() {
		super();
		generator = new Random();
	}
	
	public void update() {
		int r = generator.nextInt(1000);
		int x = generator.nextInt(1024);

		if (r < 25) {
			world.spawnActor(new Asteroid(), x, -60);
		}
	}
}
