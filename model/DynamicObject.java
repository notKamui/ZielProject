package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/* DynamicObject
 * This class represent all of the GameObject who can move in the scene
 * (Player, enemy, Item drop)
 */
public abstract class DynamicObject extends GameObject {

    final private int GRAVITY = 30;
    private boolean isFlying;
    private int speed = 10;
    private int vectX;
    private int vectY;
    private IntegerProperty directionProperty; // 0 = facing left // 180 = facing right
    private Collider collMan;
    private IntegerProperty animState; // default idle ; 1 run ; 2 rising ; 3 falling ; 4 atk

    public DynamicObject(int id, int x, int y, int width, int height, boolean isFlying) {
        super(id, x, y, width, height);
        this.directionProperty = new SimpleIntegerProperty(0);
        this.collMan = new Collider(this);
        this.isFlying = isFlying;
        this.animState = new SimpleIntegerProperty(0);
    }

    //Method that let DynamicObject act on the map(move, follow the player, jump..)
    abstract public void act();

    public void setPosition() {
        if (this.vectX < 0)
            this.setDirection(false);
        if (this.vectX > 0)
            this.setDirection(true);
        if (!isFlying)
            this.vectY += GRAVITY; // add gravity to vectY

        this.setAnimState();

        boolean collides;
        int pixel;
        int i;

        // apply vectX
        collides = false;
        pixel = 1;
        if (this.vectX < 0)
            pixel = -1;
        int oriX = this.coordXProperty().get();
        for (i = 0; i <= Math.abs(this.vectX) && !collides; i++) {
            this.setCoordXProperty(this.coordXProperty().get() + pixel);
            collides = this.collMan.collides();
        }
        i--;
        if (this.vectX < 0)
            i = -i;
        int newX = oriX + i;
        this.setCoordXProperty(newX);


        // apply vectY
        collides = false;
        pixel = 1;
        if (this.vectY < 0)
            pixel = -1;
        int oriY = this.coordYProperty().get();
        for (i = 0; i <= Math.abs(this.vectY) && !collides; i++) {
            this.setCoordYProperty(this.coordYProperty().get() + pixel);
            collides = this.collMan.collides();
        }
        i--;
        if (this.vectY < 0)
            i = -i;
        int newY = oriY + i;
        this.setCoordYProperty(newY);


        // reset vectors every frame
        this.vectX = 0;
        this.vectY = 0;

    }

    public Collider getCollMan() {
        return this.collMan;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int s) {
        this.speed = s;
    }

    public final int GRAVITY() {
        return GRAVITY;
    }

    public int getVectX() {
        return this.vectX;
    }

    public void setVectX(int vectX) {
        this.vectX = vectX;
    }

    public int getVectY() {
        return this.vectY;
    }

    public void setVectY(int vectY) {
        this.vectY = vectY;
    }

    public final IntegerProperty directionProperty() {
        return directionProperty;
    }

    private void setDirection(boolean flipped) {
        if (flipped) {
            this.directionProperty.set(180);
        } else {
            this.directionProperty.set(0);
        }
    }

    public void setAnimState() {
        if (!this.getCollMan().isOnFloor()) {
            if (vectY <= 0)
                this.animState.set(2);
            if (vectY > 0)
                this.animState.set(3);
        }
        else if (vectX != 0)
            this.animState.set(1);
        else
            this.animState.set(0);
    }

    public void setAnimState(int a) {
        this.animState.set(a);
    }

    public int getAnimState() {
        return this.animState.get();
    }

    final public IntegerProperty animStateProperty() {
        return this.animState;
    }
}
