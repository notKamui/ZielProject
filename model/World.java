package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class World {
    private Map map;
    private Player player;
    private ObservableList<DynamicObject> dynamicObjects;

    public World() {
        this.map = null;
        this.player = null;
        this.dynamicObjects = FXCollections.observableArrayList();
    }

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public ObservableList<DynamicObject> getDynamicObjects() {
        return this.dynamicObjects;
    }
}


