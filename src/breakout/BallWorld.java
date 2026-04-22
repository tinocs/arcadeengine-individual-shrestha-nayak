package breakout;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BallWorld extends World{
	
	private Paddle paddle;
    private Ball ball;
	
	public BallWorld() {
		setPrefSize(800, 600);
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
			}});

	}

	@Override
	public void act(long now) {
	}
}
