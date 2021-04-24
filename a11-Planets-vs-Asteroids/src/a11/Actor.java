package a11;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * The actor class extends sprite to add more player character capabilities.
 * This class adds health, an attack strength, and a cool down counter for 
 * activities that should not happen every frame.
 * @author dejohnso
 * 
 * Notes: main properties of an actor are that it can
 * - Attack (defend).
 * 
 */
public abstract class Actor extends Sprite {

    // For descriptions of these variables, see the constructor below
	private int health;
	private int fullHealth;
	private int coolDownCounter;
	private int coolDown;
	private int speed;
	protected int attackDamage;
	protected boolean recurringDamage;
	
	/**
	 * Constructs a new Actor with all of its essential properties.
	 * 
	 * @param xPosition the starting x pixel (0 is the left)
	 * @param yPosition the starting y pixel (0 is the top)
	 * @param size the initial dimensions of this actor (width and height)
	 * @param imgPath a path to the image file for this actor's picture
	 * @param health the max (and starting) health of this actor
	 * @param coolDown the amount of turns this actor needs to wait between
	 *                 attacks or other effects
	 * @param speed the number of pixels this moves per turn,
	 *              positive values move left
	 * @param attackDamage the amount of health this deducts from enemies
	 *                     per attack
	 * @param recurringDamage Whether or not the actor is affected by the
	 * 					recurring damage effect of asteroids
	 */
	public Actor(int xPosition, int yPosition, int size, String imgPath,
	        int health, int coolDown, int speed, int attackDamage) {
		
		super(xPosition, yPosition, size, imgPath);
		this.health = health;
		this.fullHealth = health;
		this.coolDownCounter = coolDown;
		this.coolDown = coolDown;
		this.speed = speed;
		this.attackDamage = attackDamage;
		this.recurringDamage = false;
	}

	/**
	 * Move this object horizontally. This method should be called
	 * in turns where this actor is allowed to move, i.e. it should
	 * not be colliding with something else.
	 */
	public void move() {
		shiftPosition(speed, 0);
	}
	
	/**
	 * Update the internal state of the Actor each turn. This
	 * mainly involves decrementing the cooldown counter.
	 */
	public void update() {
	    coolDownCounter--;
	}
	
	/**
	 * Returns whether the Actor still has some health left.
	 */
	public boolean isAlive() {
		return health > 0;
	}
	
	/**
	 * Takes any (optional) actions that happen when this Actor is dead.
	 * @param others all other actors that this actors might take action on.
	 */
	public void removeAction(ArrayList<Actor> others) {
	    // By default, an actor does nothing when removed,
	    // but this can be overridden
	}
	
	/**
	 * Draws this sprite, plus a health feedback with a health status bar.
	 * @param g the Graphics context to draw onto (i.e. this panel)
	 */
	@Override
	public void draw(Graphics g) {
	    super.draw(g);
	    
	    g.setColor(Color.BLACK);
	}
	
	/**
	 * If the cooldown counter hits 0, the Actor is ready to do something.
	 */
	protected boolean isReadyForAction() {
		return coolDownCounter <= 0;
	}
	
	/**
	 * Reset the cool down counter to its starting value.
	 */
	protected void resetCoolDown() {
		coolDownCounter = coolDown;
	}
		
	/**
	 * Modify the health by change value.
	 * @param change The amount by which to adjust the heatlth of the actor
	 */
	protected void changeHealth(int change, SpriteType type) {
		health += change;
	}
	
	/**
	 * Returns the health of the actor.
	 * @return health The health of the actor
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns the full health of the actor.
	 * @return fullHealth The full health of the actor
	 */
	public int getFullHealth() {
		return fullHealth;
	}
	
	/**
	 * Sets the health of the actor
	 * @param health The int value to set the health of the actor to
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Returns the SpriteType (Enum) of the actor.
	 * @return SpriteType The SpriteType of the actor
	 */
	public SpriteType getType() {
		// Evaluates the sprite and returns the appropriate SpriteType Enum value
		if (this instanceof Planet) {
			if (this instanceof Saturn) {
				return SpriteType.SATURN;
			} else if (this instanceof TitanSaturn) {
				return SpriteType.TITAN_SATURN;
			} else {
				return SpriteType.MARS;
			}
		} else {
			if (this instanceof AlkaliAsteroid) {
				return SpriteType.ALKALI_ASTEROID;
			} else {
				return SpriteType.BASIC_ASTEROID;
			}
		}
	
	}
	
	/**
	 * Allows this actor to potentially act on another actor.
	 * 
	 * This should only happen if:
	 * - Other is close enough to this actor. By default they must be touching.
	 * - This actor is ready to act again, based on its cooldown.
	 * - This actor can act on whatever type other has.
	 */
	public void actOn(Actor other) {
	    if (other instanceof Planet)
	        actOn((Planet) other);
	    else if (other instanceof Asteroid) {
	        actOn((Asteroid) other);
	    }
	}
	
	/**
	 * This method should be overidden in subclasses to specify
	 * planet-specific actions.
	 */
	protected abstract void actOn(Planet other);
	
	/**
	 * This method should be overidden in subclasses to specify
	 * asteroid-specific actions.
	 */
	protected abstract void actOn(Asteroid other);
	
	/**
	 * Sets the recurring damage attribute of the sprite
	 * @param recurringDamage The boolean value to set the recurringDamage attribute to
	 */
	protected void setRecurringDamage(boolean recurringDamage) {
		this.recurringDamage = recurringDamage;
	}
	
	/**
	 * Returns the recurringDamage attribute of the actor.
	 * @return recurringDamage The boolean value held in the recurringDamage attribute
	 */
	public boolean getRecurringDamage() {
		return recurringDamage;
	}
}
