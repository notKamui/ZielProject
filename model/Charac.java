package model;

public abstract class Charac extends GameObject {
    final private int GRAVITY = 10;
    private CollisionManager collMan;

    public Charac(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.collMan = new CollisionManager(this);
    }

    public void gravity(){
        this.move(0, GRAVITY);
    }
}
