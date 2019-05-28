package model.ItemUsableType;

import model.ItemUsable;
import model.MathDataBuilder;
import model.Tile;

public class Shovel extends ItemUsable {
	private int efficiency;
	
	public Shovel(int efficiency) {
		super("Shovel", 2, (int) (MathDataBuilder.TILESIZE*2.5));
		this.efficiency = efficiency;
	}

	@Override
	public void action(int x, int y) {
		int i = MathDataBuilder.coordsToIndex(x, y);
		if(this.isInRange(x, y)) {
			Tile target = this.getWorld().getMap().getTileAt(i);
			target.setState(target.getState()- efficiency);
			if(target.getState()<0) {
				this.getWorld().getMap().updateMap(i, 's');
			}
		}
	}
}
