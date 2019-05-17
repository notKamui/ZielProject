package model;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;

import javafx.util.Duration;


public abstract class Charac extends GameObject {
    final private int GRAVITY = 30;
    private int jumpForce;
    private boolean isJumping;
    private CollisionManager collMan;

    public Charac(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.jumpForce = 0;
        this.setJumping(false);
        this.collMan = new CollisionManager(this);
    }

    public void gravity(){
        this.move(0, GRAVITY);
    }

    // Jump functions--------
    public void jumpAnim() {
        if(this.isJumping) {
            this.move(0, -this.jumpForce);
        }
        this.jumpForce = Math.max(this.jumpForce-1, 0);
        if (this.jumpForce == 0) {
            this.setJumping(false);
        }
    }
    public boolean getIsJumping() {
        return this.isJumping;
    }
    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
        if(isJumping)
            this.jumpForce = 45;
    }
    //-------------------------
}
