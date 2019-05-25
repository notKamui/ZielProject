package model;

public abstract class ItemPlaceable extends Item{
	
	public ItemPlaceable(String n, int id) {
		super(n, id, (int) (Operations.TILESIZE*2.5));
	}

	public abstract void placeBlock(int i);
}
