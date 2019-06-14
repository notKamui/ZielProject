package model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class Enemy extends Charac {

	public Enemy(int id, int x, int y, int width, int height, boolean isFlying, double hp, double damage) {
		super(id, x, y, width, height, isFlying, hp, damage);
		this.setSpeed(5);
        this.changeHitbox();

	}
	
	@Override
	public void act() {
		this.followPlayer();
		this.setPosition();
		this.attack();
        this.setInvFrame(Math.max(0,this.getInvFrame()-1));
	}
	
	public void die() {
		this.removeHitbox();
		MathDataBuilder.world().getDynamicObjects().remove(this);
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
