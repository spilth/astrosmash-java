package org.spilth.astrosmash.actors;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import org.spilth.astrosmash.World;

public class VisibleActor extends Actor {
	protected Image image;
	protected int x = 0;
	protected int y = 0;
	protected float scale = 1.0f;
	protected int imageWidth;
	protected int imageHeight;

	public VisibleActor() {
		image = new ImageIcon(this.getClass().getResource(getImageName())).getImage();
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
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
		return (int) (imageWidth * scale);
	}

	public int getHeight() {
		return (int) (imageWidth * scale);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getWidth(), getHeight());
	}
}
