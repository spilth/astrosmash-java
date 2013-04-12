package org.spilth.astrosmash.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainMenu extends JPanel implements ActionListener {
	JButton playButton, quitButton;
	Astrosmash gameWindow;
	
	public MainMenu() {
		setFocusable(true);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setDoubleBuffered(true);
		
		playButton = new JButton("Play");
		quitButton = new JButton("Quit");
		add(playButton);
		add(quitButton);
		
		playButton.addActionListener(this);
		quitButton.addActionListener(this);
	}

	public MainMenu(Astrosmash gameWindow) {
		this();
		this.gameWindow = gameWindow;
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == playButton) {
			gameWindow.play();
		} else if (source == quitButton) {
			System.exit(0);
		}
		
	}

}
