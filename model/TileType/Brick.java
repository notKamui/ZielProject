package model.TileType;

import model.Item;
import model.Tile;
import model.ItemPlaceableType.BlockBrick;

public class Brick extends Tile {
    public Brick(int i) {
        super(4, 'B', i, 100000);
    }
    
    public Item droppedBlock(int x, int y) {
    	return new BlockBrick(x, y);
    }
}
