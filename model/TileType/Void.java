package model.TileType;

import model.Item;
import model.Tile;

public class Void extends Tile {
    public Void(int i) {
        super(0, ' ', i, -1);
        this.getHitbox().setToNonSolid();
    }
    
	public Item droppedBlock(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
}
