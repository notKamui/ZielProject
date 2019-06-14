package model.TileType;

import model.Item;
import model.Tile;
import model.ItemPlaceableType.BlockWood;

public class Wood extends Tile {
    public Wood(int i) {
        super(3, 'W', i, 1000);
    }
    
    public Item droppedBlock(int x, int y) {
    	return new BlockWood(x, y);
    }
}
