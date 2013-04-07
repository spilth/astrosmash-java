package org.spilth.astrosmash;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import org.spilth.astrosmash.actors.Actor;
import org.spilth.astrosmash.actors.Asteroid;
import org.spilth.astrosmash.actors.AsteroidSpawner;
import org.spilth.astrosmash.actors.Explosion;
import org.spilth.astrosmash.actors.Missile;
import org.spilth.astrosmash.actors.Player;
import org.spilth.astrosmash.actors.VisibleActor;


@SuppressWarnings("serial")
public class World extends JPanel implements Runnable, ActionListener {
    private Thread animator;
    private Player player;
    private int score = 0;
    
    private ArrayList<Actor> actors;
    private ArrayList<Actor> deadActors;
    private ArrayList<Actor> babyActors;
    
    private final int DELAY = 50;

    public World() {
    	addKeyListener(new TAdapter());
    	setFocusable(true);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setDoubleBuffered(true);

        actors = new ArrayList<Actor>();
        deadActors = new ArrayList<Actor>();
        babyActors = new ArrayList<Actor>();
        
        player = new Player();

        spawnActor(player, 512, 680);
        spawnActor(new AsteroidSpawner(), 0, 0);
    }

    public void spawnActor(Actor actor, int x, int y) {
    	if (actor instanceof VisibleActor) {
    		((VisibleActor) actor).setLocation(x,y);
    	}
    	actor.setWorld(this);
    	babyActors.add(actor);
	}

	public void addNotify() {
        super.addNotify();
        
        animator = new Thread(this);
        animator.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        Actor actor;
        
        for (Iterator<Actor> i = actors.iterator(); i.hasNext(); ) {
        	actor = i.next();
        	if (actor instanceof VisibleActor) {
        		((VisibleActor) actor).paint(this, g2d);
        	}
        }
        
        g.drawString("Score: " + Integer.toString(score), 768, 704);
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();		
    }

    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            updateActors();
            killActors();
            deliverActors();
            checkCollisions();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
    }
    
    private void checkCollisions() {
    	Actor a, b;
    	Rectangle aRect;
    	
        for (Iterator<Actor> i = actors.iterator(); i.hasNext(); ) { 
            a = i.next();
            if (a instanceof VisibleActor) {
	            aRect = ((VisibleActor) a).getBounds();
	
	            for (Iterator<Actor> j = actors.iterator(); j.hasNext(); ) { 
	            	b = j.next();
	            	if (b instanceof VisibleActor) {
		            	if (a != b) {
		            		if (aRect.intersects(((VisibleActor) b).getBounds())) {
		            			System.out.println("Collision! " + a + ":" + b);
		            			
		            			if (a instanceof Missile & b instanceof Asteroid) {
		            				removeActor(a);
		            				removeActor(b);
		            				spawnActor(new Explosion(), ((VisibleActor) b).getX(), ((VisibleActor) b).getY());
		            				score += 100;
		            			}
		            		}
		            	}
	            	}
	            }
            }
        }
	}

	public void updateActors() {
        for (Iterator<Actor> i = actors.iterator(); i.hasNext(); ) { 
            i.next().update();
        }
    }
    
    public void killActors() {
    	for (Iterator<Actor> i = deadActors.iterator(); i.hasNext(); ) { 
    		actors.remove(i.next());
        }
    	deadActors.clear();
    }
    
    public void deliverActors() {
    	for (Iterator<Actor> i = babyActors.iterator(); i.hasNext(); ) { 
    		actors.add(i.next());
        }
    	babyActors.clear();
    }
    
	public void removeActor(Actor actor) {
		deadActors.add(actor);
	}
    
    private class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }

	public void actionPerformed(ActionEvent ae) {}
}