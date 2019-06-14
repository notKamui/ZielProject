package model;


import java.util.ArrayList;

import model.ItemOtherType.VoidItem;
/* Player
 * This class contains all the methods that permit to control and manage the player
 * (Spawn player, take Input, Attack...)
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.ItemOtherType.VoidItem;

import java.util.ArrayList;

public class Player extends Charac {
    private IntegerProperty attackState;
    private Inventory inventory;
    private Dijkstra distanceField;
    private ArrayList<String> input;

    public Player(int x, int y) {
        super(700, x, y, MathDataBuilder.PLAYERDIM[0], MathDataBuilder.PLAYERDIM[1], false, 100, 50);
        this.inventory = new Inventory();
        this.changeHitbox();
        this.distanceField = new Dijkstra();
        this.attackState = new SimpleIntegerProperty(0);

    }

    public void act() {
        this.readInput(input);
        if (attackState.get() > 0) {
            if (attackState.get() >= 20) {
                int direction = 1;
                if (this.directionProperty().get() == 0)
                    direction = -1;
                this.setVectX(12*direction);
            } else {
                if (20 > attackState.get() && attackState.get() > 7) {
                    this.attack();
                }
                this.setVectX(0);
            }
        }
        this.setPosition();
        this.jumpAnim();
        this.pickUpItems();
        this.distanceField.applyDistanceField();
        this.setInvFrame(Math.max(0,this.getInvFrame()-1));
        this.attackState.set(Math.max(0, attackState.get()-1));
    }

    public void readInput(ArrayList<String> input) {
        for (String key : input)
            switch (key) {
                case "RIGHT":
                case "D":
                    this.setVectX(this.getSpeed());
                    break;
                case "LEFT":
                case "Q":
                    this.setVectX(-this.getSpeed());
                    break;
                case "DOWN":
                case "S":
                    if (this.getJumpForce() < 26) //can fastfall at max height of the jump
                        this.setIsJumping(false);
                    break;
                case "UP":
                case "Z":
                    if (this.getCollMan().isOnFloor() && attackState.get() == 0)
                        this.setIsJumping(true);
                    break;
                case "SPACE":
                    if (attackState.get() == 0)
                        attackState.set(30);
                default:
                    break;
            }
    }


    public void attack() {
        Item fakeHitbox = null;
        if (this.directionProperty().doubleValue() == 0) {
            fakeHitbox = new VoidItem(this.coordXProperty().get() - 65, this.coordYProperty().get());
        } else {
            fakeHitbox = new VoidItem(this.coordXProperty().get() + 5, this.coordYProperty().get());

        }
        ArrayList<Enemy> enemies = fakeHitbox.getCollMan().enemiesHurt();
        for (Enemy enemy : enemies) {
            enemy.getHurt(this.getDamage());
        }
    }

    public void die() {
    	System.exit(0);
    }
  
    public void setInput(ArrayList<String> input) {
        this.input = input;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void pickUpItems() {
        for (Item item : this.getCollMan().itemsAround()) {
            this.inventory.addItem(item);
        }
    }

    public Dijkstra getDistanceField() {
        return this.distanceField;
    }

    public void removeHitbox() {
        Collider.playerHitbox = null;
    }

    public void changeHitbox() {
        this.setHitbox();
        Collider.playerHitbox = this.getHitbox();
    }

    public int getAttackState() {
        return attackState.get();
    }

    public final IntegerProperty attackStateProperty() {
        return attackState;
    }

}
