package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends Bounds {
	final private int GRAVITY = 2;
    private IntegerProperty coordXProperty;
    private IntegerProperty coordYProperty;

    public Player(int x, int y) {
    	super(x, y, 80, 80);
        this.coordXProperty = new SimpleIntegerProperty(x);
        this.coordYProperty = new SimpleIntegerProperty(y);
    }

    public void move(int vectX, int vectY) {
        this.setCoordXProperty(this.coordXProperty.get() + vectX);
        this.setCoordYProperty(this.coordYProperty.get() + vectY);
    }

    public void gravity(){
    	this.move(0, GRAVITY);
    }
    public void setCoordXProperty(int x) {
        this.coordXProperty.setValue(x);
    }

    public void setCoordYProperty(int y) {
        this.coordYProperty.setValue(y);
    }

    public final IntegerProperty coordXProperty() {
        return this.coordXProperty;
    }

    public final IntegerProperty coordYProperty() {
        return this.coordYProperty;
    }
    
}
