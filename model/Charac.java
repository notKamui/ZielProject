package model;


public abstract class Charac extends GameObject {
    final private int GRAVITY = 30;
    private int direction;
    private int jumpForce;
    private boolean isJumping;
    private CollisionManager collMan;

    public Charac(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.direction = 0;
        this.jumpForce = 0;
        this.setIsJumping(false);
        this.collMan = new CollisionManager(this);
    }

    public void gravity(){
        this.move(0, GRAVITY);
    }

    public void move(int vectX, int vectY) {
        boolean collides = false;
        int pixel = 1;
        if (vectX < 0 || vectY < 0)
            pixel = -1;

        if (vectX != 0 && vectY == 0) { // Horizontal move
            int i;
            int oriX = this.coordXProperty().get();
            for (i = 0; i <= Math.abs(vectX) && !collides; i++) {
                this.setCoordXProperty(this.coordXProperty().get() + pixel);
                collides = this.collMan.collides();
            }
            i--;
            if (vectX < 0)
                i = -i;
            int newX = oriX + i;
            this.setCoordXProperty(newX);
        }

        if(vectX == 0 && vectY != 0) { // Vertical move
            int j;
            int oriY = this.coordYProperty().get();
            for (j = 0; j <= Math.abs(vectY) && !collides; j++) {
                this.setCoordYProperty(this.coordYProperty().get() + pixel);
                collides = this.collMan.collides();
            }
            j--;
            if (vectY < 0)
                j = -j;
            int newY = oriY + j;
            this.setCoordYProperty(newY);
        }
    }

    // Jump functions--------
    public void jumpAnim() {
        if(this.isJumping) {
            this.move(0, -this.jumpForce);
        }
        this.jumpForce = Math.max(this.jumpForce-1, 0);
        if (this.collMan.isOnFloor()) {
            this.isJumping = false;
        }
    }
    public boolean getIsJumping() {
        return this.isJumping;
    }
    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
        if(isJumping)
            this.jumpForce = 48;
    }
    public int getJumpForce() {
        return this.jumpForce;
    }
    //-------------------------

    public CollisionManager getCollMan() {
        return this.collMan;
    }

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
