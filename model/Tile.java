package model;

public class Tile extends GameObject {
	private final static int DIMENSIONS = 80;

	private String url;
	private char charCode;

	public Tile(char c, int i, int lineLength) {
		//this.bounds = new Hitbox((i%lineLength)*80, (i/lineLength)*80,DIMENSIONS, DIMENSIONS);
        super((i%lineLength)*80, (i/lineLength)*80,DIMENSIONS, DIMENSIONS);
		this.setTile(c);
	}

	public void setTile(char c) {
		this.charCode = c;

		String path = "src/resources/tiles/";
		switch (c) {
		case 's':
			path = path + "sky.png";
            super.getHitbox().setToNonSolid();
			break;
		case 'g':
			path = path + "ground/groundTop.png";
			break;
		default:
			path = path + "void.png";
            super.getHitbox().setToNonSolid();
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
}
