package controller;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import model.Map;
import model.MathDataBuilder;
import model.Player;
import model.World;

class Factory {

    static World initWorld() {
        Player player = new Player(MathDataBuilder.TILESIZE * 65, MathDataBuilder.TILESIZE * 4);
        Map map = new Map();

        World world = new World();
        world.setPlayer(player);
        world.setMap(map);
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
