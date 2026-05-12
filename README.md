# README #

## Individual Game Project ##

**Shrestha Nayak**

**Period:**	1

**Game Title:** BreakQuest

## Game Proposal ##

Write a paragraph here describing the game you want to make.  Describe how it is played and which features
you want to include in your game.  Remember, any simple game can be scaled up with features and any complex
game can be scaled down.

**Game Controls:**

+ Space: Starts the Breakout Game
+ Arrow Keys: Moves the paddle left or right
+ Cursor: Moves the paddle left or right

**Game Elements:**

+ A Ball moving around the screen to hit blocks
+ A Paddle that hits ball to control where to move it
+ Different Types of Bricks to hit (or not):
	+ *Should Hit*
    + Power Ups
    + Multi-Hit Blocks
    + Bomb Blocks that destroy blocks around it
  + *Shouldn't Hit*
    + Unbreakable Blocks that “eat” the block
    + “Bad” blocks that try to make the ball hit the ground
+ Extra Features:
  + 5 Levels with new block types unlocked each round
  + After hitting 5 Blocks in a row without loosing a life, block is "on fire" and breaks 2-3 blocks at a time the next hit


**How to Win:**

+ Break all the blocks for all 5 Levels under the following conditions:
  + 3 Lives
  + +100 for Hitting "Good" Bricks
  + -100 for Hitting "Bad" Bricks

## Link Examples ##
+ (https://share.google/0Z4LqZ7MCvKsrwTDl)

## Teacher Response ##

Your teacher can add comments and suggestions here

## Class Design and Brainstorm ##

+ **BreakoutGame** - Main application class that launches the JavaFX game and creates the Scene.
+ **BallWorld** - extends World. Controls the game loop, level setup, score, lives, and win/loss conditions.
+ **Ball** - extends Actor. Moves around the screen, bounces off walls, paddle, and bricks.
+ **Paddle** - extends Actor. Controlled by keyboard and/or mouse to hit the ball.
+ **Brick** - extends Actor. Base class for all brick types. Handles health, collisions, scoring, and destruction.
+ **NormalBrick** - extends Brick. A standard one-hit brick.
+ **StrongBrick** - extends Brick. Requires multiple hits before breaking.
+ **BombBrick** - extends Brick. Explodes and destroys nearby bricks when broken.
+ **SteelBrick** - extends Brick. Cannot be destroyed and reflects the ball.
+ **BadBrick** - extends Brick. Removes points or negatively affects the player when hit.
+ **PowerBrick** - extends Brick. Spawns a power-up when destroyed.
+ **PowerUp** - extends Actor. Falls downward and grants abilities when collected.
