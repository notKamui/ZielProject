package model.TileType;

import model.Tile;

public class Ground extends Tile {
    public Ground(int i, int lineLength) {
        super('g', i, lineLength, 15);
    }
}
