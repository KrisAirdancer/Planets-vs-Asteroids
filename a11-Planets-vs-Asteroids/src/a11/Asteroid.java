package a11;

/**
 * Creates the basic type of asteroid that simply does damage upon impacting a planet.
 */
public class Asteroid extends Actor {
    
    /**
     * Creates an asteroid. For parameter descriptions, see Actor.
     */
	public Asteroid(int xPosition, int yPosition, int size, String imgPath, int health,
	        int coolDown, int speed, int attackDamage) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, speed, attackDamage);
	}
	
	/**
	 * An attack only happens when two hitboxes are overlapping and the
	 * asteroid is ready to attack again (based on its cooldown).
	 * 
	 * Asteroids only attack planets.
	 */
	@Override
	public void actOn(Planet other) {
		if (isColliding(other)) {
			if (isReadyForAction()) {
				other.changeHealth(-attackDamage, other.getType());
				resetCoolDown();
			}
		}
	}
	
	@Override
	public void actOn(Asteroid other) {
	    // Do nothing
	}
	
	/**
	 * Overrides the sprite logic to allow asteroids to overlap
	 * with other asteroids.
	 */
	@Override
	public boolean isColliding(Sprite other) {
	    return !(other instanceof Asteroid) && super.isColliding(other);
	}
	
	@Override
	public void update() {
		// Do nothing
	}
}
