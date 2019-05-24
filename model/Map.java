package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TileType.*;
import model.TileType.Void;

import java.io.*;

public class Map {
    private World world;
    private ObservableList<Tile> map;
    private int lineLength = Operations.LINELENGTH;

    public Map(World world) {
        this.world = world;

        this.map = FXCollections.observableArrayList();

        String content = Operations.readFile("src/view/map.csv");
        String mapString = "";
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c != '\n')
                mapString = mapString + c;
        }

        for (int i = 0; i < mapString.length(); i++) {
            this.map.add(makeTile(i, mapString.charAt(i)));
        }
    }



    public void printMapConsole() {
        int i = 1;
        for (Tile t : map) {
            System.out.print(t.getCharCode());
            if (i % this.lineLength == 0)
                System.out.println();
            i++;
        }
    }

    public void updateMap(int i, char c) {
    	this.map.get(i).removeHitbox();
        this.map.set(i, makeTile(i, c));
    }

    public void saveMap() {
        try {
            File file = new File("src/view/map.csv");
            FileWriter fileWriter = new FileWriter(file, false);
            String newContent = "";
            int i = 1;
            for (Tile t : map) {
                newContent = newContent + t.getCharCode();
                if (i % this.lineLength == 0)
                    newContent = newContent + "\n";
                i++;
            }
            fileWriter.write(newContent);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Tile makeTile(int i, char c) {
        Tile tile;
        switch (c) {
            case 'g':
            	tile = new Ground(i);
                break;
            case 's':
            	tile = new Sky(i);
                break;
            default:
            	tile = new Void(i);
                break;
        }
        return tile;
    }

    public ObservableList<Tile> getTileMap() {
        return this.map;
    }

    public Tile getTileAt(int index) {
        return this.map.get(index);
    }

    public int getWidth() {
        return this.lineLength;
    }

    public int getHeight() {
        return this.map.size() / this.lineLength;
    }
}
