package model;

public abstract class Tile extends GameObject {
    private final static int DIMENSIONS = 80;
    private char charCode;

    public Tile(char c, int i, int lineLength) {
        super((i % lineLength) * 80, (i / lineLength) * 80, DIMENSIONS, DIMENSIONS);
        this.charCode = c;
    }

    public char getCharCode() {
        return this.charCode;
    }
}
