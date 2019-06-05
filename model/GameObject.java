package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public abstract class GameObject {
    private static ArrayList<Hitbox> hitboxList = new ArrayList<>();
    private IntegerProperty coordXProperty;
    private IntegerProperty coordYProperty;
    private int width;
    private int height;
    private Hitbox hitbox;

    public GameObject(int x, int y, int width, int height) {
        this.coordXProperty = new SimpleIntegerProperty(x);
        this.coordYProperty = new SimpleIntegerProperty(y);
        this.width = width;
        this.height = height;
        this.setHitbox();
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

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public ArrayList<Hitbox> getBoundsList() {
        return hitboxList;
    }

    public void removeHitbox() {
        hitboxList.remove(this.hitbox);
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public World getWorld() {
        return world;
    }
    
    public void setHitbox() {
    	this.hitbox = new Hitbox(this.coordXProperty, this.coordYProperty, width, height);
        hitboxList.add(this.hitbox);
    }
    public static void setWorld(World w) {
        world = w;
    }


}
