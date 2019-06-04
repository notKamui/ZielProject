package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Collider {
    private static World world;
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

    public static void setWorld(World w) {
        world = w;
    }

    //0 : collides()
    //1 : isOnFloor()
    private boolean collisionManager(int type) {
        boolean coll = false;

        ArrayList<Tile> tileList = new ArrayList<>(world.getMap().getTileMap());
        int x = this.self.coordXProperty().get();
        int y = this.self.coordYProperty().get();
        int i = MathDataBuilder.coordsToIndex(x + this.self.getWidth() / 2, y + this.self.getHeight() / 2);
        int radius = Math.max(this.self.getHeight(), this.self.getWidth()) / 2 + this.self.getSpeed();

        for (Hitbox hitbox : this.self.getBoundsList()) {
            if (hitbox.getBounds() != null && hitbox != this.self.getHitbox()) {
                Hitbox tempHB = this.self.getHitbox();
                if (type == 1) {
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
