package model;


import javafx.scene.image.Image;

import java.net.URL;

public class Tile {
    private Image tile;
    private char charCode;

    public Tile(char c) {
        this.charCode = c;
        String url = "src/view/tiles/";
        switch (c) {
            case 'a':
                url = url + "air.png";
                break;
            case 't':
                url = url + "terre.png";
                break;
            default:
                url = url + "air.png";
                break;
        }
        this.tile = new Image(url);
    }

    public char getCharCode() {
        return this.charCode;
    }
}
