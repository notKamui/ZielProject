package app;

import model.Map;

public class Test {
    public static void main(String[] args) {
        Map map = new Map();
        map.printMap();
        System.out.println();
        map.updateMap(4, 'A');
        map.printMap();
        map.saveMap();
    }
}
