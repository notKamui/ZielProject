package model;

//Peut devenir une superclasse par rapport aux outils, armes, ect...
public abstract class Item {
    private String name;
    private int id;
    private int quantity;
    private int quantityMax;
    private int range; // range to enable interactions with other objects

    public Item(String n, int id, int range, int q, int qMax) {
        this.name = n;
        this.id = id;
        this.range = range;
        this.quantity = q;
        this.quantityMax = qMax;
    }

    public int getId() {
        return id;
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

    public boolean isInRange(int x, int y) {
        int playerX = MathDataBuilder.world().getPlayer().coordXProperty().get() + 40;
        int playerY = MathDataBuilder.world().getPlayer().coordYProperty().get() + 40;
        if (playerX - this.range <= x && x <= playerX + this.range
                && playerY - this.range <= y && y <= playerY + this.range) {
            return true;
        }
        return false;
    }

    abstract public void action(int x, int y);
}
