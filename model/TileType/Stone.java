package model.TileType;

import model.Item;
import model.Tile;
import model.ItemPlaceableType.BlockStone;

public class Stone extends Tile {
    public Stone(int i) {
        super(2, 'S', i, 1000);
    }
    
    public Item droppedBlock(int x, int y) {
    	return new BlockStone(x, y);
    }
}
