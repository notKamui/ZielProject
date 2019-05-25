package model.ItemUseableType;

import model.ItemUseable;
import model.Operations;
import model.Tile;
import model.TileType.Sky;

public class Shovel extends ItemUseable{
	private int power;
	
	public Shovel(int power) {
		super("Pelle", 2, (int) (Operations.TILESIZE*2.5));
		this.power = power;
	}

	@Override
	public void action(int x, int y) {
		int i = Operations.coordsToIndex(x, y);
		if(this.isInRange(x, y)) {
			Tile target = this.getWorld().getMap().getTileAt(i);
			target.setState(target.getState()-power);
			if(target.getState()<0) {
				this.getWorld().getMap().updateMap(i, 's');;
			}
		}
	}
}
