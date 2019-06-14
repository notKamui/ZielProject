package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/* Map
 * This class take an ObservableList of tile to create the map and manage it
 */
public class Map {
    private ObservableList<Tile> map;

    public Map() {
        this.map = FXCollections.observableArrayList();
        String content = MathDataBuilder.readFile("src/resources/other/map.txt");
        content = content.replaceAll("\\s+", "");
        int i = 0;
        for (char c : content.toCharArray()) {
            this.map.add(MathDataBuilder.makeTile(i++, c));
        }
    }

    public void printMapConsole() {
        int i = 1;
        for (Tile t : map) {
            System.out.print(t.getCharCode());
            if (i % MathDataBuilder.LINELENGTH == 0)
                System.out.println();
            i++;
        }
    }

    public void updateMap(int i, char c) {
        this.map.get(i).removeHitbox();
        this.map.set(i, MathDataBuilder.makeTile(i, c));
    }

    public void saveMap() {
        MathDataBuilder.saveMap(this.map);
    }

    public ObservableList<Tile> getTileMap() {
        return this.map;
    }

    public Tile getTileAt(int index) {
        try {
            return this.map.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getWidth() {
        return MathDataBuilder.LINELENGTH;
    }

    public int getHeight() {
        return this.map.size() / MathDataBuilder.LINELENGTH;
    }
}
