package model;

import model.ItemPlaceableType.BlockDirt;

public abstract class ItemPlaceable extends Item{
	
	public ItemPlaceable(int id, int x, int y, String n, int q, int qMax) {
		super(id, x, y, n, (int) (MathDataBuilder.TILESIZE*2.5), q, qMax);
	}

	public abstract void placeBlock(int i);
	

@Override
public void action(int x, int y) {
	int id = MathDataBuilder.coordsToIndex(x, y);
	Tile t = MathDataBuilder.world().getMap().getTileAt(id);
	BlockDirt fakeTile = new BlockDirt(t.coordXProperty().get(), t.coordYProperty().get());
	if(MathDataBuilder.isNextToSolid(id) && this.isInRange(x, y) && t.getHitbox().getBounds()==null && !fakeTile.getCollMan().isNotEmpty()){//add a collision detector
		placeBlock(id);
		this.setQuantity(this.getQuantity()-1);
	}
	fakeTile.removeHitbox();
}
}