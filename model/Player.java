package model;

import java.util.ArrayList;

public class Player extends Charac {

    private Inventory inventory;

    public Player(int x, int y) {
        super(x, y, 80, 80);
        this.inventory = new Inventory();
    }

    public void readInput(ArrayList<String> input) {
        for (String key : input)
            switch (key) {
                case "RIGHT":
                case "D":
                    this.setVectX(this.getSpeed());
                    this.setDirection(false);
                    break;
                case "LEFT":
                case "Q":
                    this.setVectX(-this.getSpeed());
                    this.setDirection(true);
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

    public Inventory getInventory() {
        return this.inventory;
    }

}
