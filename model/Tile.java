package model;

public abstract class Tile extends GameObject {
    private final static int DIMENSIONS = 80;
    private char charCode;
    private int durability;// durability/framerate = digging time in seconds, non diggable if negative 

    public Tile(char c, int i, int lineLength, int durability) {
        super((i % lineLength) * 80, (i / lineLength) * 80, DIMENSIONS, DIMENSIONS);
        this.charCode = c;
        this.durability = durability;
    }

    public char getCharCode() {
        return this.charCode;
    }
    public int getDurability() {
    	return this.durability;
    }
}
