package breakout;

import engine.Actor;
import javafx.scene.image.Image;


public class Paddle extends Actor{
	private Image paddle;
	
	public Paddle() {
		String path = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
		paddle = new Image(path);
		setImage(paddle);
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
}
