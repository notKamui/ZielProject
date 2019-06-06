package model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Charac extends DynamicObject {
    private int jumpForce;
    private boolean isJumping;

    public Charac(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.jumpForce = 0;
        this.setIsJumping(false);
        this.changeHitbox();
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


    
    public void removeHitbox() {
        Collider.characHitboxList.remove(this.getHitbox());
      }

    public void changeHitbox() {
      	this.setHitbox();
      	Collider.characHitboxList.add(this.getHitbox());
      }

}
