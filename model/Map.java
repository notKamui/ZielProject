package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private ObservableList<Character> map;
    private int lineLength;

    public Map() {
        this.map = FXCollections.observableArrayList();
        try {
            String pathname;
            File file = new File("src/view/map.csv");
            Scanner sc = new Scanner(file);

            String line = sc.nextLine();
            this.lineLength = line.length();

            sc = new Scanner(file);
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                int i = 0;
                while (i < this.lineLength) {
                    this.map.add(line.charAt(i));
                    i++;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printMap() {
        int i = 1;
        for(char c : map) {
            System.out.print(c);
            if(i%this.lineLength == 0)
                System.out.println();
            i++;
        }
    }

    public void updateMap(int index, char c) {
        Character cc = c;
        this.map.set(index, cc);
    }

    public void saveMap() {
        try {
            File file = new File("src/view/map.csv");
            FileWriter fileWriter = new FileWriter(file, false);
            String newContent = "";
            int i = 1;
            for(char c : map) {
                newContent = newContent + c;
                if (i%this.lineLength == 0)
                    newContent = newContent + "\n";
                i++;
            }
            fileWriter.write(newContent);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList getMap() {
        return this.map;
    }

}
