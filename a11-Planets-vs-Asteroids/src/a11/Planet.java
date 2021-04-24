package a11;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This class creates the Mars planet type, which is the base type for all other planets. It takes damage when impacted by asteroids
 * and catches fire and takes recurring damage when impacted by alkali type asteroids.
 * 
 * To make the planet catch fire, the removeAction method was overridden to create a new Flaming Mars planet type to replace the extant
 * basic/Mars planet type if the impacting asteroid is of alkali type. The changeHealth method was also overridden to set the health of
 * the extant basic/Mars planet type to zero so that it will be removed during the next game cycle. Some of the functionality for this
 * is in the ActorDisplay class, such as the check for the impacting asteroid type, although most of the functionality is in this class.
 */
public class Planet extends Actor {
	
	protected Game game;
	
    /**
     * Creates a Planet. For parameter descriptions, see Actor.
     */
	public Planet(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown, int attackDamage, Game game) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, 0, attackDamage);
		
		this.game = game;
	}

	/**
	 * An attack only happens when two hitboxes are overlapping and the
	 * Planet is ready to attack again (based on its cooldown).
	 * 
	 * Planets only attack Asteroids.
	 * 
	 * @param other Another sprite object
	 */
	@Override
	public void actOn(Asteroid other) {
		// Do nothing
	}
	
	@Override
	public void actOn(Planet other) {
	    // Do nothing
	}
	
	/**
	 * Draws the sprite object and its health bar.
	 * 
	 * @param g The graphics object
	 */
	@Override
	public void draw(Graphics g) {
	    super.draw(g);
	    
	    g.setColor(Color.BLACK);
    	g.drawRect(getXPosition(), getYPosition() - 5, getSize(), 5);  
	    g.setColor(Color.RED);  
		g.fillRect(getXPosition(), getYPosition() - 5, getSize() * super.getHealth() / super.getFullHealth(), 5);	
	}
	
	/**
	 * Overrides the changeHealth method from the Actor class to cause Planets to catch fire when hit by an Alkali Asteroid.
	 * This method can be overridden in sub-classes to prevent them from catching fire, such as in Saturn and Titan Saturn.
	 * 
	 * @param change The amount by which to change the health of the Planet
	 * @param type The type of sprite acting on the Planet
	 */
	@Override
	protected void changeHealth(int change, SpriteType type) {
		if (type == SpriteType.ALKALI_ASTEROID) { // This is only triggered if the affected planet is BOTH hit by an Alkali Asteroid and hasn't been hit already (i.e. Already has recurring damage)
			setHealth(this.getHealth() - this.getHealth()); // Zero out the health of the planet so that it will be removed during the next cycle
		} else {
			setHealth(this.getHealth() + change);
		}
	}
	
	/**
	 * This method overrides the removeAction method from Actor to swap the old planet with a new one when it is hit by an
	 * Alkali Asteroid so as to add the flaming planet effect.
	 * 
	 * @param others An ArrayList of all sprites currently in the game
	 */
	@Override
	public void removeAction(ArrayList<Actor> others) {
		
		Planet tempPlanet = new FlamingMars(
				this.getXPosition(), this.getYPosition(), this.getSize(),
				"src/a11/Planet-Icons/Flaming Mars.png",
				this.getFullHealth(), 5, game.getPlanetAttackDamage(), game);
		
		tempPlanet.setHealth(tempPlanet.getHealth() - game.getAsteroidAttackDamage()); // Damage the planet due to impact

		others.add(tempPlanet); // Add the new planet to the list of actors
	}
}
