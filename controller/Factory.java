package controller;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import model.Charac;
import model.Item;
import model.Map;
import model.MathDataBuilder;
import model.Player;
import model.World;

 class Factory {

     static World initWorld() {
        World world = new World();

        Player player = new Player(80 * 2, 80 * 3);
        Map map = new Map(world);

        world.setPlayer(player);
        world.setMap(map);
        Item.setWorld(world);
        Charac.setWorld(world);
        MathDataBuilder.setWorld(world);
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
