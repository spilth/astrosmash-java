package org.spilth.astrosmash.actors;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import org.spilth.astrosmash.World;


public class VisibleActor extends Actor {
	protected Image image;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected float scale = 1;

	public VisibleActor() {
		image = new ImageIcon(this.getClass().getResource(getImageName())).getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = y = 0;
	}
	
	public void paint(World board, Graphics2D g2d) {
	    g2d.drawImage(image, x, y, this.getWidth(), this.getHeight(), board);
	}

	public String getImageName() {
		return null;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return (int) (width * scale);
	}
	
	public int getHeight() {
		return (int) (width * scale);
	}
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
}
