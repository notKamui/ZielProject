package model;

public abstract class Enemy extends Charac {

	public Enemy(int id, int x, int y, int width, int height, boolean isFlying, double hp, double damage) {
		super(id, x, y, width, height, isFlying, hp, damage);
		this.setSpeed(5);
	}
	
	@Override
	public void act() {
		this.followPlayer();
		this.setPosition();
		this.attack();
        this.setInvFrame(Math.max(0,this.getInvFrame()-1));

	}
	
	public void attack() {
		
		if(this.getCollMan().playerHurt()) {
			MathDataBuilder.world().getPlayer().getHurt(this.getDamage());
		}
	}
	public abstract void followPlayer();
	

    public void removeHitbox() {
        Collider.characHitboxList.remove(this.getHitbox());
      }

    public void changeHitbox() {
      	this.setHitbox();
      	Collider.characHitboxList.add(this.getHitbox());
      }
}
