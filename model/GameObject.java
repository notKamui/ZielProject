package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public abstract class GameObject {
    private static ArrayList<Hitbox> hitboxList = new ArrayList<>();
    private IntegerProperty coordXProperty;
    private IntegerProperty coordYProperty;
    private Hitbox hitbox;
    private CollisionManager collisionManager;

    public GameObject(int x, int y, int width, int height) {
        this.coordXProperty = new SimpleIntegerProperty(x);
        this.coordYProperty = new SimpleIntegerProperty(y);
        this.hitbox = new Hitbox(x, y, width, height);
        hitboxList.add(this.hitbox);
        this.collisionManager = new CollisionManager(this);
    }

    public void move(int vectX, int vectY) {
        if(!this.collisionManager.collides()) {
            int newX = this.coordXProperty.get() + vectX;
            int newY = this.coordYProperty.get() + vectY;
            this.setCoordXProperty(newX);
            this.setCoordYProperty(newY);
            this.hitbox.getBounds().setX(newX);
            this.hitbox.getBounds().setY(newY);
        }
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

    public ArrayList<Hitbox> getBoundsList() {
        return hitboxList;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }
}
