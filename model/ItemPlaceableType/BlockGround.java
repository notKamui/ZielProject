package model.ItemPlaceableType;

import model.ItemPlaceable;
import model.MathDataBuilder;

public class BlockGround extends ItemPlaceable{
	public BlockGround(int x, int y) {
		super(x, y, "Dirt", 1, 1, 64);
	}

	@Override
	public void placeBlock(int i) {
        MathDataBuilder.getWorld().getMap().updateMap(i, 'g');
		
	}

}
