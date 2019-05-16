package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Map {
    private ObservableList<Tile> map;
    private int lineLength;

    public Map() {
        this.map = FXCollections.observableArrayList();

        String content = readFile("src/view/map.csv");
        this.lineLength = content.indexOf('\n');
        String mapString = "";
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c != '\n')
                mapString = mapString + c;
        }

        for (int i = 0; i < mapString.length(); i++)
            this.map.add(new Tile(mapString.charAt(i), i, this.lineLength));
    }

    private String readFile(String fname) {
        String content = null;
        File file = new File(fname);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
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

    public void updateMap(int index, char c) {
        this.map.get(index).setTile(c);
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
