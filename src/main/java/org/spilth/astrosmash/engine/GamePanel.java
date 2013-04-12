package org.spilth.astrosmash.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, ActionListener {
	private GameState gameState;
	
    private Thread animator;
        
    private final int DELAY = 50;

    public GamePanel(GameState gameState) {
    	this.gameState = gameState;
	
    	addKeyListener(new TAdapter());
    	setFocusable(true);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setDoubleBuffered(true);
    }

	public void addNotify() {
        super.addNotify();
        
        animator = new Thread(this);
        animator.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        gameState.render(g2d);
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();		
    }

    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            gameState.update();
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
        
    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            gameState.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            gameState.keyReleased(e);
        }
    }

	public void actionPerformed(ActionEvent ae) {}
}