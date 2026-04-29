package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {
	// to help with score double counting blocks before they fully disappear
	private boolean isHit = false; 

	public Brick() {
		String path = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();

		setImage(new Image(path));
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean hit) {
		isHit = hit;
	}

	@Override
	public void act(long now) {
	}
}