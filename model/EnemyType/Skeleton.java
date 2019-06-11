package model.EnemyType;

import model.Enemy;
import model.MathDataBuilder;

public class Skeleton extends Enemy {

	public Skeleton(int x, int y) {
		super(x, y, false);
	}
	
	@Override
	public void act() {
		this.followPlayer();
		this.setPosition();
		this.jumpAnim();
	}

	@Override
	public void followPlayer() {

		int xPlayer = MathDataBuilder.world().getPlayer().coordXProperty().getValue();
		
		if(xPlayer < this.coordXProperty().getValue()) {
			this.setVectX(Math.max(xPlayer-this.coordXProperty().get(), -this.getSpeed()));
		}
		else if(xPlayer > this.coordXProperty().getValue()) {
			this.setVectX(Math.min(xPlayer-this.coordXProperty().get(), this.getSpeed()));
		}
		
		if(this.getCollMan().isInFrontRight() || this.getCollMan().isInFrontLeft()) {
			 if (this.getCollMan().isOnFloor())
                 this.setIsJumping(true);
		}
	}
}
