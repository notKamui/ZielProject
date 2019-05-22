package model.ItemPlaceableType;

import model.ItemPlaceable;
import model.TileType.Ground;

public class BlockGroung extends ItemPlaceable{

	public BlockGroung() {
		super("Dirt", 1);
	}

	@Override
	public void placeBlock(int i, int lineLength) {
		Ground ground = new Ground(i, lineLength);
	}
	
	

}
