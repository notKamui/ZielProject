package model;


import javafx.beans.property.IntegerProperty;
import javafx.scene.shape.Rectangle;

public class Hitbox {
	private Rectangle bounds;
	private GameObject self;
	
	public Hitbox(IntegerProperty coordX, IntegerProperty coordY, int width, int height, GameObject self) {
        this.bounds = new Rectangle();
        this.bounds.xProperty().bind(coordX);
        this.bounds.yProperty().bind(coordY);
        this.bounds.setWidth(width);
        this.bounds.setHeight(height);
        this.self = self;
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
	public GameObject getSelf() {
		return self;
	}

    public void setToNonSolid() {
	    this.bounds = null;
    }
}
