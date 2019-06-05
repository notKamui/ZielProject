package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Collider {
    private DynamicObject self;

    public Collider(DynamicObject self) {
        this.self = self;
    }

    public boolean collides() {
        return collisionManager(0);
    }

    public boolean isOnFloor() {
        return collisionManager(1);
    }

    //default : collides()
    //1 : isOnFloor()
    private boolean collisionManager(int type) {
        for (Hitbox hitbox : hitboxesArround()) {
            Hitbox selfHB;
            switch (type) {
                case 1:
                    selfHB = new Hitbox(
                            this.self.coordXProperty(),
                            new SimpleIntegerProperty(this.self.coordYProperty().get() + 1),
                            this.self.getWidth(),
                            this.self.getHeight());
                    break;
                default:
                    selfHB = this.self.getHitbox();
                    break;
            }
            Shape intersect = Shape.intersect(selfHB.getBounds(), hitbox.getBounds());
            if (intersect.getBoundsInParent().getWidth() != -1) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Hitbox> hitboxesArround() {
        ArrayList<Hitbox> hitboxesAround = new ArrayList<>();

        // gets the center of the DynamicObject
        int x = this.self.coordXProperty().get() + this.self.getWidth() / 2;
        int y = this.self.coordYProperty().get() + this.self.getHeight() / 2;

        // gets the radius around the DynamicObject
        double radius = Math.max(this.self.getHeight(), this.self.getWidth()) + this.self.getSpeed() + MathDataBuilder.TILESIZE / 2;

        // gets the hitboxes between the object and the radius
        for (Hitbox hitbox : this.self.getBoundsList()) {
            if (hitbox.getBounds() != null && hitbox != this.self.getHitbox()) {
                int xb = (int) hitbox.getBounds().getX() + MathDataBuilder.TILESIZE / 2;
                int yb = (int) hitbox.getBounds().getY() + MathDataBuilder.TILESIZE / 2;
                if (Math.sqrt(Math.pow((x - xb), 2) + Math.pow((y - yb), 2)) <= radius) { // distance calculus
                    hitboxesAround.add(hitbox);
                }
            }
        }
        return hitboxesAround;
    }
}
