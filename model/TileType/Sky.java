package model.TileType;

import model.Tile;

public class Sky extends Tile {
    public Sky(int i, int lineLength) {
        super('s', i, lineLength, -1);
        this.getHitbox().setToNonSolid();
    }
}
