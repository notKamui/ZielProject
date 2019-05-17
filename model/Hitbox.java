package model;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hitbox {
	private Rectangle bounds;
	
	public Hitbox(int coordX, int coordY, int width, int height) {
		this.setBounds(coordX+1, coordY+1, width, height);
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}

	public void setBounds(int coordX, int coordY, int width, int height) {
        this.bounds = new Rectangle(coordX, coordY, width, height);
    }

    public void setToNonSolid() {
	    this.bounds = null;
    }
}
