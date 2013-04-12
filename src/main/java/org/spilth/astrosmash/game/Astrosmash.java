package org.spilth.astrosmash.game;

import javax.swing.JFrame;

import org.spilth.astrosmash.engine.GamePanel;
import org.spilth.astrosmash.game.gamestates.PlayState;

@SuppressWarnings("serial")
public class Astrosmash extends JFrame {
	MainMenu mainMenu;
	GamePanel gamePanel;
	
    public Astrosmash() {
    	mainMenu = new MainMenu(this);    	
    	add(mainMenu);
        setTitle("Astrosmash");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    public static void main(String[] args) {
        new Astrosmash();
    }
    
    public void play() {
    	gamePanel = new GamePanel(new PlayState());
    	
    	remove(mainMenu);
       	add(gamePanel);
       	
    	validate();
    	//repaint();
    	gamePanel.requestFocusInWindow();
    }
}
