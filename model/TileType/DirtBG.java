package model.TileType;

import model.Item;
import model.Tile;

public class DirtBG extends Tile {
    public DirtBG(int i) {
        super(15, 'd', i, -1);
        this.getHitbox().setToNonSolid();
    }

	public Item droppedBlock(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
}
