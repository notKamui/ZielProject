package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Collider {
    private DynamicObject self;
    public static ArrayList<Hitbox> itemHitboxList = new ArrayList<>();
    public static ArrayList<Hitbox> characHitboxList = new ArrayList<>();
    public static ArrayList<Hitbox> tileHitboxList = new ArrayList<>();


    public Collider(DynamicObject self) {
        this.self = self;
    }

    public boolean collides() {
        return collisionManager(0);
    }

    public boolean isOnFloor() {
        return collisionManager(1);
    }

    public boolean isInFrontRight() {
    	return collisionManager(2);
    }
    
    public boolean isInFrontLeft() {
    	return collisionManager(3);
    }
    //default : collides()
    //1 : isOnFloor()
    
    //4 : itemsAround()
    private boolean collisionManager(int type) {
        for (Hitbox hitbox : hitboxesAround(type)) {
            Hitbox selfHB;
            switch (type) {
                case 1:
                    selfHB = new Hitbox(
                            this.self.coordXProperty(),
                            new SimpleIntegerProperty(this.self.coordYProperty().get() + 1),
                            this.self.getWidth(),
                            this.self.getHeight(),
                            self);
                    break;
                    
                case 2 : 
                	selfHB = new Hitbox(new SimpleIntegerProperty(this.self.coordXProperty().get() + 1), this.self.coordYProperty(), this.self.getWidth(), this.self.getHeight());
                	break;
                	
                case 3 : 
                	selfHB = new Hitbox(new SimpleIntegerProperty(this.self.coordXProperty().get() - 1), this.self.coordYProperty(), this.self.getWidth(), this.self.getHeight());
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

    public ArrayList<Item> itemsAround(){
        ArrayList<Item> itemsAround = new ArrayList<>();
        for(Hitbox hitbox : hitboxesAround(4)) {
        	itemsAround.add((Item)hitbox.getSelf());
        }
        return itemsAround;
    }
    private ArrayList<Hitbox> hitboxesAround(int type) {
        ArrayList<Hitbox> hitboxesAround = new ArrayList<>();
        double radius;
        ArrayList<Hitbox> usedHitboxList;
        
        switch(type) {
        case 4:
        	radius = 100;
        	usedHitboxList = itemHitboxList;
        	break;
        default:
        	radius = Math.max(this.self.getHeight(), this.self.getWidth()) + this.self.getSpeed() + MathDataBuilder.TILESIZE / 2;
        	usedHitboxList = tileHitboxList;

        }
        // gets the center of the DynamicObject
        int x = this.self.coordXProperty().get() + this.self.getWidth() / 2;
        int y = this.self.coordYProperty().get() + this.self.getHeight() / 2;

        // gets the radius around the DynamicObject
        

        // gets the hitboxes between the object and the radius
        for (Hitbox hitbox : usedHitboxList) {
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
