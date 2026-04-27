package breakout;

import java.io.InputStream;
import java.util.Scanner;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BallWorld extends World {

    private Paddle paddle;
    private Ball ball;
    private Score score;
    private int level;
    private Lives lives;

    public BallWorld() {
        setPrefSize(800, 600);
        level = 1;
    }

    @Override
    public void onDimensionsInitialized() {
        ball = new Ball();
        add(ball);
        ball.setX(getWidth() / 2);
        ball.setY(getHeight() / 2);

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

        score = new Score();
        score.setX(getWidth() / 12);
        score.setY(getHeight() / 10);
        getChildren().add(score);
        
        lives = new Lives();
        lives.setX(getWidth() / 12);
        lives.setY(getHeight() / 10 + 30);
        getChildren().add(lives);

        loadLevel(level);
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

        for (int r = 0; r < rows; r++) {
            String line = scanner.nextLine();

            for (int c = 0; c < cols; c++) {
                if (line.charAt(c) != '0') {
                    Brick brick = new Brick();
                    add(brick);
                    brick.setX(c * brickWidth + 90);
                    brick.setY(r * brickHeight + 100);
                }
            }
        }

        scanner.close();
    }

    public Score getScore() {
        return score;
    }
    
    public Lives getLives() {
        return lives;
    }

    @Override
    public void act(long now) {
        if (getObjects(Brick.class).isEmpty()) {
            level++;

            if (level <= 2) {
                loadLevel(level);
            } else {
                stop();
                Breakout.showTitleScreen();
            }
        }
    }
}