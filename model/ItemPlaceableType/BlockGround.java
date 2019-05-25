package model.ItemPlaceableType;

import model.ItemPlaceable;
import model.Operations;
import model.TileType.Ground;

public class BlockGround extends ItemPlaceable{
	public BlockGround() {
		super("Dirt", 1);
	}

	@Override
	public void placeBlock(int i) {
		Ground ground = new Ground(i);

	}

	@Override
	public void action(int x, int y) {
		
	}
	
	

}
