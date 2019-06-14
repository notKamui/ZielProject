package model;
/* Enemy
 * This Class allow the creation of enemy
 * A enemy can follow the player, attack it  by "act"
 */
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
	
	//Method that allow the enemy to attack the player
	public void attack() {
		
		if(this.getCollMan().playerHurt()) {
			MathDataBuilder.world().getPlayer().getHurt(this.getDamage());
		}
	}
	
	//Method that allow the enemy to target and go to the player
	public abstract void followPlayer();
	

    public void removeHitbox() {
        Collider.characHitboxList.remove(this.getHitbox());
      }

    public void changeHitbox() {
      	this.setHitbox();
      	Collider.characHitboxList.add(this.getHitbox());
      }
}
