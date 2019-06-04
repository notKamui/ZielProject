package model.ItemPlaceableType;

import model.ItemPlaceable;
import model.TileType.Ground;

public class BlockGround extends ItemPlaceable{
	public BlockGround() {
		super("Dirt", 1, 1, 64);
	}

	@Override
	public void placeBlock(int i) {
		this.getWorld().getMap().updateMap(i, 'g');
		
	}

	
	
	

}
