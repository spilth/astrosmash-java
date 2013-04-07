package org.spilth.astrosmash.actors;

import java.awt.event.KeyEvent;

public class Player extends VisibleActor {
    private int dx;
    private int dy;
   
    private static int SPEED = 8;
    
    public String getImageName() {
    	return "spacecraft.png";
    }
    
    public void update() {
        x += dx;
        y += dy;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -SPEED;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = SPEED;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -SPEED;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = SPEED;
        }
        
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
    }
    
    public void fire() {
        world.spawnActor(new Missile(), x, y);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}