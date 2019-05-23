package model.ItemPlaceableType;

import model.ItemPlaceable;

import model.TileType.Ground;

public class BlockGround extends ItemPlaceable{
	public BlockGround() {
		super("Dirt", 1);
	}

	@Override
	public void placeBlock(int i, int lineLength) {
		Ground ground = new Ground(i, lineLength);

	}
	
	

}
