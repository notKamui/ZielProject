package model;

import javafx.scene.shape.Rectangle;

public class Bounds {
	private Rectangle bounds;
	
	public Bounds(int coordX, int coordY, int width, int height) {
		this.bounds = new Rectangle(coordX, coordY, width, height);
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
}
