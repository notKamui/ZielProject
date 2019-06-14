package model.TileType;

import model.Item;
import model.Tile;

public class Sky extends Tile {
    public Sky(int i) {
        super(5, 's', i, -1);
        this.getHitbox().setToNonSolid();
    }
    
	public Item droppedBlock(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
}
