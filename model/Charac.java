package model;

/* Charac
 * This Class represent all the character who interact in the world
 */
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class Charac extends DynamicObject {
    private int jumpForce;
    private int invFrame;
    private boolean isJumping;
    private DoubleProperty hp;
    private double maxHp;
    private double damage;

    public Charac(int id, int x, int y, int width, int height, boolean isFlying, double maxHp, double damage) {
        super(id, x, y, width, height, isFlying);
        this.jumpForce = 0;
        this.setIsJumping(false);
        this.maxHp = maxHp;
        this.hp = new SimpleDoubleProperty(maxHp);
        this.damage = damage;
        this.getHpProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                Number newValue) {
				if((double)newValue<=0) {
				die();
				}
}});
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
    		this.setInvFrame(30);
    	}
    }    
    

    public abstract void die();
    
    public DoubleProperty getHpProperty() {
    	return hp;
    }
	public double getHp() {
		return hp.get();
	}

	public double getMaxHp() {
		return maxHp;
	}
	public void setHp(double hp) {
		this.hp.set(hp);
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
