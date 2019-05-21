package model;


import javafx.beans.property.IntegerProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hitbox {
	private Rectangle bounds;
	
	public Hitbox(IntegerProperty coordX, IntegerProperty coordY, int width, int height) {
        this.bounds = new Rectangle();
        this.bounds.xProperty().bind(coordX);
        this.bounds.yProperty().bind(coordY);
        this.bounds.setWidth(width);
        this.bounds.setHeight(height);
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}

    public void setToNonSolid() {
	    this.bounds = null;
    }
}
