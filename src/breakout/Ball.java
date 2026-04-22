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
		// Up & Down Bounce Off
		if (getY() <= 0) {
			dy *= -1;
		}
		if (getY() + getHeight() >= getWorld().getHeight()) {
			dy *= -1;
			BallWorld world = (BallWorld) getWorld();
		    world.getScore().setValue(world.getScore().getValue() - 1000);
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
