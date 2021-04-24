package a11;

/**
 * Creates a new Alkali Asteroid type Asteroid.
 * 
 * This Asteroid type contains highly volatile Alkali metals and ignites upon impact with a planet (not scientifically accurate).
 * To implement this in the game, the asteroid will do impact damage but will also set the planet to take recurring damage - the
 * planet is now on fire! The functionality of changing the planet to a flaming type is in the planet class.
 * 
 * @author Chris Marston
 * 
 * CS 1410 - a11 - April 2021
 *
 */
public class AlkaliAsteroid extends Asteroid {

	public AlkaliAsteroid(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown, int speed,
			int attackDamage) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, speed, attackDamage);

	}
	
	@Override
	public void actOn(Planet other) {
		if (isColliding(other)) {
			if (isReadyForAction()) {
				other.changeHealth(-attackDamage, this.getType()); // Adjust the health of other
				other.setRecurringDamage(true); // Set the planet to have recurring damage
				resetCoolDown();
			}
		}
	}

	public static void main(String[] args) {

	}

}
