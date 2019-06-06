package model.ItemOtherType;

import model.ItemOther;

public class VoidItem extends ItemOther{
	public VoidItem() {
		super(0, 0, "VoidItem", 0, 1, 1);
	}

	@Override
	public void action(int x, int y) {
		
	}
}
