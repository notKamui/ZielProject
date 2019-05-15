package model;

public class Tile extends Bounds {
	private final static int DIMENSIONS = 80;

	private String url;
	private char charCode;
	private boolean isSolid;

	public Tile(char c, int i, int lineLength) {
		super((i%lineLength)*80, (i/lineLength)*80,DIMENSIONS, DIMENSIONS);

		this.setTile(c);
	}

	public void setTile(char c) {
		this.charCode = c;

		String path = "src/ressources/tiles/";
		switch (c) {
		case 's':
			path = path + "sky.png";
			this.isSolid = false;
			break;
		case 'g':
			path = path + "ground/groundTop.png";
			this.isSolid = true;
			break;
		default:
			path = path + "void.png";
			this.isSolid = false;
			break;
		}
		this.url = path;
	}

	public String getURL() {
		return this.url;
	}

	public char getCharCode() {
		return this.charCode;
	}
	
	public boolean isSolid() {
		return this.isSolid;
	}
}
