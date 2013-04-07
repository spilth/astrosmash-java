package org.spilth.astrosmash;

import javax.swing.JFrame;

import org.spilth.astrosmash.World;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {

    public GameWindow() {
        add(new World());
        setTitle("Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    public static void main(String[] args) {
        new GameWindow();
    }
}
