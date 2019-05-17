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
                case "RIGHT":
                    this.move(SPEED, 0);
                    break;
                case "Q":
                case "LEFT":
                    this.move(-SPEED, 0);
                    break;
                case "SPACE":
                    if (!this.getIsJumping())
                        this.setJumping(true);
                    break;
                default:
                    break;
            }
    }

}
