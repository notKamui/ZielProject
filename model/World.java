package model;

public class World {
	private Map map;
	private Player player;
	
	public World(Player player, Map map) {
		this.player = player;
		this.map = map;
	}
	
	public Map getMap() {
		return this.map;
	}
	public Player getPlayer() {
		return this.player;
	}
}
