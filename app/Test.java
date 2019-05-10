package app;

import model.Map;

public class Test {
    public static void main(String[] args) {
        Map map = new Map();
        map.printMapConsole();
        System.out.println();
        map.updateMap(13, 't');
        map.printMapConsole();
        map.saveMap();
    }
}
