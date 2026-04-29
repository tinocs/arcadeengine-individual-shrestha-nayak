package breakout;

import engine.Actor;
import breakout.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


public class Paddle extends Actor{
	private Image paddle;
	
	public Paddle() {
		String path = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
		paddle = new Image(path);
		setImage(paddle);
	}
	
	@Override
	public void act(long now) {
		BallWorld world = (BallWorld)getWorld();
		double dx = 0;
		if (getWorld().isKeyPressed(KeyCode.LEFT)) {
	        dx = -10;
			move(dx, 0);
	    }
	    if (getWorld().isKeyPressed(KeyCode.RIGHT)) {
	    	dx = 10;
			move(dx, 0);
	        
	    }
	    
	    if (dx != 0) {
	        double midpoint = getWorld().getWidth() / 2;

	        if ((dx > 0 && getX() > midpoint) ||
	            (dx < 0 && getX() < midpoint)) {
	            ((BallWorld) getWorld()).scroll(dx);
	        } else {
	            move(dx, 0);
	        }
	    }

	    if (getX() < 0) {
	    	//world.scroll(-10);
	        setX(0);
	    }
	    if (getX() + getWidth() > getWorld().getWidth()) {
	    	//world.scroll(10);
	    	setX(getWorld().getWidth() - getWidth());
	    }
	}
	
}
