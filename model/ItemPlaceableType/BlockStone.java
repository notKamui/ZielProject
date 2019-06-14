package model.ItemPlaceableType;

import model.ItemPlaceable;
import model.MathDataBuilder;

public class BlockStone extends ItemPlaceable {
    public BlockStone(int x, int y) {
        super(2, x, y, "Stone", 1, 64);
    }

    @Override
    public void placeBlock(int i) {
        MathDataBuilder.world().getMap().updateMap(i, 'S');
    }
}
