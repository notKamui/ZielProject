package model;

import java.util.ArrayList;

//La classe Inventory va permettre de s'occuper de l'inventaire du perso, en attribut, une liste d'Item et
// la capacite max de l'inventaire
// /!\ voir si tableau pas mieux (même si une ArrayList empêche le risque d'erreur)
public class Inventory {
	
	private ArrayList<Item> inventory;
	private int capacity;
	
	public Inventory() {
		this.inventory = new ArrayList<>();
	}
	
	public boolean isfull() {
		if(inventory.size() >= capacity)
			return true;
		return false;
	}
	
	public void addItem(Item i) {
		if(!this.isfull()) {
			this.inventory.add(i);
		}
	}
	
	public void removeItem(Item i) {
		if(this.inventory.contains(i)) {
			this.inventory.remove(i);
		}
	}
	
	public void setCapacity(int c) {
		if(c < 0) {
			this.capacity = 0;
		}	
		else {
			this.capacity = c;
		}
	}
	
	public void clearInventory() {
		this.inventory = new ArrayList<>();
	}
	

}
