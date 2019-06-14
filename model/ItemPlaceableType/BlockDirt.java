package model.ItemPlaceableType;

import model.ItemPlaceable;
import model.MathDataBuilder;

public class BlockDirt extends ItemPlaceable{
	public BlockDirt(int x, int y) {
		super(1, x, y, "Dirt", 1, 64);
	}

	@Override
	public void placeBlock(int i) {
        MathDataBuilder.world().getMap().updateMap(i, 'D');
		
	}
}
