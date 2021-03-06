package model.ItemPlaceableType;

import model.ItemPlaceable;
import model.MathDataBuilder;

public class BlockWood extends ItemPlaceable {
    public BlockWood(int x, int y) {
        super(3, x, y, "Wood", 1, 64);
    }

    @Override
    public void placeBlock(int i) {
        MathDataBuilder.world().getMap().updateMap(i, 'W');
    }
}
