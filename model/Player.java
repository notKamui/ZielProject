package model;

import java.util.ArrayList;

public class Player extends Charac {

    private Inventory inventory;
    private ArrayList<String> input;
    public Player(int x, int y) {
        super(x, y, MathDataBuilder.PLAYERDIM[0], MathDataBuilder.PLAYERDIM[1]);
        this.inventory = new Inventory();
    }
    public void act() {
    	this.readInput(input);
    	this.setPosition();
    	this.jumpAnim();
    	this.getItems();
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

    public void setInput(ArrayList<String> input) {
    	this.input = input;
    }
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public void getItems() {
    	for(Item item :this.getCollMan().itemsAround()) {
    		this.inventory.addItem(item);
    	}
    }
}
