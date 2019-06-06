package model;

public class Ennemy extends Charac{

	private World w;
	
	public Ennemy(int x, int y, World w) {
		super(x, y, 80, 80);
		this.w = w;
		this.setSpeed(5);
	}
	
	public void followPlayer() {
		
		int xPlayer = this.w.getPlayer().coordXProperty().getValue();
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
