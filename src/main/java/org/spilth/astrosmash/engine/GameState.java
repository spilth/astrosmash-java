package org.spilth.astrosmash.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class GameState {    
    protected ArrayList<Actor> actors;
    private ArrayList<Actor> oldActors;
    private ArrayList<Actor> newActors;

    public GameState() {
        actors = new ArrayList<Actor>();
        oldActors = new ArrayList<Actor>();
        newActors = new ArrayList<Actor>();
    }
    
    public void update() {
        updateActors();
        removeActors();
        spawnActors();
    }
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
 
        renderActors(g2d);
       
        Toolkit.getDefaultToolkit().sync();
        g.dispose();		
    }
    
    public void spawnActor(Actor actor, int x, int y) {
    	if (actor instanceof VisibleActor) {
    		((VisibleActor) actor).setLocation(x,y);
    	}
    	actor.setWorld(this);
    	newActors.add(actor);
	}
    
	public void removeActor(Actor actor) {
		oldActors.add(actor);
	}

	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}

	private void renderActors(Graphics2D g2d) {
		Actor actor;
        for (Iterator<Actor> i = actors.iterator(); i.hasNext(); ) {
        	actor = i.next();
        	if (actor instanceof VisibleActor) {
        		((VisibleActor) actor).paint(g2d);
        	}
        }
	}
    
	private void updateActors() {
        for (Iterator<Actor> i = actors.iterator(); i.hasNext(); ) { 
            i.next().update();
        }
    }
    
    private void removeActors() {
    	for (Iterator<Actor> i = oldActors.iterator(); i.hasNext(); ) { 
    		actors.remove(i.next());
        }
    	oldActors.clear();
    }
    
    private void spawnActors() {
    	for (Iterator<Actor> i = newActors.iterator(); i.hasNext(); ) { 
    		actors.add(i.next());
        }
    	newActors.clear();
    }

}