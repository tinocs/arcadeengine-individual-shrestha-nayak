package breakout;

import engine.World;

public class BallWorld extends World{

	public BallWorld() {
		setPrefSize(800, 600);
	}
	
	@Override
	public void onDimensionsInitialized() {
		Ball ball = new Ball();
		add(ball);
		ball.setX(getWidth() / 2);
		ball.setY(getHeight() / 2);
		
		Paddle paddle = new Paddle();
		add(paddle);
		paddle.setX(getWidth() / 2);
		paddle.setY(3 * getHeight() / 4);
	}

	@Override
	public void act(long now) {
	}
}
