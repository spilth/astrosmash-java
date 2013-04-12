package org.spilth.astrosmash.game.gamestates;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import org.spilth.astrosmash.engine.Actor;
import org.spilth.astrosmash.engine.GameState;
import org.spilth.astrosmash.engine.VisibleActor;
import org.spilth.astrosmash.game.actors.Asteroid;
import org.spilth.astrosmash.game.actors.AsteroidSpawner;
import org.spilth.astrosmash.game.actors.Explosion;
import org.spilth.astrosmash.game.actors.Missile;
import org.spilth.astrosmash.game.actors.Player;

public class PlayState extends GameState {
	private Player player;
	private int score = 0;

	public PlayState() {
		player = new Player();

		spawnActor(player, 512, 680);
		spawnActor(new AsteroidSpawner(), 0, 0);
	}
	
	public void update() {
		super.update();
		checkCollisions();
	}

	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
	
	private void checkCollisions() {
		Actor a, b;
		Rectangle aRect;

		for (Iterator<Actor> i = actors.iterator(); i.hasNext();) {
			a = i.next();
			if (a instanceof VisibleActor) {
				aRect = ((VisibleActor) a).getBounds();

				for (Iterator<Actor> j = actors.iterator(); j.hasNext();) {
					b = j.next();
					if (b instanceof VisibleActor) {
						if (a != b) {
							if (aRect.intersects(((VisibleActor) b).getBounds())) {
								System.out.println("Collision! " + a + ":" + b);

								if (a instanceof Missile
										& b instanceof Asteroid) {
									removeActor(a);
									removeActor(b);
									spawnActor(new Explosion(),
											((VisibleActor) b).getX(),
											((VisibleActor) b).getY());
									score += 100;
								}
							}
						}
					}
				}
			}
		}
	}
}
