package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Charac {

    private Inventory inventory;
    private Dijkstra distanceField;
    private ArrayList<String> input;

    public Player(int x, int y) {
        super(700, x, y, MathDataBuilder.PLAYERDIM[0], MathDataBuilder.PLAYERDIM[1], false);
        this.inventory = new Inventory();
        this.getCollMan().playerHitbox = this.getHitbox();
        this.distanceField = new Dijkstra();
    }

    public void act() {
        this.readInput(input);
        this.setPosition();
        this.jumpAnim();
        this.pickUpItems();
        this.distanceField.applyDistanceField();
    }

    public void readInput(ArrayList<String> input) {
        for (String key : input)
            switch (key) {
            
            	case "R" :
            		Craft c = new Craft();
            		break;
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
                    if (this.getCollMan().isOnFloor())
                        this.setIsJumping(true);
                    break;
                default:
                    break;
            }
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
}
