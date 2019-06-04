package controller;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import model.*;

class Factory {

    static World initWorld() {
        World world = new World();

        Player player = new Player(80 * 2, 80 * 3);
        Map map = new Map(world);

        world.setPlayer(player);
        world.setMap(map);
        Item.setWorld(world);
        GameObject.setWorld(world);
        MathDataBuilder.setWorld(world);
        Collider.setWorld(world);
        return world;
    }

    static ImageView initPlayerView(IntegerProperty coordXProperty, IntegerProperty coordYProperty) {
        ImageView playerBox = new ImageView("file:src/resources/sprites/mario.png");
        playerBox.setRotationAxis(new Point3D(0, 1, 0));
        playerBox.translateXProperty().bind(coordXProperty);
        playerBox.translateYProperty().bind(coordYProperty);

        return playerBox;
    }
}
