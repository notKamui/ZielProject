package model;

public abstract class Enemy extends Charac{
	
	public Enemy(int x, int y) {
		super(x, y, 64, 64);
		this.setSpeed(5);
	}

	@Override
	public abstract void act();
	
	public abstract void followPlayer();

}
