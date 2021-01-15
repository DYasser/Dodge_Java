# Dodge_Java
An update of the [first version](https://github.com/DYasser/Dodge_Python) in Python. Featuring different level of difficulties and more obstacles, plus the ability to slow down time and new scoring system.

## Execution:
First screen that appears with running the program is the main menu screen

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/mainMenu.png)

As we can see there are 3 options:
- [ ] Play the game
- [ ] Look at Options
- [ ] Quit the game

Let's start explaining the most obvious one, **Quit** which is as the name says a button to quit the game.

- [ ] Play the game
- [ ] Look at Options
- [X] Quit the game

Let's continue with Options

### Options:

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/optionScreen.png)

Here we can switch the game mode from windowed to fullscreen and adjust the game volume. We can also see *about* the game.

![mainMenu](https://github.com/DYasser/Dodge_Java/blob/master/images/help.png)

Here we can see all the controls as a help menu.

- [ ] Play the game
- [X] Look at Options
- [X] Quit the game

Now let's see how the game actually looks like.
### Gameplay:

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
