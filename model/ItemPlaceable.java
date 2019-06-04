package model;

public abstract class ItemPlaceable extends Item{
	
	public ItemPlaceable(String n, int id, int q, int qMax) {
		super(n, id, (int) (MathDataBuilder.TILESIZE*2.5), q, qMax);
	}

	public abstract void placeBlock(int i);
	

@Override
public void action(int x, int y) {
	int id = MathDataBuilder.coordsToIndex(x, y);
	if(MathDataBuilder.isNextToSolid(id) && this.isInRange(x, y) && MathDataBuilder.getWorld().getMap().getTileAt(id).getHitbox().getBounds()==null){//add a collision detector
		placeBlock(id);
		this.setQuantity(this.getQuantity()-1);
	}
}
}