package model.ItemOtherType;

import model.ItemOther;

public class VoidItem extends ItemOther{
	public VoidItem(int x, int y) {
		super(0, x, y, "VoidItem", 0, 1);
	}

	@Override
	public void action(int x, int y) {
		
	}
}
