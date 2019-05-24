package model;


public abstract class Charac extends GameObject {
    final private int GRAVITY = 30;
    private static World world;
    private int range = 160; // range to enable interactions with other objects
    private int direction; // 0 = facing left // 180 = facing right
    private int jumpForce;
    private boolean isJumping;
    private Collider collMan;
    private int vectX;
    private int vectY;

    public Charac(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.world = world;
        this.direction = 0;
        this.jumpForce = 0;
        this.setIsJumping(false);
        this.collMan = new Collider(this);
    }

    public void move() {
        this.vectY += GRAVITY; // add gravity to vectY

        boolean collides;
        int pixel;
        int i;

        // apply vectX
        collides = false;
        pixel = 1;
        if (this.vectX < 0)
            pixel = -1;
        int oriX = this.coordXProperty().get();
        for (i = 0; i <= Math.abs(this.vectX) && !collides; i++) {
            this.setCoordXProperty(this.coordXProperty().get() + pixel);
            collides = this.collMan.collides();
        }
        i--;
        if (this.vectX < 0)
            i = -i;
        int newX = oriX + i;
        this.setCoordXProperty(newX);


        // apply vectY
        collides = false;
        pixel = 1;
        if (this.vectY < 0)
            pixel = -1;
        int oriY = this.coordYProperty().get();
        for (i = 0; i <= Math.abs(this.vectY) && !collides; i++) {
            this.setCoordYProperty(this.coordYProperty().get() + pixel);
            collides = this.collMan.collides();
        }
        i--;
        if (this.vectY < 0)
            i = -i;
        int newY = oriY + i;
        this.setCoordYProperty(newY);


        // reset vectors every frame
        this.vectX = 0;
        this.vectY = 0;

    }

    // Jump functions--------
    public void jumpAnim() {
        if (this.isJumping) {
            this.vectY = -this.jumpForce;
        }
        this.jumpForce = Math.max(this.jumpForce - 2, 0);
        if (this.collMan.isOnFloor()) {
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

    public Collider getCollMan() {
        return this.collMan;
    }

    public int getDirection() {
        return direction;
    }

    public int getRange() {
        return range;
    }


    public void setDirection(boolean flipped) {
        if (flipped)
            this.direction = 180;
        else
            this.direction = 0;
    }

    public void setVectX(int vectX) {
        this.vectX = vectX;
    }

    public void setVectY(int vectY) {
        this.vectY = vectY;
    }
    
	public static void setWorld(World monde) {
		world = monde;
	}
}
