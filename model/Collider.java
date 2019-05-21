package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Collider {
    GameObject self;

    public Collider(GameObject self) {
        this.self = self;
    }

    public boolean collides() {
        return collisionManager(0);
    }

    public boolean isOnFloor() {
        return collisionManager(1);
    }

    //0 : collides()
    //1 : isOnFloor()
    private boolean collisionManager(int type) {
        boolean coll = false;

        for (Hitbox hitbox : this.self.getBoundsList()) {
            if (hitbox.getBounds() != null && hitbox != this.self.getHitbox()) {
                Hitbox tempHB = this.self.getHitbox();
                if(type == 1) {
                    tempHB = new Hitbox(
                            this.self.coordXProperty(),
                            new SimpleIntegerProperty(this.self.coordYProperty().get() + 1),
                            this.self.getWidth(),
                            this.self.getHeight());
                }
                Shape intersect = Shape.intersect(tempHB.getBounds(), hitbox.getBounds());
                if (intersect.getBoundsInParent().getWidth() != -1) {
                    coll = true;
                }
            }
        }

        return coll;
    }
}
