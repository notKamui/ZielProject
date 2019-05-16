package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class GameObject {
    final private int GRAVITY = 10;
    private IntegerProperty coordXProperty;
    private IntegerProperty coordYProperty;
    private Bounds hitbox;

    public GameObject(int x, int y, int width, int height) {
        this.coordXProperty = new SimpleIntegerProperty(x);
        this.coordYProperty = new SimpleIntegerProperty(y);
        this.hitbox = new Bounds(x, y, width, height);
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
