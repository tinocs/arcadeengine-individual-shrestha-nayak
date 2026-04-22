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
		if (getY() <= 0 || getY() + getHeight() >= getWorld().getHeight()) {
			dy *= -1;
		}
		
		Paddle paddle = getOneIntersectingObject(Paddle.class);
	    if (paddle != null) {
	        dy *= -1;
	    }
	}
}
