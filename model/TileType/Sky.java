package model.TileType;

import model.Tile;

public class Sky extends Tile {
    public Sky(int i) {
        super(5, 's', i, -1);
        this.getHitbox().setToNonSolid();
    }
}
