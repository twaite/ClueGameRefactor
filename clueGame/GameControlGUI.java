package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameControlGUI {
	JFrame frame;

	public GameControlGUI() {
		//Set up frame.
		frame = new JFrame();
		frame.setSize(800, 200);
		frame.setTitle("Game Control GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 0));
		
		//Set up north panel.
		ControlTopPanel panelNorth = new ControlTopPanel();
		frame.add(panelNorth, BorderLayout.NORTH);
		
		//Set up south panel.
		ControlBottomPanel panelSouth = new ControlBottomPanel();
		frame.add(panelSouth, BorderLayout.SOUTH);
		frame.setVisible(true);

	}
	
	public static void main(String[] args) {
		GameControlGUI gameControl = new GameControlGUI();
	}

}
