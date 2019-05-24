package model.TileType;

import model.Tile;

public class Sky extends Tile {
    public Sky(int i) {
        super('s', i, -1);
        this.getHitbox().setToNonSolid();
    }
}
