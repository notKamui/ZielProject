package model;

import model.ItemPlaceableType.BlockGround;

public abstract class Tile extends GameObject {
    private final static int TILESIZE = MathDataBuilder.TILESIZE;
    private final static int LINELENGTH = MathDataBuilder.LINELENGTH;
    private char charCode;
    private int state;
    private int durability;// durability/framerate = digging time in seconds, non diggable if negative

    public Tile(char c, int i, int durability) {
        super((i % LINELENGTH) * TILESIZE, (i / LINELENGTH) * TILESIZE, TILESIZE, TILESIZE);
        this.charCode = c;
        this.durability = durability;
        this.state = durability;
        this.changeHitbox();
    }

    public char getCharCode() {
        return this.charCode;
    }

    public int getDurability() {
        return this.durability;
    }

    public int getState() {
        return state;
    }

    public void setState(int newState) {
        state = newState;
    }
    
    public void removeHitbox() {
        Collider.tileHitboxList.remove(this.getHitbox());
      }

      public void changeHitbox() {
      	this.setHitbox();
        Collider.tileHitboxList.add(this.getHitbox());
      }

    
    public void dropBloc() {
    	Item drop = null;
    	switch(charCode) {
    	case 'g':
    		drop = new BlockGround(this.coordXProperty().get()+TILESIZE/2-MathDataBuilder.ITEMSIZE/2,this.coordYProperty().get()+TILESIZE/2-MathDataBuilder.ITEMSIZE/2);
    	}
    	MathDataBuilder.getWorld().getDynamicObjects().add(drop);
    }
}
