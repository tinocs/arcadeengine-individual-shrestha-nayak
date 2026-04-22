package breakout;

import engine.Actor;
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
		if (getWorld().isKeyPressed(KeyCode.LEFT)) {
			move(-10, 0);
		}
		if (getWorld().isKeyPressed(KeyCode.RIGHT)) {
			move(10, 0);
		}
	}
	
}
