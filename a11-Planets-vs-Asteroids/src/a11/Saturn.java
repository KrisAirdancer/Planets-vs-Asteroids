package a11;

import java.util.ArrayList;

/**
 * Creates a new Saturn type Planet. Saturn type planets are immune to the effects of Alkali Asteroids (they won't take recurring damage
 * when hit by an Alkali Asteroid). This is accomplished by overriding the recurringDamage method from the Planet class to prevent the
 * Saturn type planet from taking recurring damage. The removeAction method is also overridden to ensure that Saturn type planets
 * don't catch fire when hit by Alkali Asteroids.
 * 
 * @author Chris Marston
 * 
 * CS 1410 - a11 - April 2021
 *
 */
public class Saturn extends Planet {

	public Saturn(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown, int attackDamage,
			Game game) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, attackDamage, game);
		
		
	}
	
	/**
	 *  Overrides the setRecurringDamage method from Actor to prevent Alkali Asteroids from damaging Saturn type planets.
	 *  
	 *  @param recurringDamage The boolean value to which to set the recurringDamage variable
	 */
	@Override
	protected void setRecurringDamage(boolean recurringDamage) {
		// Do nothing - Saturn is not affected by the recurring effects of Alkali Asteroids
	}
	
	/**
	 * Overrides the changeHealth method to prevent Saturn type planets from catching fire - they are unaffected by Alkali Asteroids.
	 * 
	 * @param change The amount by which to change the Planet's health
	 * @param type The type of the sprite that is acting on this Planet object
	 */
	@Override
	protected void changeHealth(int change, SpriteType type) {
		setHealth(this.getHealth() + change); // Set health of new Planet, including any damage
	}
	
	/**
	 *  Overrides the removeAction method to prevent this planet from being replaced when hit by an Alkali Asteroid.
	 *  
	 *  @param others An ArrayList of all other actors currently in the game
	 */
	@Override
	public void removeAction(ArrayList<Actor> others) {
		// Do nothing
	}
	
	public static void main(String[] args) {

	}

}
