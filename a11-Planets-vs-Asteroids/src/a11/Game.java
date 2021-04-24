package a11;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A top-level panel for playing a game similar to planets Vs asteroids.
 * 
 * This panel is primarily responsible for coordinating the various
 * aspects of the game, including:
 * - Running the game step-by-step using a timer
 * - Creating and displaying other components that make up the game
 * - Creating new planets and/or asteroids, when necessary
 * - Checking for the end of the game
 * 
 * (Not all of the above behavior is provided in the starter code)
 * 
 * @author Travis Martin and David Johnson
 */
@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener {
	private static final int NUM_ROWS = 5;
	private static final int NUM_COLS = 7;
	private static final int GRID_BUFFER_PIXELS = 20;
	private static final int CELL_SIZE = 75;
	private static final int STEP_TIME = 30;
	private static final int PLANET_ATTACK_DAMAGE = 50;
	private static final int ASTEROID_ATTACK_DAMAGE = 25;
	
	private GameMenu gameMenu = new GameMenu();
	
	Timer timer;
	
	/**
	 * This panel is responsible for displaying planets
	 * and asteroids, and for managing their interactions.
	 */
	private ActorDisplay actorDisplay = new ActorDisplay(
	        NUM_COLS * CELL_SIZE + GRID_BUFFER_PIXELS * 2,
	        NUM_ROWS * CELL_SIZE + GRID_BUFFER_PIXELS * 2,
	        gameMenu, this, "src/a11/Planet-Icons/Background Grid 1.png");

	private Game() {
		add(gameMenu);
	    add(actorDisplay);
	    
	    // This layout causes all elements to be stacked vertically
	    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	    
		// The timer calls the actionPerformed method every STEP_TIME milliseconds
		this.timer = new Timer(STEP_TIME, this);
		timer.start();
		
	}

	/**
	 * Executes game logic every time the timer ticks.
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
        actorDisplay.step();
        Random rand = new Random();
        if (rand.nextInt(100) > 98) {
            addAsteroid(NUM_COLS - 1, rand.nextInt(NUM_ROWS));
        }
    }
	
    /**
     * Adds a planet to the official game grid & display panel.
     * 
     * @param col The column that the new planet should be added to
     * @param row The row that the new planet should be added to
     * @param type The type of sprite to be added
     */
	public void addPlanet(int col, int row, SpriteType type) {
	    // The magic numbers below define various hardcoded planet properties
		switch (type) {
		case MARS:
	        actorDisplay.addActor(new Planet(
	                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
	                "src/a11/Planet-Icons/Mars.png", 100, 5, PLANET_ATTACK_DAMAGE, this));
//	        System.out.println("DOS");
			break;
		case SATURN:
	        actorDisplay.addActor(new Saturn(
	                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
	                "src/a11/Planet-Icons/Saturn.png", 100, 5, PLANET_ATTACK_DAMAGE, this));
			break;
		case TITAN_SATURN:
	        actorDisplay.addActor(new TitanSaturn(
	                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
	                "src/a11/Planet-Icons/Titan Saturn.png", 100, 5, PLANET_ATTACK_DAMAGE, this));
			break;
		}
	}
	
    /**
     * Adds an asteroid to the official game grid & display panel.
     * 
     * @param col The colum to which the asteroid will be added
     * @param row The row to which the asteroid will be added
     */
	public void addAsteroid(int col, int row) {

		Random numGenerator = new Random();
		int selection = numGenerator.nextInt(3);
		
		// The magic numbers below define various hardcoded asteroid properties	
		if (selection == 0 || selection == 2) { // Generate Alkali Asteroid
	        actorDisplay.addActor(new AlkaliAsteroid(
	                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
					"src/a11/Planet-Icons/Alkali Asteroid 2.png",
					100, 0, -2, ASTEROID_ATTACK_DAMAGE));
		} else { // Generate Basic Asteroid
			actorDisplay.addActor(new Asteroid(
	                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
					"src/a11/Planet-Icons/Basic Asteroid.png",
					100, 0, -2, ASTEROID_ATTACK_DAMAGE));
		}
	}
	
	/**
	 * Converts a row or column to its exact pixel location in the grid.
	 */
	private int gridToPixel(int rowOrCol) {
	    return rowOrCol * CELL_SIZE + GRID_BUFFER_PIXELS;
	}
	
	/**
	 * The inverse of gridToPixel
	 */
	public int pixelToGrid(int xOrY) {
	    return (xOrY - GRID_BUFFER_PIXELS) / CELL_SIZE;
	}
	
	/**
	 * Returns the planet attack damage constant 
	 * @return the constant PLANET_ATTACK_DAMAGE
	 */
	public int getPlanetAttackDamage() {
		return PLANET_ATTACK_DAMAGE;
	}
	
	/**
	 * Returns the asteroid attack damage constant
	 * @return the constant ASTEROID_ATTACK_DAMAGE
	 */
	public int getAsteroidAttackDamage() {
		return ASTEROID_ATTACK_DAMAGE;
	}
	
	/**
	 * Create, start, and run the game.
	 */
	public static void main(String[] args) {
        JFrame app = new JFrame("planet and asteroid Test");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.add(new Game());
        app.pack();
        app.setVisible(true);
	}
}