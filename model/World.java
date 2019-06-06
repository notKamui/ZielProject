package model;

import java.util.ArrayList;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class World {
	private Map map;
	private Player player;
	private Ennemy ennemy;
	private ObservableList<DynamicObject> dynamicObjects;
	
	public World() {
		this.map = null;
		this.player = null;
		this.dynamicObjects =FXCollections.observableArrayList();
	}

	public void setMap(Map map) {
		this.map = map;
	}
	public Map getMap() {
		return this.map;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return this.player;
	}
	
	public void setEnnemy(Ennemy n) {
		this.ennemy = n;
	}
	
	public Ennemy getEnnemy() {
		return this.ennemy;
  }

	public ObservableList<DynamicObject> getDynamicObjects(){
		return this.dynamicObjects;
	}
}


