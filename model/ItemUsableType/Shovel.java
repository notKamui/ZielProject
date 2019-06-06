package model.ItemUsableType;

import model.ItemUsable;
import model.MathDataBuilder;
import model.Tile;

public class Shovel extends ItemUsable {
	private int efficiency;
	
	public Shovel(int efficiency) {
		super(0, 0, "Shovel", 2, (int) (MathDataBuilder.TILESIZE*2.5), 1, 1);
		this.efficiency = efficiency;
	}

	@Override
	public void action(int x, int y) {
		int i = MathDataBuilder.coordsToIndex(x, y);
		if(this.isInRange(x, y)) {
			Tile target = MathDataBuilder.getWorld().getMap().getTileAt(i);
			if(target.getDurability()>0) {
				target.setState(target.getState()- efficiency);
				if(target.getState()<0) {
					MathDataBuilder.getWorld().getMap().updateMap(i, 's');
					target.dropBloc();
				}
			}
		}
	}
}
