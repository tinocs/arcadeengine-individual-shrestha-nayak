package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Ball extends Actor{
	
	private double dx;
	private double dy;
	private double speed;
	private Image ball;
	
	public Ball() {
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		ball = new Image(path);
		setImage(ball);
		
		dx = 5;
		dy = 5;
	}

	@Override
	public void act(long now) {
		move(dx, dy);
		// Left & Right Bounce Off
		if (getX() <= 0 || getX() + getWidth() >= getWorld().getWidth()) {
			dx *= -1;
		}
		// Top Bounce Off
		if (getY() <= 0) {
			dy *= -1;
		}
		
		// Bottom Bounce Off
		if (getY() + getHeight() >= getWorld().getHeight()) {

		    BallWorld world = (BallWorld) getWorld();

		    // lose life
		    world.getLives().setValue(world.getLives().getValue() - 1);

		    // reset ball position
		    setX(world.getWidth() / 2);
		    setY(world.getHeight() / 2);

		    dy *= -1;

		    // game over
		    if (world.getLives().getValue() <= 0) {
		        world.stop();
		        Breakout.showTitleScreen();
		    }
		}
		
		Paddle paddle = getOneIntersectingObject(Paddle.class);
	    if (paddle != null) {
	        dy *= -1;
	    }
	    
	    Brick brick = getOneIntersectingObject(Brick.class);
	    
	    
	    if (brick != null  && getWorld().getChildren().contains(brick)) {
	    	BallWorld world = (BallWorld) getWorld();
	        world.getScore().setValue(world.getScore().getValue() + 100);
	    	
	    	
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
	        getWorld().remove(brick);
	    }
	}
}
