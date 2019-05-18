package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CollisionManager {
    GameObject self;

    public CollisionManager(GameObject self) {
        this.self = self;
    }

    public boolean collides() {
        boolean collides = false;

        for (Hitbox hitbox : this.self.getBoundsList()) {
            if (hitbox.getBounds() != null && hitbox != this.self.getHitbox()) {
                Shape intersect = Shape.intersect(this.self.getHitbox().getBounds(), hitbox.getBounds());
                if (intersect.getBoundsInParent().getWidth() != -1) {
                    collides = true;
                }
            }
        }

        return collides;
    }

    public boolean isOnFloor() {
        boolean isOnFloor = false;

        for (Hitbox hitbox : this.self.getBoundsList()) {
            if (hitbox.getBounds() != null && hitbox != this.self.getHitbox()) {
                Hitbox tempHB = new Hitbox(
                        this.self.coordXProperty(),
                        new SimpleIntegerProperty(this.self.coordYProperty().get()+1),
                        this.self.getWidth(),
                        this.self.getHeight());
                Shape intersect = Shape.intersect(tempHB.getBounds(), hitbox.getBounds());
                if (intersect.getBoundsInParent().getWidth() != -1) {
                    isOnFloor = true;
                }
            }
        }

        return isOnFloor;
    }
}
