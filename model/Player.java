package model;

import model.ItemOtherType.VoidItem;

import java.util.ArrayList;

public class Player extends Charac {
    private int attackState;
    private Inventory inventory;
    private Dijkstra distanceField;
    private ArrayList<String> input;

    public Player(int x, int y) {
        super(700, x, y, MathDataBuilder.PLAYERDIM[0], MathDataBuilder.PLAYERDIM[1], false, 100, 10);
        this.inventory = new Inventory();
        this.setHitbox();
        this.distanceField = new Dijkstra();
        attackState = 0;
    }

    public void act() {
        this.readInput(input);
        if (80 > attackState && attackState > 30) {
            this.attack();
        }
        this.setPosition();
        this.jumpAnim();
        this.pickUpItems();
        this.distanceField.applyDistanceField();
        this.setInvFrame(Math.max(0, this.getInvFrame() - 1));
        this.attackState = Math.max(0, attackState - 1);
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
                    if (this.getCollMan().isOnFloor())
                        this.setIsJumping(true);
                    break;
                case "SPACE":
                    if (attackState == 0)
                        attackState = 90;
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

    public void setAttackState() {

    }

}
