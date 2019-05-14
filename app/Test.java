package app;

import model.Player;

public class Test {
    public static void main(String[] args) {
        Player j = new Player(10, 10);
        System.out.println(j.coordXProperty().get());
    }
}
