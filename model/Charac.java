package model;


public abstract class Charac extends DynamicObject {
    private int direction; // 0 = facing left // 180 = facing right
    private int jumpForce;
    private boolean isJumping;

    public Charac(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.direction = 0;
        this.jumpForce = 0;
        this.setIsJumping(false);
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(boolean flipped) {
        if (flipped)
            this.direction = 180;
        else
            this.direction = 0;
    }

}
