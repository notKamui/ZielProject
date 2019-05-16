package model;


import javafx.scene.shape.Rectangle;

public class Hitbox {
	private Rectangle bounds;
	
	public Hitbox(int coordX, int coordY, int width, int height) {
		this.setBounds(coordX, coordY, width, height);
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}

	public void setBounds(int coordX, int coordY, int width, int height) {
        this.bounds = new Rectangle(coordX, coordY, width, height);
    }

    public void setToNonSolid() {
	    this.setBounds(0, 0, 0 ,0);
    }
}
