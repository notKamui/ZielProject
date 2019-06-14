package model.TileType;

import model.Item;
import model.Tile;
import model.ItemPlaceableType.BlockDirt;

public class Dirt extends Tile {
    public Dirt(int i) {
        super(1, 'D', i, 15);
    }
    
    
    public Item droppedBlock(int x, int y) {
    	return new BlockDirt(x, y);
    }
}
