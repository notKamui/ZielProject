package model;


import javafx.scene.image.Image;

import java.net.URL;

public class Tile {
    private String url;
    private char charCode;

    public Tile(char c) {
        this.charCode = c;

        String path = "src/ressources/tiles/";
        switch (c) {
            case 'a':
                path = path + "sky.png";
                break;
            case 't':
                path = path + "groundTop.png";
                break;
            default:
                path = path + "sky.png";
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
