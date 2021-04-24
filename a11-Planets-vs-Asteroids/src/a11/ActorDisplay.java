package a11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ActorDisplay extends JPanel implements MouseListener {
	
	private GameMenu gameMenu;
	private Game game;
	private BufferedImage background;
	private int recurringDamageCount;
	private final int RECURRING_DAMAGE_CYCLE_MAX = 60;
	private final int RECURRING_DAMAGE = -10;
	
    /** Contains all planet and asteroids in this game. */
	private ArrayList<Actor> actors = new ArrayList<>();

	/**
	 * Creates a canvas upon which all actors will live.
	 * @param colPixels the number of pixels that this panel is wide
	 * @param rowPixels the number of pixels that this panel is high
	 * @param gameMenu The GameMenu object that creates the menu at the top of the game window
	 * @param game An instance of the Game class
	 * @param backgroundPath A string describing the location of the game's background image in on the computer
	 */
	public ActorDisplay(int colPixels, int rowPixels, GameMenu gameMenu, Game game, String backgroundPath) {
		// Making the game menu object avaliable in this class
		this.gameMenu = gameMenu;
		this.game = game;
		this.recurringDamageCount = 0;
		
		try { // Reading in the background image
			background = ImageIO.read(new File(backgroundPath));
		} catch (IOException e) {
			System.out.println("Could not load file: " + backgroundPath);
			System.exit(0);
		}
		
		setPreferredSize(new Dimension(colPixels, rowPixels));
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1) );
		
		// Adding mouse listener to panel
		addMouseListener(this);
	}
	
	/**
	 * Adds an actor to the master list of actors ONLY IF
	 * the provided actor is not colliding with any of the existing
	 * actors.
	 * @param actor the object to add
	 * @return false if something prevents the actor from being added, true otherwise
	 */
	public boolean addActor(Actor actor) {
	    if (actor.isCollidingAny(actors)) {
	        return false;
	    }
        actors.add(actor);
        return true;
	}

	/**
	 * This overrided method draws the details of this particular panel,
	 * including all actors that are contained within.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		for (Actor actor : actors) {
			actor.draw(g);
		}
	}

	/**
	 * Executes all of the actor logic that happens in one turn, including
	 * moving actors, checking for collisions, managing attacks, and more.
	 */
	public void step() {	
		
		// Increment actor cooldowns.
		for (Actor actor : actors) {
			actor.update();
		}

		/*
		 * Allow all actors to interact with all other actors. This is where attacks, healing, etc happen.
		 * Only allows Asteroids to act on Planets. Prohibits Planets from acting on Asteroids.
		 */
		for (Actor actor : actors) { // Checks for collisions by asteroids only to ensure that the asteroid acts on the planets first
			if (actor instanceof Asteroid) {
				for (Actor other : actors) {
					actor.actOn(other);
				}
			}
		}
		
		// Applies recurring damage to all sprites that require it
		for (Actor actor : actors) {
			if (actor.getRecurringDamage() && recurringDamageCount == RECURRING_DAMAGE_CYCLE_MAX) {
				actor.changeHealth(RECURRING_DAMAGE, SpriteType.ALKALI_ASTEROID);
			}
		}
		
		// Updates recurringDamageCount - applies recurring damage to affected planets every couple
		// of game cycles as specified by RECURRING_DAMAGE_CYCLE
		recurringDamageCount++;
		if (recurringDamageCount > RECURRING_DAMAGE_CYCLE_MAX) {
			recurringDamageCount = 0;
		}

		// Confirm the removal of any asteroids that have collided with a planet by setting their
		// health to zero so they aren't added back to the game during the next cycle.
		for (Actor actor : actors) {
			if (actor instanceof Asteroid) {
				if (actor.isCollidingAny(actors)) {
					actor.changeHealth(-actor.getHealth(), actor.getType());
				}
			}
		}
		
		// Remove planets and asteroids with low health
		ArrayList<Actor> nextTurnActors = new ArrayList<>();
		for (Actor actor : actors) {
			
			if (actor.isAlive()) {
				nextTurnActors.add(actor);
			} else {
				actor.removeAction(nextTurnActors); // Execute any special effects for dead actors
			}
		}
			
		actors = nextTurnActors; // Adds all alive actors to the actors array, effectively removing the dead actors

		// Move the (alive) actors that are not colliding.
		for (Actor actor : actors) {
		    if (!actor.isCollidingAny(actors)) {
		        actor.move();
		    }
		}
		
		// Checks if any sprite made it to the left edge of the screen, defeating the player
		for (Actor actor : actors) {
			if (actor.isCollidingLeftEdge()) {
				gameMenu.endGame(); // Adding a label to the game menu to indicate end of game
				
				game.timer.stop(); // Stopping the game
			}
		}
		
		// Redraw the scene.
		repaint();
	}
	
	/**
	 * Action listener to handle input from the clicking of the mouse.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		int convertedX = game.pixelToGrid(mouseX);
		int convertedY = game.pixelToGrid(mouseY);
		
		if (gameMenu.marsButton.isSelected()) { // If Mars radio button is selected
			// Call add method from Game class to add mars to selected space
				// Mouse location information should be gathered here
				// Will want to make a separate add method for each planet type - maybe...
			game.addPlanet(convertedX, convertedY, SpriteType.MARS);
			
		} else if (gameMenu.saturnButton.isSelected()) { // If Saturn radio button is selected
			game.addPlanet(convertedX, convertedY, SpriteType.SATURN);
			
		} else { // If Titan Saturn radio button is selected
			game.addPlanet(convertedX, convertedY, SpriteType.TITAN_SATURN);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Intentionally left blank
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Intentionally left blank
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Intentionally left blank
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Intentionally left blank
	}


	
}