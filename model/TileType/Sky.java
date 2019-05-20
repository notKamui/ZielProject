package model.TileType;

import model.Tile;

public class Sky extends Tile {
    public Sky(int i, int lineLength) {
        super('s', i, lineLength);
        this.getHitbox().setToNonSolid();
    }
}
