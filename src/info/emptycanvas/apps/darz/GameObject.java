package info.emptycanvas.apps.darz;

import java.awt.Point;

public class GameObject {
	private Point position = new Point(0,0);
	
	private double orientation = 0;
	
	private int taille = 100;
	
	public GameObject() {
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
	
}
