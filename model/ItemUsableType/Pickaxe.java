package model.ItemUsableType;

import model.ItemUsable;
import model.MathDataBuilder;
import model.Tile;

public class Pickaxe extends ItemUsable {
    private int efficiency;

    public Pickaxe(int efficiency) {
        super(101, 0, 0, "Shovel", (int) (MathDataBuilder.TILESIZE*2.5), 1, 1);
        this.efficiency = efficiency;
    }

    @Override
    public void action(int x, int y) {
        int i = MathDataBuilder.coordsToIndex(x, y);
        if(this.isInRange(x, y)) {
            Tile target = MathDataBuilder.world().getMap().getTileAt(i);
            if(target.getDurability()>0) {
                int bonus = 1;
                if ("BS".contains(target.getCharCode() + ""))
                    bonus = 3;
                target.setState(target.getState() - efficiency*bonus);
                if(target.getState()<0) {
                    char charCode = 's';
                    if (y/MathDataBuilder.TILESIZE >= 20)
                        charCode = 'd';
                    MathDataBuilder.world().getMap().updateMap(i, charCode);
                    target.dropBloc();
                }
            }
        }
    }
}
