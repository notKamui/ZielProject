package model;

//Peut devenir une superclasse par rapport aux outils, armes, ect...
public abstract class Item extends DynamicObject {
    private String name;
    private int quantity;
    private int quantityMax;
    private int range; // range to enable interactions with other objects

    public Item(int id, int x, int y, String n, int range, int q, int qMax) {
        super(id, x, y, MathDataBuilder.ITEMSIZE, MathDataBuilder.ITEMSIZE, false);
        this.name = n;
        this.range = range;
        this.quantity = q;
        this.quantityMax = qMax;
        this.changeHitbox();
    }

    public void act() {
        this.setPosition();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityMax() {
        return this.quantityMax;
    }

    public void removeHitbox() {
        Collider.itemHitboxList.remove(this.getHitbox());
    }

    public void changeHitbox() {
        this.setHitbox();
        Collider.itemHitboxList.add(this.getHitbox());
    }


    public boolean isInRange(int x, int y) {
        int playerX = MathDataBuilder.world().getPlayer().coordXProperty().get() + 40;
        int playerY = MathDataBuilder.world().getPlayer().coordYProperty().get() + 40;

        if (Math.sqrt(Math.pow(playerX - x, 2) + Math.pow(playerY - y, 2)) <= range) {
            return true;
        }
        return false;
    }

    abstract public void action(int x, int y);
}
