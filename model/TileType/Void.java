package model.TileType;

import model.Tile;

public class Void extends Tile {
    public Void(int i) {
        super(' ', i, -1);
        this.getHitbox().setToNonSolid();
    }
}
