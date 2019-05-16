package model;

import java.util.ArrayList;

public class Player extends Charac {

	public Player(int x, int y) {
		super(x, y, 80, 80);
	}

	public void readInput(ArrayList<String> input) {
		for (String key : input)
			switch (key) {
			case "Z":
				move(0, -20);
				break;
			case "S":
				move(0, 10);
				break;
			case "D":
				move(10, 0);
				break;
			case "Q":
				move(-10, 0);
				break;
			case "SPACE":
				break;
			default:
				break;
			}
	}

}
