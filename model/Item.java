package model;

//Peut devenir une superclasse par rapport aux outils, armes, ect...
public abstract class Item {

	private String name;
	private int id;
	
	public Item(String n, int id) {
		this.name = n;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
