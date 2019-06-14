package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/* GameObject
 * This class represent all of the object present in the scene
 * (Tiles, Player, Enemy, Item Drop)
 */
public abstract class GameObject {
    private int id;
    private IntegerProperty coordXProperty;
    private IntegerProperty coordYProperty;
    private int width;
    private int height;
    private Hitbox hitbox;

    public GameObject(int id, int x, int y, int width, int height) {
        this.id = id;
        this.coordXProperty = new SimpleIntegerProperty(x);
        this.coordYProperty = new SimpleIntegerProperty(y);
        this.width = width;
        this.height = height;
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

    public Hitbox getHitbox() {
        return this.hitbox;
    }
    
    abstract public void removeHitbox();


    abstract public void changeHitbox();
      
    public void setHitbox() {
    	this.hitbox = new Hitbox(this.coordXProperty, this.coordYProperty, width, height, this);
    }

    public int getId() {
        return id;
    }

}
