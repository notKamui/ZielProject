package model;

import java.util.ArrayList;

public class Player extends Charac {

    private Inventory inventory;
    private Dijkstra distanceField;

    public Player(int x, int y) {
        super(x, y, MathDataBuilder.PLAYERDIM[0], MathDataBuilder.PLAYERDIM[1]);
        this.inventory = new Inventory();
        this.distanceField = new Dijkstra();
    }

    public void readInput(ArrayList<String> input) {
        for (String key : input)
            switch (key) {
                case "RIGHT":
                case "D":
                    this.setVectX(this.getSpeed());
                    this.setDirection(true);
                    break;
                case "LEFT":
                case "Q":
                    this.setVectX(-this.getSpeed());
                    this.setDirection(false);
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
    
    public void pickUpItems() {
    	for(Item item :this.getCollMan().itemsAround()) {
    		this.inventory.addItem(item);
    	}
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Dijkstra getDistanceField() {
        return this.distanceField;
    }
}
