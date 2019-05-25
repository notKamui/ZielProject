package controller;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Charac;
import model.Item;
import model.Map;
import model.Player;
import model.World;

public class Factory {

    public static World initWorld() {
        World world = new World();

        Player player = new Player(80 * 2, 80 * 5);
        Map map = new Map(world);

        world.setPlayer(player);
        world.setMap(map);
        Item.setWorld(world);
        Charac.setWorld(world);
        return world;
    }

    public static ImageView initPlayerView(IntegerProperty coordXProperty, IntegerProperty coordYProperty) {
        ImageView playerBox = new ImageView("file:src/resources/sprites/mario.png");
        playerBox.setRotationAxis(new Point3D(0, 1, 0));
        playerBox.translateXProperty().bind(coordXProperty);
        playerBox.translateYProperty().bind(coordYProperty);

        return playerBox;
    }
}
