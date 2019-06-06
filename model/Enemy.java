package model;

public class Enemy extends Charac{
	
	public Enemy(int x, int y) {
		super(x, y, 80, 80);
		this.setSpeed(5);
	}
	
	public void followPlayer() {
		
		int xPlayer = MathDataBuilder.world().getPlayer().coordXProperty().getValue();
		if(xPlayer < this.coordXProperty().getValue()) {
			this.setVectX(-this.getSpeed());
		}
		else {
			this.setVectX(this.getSpeed());
		}
		
		if(this.getCollMan().isInFrontRight() || this.getCollMan().isInFrontLeft()) {
			 if (this.getCollMan().isOnFloor())
                 this.setIsJumping(true);
		}
	}

}
