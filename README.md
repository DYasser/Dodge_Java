# Dodge_Java
An updated version of the [first version](https://github.com/DYasser/Dodge_Python) in Python. Featuring: different level of difficulties, more obstacles, the ability to slow down time, and a new scoring system.

## Execution:
* First screen that appears when running the program is the **Main Menu** screen

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/mainMenu.png)

As we can see there are 3 options:
- [ ] Play the game
- [ ] Look at Options
- [ ] Quit the game

Since the last option "**Quit**" is (hopefully) obvious for everyone, evidently it allows the user to exit the program, I don't need to explain it.
So, we are left with only two options:

- [ ] Play the game
- [ ] Look at Options
- [X] Quit the game

Let's see what options the program have to present to us: 

* ### Options:

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/optionScreen.png)

In this screen, we are able to change the level of the volume by clicking on the *plus* sign to higher the volume or the *minus* sign to lower it instead.

We also have the choice to play the game *windowed* (meaning that the program will appear on a window with the possibility to resize the window, minimize it or even close it with he **X**) or *full screen* (the game will be displayed on the fullest ability of the screen used by the user). To be able to switch between those two modes the user only has to click on the button labelled whatever mode he wants. 

We can also have more information *about* the game by clicking on the button top right labeled "about".


* After clicking on the **About** button, we get to see this next screen:

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/help.png)

In this screen, the user is able to see all different controls he/she will be using, such as: moving right and left, running/ sprinting, jumping, using the slow motion, pausing the game, and toggle fullscreen/ windowed mode only using the keyboard.

The user is also able to understand the goal of the game which is really simple, by reading the section explaining the goal.

The user is also able to go back one page, or directly to the main menu by clicking on the right buttons top left.



- [ ] Play the game
- [X] Look at Options
- [X] Quit the game

Finally, I can show you the most interesting part of the game which is to actually **play the game**.
* ### Gameplay:

After clicking on the "Play the game" button, you are prompted to choose one of the three available difficulties: Easy, Normal, or Hard.

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/difficultyScreen.png)

After picking a difficulty, the game starts. The player is giving a 3 second counter to not surprise him and get him all the preparation needed before he/she starts playing.

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/gameStart.png)

The game is all about dodging. Here I am going to introduce all the enemies to our player:

* The **snakes** (green rectangles): These entities keep slithering on the ground and change directions each time they reach the extremities of the map. When they touch the player, this last one takes damage and his health decreases, he gets few seconds of invulnerability in order to get himself placed somewhere safe. There is a particularity about this entities, which is each time the user gets hit by them they get removed from the map and a new snake spawns randomly, but not only that, a meteor (presented later) also gets added to the initial count.

* The **white meteors**: These entities keep on falling from the sky with a random speed (from a specific range that I programed) and with a random tilt angle (that I also put a specific range for). These entities also have the same characteristic as the snakes, each time you hit them a new snake gets placed down on the ground.

* The **big white ball**: It is possible to see it in the middle of the screen, it keeps moving up and down. This enemy deals a great amount of damage compared to the two others I presented. This entity is easier to avoid since the trajectory isn't random.

* The **red lava**: A carpet-like entity that is situated under the plateforms at the beginning left and right. These obstacles deal less damage and keep appearing and disappearing, so you better keep focused around them!

* The **laser**: The most dangerous enemy out of all the ones mentioned already. This entity pops out of nowhere from time to time dealing lots of damage. It is warned before its appearance in the map with a vertical line and a timer.


In my opinion the most annoying enemies will probably be the meteors and the snakes, since these two are related. When the player gets hit by a snake a meteor gets added to the 5 initial ones. Same thing for meteors with snakes, which are 2 at the beginning.

The player has the ability to sprint but can also slow down time by using Q. All these commands can be check in the middle of the game by prompting the pause menu.

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/pauseScreen.png)

Finally, after trying his/her best, the game finally ends on a Game Over screen that shows them the score gotten and the max score that anyone has achieved yet (different for each difficulty)

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/gameOverScreen.png)

- [X] Play the game
- [X] Look at Options
- [X] Quit the game

Now that I am done explaining the player's perspective and explaining most of the logic, we can move on to explaining how I coded the logic of this game.


## The Code Logic:

I coded the game using Java, but the logic stays the same for most languages. In some cases, there might be better options for other languages with broader libraries.

I started by creating a game thread that will be leading the whole game. I created a game loop that will keep looping and updating everything needed for the user to be able to play the game smoothly.

In this section I will explain four main parts that I think are most important when talking about the process of coding this program. 

* Collisions: 
Collisions are the most important characteristic inside this game. Collisions are what allows all the entities to talk and communicate to each other. We can see two main collisions here: Block Collision such as the platforms that allows the user to step on when collided, and Damage Collision that occurs when the user hits an enemy.

Collisions work using the coordinates of two points in each entity. So, when an entity enters another we can know that by substracting the value of one entity and help us with its width or heigth to know if an entity collided with another one. The picture below demonstrates what I explained here. 

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/collisions.png)

After calculating these collisions, the program needs to be able to do so each time every millisecond. This is where the *Handler* shows its importance. The handler's job is easy, yet hard. He needs to update each entity added to him every millisecond.

By updating every entity, he is updating the new values but also the rendering since the user needs to have a graphical return to understand what is happening.

Now that I explained how I dealed with collisions we can move to another important aspect.

* Gravity:
In 
