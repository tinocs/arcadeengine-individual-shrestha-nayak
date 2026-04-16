package engine;

/*
Name:       Shrestha Nayak
Date:       April 15, 2026
Period:     1

Is this lab fully working?  Yes
*/

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {
	public Actor() {
	}

	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	public World getWorld() {
		if (getParent() instanceof World) {
			return (World) getParent();
		}
		return null;
	}

	public double getWidth() {
	    return getBoundsInParent().getWidth();
	}

	public double getHeight() {
	    return getBoundsInParent().getHeight();
	}

	public <A extends Actor> List<A> getIntersectingObjects(Class<A> cls) {
	    List<A> result = new ArrayList<>();

	    World world = getWorld();
	    if (world == null) return result;

	    for (Node node : world.getChildren()) {
	        if (node instanceof Actor) {
	            Actor a = (Actor) node;

	            if (a != this &&
	                cls.isInstance(a) &&
	                a.getBoundsInParent().intersects(this.getBoundsInParent())) {

	                result.add(cls.cast(a));
	            }
	        }
	    }

	    return result;
	}

	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {

	    World world = getWorld();
	    if (world == null) return null;

	    for (Node node : world.getChildren()) {
	        if (node instanceof Actor) {
	            Actor a = (Actor) node;

	            if (a != this &&
	                cls.isInstance(a) &&
	                a.getBoundsInParent().intersects(this.getBoundsInParent())) {

	                return cls.cast(a);
	            }
	        }
	    }

	    return null;
	}

	public abstract void act(long now);

	public void addedToWorld() {
	}
}
