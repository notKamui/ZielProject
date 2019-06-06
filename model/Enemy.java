package model;

public abstract class Enemy extends Charac{
	
	public Enemy(int x, int y) {
		super(x, y, 80, 80);
		this.setSpeed(5);
	}
	
	public abstract void act();
	
	public abstract void followPlayer();

}
