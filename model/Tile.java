package model;

import model.ItemPlaceableType.BlockDirt;

public abstract class Tile extends GameObject {
    private final static int TILESIZE = MathDataBuilder.TILESIZE;
    private final static int LINELENGTH = MathDataBuilder.LINELENGTH;
    private int index;
    private char charCode;
    private int state;
    private int durability; // durability/framerate = digging time in seconds, non diggable if negative
    //-------------Dijsktra Data------------------
    private double distance; // distance from the start node (used for the Dijkstra/BFS algorithm)

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

    public Tile(char c, int i, int durability) {
        super((i % LINELENGTH) * TILESIZE, (i / LINELENGTH) * TILESIZE, TILESIZE, TILESIZE);
        this.index = i;
        this.charCode = c;
        this.durability = durability;
        this.state = durability;
        this.changeHitbox();

        this.resetDistance(); // diskstra distance field
    }

    public void removeHitbox() {
        Collider.tileHitboxList.remove(this.getHitbox());
    }

    public void changeHitbox() {
        this.setHitbox();
        Collider.tileHitboxList.add(this.getHitbox());
    }

    public void dropBloc() {
        Item drop = null;
        switch (charCode) {
            case 'D':
                drop = new BlockDirt(this.coordXProperty().get() + TILESIZE / 2 - MathDataBuilder.ITEMSIZE / 2, this.coordYProperty().get() + TILESIZE / 2 - MathDataBuilder.ITEMSIZE / 2);
        }
        MathDataBuilder.world().getDynamicObjects().add(drop);
    }

    public int getIndex() {
        return this.index;
    }

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
