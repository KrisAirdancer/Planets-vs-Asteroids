package a11;

import java.util.ArrayList;

/**
 * Creates a new Titan Saturn type Planet. Titan Saturns cannot take damage from asteroids. The damage is taken by Saturn's largest moon Titan.
 * Titan is destroyed when impacted by an asteroid. This causes the Titan Saturn to change to a normal Saturn type planet without taking any damage.
 * After the conversion, the new Saturn type planet behaves exactly like a normal Saturn type planet - can take damage but is immune to the effects 
 * of Alkali Asteroids.
 * 
 * To accomplish this, the removeAction method was overridden and modified to create a new Saturn type planet in the stead of this one. The changeHealth
 * method was also overridden to set the health of the extant Titan Saturn planet to zero so it will be removed during the next game cycle.
 * 
 * @author Chris Marston
 * 
 * CS 1410 - a11 - April 2021
 *
 */
public class TitanSaturn extends Planet{
	
	public TitanSaturn(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown,
			int attackDamage, Game game) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, attackDamage, game);
	}
	
	/**
	 * Overrides the removeAction method in the Actor class to change the planet type instead of doing nothing.
	 * 
	 * @param others An ArrayList of all actors currently in the game
	 */
	@Override
	public void removeAction(ArrayList<Actor> others) {
		
		others.add(new Saturn(
				this.getXPosition(), this.getYPosition(), this.getSize(),
				"src/a11/Planet-Icons/Saturn.png",
				100, 5, game.getPlanetAttackDamage(), super.game));
	}
	
	/**
	 * Sets the planet's health to zero to ensure that it is removed - overrides from the Actor class.
	 * 
	 * @param change The amount by which to change the Planet's health
	 * @param type The type of the sprite object acting on this Planet object
	 */
	@Override
	protected void changeHealth(int change, SpriteType type) {
		setHealth(this.getHealth() - this.getHealth()); // Zero out the health of the planet so that it will be removed during the next cycle
	}

	public static void main(String[] args) {

	}

}
