package breakout;

import java.io.InputStream;
import java.util.Scanner;

import engine.Actor;
import engine.Sound;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class BallWorld extends World {

    private Paddle paddle;
    private Ball ball;
    private Score score;
    private Lives lives;
    private Text message;
    private int level;
    private boolean isPaused;
    private boolean isGameOver;
    private ImageView bg;
    private Sound gameLost = new Sound("breakoutresources/game_lost.wav");
    private Sound gameWon = new Sound("breakoutresources/game_won.wav");

    public BallWorld() {
        setPrefSize(800, 600);
        level = 1;
        isPaused = true;
        isGameOver = false;
        Image background = new Image("breakoutresources/background.png");
        bg = new ImageView(background);
        getChildren().add(bg);
    }

    @Override
    public void onDimensionsInitialized() {
    	bg.setX((getWidth() - bg.getImage().getWidth()) / 2);
    	ball = new Ball();
        add(ball);

        paddle = new Paddle();
        add(paddle);
        paddle.setX(getWidth() / 2);
        paddle.setY(3 * getHeight() / 4);

        
        setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                paddle.setX(event.getX() - paddle.getWidth() / 2);
            }
        });
        
        setOnMousePressed(e -> {
            if (isGameOver) {
                Breakout.showTitleScreen();
            }
        });

        score = new Score();
        score.setX(getWidth() / 12);
        score.setY(getHeight() / 10);
        getChildren().add(score);
        
        lives = new Lives();
        lives.setX(getWidth() / 12);
        lives.setY(getHeight() / 10 + 30);
        getChildren().add(lives);

        loadLevel(level);
        resetBall();
        isPaused = true;
    }
    
    public void scroll(double dx) {
    	double newX = bg.getX() - dx;

        double leftLimit = getWidth() - bg.getImage().getWidth();
        double rightLimit = 0;

        if (newX >= leftLimit && newX <= rightLimit) {
            bg.setX(newX);

            for (Actor actor : getObjects(Actor.class)) {
                actor.move(-dx, 0);
            }
        }
    }


    public void loadLevel(int level) {
        String fileName = "breakoutresources/level" + level + ".txt";

        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        if (input == null) {
            Breakout.showTitleScreen();
            return;
        }

        Scanner scanner = new Scanner(input);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        Brick sample = new Brick();
        double brickWidth = sample.getImage().getWidth();
        double brickHeight = sample.getImage().getHeight();
        
        double totalWidth = cols * brickWidth;
        double startX = (getWidth() - totalWidth) / 2;
        
        for (int r = 0; r < rows; r++) {
            String line = scanner.nextLine();

            for (int c = 0; c < cols; c++) {
                if (line.charAt(c) != '0') {
                    Brick brick = new Brick();
                    add(brick);
                    brick.setX(c * brickWidth + startX);
                    brick.setY(r * brickHeight + 100);
                }
            }
        }
        
        scanner.close();
    }
    
    private void showEndMessage(String text) {
        isPaused = true;
        isGameOver = true;

        message = new Text(text);
        message.setFont(new Font("Impact", 40));
        message.setX(getWidth() / 2 - message.getFont().getSize() * text.length() / 4);
        message.setY(getHeight() / 2);

        getChildren().add(message);
    }

    public Score getScore() {
        return score;
    }
    
    public Lives getLives() {
        return lives;
    }
    
    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }
    
    public void resetBall() {
    	ball.setX(paddle.getX() + paddle.getWidth() / 2 - ball.getWidth() / 2);
    	ball.setY(paddle.getY() - ball.getHeight());
    }

    @Override
    public void act(long now) {
    	if (!isGameOver && isKeyPressed(KeyCode.SPACE) && isPaused) {
            isPaused = false;
        }
    	
    	if (!isGameOver && lives.getValue() <= 0) {
            gameLost.play();
            showEndMessage("GAME OVER");
            return;
        }
    	
    	if (!isGameOver && getObjects(Brick.class).isEmpty()) {
            level++;
            
            if (level <= 2) {
                loadLevel(level);
                resetBall();
                isPaused = true;
            } else {
            	gameWon.play();
            	showEndMessage("YOU WIN!");
            	return;
            }
        }
    	
    	if (isGameOver && isKeyPressed(KeyCode.SPACE)) {
            Breakout.showTitleScreen();
        }
    }
}