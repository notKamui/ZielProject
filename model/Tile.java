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
            case 's':
                path = path + "sky.png";
                break;
            case 'g':
                path = path + "ground/groundTop.png";
                break;
            default:
                path = path + "void.png";
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
