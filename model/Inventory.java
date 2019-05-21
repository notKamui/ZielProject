package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//La classe Inventory va permettre de s'occuper de l'inventaire du perso, en attribut, une liste d'Item et
// la capacite max de l'inventaire
// /!\ voir si tableau pas mieux (même si une ArrayList empêche le risque d'erreur)
public class Inventory {
	
	private ObservableList<Item> inventory;
	private int capacity;
	final private Item VOID = new Item("void",0);
	
	public Inventory() {
		this.inventory = FXCollections.observableArrayList();
		this.capacity = 10;
		for(int i = 0; i < capacity; i++) {
			inventory.add(VOID);
		}
	}
	
	public boolean isfull() {
		if(inventory.contains(VOID))
			return false;
		return true;
	}
	
	public void addItem(Item i) {
		if(!this.isfull()) {
			int slot = inventory.indexOf(VOID);
			this.inventory.remove(slot);
			this.inventory.add(slot, i);
		}
	}
	
	public void removeItem(int slot) {
			this.inventory.remove(slot);
			this.inventory.add(slot, VOID);
	}
	
	public void setCapacity(int c) {
		if(c < 0) {
			this.capacity = 0;
		}	
		else {
			this.capacity = c;
		}
	}
	
	public ObservableList<Item> returnInventory() {
		return this.inventory;
	}
	
	public void clearInventory() {
		this.inventory = FXCollections.observableArrayList();
	}
	

}
