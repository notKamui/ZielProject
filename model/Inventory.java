package model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemOtherType.VoidItem;

//La classe Inventory va permettre de s'occuper de l'inventaire du perso, en attribut, une liste d'Item et
// la capacite max de l'inventaire
public class Inventory {

    final private static Item VOID = new VoidItem(0,0);
	private ObservableList<Item> inventory;
	private int capacity;
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
		int s = indexOfSlotByQuantity(i);
		if(s != -1) {
			System.out.println(i.getQuantity());
			i.removeHitbox();
			MathDataBuilder.world().getDynamicObjects().remove(i);
			this.inventory.set(s, i);
			i.setSlot(s);
		}
		else if(!this.isfull()) {
			i.removeHitbox();
			MathDataBuilder.world().getDynamicObjects().remove(i);
			int slot = inventory.indexOf(VOID);
			this.inventory.set(slot, i);
			i.setSlot(slot);
		}
	}
	
	private int indexOfSlotByQuantity(Item i) {
		for(int slot = 0; slot < this.capacity; slot++) {
			if(i.getId() == inventory.get(slot).getId()) {
				if(i.getQuantity() + inventory.get(slot).getQuantity() <= inventory.get(slot).getQuantityMax()) {
					i.setQuantity(i.getQuantity() + inventory.get(slot).getQuantity());
					return slot;
				}
			}
		}
		return -1;
	}
	
	public int removeItem(int slot) {
			this.inventory.set(slot, VOID);
			return slot;
	}
	
	public Item getItemByIndex(int i) {
		return this.inventory.get(i);
	}
	
	public void setCapacity(int c) {
		if(c < 0) {
			this.capacity = 0;
		}	
		else {
			this.capacity = c;
		}
	}
	
	public ObservableList<Item> getInventoryContent() {
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
