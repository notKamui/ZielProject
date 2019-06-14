package model;

import model.ItemPlaceableType.BlockDirt;
import model.ItemPlaceableType.BlockStone;
import model.ItemPlaceableType.BlockWood;

/* Tile
 * This class create and manage all the tile in the scene
 */
public abstract class Tile extends GameObject {
    private final static int TILESIZE = MathDataBuilder.TILESIZE;
    private final static int LINELENGTH = MathDataBuilder.LINELENGTH;
    private int index;
    private char charCode;
    private int state;
    private int durability; // durability/framerate = digging time in seconds, non diggable if negative

    public Tile(int id, char c, int i, int durability) {
        super(id, (i % LINELENGTH) * TILESIZE, (i / LINELENGTH) * TILESIZE, TILESIZE, TILESIZE);
        this.index = i;
        this.charCode = c;
        this.durability = durability;
        this.state = durability;
        this.changeHitbox();

        this.resetDistance(); // diskstra distance field
    }

    public char getCharCode() {
        return this.charCode;
    }

    public int getDurability() {
        return this.durability;
    }

    public int getState() {
        return state;
    }

    public void setState(int newState) {
        state = newState;
    }

    public void removeHitbox() {
        Collider.tileHitboxList.remove(this.getHitbox());
    }

    public void changeHitbox() {
        this.setHitbox();
        Collider.tileHitboxList.add(this.getHitbox());
    }
    
    public int getIndex() {
        return this.index;
    }
    public abstract Item droppedBlock(int x, int y);
    
    public void dropBloc() {
        int x = this.coordXProperty().get() + TILESIZE / 2 - MathDataBuilder.ITEMSIZE / 2;
        int y = this.coordYProperty().get() + TILESIZE / 2 - MathDataBuilder.ITEMSIZE / 2;
        Item drop = this.droppedBlock(x,y);
        MathDataBuilder.world().getDynamicObjects().add(drop);
    }

    //-------------Dijsktra Data------------------
    private double distance; // distance from the start node (used for the Dijkstra/BFS algorithm)

    
    public void resetDistance() {
        this.setDistance(Double.MAX_VALUE);
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    //--------------------------------------------
}
