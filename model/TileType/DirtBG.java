package model.TileType;

import model.Tile;

public class DirtBG extends Tile {
    public DirtBG(int i) {
        super(15, 'd', i, -1);
        this.getHitbox().setToNonSolid();
    }
}
