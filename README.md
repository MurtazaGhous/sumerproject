# sumerproject
scc110 summer project


How to play: 

The paddle on the right can be moved using the arrow keys, the paddles on the left can be moved using the WASD keys.

The first player to reach the score of 6 wins.

Press Y to play the game again once one of the player reaches 6

Press M to mute/unmute the audio 

Further info:

This is a simple airhockey game made in java. The classes used are as follow:

AirHockey.java : this is the class with the main method. It sets up the arena pannel using the GameArena class and the aihockey table using the Table class. It then initiaties the gamelogic class allowing the paddles and puck to be added and the satrt the game loop

GameLogic.java: This class handles the game controls, collision and deflection of the puck and paddles, the goal detection and score counter, the audio to be played at certain moments using the SoundManager class and allows the player to play the game again once one of the player reaches the score of 6

SoundManager.java: This class is used to play the audio files as intructed by the GameLogic class as well as allowing the user to mute/unmute the audio. 

All the other classes and files were provided and used throught the classes described above. 



