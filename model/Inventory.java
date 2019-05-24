package model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//La classe Inventory va permettre de s'occuper de l'inventaire du perso, en attribut, une liste d'Item et
// la capacite max de l'inventaire
public class Inventory {
	
	private ObservableList<Item> inventory;
	private int capacity;
	final private Item VOID = new model.ItemOtherType.Void();
	private IntegerProperty indexProperty;
	
	public Inventory() {
		this.inventory = FXCollections.observableArrayList();
		this.capacity = 10;
		for(int i = 0; i < capacity; i++) {
			inventory.add(VOID);
		}
		this.indexProperty = new SimpleIntegerProperty(0);
	}
	
	public boolean isfull() {
		if(inventory.contains(VOID))
			return false;
		return true;
	}
	
	public void addItem(Item i) {
		if(!this.isfull()) {
			int slot = inventory.indexOf(VOID);
			this.inventory.add(slot, i);
		}
	}
	
	public int removeItem(int slot) {
			this.inventory.remove(slot);
			this.inventory.add(slot, VOID);
			return slot;
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
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public IntegerProperty getIndexProperty() {
		return this.indexProperty;
	}
	
	public int getIndex() {
		return this.indexProperty.getValue();
	}
	
	public void setIndexProperty(int index) {
		this.indexProperty.setValue(index);
	}
	
	public void clearInventory() {
		this.inventory = FXCollections.observableArrayList();
	}
	

}
