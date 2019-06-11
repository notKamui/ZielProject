package model;

public abstract class Enemy extends Charac{
	

	public Enemy(int x, int y, boolean isFlying) {
		super(x, y, 64, 64, isFlying);
		this.setSpeed(5);
	}
	
	@Override
	public void act() {
		this.followPlayer();
		this.setPosition();
	}
	public abstract void followPlayer();

}
