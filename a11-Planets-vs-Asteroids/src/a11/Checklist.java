package a11;

public class Checklist {
	// A blank class to use as a checklist
}


/***** CHECKLIST *****
 * [X] Build the Asteroid Super-Class 
 * [X] Build the Planet Super-Class
 * [X] Build the Basic Asteroid Sub-Class
 * [X] Build the Alkali Asteroid Sub-Class
 * [X] Build menu at top of game window
 * 		[X] Add buttons to the menu
 * 		[X] Make it so that I can only select one button at a time
 * 		[X] Build out the action listener class in the GameMenu class
 * [X] Add the ability for players to select which planet to place
 * [X] Change background image
 * 		[X] Draw new background in paint or piskel - must be PNG file type
 * 		[X] Set up the ActorDisplay class to read the file in and set it as the background
 * [N/A] Ask on Piazza if I should delete the unused mouse listener methods that were auto-implemented
 * 
 * [X] Make a separate Saturn planet object that is a bit stronger than the Mars object - maybe give it some other ability,
 * 		but stick with the basic decreased damage rate for now. Maybe make it so that it isn't affected by Alkali Asteroids.
 * [X] Check to see if I really need the SpriteType type parameter in the changeHealth methods - the method's parent class is Actor
 * 		- Yes, I do need it.
 * 
 * [X] Move the code that makes Saturn impervious to Alkali Asteroids to the Saturn class - it should override the changeHealth or actOn methods
 * 		(or whichever method is relevant those are just a guess) that it inherits from the Planet, Actor, or Sprite class to not be triggered
 * 		if the asteroid type is Alkali.
 * 
 * [N/A] Add levels to the game******************
 * 		- Make some functionality in ActorDisplay.step to keep track of the game level - will need a constant and a counter I think
 * 		- For this part set the Game.addAsteroid method up to accept a SpriteType.
 * 		- Then, change the method that calls it (I think that method is in ActorDisplay.step) to send over the correct type of sprite depending
 * 			on the level of the game.
 * 		- Add a component to the GameMenu to track the game level
 * 			- Also might want to add a label to the planet selection buttons group that says something like "Planets"
 * 		- Then add a piece to the level manager that creates Alkali Asteroids
 * 
 * [X] Figure out why the planets on the right side of the screen aren't taking damage when they are hit
 * 		- Issue was that the cooldown method was running and preventing the asteroids from taking an action until they had been on the screen for
 * 		a certain period of time. This made it look like the issue was their location, but it was actually a temporal issue.
 * 
 * [X] If time permits, add the flaming planets in. Any planet hit by an Alkali Asteroid should change to the flaming planet image - except Saturn and Titan Saturn, so just Mars.
 * 
 * [o] Adjust the game grid so that it lines up with the game cells better
 * [X] Maybe adjust the rate at which asteroids span upward a bit - the game isn't exactly challenging the way it is
 * 
 * [X] Remove all unused methods and code that I don't need anymore
 * 		[X] Actor
 * 		[X] ActorDisplay
 * 		[X] AlkaliAsteroid
 * 		[X] Asteroid
 * 		[X] FlamingMars
 * 		[X] Game
 * 		[X] GameMenu
 * 		[X] Planet
 * 		[X] Saturn
 * 		[X] Sprite
 * 		[X] SpriteType
 * 		[X] TitanSaturn
 * 
 * [X] *** READ ALL OF THE COMMENTS AND CODE IN THE ENTIRE GAME! -  be sure that I got everything taken care of! *** [ ]
 * 		[X] Add Javadocs to everything that needs them
 *  		[X] Actor
 * 			[X] ActorDisplay
 * 			[X] AlkaliAsteroid
 * 			[X] Asteroid
 * 			[X] FlamingMars
 * 			[X] Game
 * 			[X] GameMenu
 * 			[X] Planet
 * 			[X] Saturn
 * 			[X] Sprite
 * 			[X] SpriteType
 * 			[X] TitanSaturn
 * 		[X] Add in-line comments anywhere they are needed
 * 			[X] Actor
 * 			[X] ActorDisplay
 * 			[X] AlkaliAsteroid
 * 			[X] Asteroid
 * 			[X] FlamingMars
 * 			[X] Game
 * 			[X] GameMenu
 * 			[X] Planet
 * 			[X] Saturn
 * 			[X] Sprite
 * 			[X] SpriteType
 * 			[X] TitanSaturn
 * 
 * [X] Do a search of each and every file for the game and change all words of "zombie" or "plant" to "asteroid" or "planet"
 * 		- Including in the comments and Javadocs - EVERYTHING!
 * 		[X] Actor
 * 		[X] ActorDisplay
 * 		[X] AlkaliAsteroid
 * 		[X] Asteroid
 * 		[X] FlamingMars
 * 		[X] Game
 * 		[X] GameMenu
 * 		[X] Planet
 * 		[X] Saturn
 * 		[X] Sprite
 * 		[X] SpriteType
 * 		[X] TitanSaturn
 * [X] Remove all sysout lines from ALL files - conduct a ctrl + f
 * 		[X] Actor
 * 		[X] ActorDisplay
 * 		[X] AlkaliAsteroid
 * 		[X] Asteroid
 * 		[X] FlamingMars
 * 		[X] Game
 * 		[X] GameMenu
 * 		[X] Planet
 * 		[X] Saturn
 * 		[X] Sprite
 * 		[X] SpriteType
 * 		[X] TitanSaturn
 * [X] Remove all TODO lines from ALL files - conduct a ctrl + f
 * 		[X] Actor
 * 		[X] ActorDisplay
 * 		[X] AlkaliAsteroid
 * 		[X] Asteroid
 * 		[X] FlamingMars
 * 		[X] Game
 * 		[X] GameMenu
 * 		[X] Planet
 * 		[X] Saturn
 * 		[X] Sprite
 * 		[X] SpriteType
 * 		[X] TitanSaturn
 * 
 * [X] Check the class Javadocs of ALL classes - see if I need to date and sign them - also add the course information
 * 
 * [X] Record a video demonstration of the game as per J's instructions
 * 		- Demonstrate sprite placement
 * 		- Demonstrate different sprite types: Mars, Saturn, & Titan Saturn
 * 		- Show & explain the two different Asteroid types: Basic & Alkali
 * 		- Demonstrate the "Game Over" situation
 * 
 * [ ] Archive this file somehow or turn it into a README that goes with the game - I should start taking better notes in these checklists
 * 		and then keep the checklist after I'm done with it.
 * 
 * [ ] Upload this game to GitHub - include a README that briefly explains the project and credits some of the code to J.
 * 
 */