package engine;

/*
Name:       Shrestha Nayak
Date:       April 15, 2026
Period:     1

Is this lab fully working?  Yes
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	private AnimationTimer timer;
	private boolean timerRunning;
	private Set<KeyCode> keysPressed;

	private boolean widthSet;
	private boolean heightSet;
	private double width;
	private double height;
	private boolean initialized;

	public World() {
		this.timerRunning = false;
		this.keysPressed = new HashSet<>();

		this.widthSet = false;
		this.heightSet = false;
		this.width = 0;
		this.height = 0;

		this.initialized = false;

		this.widthProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal.doubleValue() > 0 && !widthSet) {
				widthSet = true;
				width = newVal.doubleValue();
			}

			if (widthSet && heightSet && !initialized) {
				initialized = true;
				onDimensionsInitialized();
			}
		});

		this.heightProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal.doubleValue() > 0 && !heightSet) {
				heightSet = true;
				height = newVal.doubleValue();
			}

			if (widthSet && heightSet && !initialized) {
				initialized = true;
				onDimensionsInitialized();
			}
		});
		
		this.sceneProperty().addListener((obs, oldScene, newScene) -> {
		    if (newScene != null) {
		    	this.requestFocus();
		    	
		        newScene.setOnKeyPressed(e -> {
		            keysPressed.add(e.getCode());
		        });

		        newScene.setOnKeyReleased(e -> {
		            keysPressed.remove(e.getCode());
		        });
		    }
		});

		this.timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				act(now);

				List<Node> snapshot = new ArrayList<>(getChildren());

				for (Node node : snapshot) {
				    if (node instanceof Actor) {
				        Actor actor = (Actor) node;

				        if (getChildren().contains(actor)) {
				            actor.act(now);
				        }
				    }
				}
			}

		};
	}

	public void start() {
		if (!timerRunning) {
			timer.start();
			timerRunning = true;
		}
	}

	public void stop() {
		if (timerRunning) {
			timer.stop();
			timerRunning = false;
		}
	}

	public boolean isStopped() {
		return !timerRunning;
	}

	public void add(Actor actor) {
		getChildren().add(actor);
		actor.addedToWorld();
	}

	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	public <A extends Actor> List<A> getObjects(Class<A> cls) {
		List<A> result = new ArrayList<>();

		for (Node node : getChildren()) {
			if (cls.isInstance(node)) {
				result.add(cls.cast(node));
			}
		}

		return result;
	}

	public <A extends Actor> List<A> getObjectsAt(double x, double y, Class<A> cls) {
		List<A> result = new ArrayList<>();

		for (Node node : getChildren()) {
			if (cls.isInstance(node)) {
				if (node.getBoundsInParent().contains(x, y)) {
					result.add(cls.cast(node));
				}
			}
		}

		return result;
	}

	public boolean isKeyPressed(KeyCode code) {
		return keysPressed.contains(code);
	}

	public abstract void onDimensionsInitialized();

	public abstract void act(long now);
}
