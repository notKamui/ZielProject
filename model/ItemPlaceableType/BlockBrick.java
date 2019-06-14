package model.ItemPlaceableType;

import model.ItemPlaceable;
import model.MathDataBuilder;

public class BlockBrick extends ItemPlaceable {
    public BlockBrick(int x, int y) {
        super(4, x, y, "Brick", 1, 64);
    }

    @Override
    public void placeBlock(int i) {
        MathDataBuilder.world().getMap().updateMap(i, 'B');
    }
}