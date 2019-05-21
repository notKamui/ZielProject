package model.TileType;

import model.Tile;

public class Void extends Tile {
    public Void(int i, int lineLength) {
        super(' ', i, lineLength);
        this.getHitbox().setToNonSolid();
    }
}