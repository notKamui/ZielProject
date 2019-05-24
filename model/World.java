package model;

public class World {
	private Map map;
	private Player player;
	
	public World() {
		this.map = null;
		this.player = null;
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
}


