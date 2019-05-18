package model;

import java.util.ArrayList;

public class Player extends Charac {

    final private int SPEED = 10;

    public Player(int x, int y) {
        super(x, y, 80, 80);
    }

    public void readInput(ArrayList<String> input) {
        for (String key : input)
            switch (key) {
                case "D":
                    this.move(SPEED, 0);
                    break;
                case "Q":
                    this.move(-SPEED, 0);
                    break;
                case "S":
                    if(this.getJumpForce() < 26) //can fastfall at max height of the jump
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

}
