package model.ItemUseableType;

import model.ItemUseable;
import model.TileType.Sky;

public class Shovel extends ItemUseable{
	private int power;
	
	public Shovel(int power) {
		super("Pelle", 2);
		this.power = power;
	}

	@Override
	public void action(int x, int y) {
	//	this.getWorld().getMap().getTileMap().set(arg0, new Sky())
	}
}
