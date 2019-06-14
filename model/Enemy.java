package model;

public abstract class Enemy extends Charac {


    public Enemy(int id, int x, int y, int width, int height, boolean isFlying) {
        super(id, x, y, width, height, isFlying);
        this.setSpeed(5);
    }

    @Override
    public abstract void act();

    public abstract void followPlayer();

}
