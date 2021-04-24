package a11;

import java.util.ArrayList;

/**
 * Creates a new Flaming Mars type Planet. Flaming Mars planets are created when a basic planet type is hit by an Alkali Asteroid.
 * Flaming Mars planets will take recurring damage until they are destroyed. The removeAction method is overridden here to prevent
 * a second Alkali Asteroid impact from resetting this planet type back to a Flaming Mars type and resetting its health.
 * 
 * @author Chris Marston
 * 
 * CS 1410 - a11 - April 2021
 *
 */
public class FlamingMars extends Planet {

	public FlamingMars(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown,
			int attackDamage, Game game) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, attackDamage, game);
		
		this.setRecurringDamage(true); // This planet type always has recurring damage
	}
	
	/**
	 * Sets the health of the new Planet and overrides the changeHealth method to prevent
	 * flaming planets from catching fire a second time.
	 */
	@Override
	protected void changeHealth(int change, SpriteType type) {
		setHealth(this.getHealth() + change); // Sets the health of the new, likely damaged, Planet
	}
	
	/**
	 * Overrides the removeAction method to prevent this planet from being replaced when hit by an Alkali Asteroid.
	 */
	@Override
	public void removeAction(ArrayList<Actor> others) {
		// Do nothing
	}
	
	public static void main(String[] args) {

	}

}
