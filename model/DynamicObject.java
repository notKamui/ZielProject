package model;

public abstract class DynamicObject extends GameObject {

    final private int GRAVITY = 30;
    private int speed = 10;
    private int vectX;
    private int vectY;
    private Collider collMan;

    public DynamicObject(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.collMan = new Collider(this);
    }

    public void setPosition() {
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

    public Collider getCollMan() {
        return this.collMan;
    }

    public int getSpeed() {
        return this.speed;
    }

    public final int GRAVITY() {
        return GRAVITY;
    }

    public int getVectX() {
        return this.vectX;
    }

    public void setVectX(int vectX) {
        this.vectX = vectX;
    }

    public int getVectY() {
        return this.vectY;
    }

    public void setVectY(int vectY) {
        this.vectY = vectY;
    }
}