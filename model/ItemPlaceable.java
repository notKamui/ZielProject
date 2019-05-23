package model;

public abstract class ItemPlaceable extends Item{
	
	public ItemPlaceable(String n, int id) {
		super(n, id);
	}

	public abstract void placeBlock(int i, int linelength);
}
