package a11;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Creates a game menu for the player to select which Planet type to add to the game.
 * 
 * @author Chris Marston
 * 
 * CS 1410 - a11 - April 2021
 *
 */
public class GameMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	public ButtonGroup menuButtons = new ButtonGroup();
	
	public JRadioButton marsButton = new JRadioButton("Mars");
	public JRadioButton saturnButton = new JRadioButton("Saturn");
	public JRadioButton titanSaturn = new JRadioButton("Titan Saturn");
	
	private JLabel gameOver = new JLabel();

	public GameMenu() {
		
		// Setting up gameOver label
		gameOver.setForeground(Color.WHITE);		
		add(gameOver);
		
		// Add the buttons to the panel
		add(marsButton);
		add(saturnButton);
		add(titanSaturn);
		
		marsButton.setSelected(true); // Start the game with the Mars Planet button selected
		
		// Add the buttons to the button group
		menuButtons.add(marsButton);
		menuButtons.add(saturnButton);
		menuButtons.add(titanSaturn);
		
		// Set background color of radio buttons
		marsButton.setBackground(Color.GRAY);
		saturnButton.setBackground(Color.GRAY);
		titanSaturn.setBackground(Color.GRAY);
		
		// Set panel background color
		setBackground(Color.GRAY);
		
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1) ); // Apply a boarder to the game menu panel
		
	}
	
	/**
	 * Updates the game menu to display the "Game Over" text.
	 */
	public void endGame() {
		// Hide the gameplay buttons
		marsButton.setVisible(false);
		saturnButton.setVisible(false);
		titanSaturn.setVisible(false);
		// Display "Game Over" text
		gameOver.setText("GAME OVER");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public static void main(String[] args) {

	}

}
