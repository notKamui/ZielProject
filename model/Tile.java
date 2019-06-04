package model;

public abstract class Tile extends GameObject {
    private final static int TILESIZE = MathDataBuilder.TILESIZE;
    private final static int LINELENGTH = MathDataBuilder.LINELENGTH;
    private char charCode;
    private int state;
    private int durability;// durability/framerate = digging time in seconds, non diggable if negative

    public Tile(char c, int i, int durability) {
        super((i % LINELENGTH) * 80, (i / LINELENGTH) * 80, TILESIZE, TILESIZE);
        this.charCode = c;
        this.durability = durability;
        this.state = durability;
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
}
