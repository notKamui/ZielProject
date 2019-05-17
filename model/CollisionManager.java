package model;

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
}
