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
        boolean collides = false;
        int pixel = 1;
        if (vectX < 0 || vectY < 0)
            pixel = -1;

        if (vectX != 0 && vectY == 0) { // Horizontal move
            int i;
            for (i = 0; i <= Math.abs(vectX) && !collides; i++) {
                this.hitbox.getBounds().setX(this.hitbox.getBounds().getX() + pixel);
                collides = this.collisionManager.collides();
            }
            i--;
            if (vectX < 0)
                i = -i;
            int newX = this.coordXProperty.get() + i;
            this.setCoordXProperty(newX);
            this.hitbox.getBounds().setX(newX);
        }

        if(vectX == 0 && vectY != 0) { // Vertical move
            int j;
            for (j = 0; j <= Math.abs(vectY) && !collides; j++) {
                this.hitbox.getBounds().setY(this.hitbox.getBounds().getY() + pixel);
                collides = this.collisionManager.collides();
            }
            j--;
            if (vectY < 0)
                j = -j;
            int newY = this.coordYProperty.get() + j;
            this.setCoordYProperty(newY);
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
