package model;


public abstract class Charac extends DynamicObject {
    private int jumpForce;
    private int invFrame;
    private boolean isJumping;
    private double hp;
    private double damage;

    public Charac(int id, int x, int y, int width, int height, boolean isFlying) {
        super(id, x, y, width, height, isFlying);
        this.jumpForce = 0;
        this.setIsJumping(false);
        this.changeHitbox();
        this.hp = hp;
        this.damage = damage;
    }

    // Jump functions--------
    public void jumpAnim() {
        if (this.isJumping) {
            this.setVectY(-this.jumpForce);
        }
        this.jumpForce = Math.max(this.jumpForce - 2, 0);
        if (this.getCollMan().isOnFloor()) {
            this.isJumping = false;
        }
    }

    public boolean getIsJumping() {
        return this.isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
        if (isJumping)
            this.jumpForce = 48;
    }

    public int getJumpForce() {
        return this.jumpForce;
    }
  //-------------------------
    
    public void getHurt(double damage) {
    	if(this.getInvFrame()==0) {
    		this.setHp(this.getHp()-damage);
    		this.setInvFrame(90);
    	}
    }    
    


    

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public int getInvFrame() {
		return invFrame;
	}

	public void setInvFrame(int invFrame) {
		this.invFrame = invFrame;
	}

}
