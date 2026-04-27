package breakout;

import engine.Actor;
import engine.Sound;
import javafx.scene.image.Image;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Ball extends Actor{
	
	private double dx;
	private double dy;
	private double speed;
	private Image ball;
	private Sound bounceSound = new Sound("breakoutresources/ball_bounce.wav");
	private Sound brickSound = new Sound("breakoutresources/brick_hit.wav");
	private Sound loseLifeSound = new Sound("breakoutresources/lose_life.wav");
	
	public Ball() {
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		ball = new Image(path);
		setImage(ball);
		
		dx = 5;
		dy = 5;
	}

	@Override
	public void act(long now) {
		BallWorld world = (BallWorld) getWorld();
		if (world == null) return;
		if (world.isPaused()) return;
		
		move(dx, dy);
		// Left & Right Bounce Off
		if (getX() <= 0 || getX() + getWidth() >= getWorld().getWidth()) {
			dx *= -1;
			bounceSound.play();
		}
		// Top Bounce Off
		if (getY() <= 0) {
			dy *= -1;
			bounceSound.play();
		}
		
		// Bottom Bounce Off
		if (getY() + getHeight() >= getWorld().getHeight()) {
			bounceSound.play();
			
		    // lose life
		    world.getLives().setValue(world.getLives().getValue() - 1);
		    loseLifeSound.play();
		    
		    world.setPaused(true);
		    world.resetBall();
		}
		
		Paddle paddle = getOneIntersectingObject(Paddle.class);
	    if (paddle != null) {
	        dy *= -1;
	        bounceSound.play();
	    }
	    
	    Brick brick = getOneIntersectingObject(Brick.class);
	    
	    
	    if (brick != null  && getWorld().getChildren().contains(brick)) {
	        world.getScore().setValue(world.getScore().getValue() + 100);
	        brickSound.play();
	    	
	        if (getX() >= brick.getX() &&
	            getX() <= brick.getX() + brick.getWidth()) {

	            dy *= -1;
	        } else if (getY() >= brick.getY() &&
	                 getY() <= brick.getY() + brick.getHeight()) {

	            dx *= -1;
	        } else {
	            dx *= -1;
	            dy *= -1;
	        }
	        
	        // fade to remove animation
	        FadeTransition fade = new FadeTransition(Duration.millis(500), brick);
	        fade.setFromValue(1.0);
	        fade.setToValue(0.0);

	        fade.setOnFinished(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	                getWorld().remove(brick);
	            }
	        });

	        fade.play();
	    }
	}
}
