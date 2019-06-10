package model.EnemyType;

import model.Enemy;
import model.MathDataBuilder;

public class Gargoyle extends Enemy {

    public Gargoyle(int x, int y) {
        super(x, y);
    }

    @Override
    public void act() {
        this.followPlayer();
        this.setPosition();
    }

    @Override
    public void followPlayer() {
        int xPlayer = MathDataBuilder.world().getPlayer().coordXProperty().getValue();

        if (xPlayer < this.coordXProperty().getValue()) {
            this.setVectX(Math.max(xPlayer - this.coordXProperty().get(), -this.getSpeed()));
            this.directionProperty().set(180);
        } else if (xPlayer > this.coordXProperty().getValue()) {
            this.setVectX(Math.min(xPlayer - this.coordXProperty().get(), this.getSpeed()));
            this.directionProperty().set(0);
        }

        int yPlayer = MathDataBuilder.world().getPlayer().coordYProperty().getValue();

        if (yPlayer < this.coordYProperty().getValue()) {
            this.setVectY(Math.max(yPlayer - this.coordYProperty().get(), -this.getSpeed()) - 30);
        } else if (yPlayer > this.coordYProperty().getValue()) {
            this.setVectY(Math.min(yPlayer - this.coordYProperty().get(), this.getSpeed()) - 30);
        } else {
            this.setVectY(-30);
        }

        if (this.getCollMan().isInFrontRight() || this.getCollMan().isInFrontLeft()) {
            this.setVectY(-this.getSpeed() - 30);
        }
    }
}
