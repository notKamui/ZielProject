package model;

public class CollisionManager {
    GameObject self;

    public CollisionManager(GameObject self) {
        this.self = self;
    }

    public boolean collides() {
        boolean collides = false;

        for(Hitbox hitbox : this.self.getBoundsList()) {
            if(this.self.getHitbox().getBounds().getBoundsInParent().intersects(hitbox.getBounds().getBoundsInParent()) && this.self.getHitbox() != hitbox)
                collides = true;
        }

        return collides;
    }
}
