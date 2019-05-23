package model;

import java.util.ArrayList;

public class Player extends Charac {

    private Inventory inventory;
    final private int SPEED = 10;

    public Player(int x, int y) {
        super(x, y, 80, 80);
        this.inventory = new Inventory();
    }

    public void readInput(ArrayList<String> input) {
        for (String key : input)
            switch (key) {
                case "D":
                    this.setVectX(SPEED);
                    this.setDirection(false);
                    break;
                case "Q":
                    this.setVectX(-SPEED);
                    this.setDirection(true);
                    break;
                case "S":
                    if (this.getJumpForce() < 26) //can fastfall at max height of the jump
                        this.setIsJumping(false);
                    break;
                case "SPACE":
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
