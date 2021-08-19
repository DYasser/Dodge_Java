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

You first choose what difficulty you want to choose then the game starts:

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/gameStart.png)

The game is all about dodge, hence the name. There are 3 main entities that the player should try to avoid at all costs, the **green dots** (snakes), the **white meteors** that keep falling from the sky and the **big white ball** in the middle of the screen that keeps moving up and down.
There are other entities such as the *red lava* carpet under the plateforms at the beginning left and right. And the final entity which is a random *laser* that pops out of nowhere from time to time dealing lots of damage. Each entity deals an amount of damage with some having another effect on the game.
The most anoying enemies will probably be the meteors and the snakes, since these two are related. When the player gets hit by a snake a meteor gets added to the 5 initial ones. Same thing for meteors with snakes, which are 2 at the beginning.

The player has the ability to sprint but can also slow down time by using Q. All these commands can be check in the middle of the game by prompting the pause menu.

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/pauseScreen.png)

Finally, after trying your best the game finally ends by a gameover screen showing you the score you got and the max score that anyone has done yet (different for each difficulty)

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/gameOverScreen.png)

- [X] Play the game
- [X] Look at Options
- [X] Quit the game
