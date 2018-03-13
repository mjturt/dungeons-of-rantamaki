![Dundeons of Rantamaki logo](/images/logo.png?raw=true "Dungeons of Räntämäki")

Small cross-platform 2D RPG-game written in pure java, without any game engine. Rendering is made using java AWT and Swing libraries. Originally made as an excercise job for university programming course, OOP 2018.

Dungeons of Räntämäki is game where you have to find your way out of maze like Räntämäki. The player faces many enemies from which an RPG-type battle window appears. Game ends when you find yellow Turku bus or when player dies.

Räntämäki is a small district of Turku, Finland.

Screenshot
------------------
![Screenshot](/images/screenshots/game1.png?raw=true "Screenshot")

Requirements
------------------
- Java 7 or later

Running the game
------------------
```
git clone https://github.com/mjturt/dungeons-of-rantamaki.git
cd dungeons-of-rantamaki
java -jar Dungeons-of-Rantamaki.jar
```
When saving, game creates game.sav file in .rantamaki directory under home directory

Controls
------------------
In menus and when combat window appears navigation is done with the mouse.
In game controlling of the character is done with arrow keys. Esc for pause menu.

Authors
------------------
##### Programming
- Maks Turtiainen
- Vili Ahava
- Mikko Malkavaara
##### Graphics
- Maks Turtiainen
##### Packaging
- Maks Turtiainen
### External assets
- Music by DL-sounds
- Fonts

Known issues
------------------
- When losing battle and player dies, "New Game"-button does not work
- On UNIX-like systems pause menu does not work correctly

---
License: BSD 2-Clause
