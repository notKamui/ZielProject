package model.ItemPlaceableType;

import model.ItemPlaceable;
import model.MathDataBuilder;

public class BlockDirt extends ItemPlaceable{
	public BlockDirt(int x, int y) {
		super(x, y, "Dirt", 1, 1, 64);
	}

	@Override
	public void placeBlock(int i) {
        MathDataBuilder.world().getMap().updateMap(i, 'D');
		
	}

	
	
	

}
