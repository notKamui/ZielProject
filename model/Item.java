package model;

//Peut devenir une superclasse par rapport aux outils, armes, ect...
public abstract class Item {
	private static World world;
	private String name;
	private int id;
	private int quantity;
	private int quantityMax;
	
	public Item(String n, int id) {
		this.name = n;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public int getQuantityMax() {
		return this.quantityMax;
	}
	
	public World getWorld() {
		return world;
	}
	public static void setWorld(World monde) {
		world = monde;
	}
	
	abstract public void action(int x, int y);
}
