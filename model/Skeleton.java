package model;

public class Skeleton extends Enemy{

	public Skeleton(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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
			this.directionProperty().set(180);
		}
		else if(xPlayer > this.coordXProperty().getValue()) {
			this.setVectX(Math.min(xPlayer-this.coordXProperty().get(), this.getSpeed()));
			this.directionProperty().set(0);
		}
		
		if(this.getCollMan().isInFrontRight() || this.getCollMan().isInFrontLeft()) {
			 if (this.getCollMan().isOnFloor())
                 this.setIsJumping(true);
		}
	}
}
